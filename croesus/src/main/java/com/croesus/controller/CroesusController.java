package com.croesus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.croesus.bean.Request;
import com.croesus.main.CroesusMain;

@Controller
public class CroesusController {
	
	@RequestMapping("/compareFees") 
	public String cmpareFees(Model model) {
		
		CroesusMain main = new CroesusMain();
		Request request = main.getRequest();
		
		model.addAttribute("request", request);
		
		return "compareFees";
	}

}
