package com.imamesta.domain.orders;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "ACTIVE_ORDER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ActiveOrder extends MyOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CHECKED")
	private Boolean checked;
	
	@Column(name = "MY_TAB")
	private Integer myTab;

	public Integer getMyTab() {
		return myTab;
	}

	public void setMyTab(Integer myTab) {
		this.myTab = myTab;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
