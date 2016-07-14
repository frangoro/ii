package org.frangoro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private static final String VIEW_INDEX = "index";
	
	@RequestMapping(value = "/")
	public String index() {
		return VIEW_INDEX;
	}
	
	@RequestMapping(value = "/index")
	public void indexAlternative() {
		index();
	}
}
