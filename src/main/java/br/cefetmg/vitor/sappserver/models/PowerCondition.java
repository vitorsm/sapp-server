package br.cefetmg.vitor.sappserver.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "power_condition")
@Data
public class PowerCondition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "power_condition_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operation_type_id", nullable = false)
	private OperationType operationType;
	
	@Column(name = "value", nullable = false)
	private float value;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({
//			@JoinColumn(name = "input_pin_number", referencedColumnName = "input_pin_number"),
//			@JoinColumn(name = "input_control_module_id", referencedColumnName = "input_control_module_id")
//	})
	@JoinColumn(name = "input_pin_id", referencedColumnName = "pin_id")
	private Pin input;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({
//			@JoinColumn(name = "pin_number", referencedColumnName = "pin_number"),
//			@JoinColumn(name = "control_module_id", referencedColumnName = "control_module_id")
//	})
	@JoinColumn(name = "pin_id", referencedColumnName = "pin_id")
	private Pin pin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;
	
	@Column(name = "created_at", nullable = false)
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
		PowerCondition other = (PowerCondition) obj;
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
