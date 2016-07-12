package org.frangoro.service;

import java.util.List;

import org.frangoro.domain.Items;
import org.frangoro.dto.ItemDto;

public interface ItemService {
	public List<ItemDto> getItems();

	public Boolean update(Items item);
	
	public Items get(Long id);
}
