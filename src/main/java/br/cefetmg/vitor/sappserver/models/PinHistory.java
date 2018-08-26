package br.cefetmg.vitor.sappserver.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "pin_history")
@IdClass(value = PinHistoryId.class)
@Data
public class PinHistory {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "pin_number",
				referencedColumnName = "pin_number",
				nullable = false),
		@JoinColumn(name = "module_control",
				referencedColumnName = "module_control",
				nullable = false)
	})
	private Pin pin;
	
	@Id
	@Column(name = "time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	@Column(name = "value", nullable = false)
	private float value;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PinHistory other = (PinHistory) obj;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	
}
