package br.cefetmg.vitor.sappserver.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "pid_control")
@Data
public class PIDControl implements Serializable {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({
//		@JoinColumn(name = "pin_number", referencedColumnName = "pin_number", nullable = false),
//		@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
//	})
	@JoinColumn(name = "pin_id", nullable = false)
	private Pin pin;
	
	@Column(name = "kp", nullable = false)
	private float kp;
	
	@Column(name = "ki", nullable = false)
	private float ki;
	
	@Column(name = "kd", nullable = false)
	private float kd;
	
	@Column(name = "sample_time", nullable = false)
	private long sampleTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({
//		@JoinColumn(name = "input_pin_number", referencedColumnName = "input_pin_number", nullable = false),
//		@JoinColumn(name = "input_user_id", referencedColumnName = "input_user_id", nullable = false)
//	})
	@JoinColumn(name = "input_pin_id", referencedColumnName = "pin_id", nullable = false)
	private Pin input;

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
		PIDControl other = (PIDControl) obj;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		return result;
	}
	
}
