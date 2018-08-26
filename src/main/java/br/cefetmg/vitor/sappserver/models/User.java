package br.cefetmg.vitor.sappserver.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.CascadeType;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "login", nullable = false, unique = true)
	private String login;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToMany(
			cascade = {
				CascadeType.ALL
			}, 
			fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_has_permission",
			joinColumns = { 
					@JoinColumn(name = "user_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "permission_id")
			}
	)
	private List<Permission> permissions;
	
	@OneToMany(mappedBy = "user",
			fetch = FetchType.LAZY,
			orphanRemoval = true,
			cascade = CascadeType.ALL)
	private List<RFIdCard> rfIdCards;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
