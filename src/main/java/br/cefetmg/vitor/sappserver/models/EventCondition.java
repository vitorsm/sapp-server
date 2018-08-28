package br.cefetmg.vitor.sappserver.models;

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

import lombok.Data;

@Entity
@Table(name = "event_condition")
@Data
public class EventCondition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_condition_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operation_type_id", nullable = false)
	private OperationType operationType;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({
//		@JoinColumn(name = "input_pin_number",
//				referencedColumnName = "input_pin_number",
//				nullable = false),
//		@JoinColumn(name = "input_module_control",
//				referencedColumnName = "input_module_control",
//				nullable = false)
//	})
	@JoinColumn(name = "input_pin_id", referencedColumnName = "pin_id", nullable = false)
	private Pin input;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;
	
	@Column(name = "value", nullable = false)
	private float value;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCondition other = (EventCondition) obj;
		if (id != other.id)
			return false;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		return result;
	}
	
}
