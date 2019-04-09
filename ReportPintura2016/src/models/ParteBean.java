package models;

import java.util.ArrayList;

public class ParteBean {
	
	int idParte;
	String fecha; 	// es cargado por el usuario DD-MM-AAAA. 
	String id_empleado; // ID_EMPLEADO
	String id_sector; 
    String id_tarea;
    
    
    String id_obra; // id de la obra para la cual esta haciendo la tarea . 
	String id_paquete; 		// lista de produccion . 
	String desde; 	// fecha de inicio de la tarea HH:MM . 
	String hasta; 	// fecha de finalizacion de la tareaHH:MM .  
	boolean estado;
	String posicion; //posicion de la pieza 
	String cantidad; //  x - n   ver si podemos aplicar alguna logica . 

	
	public int getIdParte() {
		return idParte;
	}
	public void setIdParte(int idParte) {
		this.idParte = idParte;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}
	public String getId_sector() {
		return id_sector;
	}
	public void setId_sector(String id_sector) {
		this.id_sector = id_sector;
	}
	public String getId_tarea() {
		return id_tarea;
	}
	public void setId_tarea(String id_tarea) {
		this.id_tarea = id_tarea;
	}
	public String getId_obra() {
		return id_obra;
	}
	public void setId_obra(String id_obra) {
		this.id_obra = id_obra;
	}
	public String getId_paquete() {
		return id_paquete;
	}
	public void setId_paquete(String id_paquete) {
		this.id_paquete = id_paquete;
	}
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	

	
	

}
	
	