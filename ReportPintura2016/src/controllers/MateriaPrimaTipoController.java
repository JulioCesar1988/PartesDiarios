package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import models.MateriaPrimaTipoBean;
import bd.Conector;
import bd.ParametrosConexion;

public class MateriaPrimaTipoController {


	Conector c;
	Connection conexion;
	
	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		return vector;
	}
	
	public int insert (MateriaPrimaTipoBean tipo){
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.[materia_prima_tipo_insertar] (?, ?, ?)}");
			cs.setString(1, tipo.getTipo());
			cs.setString(2, tipo.getCodificacion());
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			int aux = cs.getInt(3);
			return aux; 
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return -1;
		}
	}
	
	
	
	
	public ArrayList<MateriaPrimaTipoBean> devolverTodosLosTipos() throws SQLException {
		
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materia_prima_tipo_devolver_todos ()}");
			cs.registerOutParameter(1, Types.OTHER);
			ResultSet resultado = cs.executeQuery();
			ArrayList<MateriaPrimaTipoBean> lista = new ArrayList<>();
			while (resultado.next()) {
				
				MateriaPrimaTipoBean tipoMateriaPrima = new MateriaPrimaTipoBean();
				tipoMateriaPrima.setTipo(resultado.getString(1));
				lista.add(tipoMateriaPrima);
			}
			

			return lista;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}




	public String[] devolverArregloTodosLosTipos() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materia_prima_tipo_devolver_todos ()}");
			cs.registerOutParameter(1, Types.OTHER);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<>();
			while (resultado.next()) {
								lista.add(resultado.getString(1));
			}
			return pasarAVerctor(lista);
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}

}

