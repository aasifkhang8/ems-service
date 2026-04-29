package com.org.ems_service.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.ems_service.util.Constants;

public class TempQuestionsDto {
	private int id;
	private int uiIdCounter;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String optionE;
	private String optionF;
	private String correctOption;
	private int optionTypeId;
	private int questionLevelId;
	private int chapterId;
	private String isActive;
	private String insertedBy;
	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DEFAULT_DATE_FORMAT, timezone = Constants.DEFAULT_TIME_ZONE)
	private LocalDateTime insertedDttm;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DEFAULT_DATE_FORMAT, timezone = Constants.DEFAULT_TIME_ZONE)
	private LocalDateTime updatedDttm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getOptionE() {
		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	public String getOptionF() {
		return optionF;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	public int getOptionTypeId() {
		return optionTypeId;
	}

	public void setOptionTypeId(int optionTypeId) {
		this.optionTypeId = optionTypeId;
	}

	public int getQuestionLevelId() {
		return questionLevelId;
	}

	public void setQuestionLevelId(int questionLevelId) {
		this.questionLevelId = questionLevelId;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getInsertedDttm() {
		return insertedDttm;
	}

	public void setInsertedDttm(LocalDateTime insertedDttm) {
		this.insertedDttm = insertedDttm;
	}

	public LocalDateTime getUpdatedDttm() {
		return updatedDttm;
	}

	public void setUpdatedDttm(LocalDateTime updatedDttm) {
		this.updatedDttm = updatedDttm;
	}

	public int getUiIdCounter() {
		return uiIdCounter;
	}

	public void setUiIdCounter(int uiIdCounter) {
		this.uiIdCounter = uiIdCounter;
	}

}
