package org.frangoro.dao;

import java.util.List;

import org.frangoro.domain.ItemsTransLoc;

public interface ItemTranLocDao {
	public List<ItemsTransLoc> findAll();

	public ItemsTransLoc findById(Long id);
}
