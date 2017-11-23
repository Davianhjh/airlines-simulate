package forTest;

import java.sql.SQLException;

import com.airline.flightManage;
import com.airline.flightManageRes;

public class test {
	public static void main(String[] args) {
		String airlineCode = "MU";
		try {
			flightManage fm = new flightManage(airlineCode);
			flightManageRes res = fm.getFlightList(2);
			System.out.println("SUCCESS");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}
	}
}
