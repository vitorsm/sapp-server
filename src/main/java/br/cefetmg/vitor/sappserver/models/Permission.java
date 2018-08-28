package br.cefetmg.vitor.sappserver.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "permission")
@Data
public class Permission {

	final public static int INSERT_ACCESS_HISTORY = 1;
	final public static int MANAGE_ACCESS_HISTORY = 2;
	final public static int LIST_ACCESS_HISTORY = 3;
	
	final public static int MANAGE_MODULE_CONTROL = 4;
	final public static int LIST_MODULE_CONTROL = 5;
	
	final public static int MANAGE_DOOR_LOCK = 6;
	final public static int LIST_DOOR_LOCK = 7;
	
	final public static int MANAGE_EVENT = 8;
	final public static int LIST_EVENT = 9;
	
	final public static int INSERT_PIN_HISTORY = 10;
	final public static int MANAGE_PIN_HISTORY = 11;
	final public static int LIST_PIN_HISTORY = 12;
	
	final public static int MANAGE_PLACE = 13;
	final public static int LIST_PLACE = 14;
	
	final public static int MANAGE_RFID_CARD = 15;
	final public static int LIST_RFID_CARD = 16;
	
	final public static int MANAGE_USER = 17;
	final public static int LIST_USER = 18;
	
	public Permission() {
		
	}
	
	public Permission(int id) {
		this.id = id;
	}
	
	public Permission(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
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
