package controllers;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import models.AreaBean;
import bd.Conector;
import bd.ParametrosConexion;



public class AreaController {

	Conector c;
	Connection conexion; 
	
	
	
	public String[] getListaDeAreas() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.area_devolver_combobox ()}");
			cs.registerOutParameter(1, Types.OTHER);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1));
			}
			String vector [] = pasarAVerctor(lista);
			return vector;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}
	

	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		return vector;
	}


	public int insert(AreaBean area) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call area_cargar (?, ?)}");
			cs.setString(1, area.getNombre());
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			return cs.getInt(2);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}
	}
	
	public int modificar(AreaBean area,String arCom) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call area_modificar (?, ?, ?)}");
			cs.setString(1, area.getNombre());
			cs.setString(2, arCom);
			
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			return cs.getInt(3);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}
	}
	
	public int eliminar(String area) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call area_eliminar (?, ?)}");
			cs.setString(1, area);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			return cs.getInt(2);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}
	}
}
