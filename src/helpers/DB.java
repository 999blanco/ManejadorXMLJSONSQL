package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.Reserva;

public class DB {

	/**
	 * Creamos la conexion a la base de datos mysql y preguntamos al usuario que
	 * consulta desea ejecutar
	 * @return
	 */
	private static Connection conexion() {
		// Creamos el url del jdbc
		String url = "jdbc:mysql://localhost:3306/salon";		
		String user = "root";
		String pass = "admin";

		try {
			// Cargamos el controlador JDBC y creamos la conexion
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, pass);
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Ejecuta una query INSERT introducida por el usuario
	 * @param query
	 */
	public static void insert(String query) {
		
		// Creamos la conexion
		Connection connection = conexion();

		// Creamos el statement
		try (Statement statement = connection.createStatement()) {
			statement.execute(query); // Ejecutamos la query
			System.out.println("La query se ha ejecutado correctamente!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Cerramos la conexion
		closeConexion(connection);
	}
	
	/**
	 * Ejecuta una query SELECT introducida por el usuario
	 * @param query
	 */
	public static Reserva select(String query) {
		
		// Creamos la conexion
		Connection connection = conexion();
		Reserva reserva = null;
		
		// Creamos el statement
				try (Statement statement = connection.createStatement()) { 
		            // Ejecutamos la consulta y obtenemos el resultSet
		            try (ResultSet resultSet = statement.executeQuery(query)) {
		                // Procesamos los datos del resultSet
		                while (resultSet.next()) {
		                	reserva = new Reserva(resultSet.getString("nombre"), resultSet.getString("fechaEvento"), resultSet.getString("tipo"), resultSet.getString("asistentes"), resultSet.getString("tipoCocina"), resultSet.getString("numeroJornadas"), resultSet.getString("habitaciones"), resultSet.getString("tipoMesa"), resultSet.getString("comensalesMesa"));
		                	break;
		                }
		    			System.out.println("Reserva leída de forma satisfactoria");
		            }
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		// Cerramos la conexion
		closeConexion(connection);
		
		return reserva;
	}

	/**
	 * Metodo que cierra la conexion recibida por parametro
	 * @param conexion
	 */
	private static void closeConexion(Connection conexion) {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
