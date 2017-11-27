package com.airline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.HiKariCP.HiKariCPHandler;

public class flightManage {
	private Connection conn;
	private String airlineCode;
	private SimpleDateFormat sdf;
	public flightManage(String airlineCode) throws SQLException {
		this.conn = HiKariCPHandler.getConn(); 
		this.airlineCode = airlineCode;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public flightManageRes getFlightList(int dayLap) {
		PreparedStatement pst = null;
    	ResultSet ret = null;
    	flightManageRes res = new flightManageRes();
    	ArrayList<flight> flights = new ArrayList<flight>();
    	
		Date now = new Date();
		String start = sdf.format(now);
		Calendar c = Calendar.getInstance();  
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, dayLap+1);
		String end = sdf.format(c.getTime());
		String sql = "SELECT flightNo, flightDate, dptAirport, arvAirport, depTime, arrTime FROM flightManage WHERE airlineCode=? "
				+ "AND depTime BETWEEN ? AND ?;";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, airlineCode);
			pst.setString(2, start);
			pst.setString(3, end);
			ret = pst.executeQuery();
			while(ret.next()) {
				flight list = new flight();
				list.setFlightNo(ret.getString(1));
				list.setFlightDate(ret.getString(2).substring(0,10));
				list.setDptAirport(ret.getString(3));
				list.setArvAirport(ret.getString(4));
				list.setDepTime(ret.getString(5).substring(0,19));
				list.setArrTime(ret.getString(6).substring(0,19));
				//System.out.println(list.getDepTime());
				//System.out.println(list.getArrTime());
				flights.add(list);
			}
			res.setFlights(flights);
			res.setFlag("SUCCESS");
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			res.setFlag("FAIL");
			res.setErrCode(2000);
			res.setErrMsg("Mysql server exception error");
			return res;
		}
	}
}
