package Logica;

import java.util.Comparator;

import TDAColaCP.Heap;

public class Habitacion {
	
	private Heap<Integer, Paciente> pacientes;
	
	
	public Habitacion() {
		Comparator<Paciente> compPacientes = new Comparator<Paciente>();
		pacientes = new Heap<Integer, Paciente>(compPacientes)
				
				;
	}
}
