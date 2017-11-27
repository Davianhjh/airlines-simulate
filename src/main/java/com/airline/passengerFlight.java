package com.airline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.HiKariCP.HiKariCPHandler;

public class passengerFlight {
	private Connection conn;
	private String passengerID;
	private String passengerName;
	private SimpleDateFormat sdf;
	public passengerFlight(String name, String id) throws SQLException {
		this.conn = HiKariCPHandler.getConn();
		this.passengerID = id;
		this.passengerName = name;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public passengerFlightRes getTicketList() {
		PreparedStatement pst = null;
    	ResultSet ret = null;
    	passengerFlightRes res = new passengerFlightRes();
    	ArrayList<ticket> tickets = new ArrayList<ticket>();
    	
    	Date now = new Date();
		String start = sdf.format(now);
		String sql = "SELECT flightNo,flightDate,ticketNo,carbinClass,dptAirport,arvAirport,depTime,arrTime FROM passengerFlight "
				+ "WHERE passengerName=? and certificateNo=? and depTime>?;";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, passengerName);
			pst.setString(2, passengerID);
			pst.setString(3, start);
			ret = pst.executeQuery();
			while(ret.next()) {
				ticket list= new ticket();
				list.setPassengerName(passengerName);
				list.setFlightNo(ret.getString(1));
				list.setFlightDate(ret.getString(2).substring(0,10));
				list.setTicketNo(ret.getString(3));
				list.setCertificateNo(passengerID);
				list.setCarbinClass(ret.getString(4));
				list.setDptAirport(ret.getString(5));
				list.setArvAirport(ret.getString(6));
				list.setDepTime(ret.getString(7).substring(0,19));
				list.setArrTime(ret.getString(8).substring(0,19));
				tickets.add(list);
			}
			res.setTickets(tickets);
			res.setFlag("SUCCESS");
			return res;
		} catch(SQLException e) {
			e.printStackTrace();
			res.setFlag("FAIL");
			res.setErrCode(2000);
			res.setErrMsg("Mysql server exception error");
			return res;
		}
	}

}
