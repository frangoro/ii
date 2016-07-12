package org.frangoro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.frangoro.dao.ItemDao;
import org.frangoro.domain.Items;
import org.frangoro.dto.ItemDto;
import org.frangoro.dto.conversor.ItemConversor;
import org.frangoro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;

	public List<ItemDto> getItems() {
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		List<Items> items = itemDao.findAll();
		ItemConversor.entityToDtos(items,itemDtos);
		return itemDtos;
	}

	public Boolean update(Items item) {
		return itemDao.update(item);
	}
	
	public Items get(Long id) {
		Items item = itemDao.findById(id);
		if (item == null) {
			return null;
		}
		return item;
	}

}
