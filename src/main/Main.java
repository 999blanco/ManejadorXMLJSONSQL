package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import helpers.Manejador;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Byte respuestaLectura = 0;

		System.out.println("Bienvenido!");
		System.out.println("¿Como deseas obtener los datos? (1 | 2 | 3)");
		System.out.println("1. Obtener de un fichero XML");
		System.out.println("2. Obtener de un fichero JSON");
		System.out.println("3. Obtener registros de la base de datos");

		if (scanner.hasNextByte()) {
			respuestaLectura = scanner.nextByte();
			if (respuestaLectura > 0 && respuestaLectura <= 3) {
				Reserva reserva = null;
				if (respuestaLectura == 1 || respuestaLectura == 2) {
					String ficheroLectura = null;
					System.out.println("Introduce el nombre del fichero a leer (sin su extension)");
					ficheroLectura = scanner.next();
					if (respuestaLectura == 1) {
						reserva = Manejador.leerXML(new File(ficheroLectura + ".xml"));
					} else {
						reserva = Manejador.leerJSON(new File(ficheroLectura + ".json"));
					}
				} else {
					reserva = Manejador.leerSQL();
				}
				
				if (reserva != null) {
					Byte respuestaEscritura = 0;

					System.out.println("¿Como deseas obtener los datos? (1 | 2 | 3)");
					System.out.println("1. Escribir en un fichero XML");
					System.out.println("2. Escribir en un fichero JSON");
					System.out.println("3. Escribir en una tabla de base de datos");
					
					if (scanner.hasNextByte()) {
						respuestaEscritura = scanner.nextByte();
						if (respuestaEscritura > 0 && respuestaEscritura <= 3) {
							if (respuestaEscritura == 1 || respuestaEscritura == 2) {
								System.out.println("Introduce el nombre del fichero a crear con los datos obtenidos (sin su extension)");
								if (respuestaEscritura == 1) {
									File ficheroEscritura = new File(scanner.next() + ".xml");
									if (ficheroEscritura.createNewFile()) {
										System.out.println("Fichero creado: " + ficheroEscritura.getName());
									} else {
										System.out.println("Fichero sobreescrito: " + ficheroEscritura.getName());
									}
									Manejador.escribirXML(ficheroEscritura, reserva);
								} else {
									File ficheroEscritura = new File(scanner.next() + ".json");
									if (ficheroEscritura.createNewFile()) {
										System.out.println("Fichero creado: " + ficheroEscritura.getName());
									} else {
										System.out.println("Fichero sobreescrito: " + ficheroEscritura.getName());
									}
									Manejador.escribirJSON(ficheroEscritura, reserva);
								}
							} else {
								Manejador.escribirSQL(reserva);
							}
						} else {
							System.err.println("¡Debes introducir 1, 2 o 3 como respuesta!");
						}
					} else {
						System.err.println("¡Debes introducir 1, 2 o 3 como respuesta!");
					}
				}
			} else {
				System.err.println("¡Debes introducir 1, 2 o 3 como respuesta!");
			}
		} else {
			System.err.println("¡Debes introducir 1, 2 o 3 como respuesta!");
		}
	}

}
