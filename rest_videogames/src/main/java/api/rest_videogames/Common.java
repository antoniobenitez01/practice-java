package api.rest_videogames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Common 
{
	//CONNECTION SQL - Intenta conexión SQL con parámetros devolviendo true si se conecta correctamente
	public static Connection connectionSQL(String url, String user, String password)
	{
		System.out.println("Trying SQL Connection ...");
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("SQL Connection Succesful.");
		}catch(SQLException ex)
		{
			System.out.println("SQL Exception: " + ex.getMessage());
		}
		return conn;
	}
}
