package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bd.Conector;
import bd.ParametrosConexion;

public class EstadoController {
	
	Conector c;
	Connection conexion;
	public String[] getListaDeEstado() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.estado_devolver_combobox ()}");
			
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
