package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import main.Reserva;

public class Manejador {

	/**
	 * Leer un fichero JSON que contenga una reserva
	 * 
	 * @param fichero
	 * @return Devuelve una instancia del objeto Reserva
	 */
	public static Reserva leerJSON(File fichero) {
		FileReader fr = null;
		BufferedReader br = null;
		Reserva reserva;

		try {
			fr = new FileReader(fichero);
			br = new BufferedReader(fr);

			String linea;
			String etiqueta = null;
			String[] etiquetas = { "nombre", "fechaEvento", "tipo", "asistentes", "tipoCocina", "numeroJornadas",
					"habitaciones", "tipoMesa", "comensalesMesa" };
			String[] valores = new String[etiquetas.length];

			while ((linea = br.readLine()) != null) {
				for (int i = 0; i < etiquetas.length; i++) {
					if (linea.contains("\"" + etiquetas[i] + "\"")) {
						String valor = linea
								.substring(linea.indexOf("\"" + etiquetas[i] + "\"") + etiquetas[i].length() + 4);
						valor = valor.replaceAll("\"", "");
						valor = valor.replaceAll(",", "");
						valores[i] = valor;
					}
				}
			}

			fr.close();
			br.close();
			System.out.println("Fichero leído de forma satisfactoria");
			return new Reserva(valores[0], valores[1], valores[2], valores[3], valores[4], valores[5], valores[6],
					valores[7], valores[8]);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("No se ha podido leer el fichero introducido!");
			return null;
		}
	}

	/**
	 * Leer un fichero XML que contenga una reserva
	 * 
	 * @param fichero
	 * @return Devuelve una instancia del objeto Reserva
	 */
	public static Reserva leerXML(File fichero) {
		FileReader fr = null;
		BufferedReader br = null;
		Reserva reserva;

		try {
			fr = new FileReader(fichero);
			br = new BufferedReader(fr);

			String linea;
			String[] etiquetas = { "nombre", "fechaEvento", "tipo", "asistentes", "tipoCocina", "numeroJornadas",
					"habitaciones", "tipoMesa", "comensalesMesa" };
			String[] valores = new String[etiquetas.length];

			while ((linea = br.readLine()) != null) {
				for (int i = 0; i < etiquetas.length; i++) {
					if (linea.contains("<" + etiquetas[i] + ">") && linea.contains("</" + etiquetas[i] + ">")) {
						String valor = linea.substring(
								linea.indexOf("<" + etiquetas[i] + ">") + etiquetas[i].length() + 2,
								linea.indexOf("</" + etiquetas[i] + ">"));
						valores[i] = valor;
					}
				}
			}

			fr.close();
			br.close();

			System.out.println("Fichero leído de forma satisfactoria");
			return new Reserva(valores[0], valores[1], valores[2], valores[3], valores[4], valores[5], valores[6],
					valores[7], valores[8]);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("No se ha podido leer el fichero introducido!");
			return null;
		}
	}
	
	/**
	 * Leer reservas de la tabla reservas de la database salon
	 * 
	 * @param reserva
	 */
	public static Reserva leerSQL() {
		Reserva reserva = DB.select("SELECT * FROM reservas");
		return reserva;
	}

	/**
	 * Escribir una reserva en un fichero json
	 * 
	 * @param fichero
	 * @param reserva
	 */
	public static void escribirJSON(File fichero, Reserva reserva) {
		try {
			FileWriter fw = new FileWriter(fichero, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("{\n\"reserva\": {\n");
			bw.write("\"nombre\": \"" + reserva.getNombre() + "\",\n");
			bw.write("\"fechaEvento\": \"" + reserva.getFechaEvento() + "\",\n");
			bw.write("\"tipo\": \"" + reserva.getTipo() + "\",\n");
			bw.write("\"asistentes\": \"" + reserva.getAsistentes() + "\",\n");
			bw.write("\"tipoCocina\": \"" + reserva.getTipoCocina() + "\",\n");
			bw.write("\"numeroJornadas\": \"" + reserva.getNumeroJornadas() + "\",\n");
			bw.write("\"habitaciones\": \"" + reserva.getHabitaciones() + "\",\n");
			bw.write("\"tipoMesa\": \"" + reserva.getTipoMesa() + "\",\n");
			bw.write("\"comensalesMesa\": \"" + reserva.getComensalesMesa() + "\"\n");
			bw.write("}\n}");

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Escribir una reserva en un fichero xml
	 * 
	 * @param fichero
	 * @param reserva
	 */
	public static void escribirXML(File fichero, Reserva reserva) {
		try {
			FileWriter fw = new FileWriter(fichero, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("<reserva>\n");
			bw.write("<nombre>" + reserva.getNombre() + "</nombre>\n");
			bw.write("<fechaEvento>" + reserva.getFechaEvento() + "</fechaEvento>\n");
			bw.write("<tipo>" + reserva.getTipo() + "</tipo>\n");
			bw.write("<asistentes>" + reserva.getAsistentes() + "</asistentes>\n");
			bw.write("<tipoCocina>" + reserva.getTipoCocina() + "</tipoCocina>\n");
			bw.write("<numeroJornadas>" + reserva.getNumeroJornadas() + "</numeroJornadas>\n");
			bw.write("<habitaciones>" + reserva.getHabitaciones() + "</habitaciones>\n");
			bw.write("<tipoMesa>" + reserva.getTipoMesa() + "</tipoMesa>\n");
			bw.write("<comensalesMesa>" + reserva.getComensalesMesa() + "</comensalesMesa>\n");
			bw.write("</reserva>\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insertar una reserva en la tabla reservas de la base de datos
	 * 
	 * @param reserva
	 */
	public static void escribirSQL(Reserva reserva) {
		DB.insert(
				"INSERT INTO reservas (nombre, fechaEvento, tipo, asistentes, tipoCocina, numeroJornadas, habitaciones, tipoMesa, comensalesMesa) VALUES ('"
						+ reserva.getNombre() + "', '" + reserva.getFechaEvento() + "', '" + reserva.getTipo() + "','"
						+ reserva.getAsistentes() + "', '" + reserva.getTipoCocina() + "', '"
						+ reserva.getNumeroJornadas() + "', '" + reserva.getHabitaciones() + "', '"
						+ reserva.getTipoMesa() + "', '" + reserva.getComensalesMesa() + "');");
	}
}