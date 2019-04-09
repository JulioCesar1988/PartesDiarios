package models;

import java.sql.SQLException;

import controllers.ParteDiarioController;



public class ParteDiarioTareaBean {
	private int legajoElmpleado; 
	private String empresaEmpleado;
	private String tareaCodigo;
	private int numeroObra;
	private String horaInicio;
	private String horaFin;
	private String fecha;
	
	
	public int getLegajoElmpleado() {
		return legajoElmpleado;
	}
	public void setLegajoElmpleado(int legajoElmpleado) {
		this.legajoElmpleado = legajoElmpleado;
	}
	public String getEmpresaEmpleado() {
		return empresaEmpleado;
	}
	public void setEmpresaEmpleado(String empresaEmpleado) {
		this.empresaEmpleado = empresaEmpleado;
	}
	public String getTareaCodigo() {
		return tareaCodigo;
	}
	public void setTareaCodigo(String tareaCodigo) {
		this.tareaCodigo = tareaCodigo;
	}
	public int getNumeroObra() {
		return numeroObra;
	}
	public void setNumeroObra(int numeroObra) {
		this.numeroObra = numeroObra;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int guardar() {
		ParteDiarioController pController = new ParteDiarioController();
		try {
			return pController.insert(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} 
	}

	
	
	
}
