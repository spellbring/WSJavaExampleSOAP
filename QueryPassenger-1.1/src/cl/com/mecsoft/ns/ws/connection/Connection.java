package cl.com.mecsoft.ns.ws.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
	private static String server = "jdbc:mysql://127.0.0.1:3306";
	private static String user = "root";
	private static String pass = "12345";
	private static String driver = "com.mysql.jdbc.Driver";
	private static java.sql.Connection connection;
	
	public Connection() throws ClassNotFoundException, SQLException{
		
		Class.forName(driver);
		connection = DriverManager.getConnection(server, user, pass);
		System.out.println("Conectado");
	
	}
	public java.sql.Connection getConnection(){
		return connection;
	}
	public static void closeConnection() throws SQLException {
		connection.close();
	}
}
