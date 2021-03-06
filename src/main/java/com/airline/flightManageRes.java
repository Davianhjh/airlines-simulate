package com.airline;

import java.util.ArrayList;

public class flightManageRes {
	private String flag;
	private int errCode;
	private String errMsg;
	private ArrayList<flight> flightList;
	
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
	
	public ArrayList<flight> getFlights(){
		return flightList;
	}
	
	public void setFlights(ArrayList<flight> flights) {
		this.flightList = new ArrayList<flight>(flights);
	}
}
