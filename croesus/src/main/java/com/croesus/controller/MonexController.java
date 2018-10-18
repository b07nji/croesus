package com.croesus.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.croesus.scraping.ScrapingMain;


@Controller
public class MonexController {
	
	@RequestMapping("/monex")
	public String monexPrice(Model model) throws IOException {
		
		ScrapingMain scraping = new ScrapingMain();
		
		
		model.addAttribute("price", scraping.getMonexPrice());
		return "monex_price";
		
	}

}