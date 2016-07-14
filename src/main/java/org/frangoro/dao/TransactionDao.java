package org.frangoro.dao;

import java.util.List;

import org.frangoro.domain.Items;
import org.frangoro.domain.Transactions;

public interface TransactionDao {
	public List<Items> findAll();

	public Boolean update(Items item);

	public Items findById(Long id);

	public boolean create(Items item);

	public Items delete(Long id);

	public Transactions findByItemId(Long id);

	public Boolean create(Transactions transactionNew);
}
