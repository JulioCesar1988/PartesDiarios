package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import bd.Conector;
import bd.ParametrosConexion;
import models.DetalleparteBean;
import models.MasterparteBean;

public class DetalleparteController {

	Conector c;
	Connection conexion; 
	  	
	public ArrayList<Vector<Object>> getDetalle( int id_masterparte ) throws Exception {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			System.out.println(id_masterparte);
			CallableStatement cs = conexion.prepareCall("{ call parte_piezas_detalle_select(?)}");
			cs.setInt(1, id_masterparte);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getBoolean(1)); // esPieza .
				vector.add(1, this.getDateFormat(resultado.getString(2)));  // desde
				vector.add(2, this.getDateFormat(resultado.getString(3)));  // hasta
				vector.add(3, resultado.getString(4));  // id_obra
				vector.add(4, resultado.getString(5));  // id_paquete
				vector.add(5, resultado.getString(6));  // posicion
				vector.add(6, resultado.getString(7));  // cantidad
				vector.add(7, resultado.getBoolean(8));  // terminado
				vector.add(8, resultado.getString(9));  // eliminado 
				vector.add(9, resultado.getString(10)); // id_masterParte  
				lista.add(vector);
			}
			return lista;
		}catch (SQLException e) {
			throw new Exception("No se ha podiddo cargar la lista de items del Parte");
		} finally {
			conexion.close();
		}
	}

	
	private String getDateFormat(String value) {
		return value.split(":")[0] + ":" + value.split(":")[1];
	}
	
	
	
	
	
	
	
	
	

}
