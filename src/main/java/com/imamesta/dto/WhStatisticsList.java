package com.imamesta.dto;

import java.io.Serializable;

public class WhStatisticsList implements Serializable {
	
	private String ingredientName;
	
	private double nabavka;
	
	private double otpis;
	
	private double potrosnja;
	
	public WhStatisticsList(String ingredientName, double nabavka, double otpis, double potrosnja) {
		this.ingredientName = ingredientName;
		this.nabavka = nabavka;
		this.otpis = otpis;
		this.potrosnja = potrosnja;
	}
	
	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public double getNabavka() {
		return nabavka;
	}

	public void setNabavka(double nabavka) {
		this.nabavka = nabavka;
	}

	public double getOtpis() {
		return otpis;
	}

	public void setOtpis(double otpis) {
		this.otpis = otpis;
	}

	public double getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(double potrosnja) {
		this.potrosnja = potrosnja;
	}
}
