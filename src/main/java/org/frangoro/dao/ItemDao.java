package org.frangoro.dao;

import java.util.List;

import org.frangoro.domain.Items;

public interface ItemDao {
	public List<Items> findAll();

	public Boolean update(Items item);

	public Items findById(Long id);
}
