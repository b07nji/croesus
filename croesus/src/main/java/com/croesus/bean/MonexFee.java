package com.croesus.bean;

public class MonexFee extends FeeObject{
	
	private String feeForPc;
	private String feeForPhone;
	
	public String getFeeForPc() { return feeForPc; }
	public String getFeeForPhone() { return feeForPhone; }
	
	public void setFeeForPc(String feeForPc) {
		this.feeForPc = feeForPc;
	}
	
	public void setFeeForPhone(String feeForPhone) {
		this.feeForPhone = feeForPhone;
	}

}
