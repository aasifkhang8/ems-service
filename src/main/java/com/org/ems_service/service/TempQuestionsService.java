package com.org.ems_service.service;

import org.springframework.stereotype.Component;

import com.org.ems_service.dto.TempQuestionsDto;

@Component
public interface TempQuestionsService {
	String saveTempQuestions(TempQuestionsDto dto) throws Exception;
//
//	String deleteTempQuestions(Integer id) throws Exception;
//
//	TempQuestions findTempQuestionsById(Integer id);
//
//	List<TempQuestions> findAllTempQuestions(TempQuestions employee) throws Exception;
}
