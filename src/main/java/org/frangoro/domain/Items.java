package org.frangoro.domain;
// Generated 06-jul-2016 14:46:01 by Hibernate Tools 5.0.0.Alpha3

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Items generated by hbm2java
 */
@Entity
@Table(name = "ITEMS")
public class Items implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private Items items;
	private String code;
	private String name;
	private String description;
	private String brand;
	private String model;
	private String serialNumber;
	private String features;
	private Set<Items> itemses = new HashSet(0);
	private Set<Transactions> transactionses = new HashSet(0);

	public Items() {
	}

	public Items(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Items(long id, Items items, String code, String name, String description, String brand, String model,
			String serialNumber, String features, Set<Items> itemses, Set<Transactions> transactionses) {
		this.id = id;
		this.items = items;
		this.code = code;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.model = model;
		this.serialNumber = serialNumber;
		this.features = features;
		this.itemses = itemses;
		this.transactionses = transactionses;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Items getItems() {
		return this.items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	@Column(name = "CODE", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "BRAND", length = 50)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "MODEL", length = 50)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "SERIAL_NUMBER", length = 50)
	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "FEATURES", length = 200)
	public String getFeatures() {
		return this.features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "items")
	public Set<Items> getItemses() {
		return this.itemses;
	}

	public void setItemses(Set<Items> itemses) {
		this.itemses = itemses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "items")
	public Set<Transactions> getTransactionses() {
		return this.transactionses;
	}

	public void setTransactionses(Set<Transactions> transactionses) {
		this.transactionses = transactionses;
	}

}
