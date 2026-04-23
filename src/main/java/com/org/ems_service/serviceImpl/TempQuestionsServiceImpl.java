package com.org.ems_service.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.org.ems_service.dto.TempQuestionsDto;
import com.org.ems_service.service.TempQuestionsService;
import com.org.ems_service.util.Constants;
import com.org.ems_service.util.JDBCHelper;
import com.org.ems_service.util.QueryConstant;

@Service
public class TempQuestionsServiceImpl implements TempQuestionsService {

	@Override
	public String saveTempQuestions(TempQuestionsDto dto) throws Exception {
		JDBCHelper jdbchelper = null;
		try {
			jdbchelper = new JDBCHelper();
			jdbchelper.addStringParameter(":QUESTION", dto.getQuestion());
			jdbchelper.addStringParameter(":OPTION_A", dto.getOptionA());
			jdbchelper.addStringParameter(":OPTION_B", dto.getOptionB());
			jdbchelper.addStringParameter(":OPTION_C", dto.getOptionC());
			jdbchelper.addStringParameter(":OPTION_D", dto.getOptionD());
			jdbchelper.addStringParameter(":OPTION_E", dto.getOptionE());
			jdbchelper.addStringParameter(":OPTION_F", dto.getOptionF());
			jdbchelper.addStringParameter(":CORRECT_OPTION", dto.getCorrectOption());
			jdbchelper.addIntegerParameter(":OPTION_TYPE_ID", dto.getOptionTypeId());
			jdbchelper.addIntegerParameter(":QUESTION_LEVEL_ID", dto.getQuestionLevelId());
			jdbchelper.addIntegerParameter(":CHAPTER_ID", dto.getChapterId());
			jdbchelper.addStringParameter(":IS_ACTIVE", dto.getIsActive());
			jdbchelper.addStringParameter(":INSERTED_BY", dto.getInsertedBy());
			jdbchelper.addStringParameter(":UPDATED_BY", dto.getUpdatedBy());

			jdbchelper.executeNonQueryWithParameters(QueryConstant.INSERT_TEMP_QUESTION);

		} catch (Exception e) {
			throw new Exception();
		} finally {
			jdbchelper.dispose();
		}

		return Constants.SAVE;
	}

	@Override
	public List<LinkedHashMap<String, Object>> findByTempQuestionsId(TempQuestionsDto dto) throws Exception {
		JDBCHelper jdbchelper = null;
		try {
			jdbchelper = new JDBCHelper();
			jdbchelper.addIntegerParameter(":ID", dto.getId());
			return jdbchelper.getStringResultSetHashMapWithParams(QueryConstant.FIND_QUESTION_BY_ID);
		} catch (Exception e) {
			throw new Exception();
		} finally {
			jdbchelper.dispose();
		}
	}

}
