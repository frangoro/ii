package org.frangoro.controller;

import org.frangoro.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/location")
public class LocationController {

	@Autowired
	ItemService itemService;
	
	Logger log = LoggerFactory.getLogger(LocationController.class);

	private static final String VIEW_INDEX = "index";

	@RequestMapping(value = "/")
	public String index() {
		return VIEW_INDEX;
	}
}
