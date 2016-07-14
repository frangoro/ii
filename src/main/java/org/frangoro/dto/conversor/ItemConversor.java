package org.frangoro.dto.conversor;

import java.util.List;

import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.dto.ItemDto;
import org.springframework.beans.BeanUtils;

public class ItemConversor {

	public static void entityToDtos(List<ItemsTransLoc> items, List<ItemDto> itemDtos) {
		for (ItemsTransLoc item : items) {
			ItemDto itemDto = new ItemDto();
			entityToDto(item, itemDto);
			itemDtos.add(itemDto);
		}
	}
	
	public static void entityToDto (ItemsTransLoc item, ItemDto itemDto) {
		BeanUtils.copyProperties(item, itemDto);
	}
	
	public static void dtoToEntity (ItemDto itemDto, Items item) {
		BeanUtils.copyProperties(itemDto, item);
	}
	
}
