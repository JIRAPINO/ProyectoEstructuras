package Logica;

public class Fecha {
		private int anio;
		private int mes;
		private int dia;
		
		public Fecha(int anio, int mes, int dia) {
			this.anio = anio;
			this.mes = mes;
			this.dia = dia;
		}
		
		public int getDia() {
			return dia;
		}
		
		public int getMes() {
			return mes;
		}
		
		public int getAnio() {
			return anio;
		}
		
		public void setDia(int dia) {
			this.dia = dia;
		}
		
		public void setMes(int mes) {
			this.mes = mes;
		}
		
		public void setAnio(int anio) {
			this.anio = anio;
		}
}
