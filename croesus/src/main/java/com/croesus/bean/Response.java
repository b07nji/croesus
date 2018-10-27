package com.croesus.bean;

import java.util.List;

public class Response {
	
	private List<MatsuiFee> matsuiFee;
	private List<SBIstandardFee> sbiFee;
	private List<GMOFee> gmoFee;
	private List<RakutenFee> rakutenFee;
	private List<MonexFee> monexFee;
	
	public List<MatsuiFee> getMatsuiFee() {
		return matsuiFee;
	}
	public void setMatsuiFee(List<MatsuiFee> matsuiFee) {
		this.matsuiFee = matsuiFee;
	}
	public List<SBIstandardFee> getSbiFee() {
		return sbiFee;
	}
	public void setSbiFee(List<SBIstandardFee> sbiFee) {
		this.sbiFee = sbiFee;
	}
	public List<GMOFee> getGmoFee() {
		return gmoFee;
	}
	public void setGmoFee(List<GMOFee> gmoFee) {
		this.gmoFee = gmoFee;
	}
	public List<RakutenFee> getRakutenFee() {
		return rakutenFee;
	}
	public void setRakutenFee(List<RakutenFee> rakutenFee) {
		this.rakutenFee = rakutenFee;
	}
	public List<MonexFee> getMonexFee() {
		return monexFee;
	}
	public void setMonexFee(List<MonexFee> monexFee) {
		this.monexFee = monexFee;
	}

}
