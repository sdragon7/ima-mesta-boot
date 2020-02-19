package com.imamesta.domain.orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ACTIVE_ORDER")
public class ActiveOrder extends MyOrder {

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
