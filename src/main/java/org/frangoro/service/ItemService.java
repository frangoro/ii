package org.frangoro.service;

import java.util.List;
import java.util.Map;

import org.frangoro.domain.Items;
import org.frangoro.domain.ItemsTransLoc;
import org.frangoro.dto.ItemDto;

public interface ItemService {

	/**
	 * Obtiene todos los items. Solo los datos correspondientes a su última
	 * transacción. Incluye eliminados.
	 * 
	 * @return
	 */
	public List<ItemsTransLoc> getItems();

	/**
	 * Modifica un item creando una nueva transacción.
	 * 
	 * @param item
	 * @return
	 */
	public Boolean update(Items item);

	/**
	 * Obtiene la información correspondiente de la última transacción del item
	 * indicado por el id.
	 * 
	 * @param id
	 * @return
	 */
	public ItemDto getInfo(Long id);

	/**
	 * Obtiene el item indicado por el id.
	 * 
	 * @param id
	 * @return
	 */
	public Items getItem(Long id);

	/**
	 * Crea un nuevo item creando una nueva transacción.
	 * 
	 * @param itemDto
	 * @return
	 */
	public Boolean create(ItemDto itemDto);

	/**
	 * Elimina el item indicado por su id creando una nueva transacción.
	 * 
	 * @param id
	 * @return
	 */
	public Boolean delete(Long id);

	public List<ItemsTransLoc> queryFilter(Map<String, String> filter);
}
