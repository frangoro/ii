package org.frangoro.domain;
// Generated 06-jul-2016 14:46:01 by Hibernate Tools 5.0.0.Alpha3

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transactions generated by hbm2java
 */
@Entity
@Table(name = "TRANSACTIONS")
public class Transactions implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Locations locations;
	private Items items;
	private Date transactionDate;
	private String transactionUser;
	private String operation;

	public Transactions() {
	}

	public Transactions(Long id, Date transactionDate, String transactionUser, String operation) {
		this.id = id;
		this.transactionDate = transactionDate;
		this.transactionUser = transactionUser;
		this.operation = operation;
	}

	public Transactions(Long id, Locations locations, Items items, Date transactionDate, String transactionUser,
			String operation) {
		this.id = id;
		this.locations = locations;
		this.items = items;
		this.transactionDate = transactionDate;
		this.transactionUser = transactionUser;
		this.operation = operation;
	}
	
	public Transactions(Transactions t) {
		this(null, t.getLocations(), t.getItems(), t.getTransactionDate(), t.getTransactionUser(),
				t.getOperation());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
	@SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOCATION_ID")
	public Locations getLocations() {
		return this.locations;
	}

	public void setLocations(Locations locations) {
		this.locations = locations;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	public Items getItems() {
		return this.items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRANSACTION_DATE", nullable = false, length = 7)
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(name = "TRANSACTION_USER", nullable = false, length = 50)
	public String getTransactionUser() {
		return this.transactionUser;
	}

	public void setTransactionUser(String transactionUser) {
		this.transactionUser = transactionUser;
	}

	@Column(name = "OPERATION", nullable = false, length = 20)
	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
