package br.cefetmg.vitor.sappserver.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "rfid_card")
@Data
public class RFIdCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rfid_card_id")
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "number", nullable = false)
	private int number;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "created_id", nullable = false)
	private User createdBy;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "modified_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;

	
	public void setCreatedAt(Date createdAt) {
		
		this.createdAt = createdAt;
		
		if (this.modifiedAt == null)
			this.modifiedAt = createdAt;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RFIdCard other = (RFIdCard) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
}
