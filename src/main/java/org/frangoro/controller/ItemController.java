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
public class ItemController {

	@Autowired
	ItemService itemService;
	
	private static final Log log = LogFactory.getLog(ItemController.class);

	private static final String VIEW_INDEX = "index";

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ItemDto>> items() {
		List<ItemDto> itemdtos = itemService.getItems();
		return new ResponseEntity<List<ItemDto>>(itemdtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ItemDto> update(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
		Items item = itemService.get(id);
		if (item == null) {
			log.warn("Item not found");
			return new ResponseEntity<ItemDto>(HttpStatus.NOT_FOUND);
		}
		
		ItemConversor.DtoToEntity(itemDto, item);
		if (itemService.update(item)) {
			log.debug("Item updated");
			return new ResponseEntity<ItemDto>(HttpStatus.OK);
		}
		log.warn("Item not updated");
		return new ResponseEntity<ItemDto>(HttpStatus.NOT_MODIFIED);
	}

	@RequestMapping(value = "/")
	public String index() {
		return VIEW_INDEX;
	}
}
