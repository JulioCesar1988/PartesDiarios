package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bd.Conector;
import bd.ParametrosConexion;
import models.ChoferBean;


public class ChoferController {
	
	Conector c;
	Connection conexion; 
	
	public int insert(ChoferBean chofer) throws SQLException {
		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call choferes_cargar (?, ?, ?,?)}");
			cs.setInt(1, chofer.getDni());
			cs.setString(2, chofer.getNombre());
			cs.setString(3, chofer.getApellido());
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			return cs.getInt(4);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}
	}
	
	public ChoferBean getPorDni(int dni) throws SQLException{
		try{
			String queryString = "SELECT * FROM Choferes WHERE dni="+dni+" and activado=1;";
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			ResultSet resultados = query.executeQuery();
			resultados.next();
			ChoferBean	chofer = new ChoferBean();
			chofer.setTodo(resultados.getInt(1), resultados.getInt(2), resultados.getString(3), resultados.getString(4));
			return chofer;
		//	return resultados;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
		
	}
	
	public int getPorDni(int dni, ChoferBean Chofer) throws SQLException{
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.choferes_devolver (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, dni);
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			if (resultado.next()) {
				Chofer.setNombre(resultado.getString(1));
				Chofer.setApellido(resultado.getString(2));
			
				return 1;
			}else {
				return 0;	
			}
			
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return -1;
		}
		finally{
			conexion.close();
		}
		
	}

public int modificar(ChoferBean Chofer) throws SQLException {
		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call choferes_modificar (?, ?, ?, ?)}");
			cs.setInt(1, Chofer.getDni());
			cs.setString(2, Chofer.getNombre());
			cs.setString(3, Chofer.getApellido());
			
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			return cs.getInt(4);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}
	}
	public int eliminar(int dni) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call choferes_eliminar (?, ?)}");
			cs.setInt(1, dni);
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

	public String[] getListaDeChoferes() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.chofer_devolver_combobox ()}");
			
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

}
