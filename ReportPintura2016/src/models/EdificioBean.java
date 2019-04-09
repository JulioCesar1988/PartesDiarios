package models;
public class EdificioBean {
String nombre;
String observaciones;

// Agregado
String observacion_tecnica;


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
	// Agregado
	public String getObservacion_tecnica() {
		
		return observacion_tecnica;
	
	}
	public void setObservacion_tecnica(String observaciones) {
		
		this.observaciones = observacion_tecnica;
	
	}
	


}
