package org.frangoro.dao;

import java.util.List;

import org.frangoro.domain.Items;

/**
 * Este DAO se encarga exclusivamente de 
 * los items y no de su relación 
 * con las transacciones.
 */
public interface ItemDao {

	/**
	 * Encuentra todos los items
	 * de la tabla Items.
	 * @return Items
	 */
	public List<Items> findAll();
	
	/**
	 * Encuentra un item por id
	 * de la tabla Item.
	 * @param id
	 * @return
	 */
	public Items findById(Long id);
	
	/**
	 * Actualiza un item si actualizar
	 * su transacción. No es el comportamiento
	 * habitual.
	 * @param item
	 * @return
	 */
	public Boolean update(Items item);
	
	/**
	 * Crea un item si crear una transacción.
	 * No es el comportamiento habitual.
	 * @param item
	 * @return
	 */
	public Boolean create(Items item);
	
	/**
	 * Obtiene el item correspondiente
	 * al código indicado. El código es único.
	 * 
	 * @param id
	 * @return
	 */
	public Items findByCode(String code);

	/**
	 * Obtiene el item indicado por el id de entre los 
	 * items disponibles (Estado NEW o RETURNED)
	 * 
	 * @param id
	 * @return
	 */
	public Items findAvailableById(Long id);

}
