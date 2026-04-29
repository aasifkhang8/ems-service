package com.org.ems_service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionResponseDto {

	@JsonProperty("Physics")
	List<TempQuestionsDto> physics;

	@JsonProperty("Chemistry")
	List<TempQuestionsDto> chemistry;

	@JsonProperty("Maths")
	List<TempQuestionsDto> maths;

	public List<TempQuestionsDto> getPhysics() {
		return physics;
	}

	public void setPhysics(List<TempQuestionsDto> physics) {
		this.physics = physics;
	}

	public List<TempQuestionsDto> getChemistry() {
		return chemistry;
	}

	public void setChemistry(List<TempQuestionsDto> chemistry) {
		this.chemistry = chemistry;
	}

	public List<TempQuestionsDto> getMaths() {
		return maths;
	}

	public void setMaths(List<TempQuestionsDto> maths) {
		this.maths = maths;
	}

}
