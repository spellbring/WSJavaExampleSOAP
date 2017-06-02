package cl.com.mecsoft.ns.ws.impl;

import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import cl.com.mecsoft.ns.ws.connection.Connection;
import cl.com.mecsoft.ns.ws.dao.PassengerDAO;
import cl.com.mecsoft.ns.ws.dto.PassengerDTO;
import cl.mecsoft.entities.v1_0.PassengerType;
import cl.mecsoft.ns.ws.v1_0.QueryPassengerRQ;
import cl.mecsoft.ns.ws.v1_0.QueryPassengerRS;



@WebService(
		portName = "QueryPassengerPortType",
		name = "QueryPassenger-1.1",
		targetNamespace = "http://www.mecsoft.cl/ns/ws/" + "v1_0",
		serviceName = "QueryPassenger-1.1",
		wsdlLocation = "WEB-INF/wsdl/QueryPassenger-1.1.wsdl"
)
@SOAPBinding( parameterStyle=ParameterStyle.BARE)
public class QueryPassengerImpl {
	
	@WebMethod
	@WebResult(
		name="QueryPassengerRS", 
		targetNamespace="http://www.mecsoft.cl/ns/ws/" + "1_0", 
		partName="QueryPassengerRS")
	public QueryPassengerRS queryPassenger(
			@WebParam(name="QueryPassengerRQ"
			, targetNamespace = "http://www.mecsoft.cl/ns/ws/" + "v1_0"
			, partName="QueryPassengerRQ")
			QueryPassengerRQ request){
		
		QueryPassengerRS response = new QueryPassengerRS();
		
		
		
		try {
			PassengerDAO passengerDAO = new PassengerDAO();
			for(String passengerTypeService : request.getIdPassenger()){
				for(PassengerDTO passenger : passengerDAO.getPassengerList(passengerTypeService)){
					PassengerType passengerType = new PassengerType();
					passengerType.setAddress(passenger.getAddress());
					passengerType.setEmail(passenger.getEmail());
					passengerType.setIdPassenger(passenger.getIdPassenger());
					passengerType.setName(passenger.getName());
					passengerType.setPhone(passenger.getPhone());
					
					response.getPassenger().add(passengerType);
				}
			}
			Connection.closeConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		
		return response;
	}

}
