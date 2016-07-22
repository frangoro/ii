package org.frangoro.dao;

import java.util.List;
import java.util.Map;

import org.frangoro.domain.ItemsTransLoc;

public interface ItemTranLocDao {
	public List<ItemsTransLoc> findAll();

	public ItemsTransLoc findById(Long id);

	List<ItemsTransLoc> queryFilter(Map<String, String> filter);
}
