package com.org.ems_service.service;

import java.util.LinkedHashMap;
import java.util.List;

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

	List<LinkedHashMap<String, Object>> findByTempQuestionsId(TempQuestionsDto tempQuestions) throws Exception;
}
