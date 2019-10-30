package Logica;

public class Paciente implements Comparable<Paciente>{
	
	private int DNI;
	private Fecha fechaNacimiento;
	private String obraSocial;
	private int codigoUrgencia;
	private char habitacion;
	
	public Paciente(int DNI, Fecha fechaNac, String obSoc, int codUrg, char hab) {
		this.DNI = DNI;
		fechaNacimiento = fechaNac;
		obraSocial = obSoc;
		codigoUrgencia = codUrg;
		habitacion = hab;
	}
	
	public int getDNI() {
		return DNI;
	}
	
	public Fecha getFechaNacimiento() {
		return fechaNacimiento ;
	}
	
	public String obraSocial() {
		return obraSocial;
	}
	
	public int getCodigoUrgencia() {
		return codigoUrgencia;
	}
	
	public char getHabitacion() {
		return habitacion;
	}
	
	public void setDNI(int DNI) {
		this.DNI = DNI;
	}
	
	public void setFechaNacimiento(Fecha fNac) {
		fechaNacimiento = fNac;
	}
	
	public void setObraSocial(String obSoc) {
		obraSocial = obSoc;
	}
	
	public void setCodigoUrgencia(int codUrg) {
		codigoUrgencia = codUrg;
	}
	
	public void setHabitacion(char hab) {
		habitacion = hab;
	}
	
	public String obtenerDatosPaciente() {
		return "Fecha nacimiento: "+fechaNacimiento+" Obra social: "+obraSocial+" Habitacion: "+habitacion;
	}

	@Override
	public int compareTo(Paciente pac) {
		return codigoUrgencia - pac.getCodigoUrgencia();
	}
}
