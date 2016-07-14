package org.frangoro.service.impl;

import java.util.List;

import org.frangoro.dao.ItemDao;
import org.frangoro.dao.ItemTranLocDao;
import org.frangoro.dao.TransactionDao;
import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.domain.Transactions;
import org.frangoro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ItemTranLocDao itemTranLocDao;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Override
	public List<ItemsTransLoc> getItems() {
		List<ItemsTransLoc> items = itemTranLocDao.findAll();
		return items;
	}

	@Override
	public Boolean update(Items item) {
		return itemDao.update(item);
	}
	
	@Override
	public ItemsTransLoc getInfo(Long id) {
		ItemsTransLoc item = itemTranLocDao.findById(id);
		if (item == null) {
			return null;
		}
		return item;
	}
	
	@Override
	public Boolean create(Items item) {
		return itemDao.create(item);
	}

	@Override
	public Boolean delete(Long id) {
		Transactions transaction;
		Transactions transactionNew;
		// Buscar transacción por id del item
		transaction = transactionDao.findByItemId(id);
		// Duplicar transacción con la operacion a DELETE
		transactionNew = new Transactions(transaction);
		return transactionDao.create(transactionNew);
	}

	@Override
	public Items getItem(Long id) {
		Items item = itemDao.findById(id);
		if (item == null) {
			return null;
		}
		return item;
	}

}
