package com.alten.hotel.booking.domain.dto;

public enum PaymentMode {
	
	DEBIT("DEBIT"),
	CREDIT("CREDIT"),
	CASH("CASH");
	
	private String paymentmode;
	
	PaymentMode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	
	@Override
    public String toString() {
        return paymentmode;
    }
}
