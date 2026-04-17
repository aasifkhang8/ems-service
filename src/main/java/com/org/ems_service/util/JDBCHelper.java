package com.org.ems_service.util;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class JDBCHelper {

	private static final Logger LOGGER = LogManager.getLogger(JDBCHelper.class);

	@Value("${dbUrl}")
	private static String dbUrl;
	@Value("${dbUserName}")
	private static String dbUserName;
	@Value("${dbPassWord}")
	private static String dbPassWord;
	@Value("${driverClassName}")
	private static String driverClassName;

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;

	private List<Parameter> parameterList = null;
	private List<List<Parameter>> batchParameterList = null;

	public void dispose() {
		try {
			this.parameterList = null;
			this.batchParameterList = null;

			if (preparedStatement != null) {
				preparedStatement.close();
				preparedStatement = null;
			}

			if (statement != null) {
				statement.close();
				statement = null;
			}

			if (connection != null) {
				connection.close();
				connection = null;
			}

			LOGGER.info("method dispose() called, connection, statement and preparedStatement closed successfully.");
		} catch (Exception e) {
			LOGGER.error("method dispose() thrown exception {}", ExceptionUtils.getStackTrace(e));
		}
	}

	public void openConnection() throws Exception {
		this.closeConnection(false);
		Integer connectAttemptCount = 0;
		while (connectAttemptCount < 5 && (this.connection == null && this.connection.isClosed())) {
			try {
				Class.forName(driverClassName);
				this.connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
			} catch (Exception e) {
				Thread.sleep(5000);
			}
			connectAttemptCount++;
		}
	}

	public void closeConnection(boolean isClearParameter) throws SQLException {
		if (this.parameterList != null && isClearParameter) {
			this.parameterList.clear();
		}

		if (this.batchParameterList != null && isClearParameter) {
			this.batchParameterList.clear();
		}

		if (this.statement != null && !this.statement.isClosed()) {
			this.statement.close();
			this.statement = null;
		}

		if (this.preparedStatement != null && !this.preparedStatement.isClosed()) {
			this.preparedStatement.close();
			this.preparedStatement = null;
		}
	}

	private String replaceParameterName(String query) {
		if (this.parameterList != null && !this.parameterList.isEmpty()) {
			for (Parameter parameter : this.parameterList) {
				if (parameter.getType() == Types.ARRAY) {
					String value = ((List<?>) parameter.getValue()).stream().map(r -> "?")
							.collect(Collectors.joining(", "));
					query = query.replaceFirst(parameter.getName(), value);
				} else {
					query = query.replaceFirst(parameter.getName(), "?");
				}
			}
		}
		return query;
	}

	private void addParameterToStatement() throws SQLException {
		int batchEndIndex = 1;
		if (this.batchParameterList != null && !this.batchParameterList.isEmpty()) {
			batchEndIndex = this.batchParameterList.size();
		}

		for (int batchIndex = 0; batchIndex < batchEndIndex; batchIndex++) {
			if (this.batchParameterList != null && !this.batchParameterList.isEmpty()) {
				this.parameterList = this.batchParameterList.get(batchIndex);
			}

			if (this.parameterList != null && !this.parameterList.isEmpty()) {
				int parameterIndex = 1;
				for (Parameter parameter : this.parameterList) {
					if (parameter.getType() == null) {
						this.preparedStatement.setNull(parameterIndex, parameter.getType());
						parameterIndex++;
					} else if (parameter.getType() == Types.ARRAY) {
						List<Object> arrayValue = ((List<?>) parameter.getValue()).stream().map(r -> r)
								.collect(Collectors.toList());
						for (Object value : arrayValue) {
							if (value == null) {
								this.preparedStatement.setNull(parameterIndex, Types.VARCHAR);
							} else {
								this.preparedStatement.setObject(parameterIndex, value);
							}
							parameterIndex++;
						}
					} else {
						this.preparedStatement.setObject(parameterIndex, parameter.getValue());
						parameterIndex++;
					}
				}
			}
		}

	}

	public List<LinkedHashMap<String, Object>> getResultSetHashMap(String query) throws Exception {
		ResultSet resultSet = null;
		try {
			this.openConnection();
			query = this.replaceParameterName(query);
			this.preparedStatement = this.connection.prepareStatement(query);
			this.preparedStatement.setFetchSize(10000);
			this.addParameterToStatement();
			resultSet = this.preparedStatement.executeQuery();
			Integer columnCount = resultSet.getMetaData().getColumnCount();
			List<LinkedHashMap<String, Object>> result = new ArrayList<>();
			LinkedHashMap<String, Object> record;
			while (resultSet.next()) {
				record = new LinkedHashMap<>();
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
					record.put(resultSet.getMetaData().getColumnLabel(columnIndex + 1),
							resultSet.getObject(columnIndex + 1));
				}
				result.add(record);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			this.closeConnection(true);
		}
	}

	public List<LinkedHashMap<String, Object>> getStringResultSetHashMapWithParams(String query) throws Exception {
		ResultSet resultSet = null;
		try {
			this.openConnection();
			query = this.replaceParameterName(query);
			this.preparedStatement = this.connection.prepareStatement(query);
			this.preparedStatement.setFetchSize(10000);
			this.addParameterToStatement();
			resultSet = this.preparedStatement.executeQuery();
			Integer columnCount = resultSet.getMetaData().getColumnCount();
			List<LinkedHashMap<String, Object>> result = new ArrayList<>();
			LinkedHashMap<String, Object> record;
			while (resultSet.next()) {
				record = new LinkedHashMap<>();
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
					record.put(resultSet.getMetaData().getColumnLabel(columnIndex + 1),
							resultSet.getString(columnIndex + 1));
				}
				result.add(record);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			this.closeConnection(true);
		}
	}

	public List<Object[]> getResultSetArray(String query) throws Exception {
		ResultSet resultSet = null;
		try {
			this.openConnection();
			query = this.replaceParameterName(query);
			this.statement = this.connection.createStatement();
			this.statement.setFetchSize(10000);
			resultSet = this.statement.executeQuery(query);
			Integer columnCount = resultSet.getMetaData().getColumnCount();
			List<Object[]> result = new ArrayList<>();
			Object[] record;
			while (resultSet.next()) {
				record = new Object[columnCount];
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
					record[columnIndex] = resultSet.getObject(columnIndex + 1);
				}
				result.add(record);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			this.closeConnection(true);
		}
	}

	public List<Object[]> getResultSetArrayWithParams(String query) throws Exception {
		ResultSet resultSet = null;
		try {
			this.openConnection();
			query = this.replaceParameterName(query);
			this.preparedStatement = this.connection.prepareStatement(query);
			this.preparedStatement.setFetchSize(10000);
			this.addParameterToStatement();
			resultSet = this.preparedStatement.executeQuery();
			Integer columnCount = resultSet.getMetaData().getColumnCount();
			List<Object[]> result = new ArrayList<>();
			Object[] record;
			while (resultSet.next()) {
				record = new Object[columnCount];
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
					record[columnIndex] = resultSet.getObject(columnIndex + 1);
				}
				result.add(record);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			this.closeConnection(true);
		}
	}

	public Integer executeNonQuery(String query) throws Exception {
		try {
			this.openConnection();
			this.statement = this.connection.createStatement();
			return this.statement.executeUpdate(query);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			this.closeConnection(true);
		}
	}

	public Integer executeNonQueryWithParameters(String query) throws Exception {
		try {
			this.openConnection();
			query = this.replaceParameterName(query);
			this.preparedStatement = this.connection.prepareStatement(query);
			this.addParameterToStatement();
			return this.preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			this.closeConnection(true);
		}
	}

	public void beginTransaction() throws Exception {
		if (this.connection != null) {
			this.connection.close();
			this.connection = null;
		}
		this.openConnection();
		this.connection.setAutoCommit(false);

	}

	public void commitTransaction() throws SQLException {
		if (this.connection != null && !this.connection.isClosed()) {
			this.connection.commit();
			this.connection.setAutoCommit(true);
		}
		this.closeConnection(true);
	}

	public void rollbackTransaction() throws SQLException {
		if (this.connection != null && !this.connection.isClosed()) {
			this.connection.rollback();
			this.connection.setAutoCommit(true);
		}
		this.closeConnection(true);
	}

	public void addStringParameter(String name, String value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.VARCHAR, value));
	}

	public void addIntegerParameter(String name, Integer value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.INTEGER, value));
	}

	public void addLongParameter(String name, Long value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.BIGINT, value));
	}

	public void addBooleanParameter(String name, Boolean value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.BOOLEAN, value));
	}

	public void addDoubleParameter(String name, Double value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.DOUBLE, value));
	}

	public void addClobParameter(String name, Clob value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.CLOB, value));
	}

	public void addBlobParameter(String name, Blob value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.BLOB, value));
	}

	public void addDateParameter(String name, Date value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.DATE, value));
	}

	public void addNullParameter(String name, int type) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, type, null));
	}

	public void addObjectParameter(String name, Object value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.OTHER, value));
	}

	public void addArrayParameter(String name, Array value) {
		if (this.parameterList == null)
			this.parameterList = new ArrayList<>();
		this.parameterList.add(new Parameter(name, Types.ARRAY, value));
	}

	public String getQueryWithParameters(String query) {
		if (this.parameterList != null && !this.parameterList.isEmpty()) {
			for (Parameter parameter : this.parameterList) {
				{
					query = query = query.replace(parameter.name,
							parameter.value == null ? "" : parameter.value.toString());
				}
			}
		}
		return query;
	}

	public void addBatchParameter() {
		if (this.batchParameterList == null)
			this.batchParameterList = new ArrayList<>();
		if (this.parameterList != null && !this.parameterList.isEmpty()) {
			this.batchParameterList.add(this.parameterList);
			this.parameterList.clear();
		}
	}

	public int[] executeBatchQuery(String query) throws Exception {
		try {
			this.openConnection();
			query = this.replaceParameterName(query);
			this.preparedStatement = this.connection.prepareStatement(query);
			return this.preparedStatement.executeBatch();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			this.closeConnection(true);
		}
	}

	public class Parameter {
		String name;
		Object value;
		Integer type;

		public Parameter(String name, Integer type, Object value) {
			super();
			this.name = name;
			this.type = type;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

	}

}
