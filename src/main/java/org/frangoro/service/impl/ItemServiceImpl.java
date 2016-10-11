package org.frangoro.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.frangoro.dao.ItemDao;
import org.frangoro.dao.ItemTranLocDao;
import org.frangoro.dao.TransactionDao;
import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.domain.Locations;
import org.frangoro.domain.Transactions;
import org.frangoro.dto.ItemDto;
import org.frangoro.dto.conversor.ItemConversor;
import org.frangoro.enums.OperationEnum.Operation;
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
	public ItemDto getInfo(Long id) {
		ItemsTransLoc item = itemTranLocDao.findById(id);
		if (item == null) {
			return null;
		}
		ItemDto itemDto = new ItemDto();
		ItemConversor.entityToDto(item, itemDto);
		
		return itemDto;
	}
	
	/**
	 * Create one item and one transaction
	 * with operation NEW.
	 */
	@Override
	public Boolean create(ItemDto itemDto) {
		// Comprobar si existe otro item con el mismo código
		Items item = getItemByCode(itemDto.getCode());
		if (item != null) {
			return false;
		}
		item = new Items();
		Transactions transaction = new Transactions();
		ItemConversor.dtoToEntity(itemDto, item);
		ItemConversor.dtoToEntity(itemDto, transaction);
		transaction.setOperation(Operation.NEW.name());
		Boolean created = itemDao.create(item);
		if (created) {
			transaction.setItems(item);
			//Default Location //TODO: Pedir location en el front
			Locations location = new Locations();
			location.setId(1);
			transaction.setLocations(location);
			// Auditoría
			transaction.setTransactionUser("frangoro");//FIXME
			transaction.setTransactionDate(new Date());
			created = transactionDao.create(transaction);
		}
		return created;
	}

	@Override
	public Boolean delete(Long id) {
		Transactions transaction;
		Transactions transactionNew;
		// Buscar transacción por id del item
		transaction = transactionDao.findByItemId(id);
		// Duplicar transacción con la operacion a DELETE
		transactionNew = new Transactions(transaction);
		transactionNew.setOperation("REMOVED");
		transactionNew.setTransactionDate(new Date());
		transactionNew.setTransactionUser("frangoro");//FIXME
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
	
	@Override
	public Items getAvailableItem(Long id) {
		Items item = itemDao.findAvailableById(id);
		if (item == null) {
			return null;
		}
		return item;
	}
	
	@Override
	public List<ItemsTransLoc> queryFilter (Map<String, String> filter) {
		return itemTranLocDao.queryFilter(filter);
	}
	
	private Items getItemByCode(String code) {
		Items item = itemDao.findByCode(code);
		if (item == null) {
			return null;
		}
		return item;
	}

}
