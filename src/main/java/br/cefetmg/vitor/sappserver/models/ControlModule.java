package br.cefetmg.vitor.sappserver.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.cefetmg.vitor.sappserver.models.listener.ControlModuleListener;
import br.cefetmg.vitor.sappserver.utils.EntityUtils;
import lombok.Data;

@Entity
@Table(name = "control_module")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id")
@EntityListeners(ControlModuleListener.class)
@Data
public class ControlModule extends User implements Serializable {
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id")
	private Place place;
	
	@OneToMany(mappedBy = "controlModule",
//			cascade = CascadeType.PERSIST,
			cascade=CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER)
	private List<Pin> pins;
	
	
	public void mergePins(List<Pin> pins) {
		
		if (this.pins == null)
			this.pins = new ArrayList<Pin>();
		
		EntityUtils.mergeList(this.pins, pins);
	}
	
}
