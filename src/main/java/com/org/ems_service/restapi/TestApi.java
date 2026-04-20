package com.org.ems_service.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ems_service.util.JDBCHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class TestApi {
//	
//	@Autowired
//	Environment ds;

	@GetMapping("test")
	public String test() throws Exception {
		return "<h1>" + "Success" + "<h1>";
	}
	
	@GetMapping("con")
	public String test1() throws Exception {
		JDBCHelper help = new JDBCHelper();
		help.openConnection();
		return "<h1>" + "SS" + "<h1>";
	}

}
