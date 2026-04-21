package com.org.ems_service.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ems_service.dto.ResponseDto;
import com.org.ems_service.dto.TempQuestionsDto;
import com.org.ems_service.service.TempQuestionsService;

@RestController
@RequestMapping("/api")
public class TempQuestionsController {
	
	@Autowired
	private TempQuestionsService tempQuestionsService;

	@PostMapping(value = "/saveTempQuestions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> saveTempQuestions(@RequestBody TempQuestionsDto tempQuestions) throws Exception {
		ResponseDto<Void> response = new ResponseDto<Void>();
		String message = this.tempQuestionsService.saveTempQuestions(tempQuestions);
		response.setMessage(message);
		return ResponseEntity.ok(response);
	}

//	@PostMapping(value = "/findAllEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<ResponseDto<Employee>> findAllEmployee(@RequestBody Employee Employee) throws Exception {
//		ResponseDto<Employee> response = new ResponseDto<Employee>();
//		List<Employee> EmployeeList = this.EmployeeService.findAllEmployee(Employee);
//		response.setData(EmployeeList);
//		return ResponseEntity.ok(response);
//	}


}
