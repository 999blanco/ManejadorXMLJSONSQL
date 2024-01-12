package main;

public class Reserva {

	String nombre;
	String fechaEvento;
	String tipo;
	String asistentes;
	String tipoCocina;
	String numeroJornadas;
	String habitaciones;
	String tipoMesa;
	String comensalesMesa;

	public Reserva(String nombre, String fechaEvento, String tipo, String asistentes, String tipoCocina,
			String numeroJornadas, String habitaciones, String tipoMesa, String comensalesMesa) {
		this.nombre = nombre;
		this.fechaEvento = fechaEvento;
		this.tipo = tipo;
		this.asistentes = asistentes;
		this.tipoCocina = tipoCocina;
		this.numeroJornadas = numeroJornadas;
		this.habitaciones = habitaciones;
		this.tipoMesa = tipoMesa;
		this.comensalesMesa = comensalesMesa;
	}

	public String getNombre() {
		return nombre;
	}

	public String getFechaEvento() {
		return fechaEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public String getAsistentes() {
		return asistentes;
	}

	public String getTipoCocina() {
		return tipoCocina;
	}

	public String getNumeroJornadas() {
		return numeroJornadas;
	}

	public String getHabitaciones() {
		return habitaciones;
	}

	public String getTipoMesa() {
		return tipoMesa;
	}

	public String getComensalesMesa() {
		return comensalesMesa;
	}

//	public static void leerSQL() {
//		String db = "db_david";
//		String user = "root";
//		String pass = "admin";
//		
//		Connection connection = DB.conexion(db, user, pass);
//		DB.select(connection, "SELECT * FROM reservas;");
//	}
}
