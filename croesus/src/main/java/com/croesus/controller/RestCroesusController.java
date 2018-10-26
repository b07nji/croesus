package com.croesus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.croesus.bean.Request;
import com.croesus.main.CroesusMain;

@RestController
public class RestCroesusController {
	
	@RequestMapping("/rest")
	public Request getRequest() {
		CroesusMain main = new CroesusMain();
		Request request = main.getRequest();
		
		return request;
	}

}
