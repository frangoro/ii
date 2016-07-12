package org.frangoro.dto.conversor;

import java.util.List;

import org.frangoro.domain.Items;
import org.frangoro.dto.ItemDto;
import org.springframework.beans.BeanUtils;

public class ItemConversor {

	public static void entityToDtos(List<Items> items, List<ItemDto> itemDtos) {
		for (Items item : items) {
			ItemDto itemDto = new ItemDto();
			entityToDto(item, itemDto);
			itemDtos.add(itemDto);
		}
	}
	
	public static void entityToDto (Items item, ItemDto itemDto) {
		BeanUtils.copyProperties(item, itemDto);
	}
	
	public static void DtoToEntity (ItemDto itemDto, Items item) {
		BeanUtils.copyProperties(itemDto, item);
	}
	
}
