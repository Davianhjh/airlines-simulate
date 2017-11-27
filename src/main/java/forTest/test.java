package forTest;

import java.sql.SQLException;

import com.airline.flightManage;
import com.airline.flightManageRes;
import com.airline.passengerFlight;
import com.airline.passengerFlightRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
	public static void main(String[] args) {
		String airlineCode = "MU";
		try {
			flightManage fm = new flightManage(airlineCode);
			flightManageRes res = fm.getFlightList(2);
			ObjectMapper mapper = new ObjectMapper(); 
		    String flights= mapper.writeValueAsString(res); 
		    System.out.println(flights);
		    
		    passengerFlight pf = new passengerFlight("ºúxx","420957197001010000");
		    passengerFlightRes res2 = pf.getTicketList();
		    String tickets = mapper.writeValueAsString(res2);
		    System.out.println(tickets);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("Parse Error");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Mysql connection Error");
		}
	}
}
