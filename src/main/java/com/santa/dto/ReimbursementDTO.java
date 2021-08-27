package com.santa.dto;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

import com.santa.model.User;

public class ReimbursementDTO {

	private int reimAmount;
	private Timestamp reimSubmitted;
	private String reimDescription;
	private Blob reimReceipt;
	private int reimType;
	private User reimAuthor;
	
	public ReimbursementDTO() {
		super();
	}

	public ReimbursementDTO(int reimAmount, String reimDescription, Blob reimReceipt, int reimType) {
		super();
		this.reimAmount = reimAmount;
		this.reimDescription = reimDescription;
		this.reimReceipt = reimReceipt;
		this.reimType = reimType;

	}

	public int getReimAmount() {
		return reimAmount;
	}

	public void setReimAmount(int reimAmount) {
		this.reimAmount = reimAmount;
	}

	public Timestamp getReimSubmitted() {
		return reimSubmitted;
	}

	public void setReimSubmitted(Timestamp reimSubmitted) {
		this.reimSubmitted = reimSubmitted;
	}

	public String getReimDescription() {
		return reimDescription;
	}

	public void setReimDescription(String reimDescription) {
		this.reimDescription = reimDescription;
	}

	public Blob getReimReceipt() {
		return reimReceipt;
	}

	public void setReimReceipt(Blob reimReceipt) {
		this.reimReceipt = reimReceipt;
	}

	public int getReimType() {
		return reimType;
	}

	public void setReimType(int reimType) {
		this.reimType = reimType;
	}

	public User getReimAuthor() {
		return reimAuthor;
	}

	public void setReimAuthor(User reimAuthor) {
		this.reimAuthor = reimAuthor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reimAmount, reimAuthor, reimDescription, reimReceipt, reimSubmitted, reimType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementDTO other = (ReimbursementDTO) obj;
		return Double.doubleToLongBits(reimAmount) == Double.doubleToLongBits(other.reimAmount)
				&& Objects.equals(reimAuthor, other.reimAuthor)
				&& Objects.equals(reimDescription, other.reimDescription)
				&& Objects.equals(reimReceipt, other.reimReceipt) && Objects.equals(reimSubmitted, other.reimSubmitted)
				&& Objects.equals(reimType, other.reimType);
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [reimAmount=" + reimAmount + ", reimSubmitted=" + reimSubmitted + ", reimDescription="
				+ reimDescription + ", reimReceipt=" + reimReceipt + ", reimType=" + reimType + ", reimAuthor="
				+ reimAuthor + "]";
	}

}