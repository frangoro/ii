package org.frangoro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.dto.ItemDto;
import org.frangoro.dto.conversor.ItemConversor;
import org.frangoro.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONDeserializer;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	Logger log = LoggerFactory.getLogger(ItemController.class);

	/**
	 * Show the specified item by id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemDto> show(@PathVariable("id") Long id) {
		ItemDto itemDto = itemService.getInfo(id);
		if (itemDto == null) {
			log.warn("Item not found");
			return new ResponseEntity<ItemDto>(HttpStatus.NOT_FOUND);
		}
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
		} else {
			Map<String, String> filterMap = new HashMap<String, String>();
			List<HashMap<String,String>> filterList = new JSONDeserializer<ArrayList<HashMap<String,String>>>().deserialize(filter);
            for (HashMap<String,String> att : filterList) {
                String name = att.get("property");
                String value = att.get("value");
                filterMap.put(name, value);
            }
            items = itemService.queryFilter(filterMap);
		}
		if (items == null) {
			log.warn("Items not found");//Devuelve 404, Habría que devolver mensaje de info
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
			return new ResponseEntity<ItemDto>(itemDto, HttpStatus.OK);
		}
		log.warn("Item not updated");
		return new ResponseEntity<ItemDto>(HttpStatus.NOT_MODIFIED);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ItemDto> create(@RequestBody ItemDto itemDto) {
		Items item = new Items();
		
		if (itemService.create(itemDto)) {
			log.debug("Item created with id: " + item.getId() + " and code: " + item.getCode());
			return new ResponseEntity<ItemDto>(itemDto, HttpStatus.CREATED);
		}
		log.warn("Item not created");
		return new ResponseEntity<ItemDto>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ItemDto> delete(@PathVariable("id") Long id) {
		Items item = itemService.getAvailableItem(id);
		if (item == null) {
			log.warn("Item not found with id: " + id);
			return new ResponseEntity<ItemDto>(HttpStatus.NOT_FOUND);
		}
		if (itemService.delete(id)) {
			log.debug("Item deleted with id: " + item.getId() + " and code: " + item.getCode());
			return new ResponseEntity<ItemDto>(HttpStatus.OK);
		}
		return new ResponseEntity<ItemDto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
