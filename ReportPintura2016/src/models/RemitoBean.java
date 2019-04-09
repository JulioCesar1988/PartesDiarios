package models;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import controllers.RemitoController;
import session.Session;

public class RemitoBean {

	int idRemito;
	int idEmpleado;
	int idObra;
	int idCamion;
	int idChofer;
	Date fecha;
	ArrayList<Vector<Object>> listaDePiezasTipoCantidadMasCodigo; 
	String patenteSemi;
	int numero;
	
	
	
	public ArrayList<Vector<Object>> getlistaDePiezasTipoCantidadMasCodigo() {
		return listaDePiezasTipoCantidadMasCodigo;
	}
	public String getPatenteSemi() {
		return patenteSemi;
	}
	public void setPatenteSemi(String patenteSemi) {
		this.patenteSemi = patenteSemi;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setlistaDePiezasTipoCantidadMasCodigo(ArrayList<Vector<Object>> listaDePiezasTipoCantidadMasCodigo) {
		this.listaDePiezasTipoCantidadMasCodigo = listaDePiezasTipoCantidadMasCodigo;
	}
	public int getIdRemito() {
		return idRemito;
	}
	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	public int getIdCamion() {
		return idCamion;
	}
	public void setIdCamion(int idCamion) {
		this.idCamion = idCamion;
	}
	public int getIdChofer() {
		return idChofer;
	}
	public void setIdChofer(int idChofer) {
		this.idChofer = idChofer;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setTodo(int idRemito, int idEmpleado, int idObra, int idCamion, int idChofer, Date fecha) {
	
		this.idRemito = idRemito;
		this.idEmpleado = idEmpleado;
		this.idObra = idObra;
		this.idCamion = idCamion;
		this.idChofer = idChofer;
		this.fecha = fecha;
	}
	
	public void guardar() {
		RemitoController rController = new RemitoController(); 
		try {
			rController.crearRemito(this,Session.getNombreUsuario());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void guardar2() {
		RemitoController rController = new RemitoController(); 
		try {
			rController.crearRemitoWarehouse(this,Session.getNombreUsuario());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
