package br.cefetmg.vitor.sappserver.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "door_lock")
@Data
public class DoorLock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "door_lock_id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "keep_open", nullable = false)
	private boolean keepOpen;
	
	@ManyToMany(
			cascade = {
				CascadeType.ALL
			}, 
			fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_has_door_lock_permission",
			joinColumns = { 
					@JoinColumn(name = "door_lock_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "user_id")
			}
	)
	private List<User> usersPermission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id", nullable = false)
	private Place place;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoorLock other = (DoorLock) obj;
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
