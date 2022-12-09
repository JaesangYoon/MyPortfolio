package com.myportfolio.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {
	@GetMapping("/board/list")
	public String read(Integer page, Integer pageSize, Model m){
		m.addAttribute("page", 10);


		return "boardList";
	}


}
