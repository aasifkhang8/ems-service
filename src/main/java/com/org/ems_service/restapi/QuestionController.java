/**
 * 
 */
package com.org.ems_service.restapi;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ems_service.dto.ResponseDto;
import com.org.ems_service.dto.TempQuestionsDto;

/**
 * 
 */
@RestController
@RequestMapping("/api/question")
@CrossOrigin
public class QuestionController {
	
	@PostMapping(value = "/getQuestions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveTempQuestions(@RequestBody TempQuestionsDto tempQuestions) throws Exception {
		ResponseDto<String> response = new ResponseDto<>();
		String message = "{\r\n"
				+ "  \"a\": {\r\n"
				+ "    \"items\": [\r\n"
				+ "      { \"id\": 1, \"name\": \"alpha\" },\r\n"
				+ "      { \"id\": 2, \"name\": \"beta\" }\r\n"
				+ "    ]\r\n"
				+ "  },\r\n"
				+ "  \"b\": {\r\n"
				+ "    \"items\": [\r\n"
				+ "      { \"id\": 3, \"name\": \"gamma\" },\r\n"
				+ "      { \"id\": 4, \"name\": \"delta\" }\r\n"
				+ "    ]\r\n"
				+ "  },\r\n"
				+ "  \"c\": {\r\n"
				+ "    \"items\": [\r\n"
				+ "      { \"id\": 5, \"name\": \"epsilon\" },\r\n"
				+ "      { \"id\": 6, \"name\": \"zeta\" }\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}";
		return ResponseEntity.ok(message);
	}
	

}
