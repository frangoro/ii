package org.frangoro.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frangoro.domain.Items;
import org.frangoro.dto.ItemDto;
import org.frangoro.dto.conversor.ItemConversor;
import org.frangoro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	ItemService itemService;
	
	private static final Log log = LogFactory.getLog(TransactionController.class);

	private static final String VIEW_INDEX = "index";


	@RequestMapping(value = "/")
	public String index() {
		return VIEW_INDEX;
	}
}
