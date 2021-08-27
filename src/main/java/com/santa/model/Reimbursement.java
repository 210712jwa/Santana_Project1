package com.santa.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ersReimbursement")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimbId")
	private int id;
	
	@Column(name = "reimbAmount")
	private int amount;

	@Column(name = "reimbSubmitted")
	private Timestamp submitted;
	
	@Column(name = "reimbResolved")
	private Timestamp resolved;
	
	@Column(name = "reimbDescription")
	private String description;
	
	@Column(name = "reimbReceipt")
	private Blob receipt;
	
	@ManyToOne
	@JoinColumn(name = "reimbAuthor", nullable = false)
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "reimbResolver")
	private User resolver;
	
	@ManyToOne
	@JoinColumn(name = "reimbStatusId")
	private ReimbursementStatus status;
	
	@ManyToOne
	@JoinColumn(name = "reimbTypeId")
	private ReimbursementType type;

	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescription, Blob reimbReceipt) {
		super();
		
		this.amount = reimbAmount;
		this.submitted = reimbSubmitted;
		this.resolved = reimbResolved;
		this.description = reimbDescription;
		this.receipt = reimbReceipt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, id, receipt, resolved, resolver, status, submitted, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(receipt, other.receipt) && Objects.equals(resolved, other.resolved)
				&& Objects.equals(resolver, other.resolver) && Objects.equals(status, other.status)
				&& Objects.equals(submitted, other.submitted) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + ", author=" + author + ", resolver="
				+ resolver + ", status=" + status + ", type=" + type + "]";
	}
	
	
	

}
