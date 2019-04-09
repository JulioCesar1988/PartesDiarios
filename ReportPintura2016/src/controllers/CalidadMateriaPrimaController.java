package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bd.Conector;
import bd.ParametrosConexion;
import models.CalidadMateriaPrimaBean;
import models.EmpresaProveedorBean;

public class CalidadMateriaPrimaController {


	Conector c;
	Connection conexion;
	
	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		return vector;
	}
	
	public int insert (CalidadMateriaPrimaBean calidad){
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.[calidad_materia_prima_insertar] (?, ?)}");
			cs.setString(1, calidad.getNombre()  );
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			int aux = cs.getInt(2);
			return aux; 
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return -1;
		}
	}
	
	
	
	
	public String[] devolverArregloTodasLasCalidades() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.calidad_materia_prima_devolver_para_combobox ()}");
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

