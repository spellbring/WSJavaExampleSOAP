package cl.com.mecsoft.ns.ws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cl.com.mecsoft.ns.ws.connection.Connection;
import cl.com.mecsoft.ns.ws.dto.PassengerDTO;

public class PassengerDAO extends Connection {
	
	public PassengerDAO() throws ClassNotFoundException, SQLException{
		super();
	}
	
	public List<PassengerDTO> getPassengerList(String id) throws SQLException {
		List<PassengerDTO> listPassenger = new ArrayList<>();
		String sql = "SELECT idpassenger, name, phone, email, addres FROM crm.passenger where id = ?";
		java.sql.Connection con = this.getConnection();
		
		PreparedStatement query = con.prepareStatement(sql); 
		
		query.setString(1, id);
		
		ResultSet result = query.executeQuery();
		
		while(result.next()){
			PassengerDTO passenger = new PassengerDTO();
			passenger.setIdPassenger(result.getString(1));
			passenger.setName(result.getString(2));
			passenger.setPhone(result.getString(3));
			passenger.setEmail(result.getString(4));
			passenger.setAddress(result.getString(5));
			listPassenger.add(passenger);
		}
		
		return listPassenger;
	}
	

}
