package org.frangoro.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	private static final Log log = LogFactory.getLog(ItemController.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemDto> show(@PathVariable("id") Long id) {
		ItemDto itemDto = new ItemDto();
		ItemsTransLoc item = itemService.getInfo(id);
		if (item == null) {
			log.warn("Item not found");
			return new ResponseEntity<ItemDto>(HttpStatus.NOT_FOUND);
		}
		ItemConversor.entityToDto(item, itemDto);
		return new ResponseEntity<ItemDto>(itemDto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ItemDto>> query(@RequestParam(value = "filter", required = false) String filter) {
		List<ItemsTransLoc> items = null;
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		if (filter == null) {
			log.debug("Searching without filter. Return all records.");
			items = itemService.getItems();
		} /*else {
			items = itemService.getItemsFilter();
		}*/
		if (items == null) {
			log.warn("Items not found");
			return new ResponseEntity<List<ItemDto>>(HttpStatus.NOT_FOUND);
		}
		ItemConversor.entityToDtos(items, itemDtos);
		return new ResponseEntity<List<ItemDto>>(itemDtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ItemDto> update(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
		Items item = itemService.getItem(id);
		if (item == null) {
			log.warn("Item not found with id: " + id);
			return new ResponseEntity<ItemDto>(HttpStatus.NOT_FOUND);
		}
		
		ItemConversor.dtoToEntity(itemDto, item);
		if (itemService.update(item)) {
			log.debug("Item updated");
			return new ResponseEntity<ItemDto>(HttpStatus.OK);
		}
		log.warn("Item not updated");
		return new ResponseEntity<ItemDto>(HttpStatus.NOT_MODIFIED);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ItemDto> create(@RequestBody ItemDto itemDto) {
		Items item = new Items();
		ItemConversor.dtoToEntity(itemDto, item);
		if (itemService.create(item)) {//TODO: Controlar si existe
			log.debug("Item created with id: " + item.getId() + " and code: " + item.getCode());
			return new ResponseEntity<ItemDto>(HttpStatus.CREATED);
		}
		log.warn("Item not created");
		return new ResponseEntity<ItemDto>(HttpStatus.NO_CONTENT);
	}

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemDto> delete(@PathVariable("id") Long id) {
		Items item = itemService.get(id);
		if (item == null) {
			return new ResponseEntity<ItemDto>(HttpStatus.NOT_FOUND);
		}
		itemService.delete(id);
		log.debug("Item deleted with id: " + item.getId() + " and code: " + item.getCode());
		return new ResponseEntity<ItemDto>(HttpStatus.OK);
	}*/

}
