package org.frangoro.dto.conversor;

import java.util.List;

import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.domain.Transactions;
import org.frangoro.dto.ItemDto;
import org.springframework.beans.BeanUtils;
//TODO: Usar tipos genéricos para agrupar métodos
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
	
	public static void entityToDto (Object item, ItemDto itemDto) {
		BeanUtils.copyProperties(item, itemDto);
	}
	
	public static void dtoToEntity (ItemDto itemDto, Items item) {
		BeanUtils.copyProperties(itemDto, item);
	}
	
	public static void entityToItemsTransLoc (Object entity, ItemsTransLoc itl) {
		Object[] e = (Object[])entity;
		itl.setId((Long)e[0]);
		itl.setParentId((Long)e[1]);
		itl.setCode((String)e[2]);
		itl.setName((String)e[3]);
		itl.setDescription((String)e[4]);
		itl.setBrand((String)e[5]);
		itl.setModel((String)e[6]);
		itl.setSerialNumber((String)e[7]);
		itl.setFeatures((String)e[8]);
		itl.setStatus((String)e[9]);
		itl.setLocation((String)e[10]);
	}

	public static void dtoToEntity(ItemDto itemDto, Transactions transaction) {
		BeanUtils.copyProperties(itemDto, transaction, "id");
	}
	
}
