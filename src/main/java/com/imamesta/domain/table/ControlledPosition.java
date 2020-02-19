package com.imamesta.domain.table;

public class ControlledPosition {
	
	private Double x;
	
	private Double y;
	
	public ControlledPosition() {
		
	}

	public ControlledPosition(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
}
