package com.airline;

import java.util.ArrayList;

public class passengerFlightRes {
	private String flag;
	private int errCode;
	private String errMsg;
	private ArrayList<ticket> ticketList;
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public int getErrCode() {
		return errCode;
	}
	
	public void setErrCode(int code) {
		this.errCode = code;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	
	public void setErrMsg(String msg) {
		this.errMsg = msg;
	}
	
	public ArrayList<ticket> getTickets(){
		return ticketList;
	}
	
	public void setTickets(ArrayList<ticket> tickets) {
		this.ticketList = new ArrayList<ticket>(tickets);
	}
}
