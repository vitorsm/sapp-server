package br.cefetmg.vitor.sappserver.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "pin")
@IdClass(value = PinId.class)
@Data
public class Pin {

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private ControlModule controlModule;
	
	@Id
	@Column(name = "pin_number")
	private int number;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "history_sample_time", nullable = false)
	private long historySampleTime;
	
	@Column(name = "is_powered", nullable = false)
	private boolean isPowered;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pin_type_id", nullable = false)
	private PinType pinType;
	
	@Column(name = "set_point", nullable = false)
	private float setPoint;
	
	@OneToMany(mappedBy = "pin",
			fetch = FetchType.LAZY,
			orphanRemoval = true,
			cascade = CascadeType.ALL)
	private List<PowerCondition> powerConditions;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid_control_id")
	private PIDControl pidControl;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pin other = (Pin) obj;
		if (controlModule == null) {
			if (other.controlModule != null)
				return false;
		} else if (!controlModule.equals(other.controlModule))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controlModule == null) ? 0 : controlModule.hashCode());
		result = prime * result + number;
		return result;
	}
	
}
