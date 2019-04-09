package models;

import java.sql.SQLException;
import java.util.ArrayList;

import controllers.EdificioController;
import controllers.ObrasController;
import controllers.PaqueteController;

public class PaqueteBean {

	int id_paquete;
	int id_obra;
	int id_edificio;
	

	int numero;
	int numeroRevision;
	int numeroObra;
	String nombreEdificio;
	

	String descripcion;
	String softDiseno;
	String fechaFinalizacion;
	String fechaDespacho;
	

	ArrayList<PiezaBean> piezas;

	
	
	public int getId_obra() {
		return id_obra;
	}
	public void setId_obra(int id_obra) {
		this.id_obra = id_obra;
	}
	public int getId_paquete() {
		return id_paquete;
	}
	public void setId_paquete(int id_paquete) {
		this.id_paquete = id_paquete;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSoftDiseno() {
		return softDiseno;
	}
	public void setSoftDiseno(String softDiseno) {
		this.softDiseno = softDiseno;
	}
	public String getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(String fecha) {
		this.fechaFinalizacion = fecha;
	}
	public int getNumeroRevision() {
		return numeroRevision;
	}
	public void setNumeroRevision(int numeroRevision) {
		this.numeroRevision = numeroRevision;
	}
	public int getNumeroObra() {
		return numeroObra;
	}
	public void setNumeroObra(int numeroObra) {
		this.numeroObra = numeroObra;
	}
	public String getNombreEdificio() {
		return nombreEdificio;
	}
	public void setNombreEdificio(String nombreEdificio) {
		this.nombreEdificio = nombreEdificio;
	}
	public ArrayList<PiezaBean> getPiezas() {
		return piezas;
	}
	public void setPiezas(ArrayList<PiezaBean> piezas) {
		this.piezas = piezas;
	}
	public int getId_edificio() {
		return id_edificio;
	}
	public void setId_edificio(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	public String getFechaDespacho() {
		return fechaDespacho;
	}
	public void setFechaDespacho(String fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}
	
	/*
	 * lo comento por que esta aplicacion no lo uso . 
	public void guardarPaquete() {
		ObrasController oController = new ObrasController();
		EdificioController eController=new EdificioController();
		try {
			setId_obra(oController.getIdObraPorId(getNumeroObra()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			setId_edificio(eController.getIdEdificioPorNombre(getNombreEdificio(),getId_obra()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PaqueteController pController = new PaqueteController();
		if (getId_obra()!=0) {
			try {
				pController.insert(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "Error al asociar paquete con identificador de obra");
		}	
	}
	
	*/
	
	public boolean actualizarPaquete() {
		
		PaqueteController pController = new PaqueteController();
		
			try {
				return pController.update(this); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
			
	}
	
	/*public void guardarPaqueteRevisado() {
		PaqueteController pController = new PaqueteController();
		try {
			pController.insert2(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	

	public void guardarPaqueteModificado() {
		PaqueteController pController = new PaqueteController();
		try {
			pController.guardarRevisionDePaquete(this);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void guardarPaqueteRevisionado() {
		PaqueteController pController = new PaqueteController();
		try {
			pController.guardarRevisionadoDePaquete(this);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void asociarTareasAPaquete() {
		PaqueteController pController = new PaqueteController();
		try {
			pController.insertDeTareas(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarAprobarPaquete() {
		PaqueteController pController = new PaqueteController();
		try {
			pController.aprobarPaquete(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void guardarAprobarPaqueteControlProduccion() {
		PaqueteController pController = new PaqueteController();
		try {
			pController.aprobarPaqueteControlProduccion(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
