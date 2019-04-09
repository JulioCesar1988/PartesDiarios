package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Vector;
import bd.Conector;
import bd.ParametrosConexion;
import models.MaterialBean;

public class MaterialController {
	
	Conector c;
	Connection conexion;

	public String[] getListaDeTipo() throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_tipo ()}");
			
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




	public int insert(MaterialBean mat) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call materiales_cargar (?, ?, ?, ?, ?, ?, ?,?)}");
			cs.setString(1, mat.getCodigo());
			cs.setString(2, mat.getTipo());
			cs.setFloat(3, mat.getPesoEspecifico());
			cs.setFloat(4, mat.getEspesor());
			cs.setString(5, mat.getCalidad());
			cs.setString(6, mat.getUnidadPeso());
			cs.setInt(7, mat.getAncho());
			cs.registerOutParameter(8, Types.INTEGER);
			cs.execute();
			return cs.getInt(8);
		}catch (Exception e) {
		    e.printStackTrace();
		    return -1;
		} finally {
			conexion.close();
		}
	}
	
	
	public ArrayList<MaterialBean> getTodos() throws SQLException{
		try{
			String queryString = "SELECT * FROM Materiales;";
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			ResultSet resultados = query.executeQuery();
			ArrayList<MaterialBean> materiales = new ArrayList<MaterialBean>();
			while(resultados.next()){
				MaterialBean material = new MaterialBean();
				material.setTodo(resultados.getInt(1), resultados.getString(2), resultados.getFloat(3), resultados.getFloat(4), resultados.getString(5), resultados.getString(6));
				materiales.add(material);
				
			}
			return materiales;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
		
	}




	public String[] getListaDeEspesor(String tipo) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_espesor (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tipo);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(Float.toString(resultado.getFloat(1)));
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




	public String[] getListaDeCalidad(String tipo, String espesor) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_calidad (?, ?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tipo);
			cs.setString(3, espesor);
	
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




	public String[] getListaDePeso(String tipo, String espesor, String calidad) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_peso (?, ?, ?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tipo);
			cs.setString(3, espesor);
			cs.setString(4, calidad);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(Float.toString(resultado.getFloat(1)));
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
	
	public String[] getListaDeAncho(String tipo, String espesor, String calidad) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_ancho (?, ?, ?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tipo);
			cs.setString(3, espesor);
			cs.setString(4, calidad);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(Integer.toString(resultado.getInt(1)));
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




	public String[] getListaDePesoEspesorCalidad(String tipo) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_pesoespesorcalidad (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tipo);
			
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1)+" | "+resultado.getString(2)+" | "+resultado.getString(3));
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


	public int getPorCodigo(String codigo, MaterialBean material) throws SQLException{
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, codigo);
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			if (resultado.next()) {
				material.setCodigo(resultado.getString(1));
				material.setTipo(resultado.getString(2));
				material.setPesoEspecifico(resultado.getFloat(3));
				material.setEspesor(resultado.getFloat(4));
				material.setCalidad(resultado.getString(5));
				material.setIdmaterial(resultado.getInt(6));
				material.setUnidadPeso(resultado.getString(7));
				material.setAncho(resultado.getInt(8));
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
	
	public int getCodigo(MaterialBean material) throws SQLException{
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_codigo (?, ?, ?, ?,?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, material.getTipo());
			cs.setDouble(3, material.getPesoEspecifico());
			cs.setDouble(4, material.getEspesor());
			cs.setString(5, material.getCalidad());
			cs.setInt(6, material.getAncho());
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			if (resultado.next()) {
				material.setCodigo(resultado.getString(1));
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
	
	
	public int eliminar(String codigo) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call materiales_eliminar (?, ?)}");
			cs.setString(1, codigo);
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
	
	
	public int modificar(MaterialBean mat, String codigoViejo) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call materiales_modificar (?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setString(1, codigoViejo);
			cs.setString(2, mat.getCodigo());
			cs.setString(3, mat.getTipo());
			cs.setFloat(4, mat.getPesoEspecifico());
			cs.setFloat(5, mat.getEspesor());
			cs.setString(6, mat.getCalidad());
			cs.setString(7, mat.getUnidadPeso());
			cs.setInt(8, mat.getAncho());
			cs.registerOutParameter(9, Types.INTEGER);
			cs.execute();
			return cs.getInt(9);
		}catch (Exception e) {
		    e.printStackTrace();
		    return -1;
		} finally {
			conexion.close();
		}
	}
	
	public ArrayList<String> getListaDeMateriales() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.material_devolver_combobox ()}");
			
			cs.registerOutParameter(1, Types.OTHER);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1)+" - "+resultado.getString(2)+" "+resultado.getString(3)+" - "+resultado.getString(4));
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
	public String getUnidadPeso(String tipo, String espesor, String calidad,
		String peso) throws SQLException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_unidadpeso_x_tipo_espesor_calidad (?, ?, ?, ?)}");	
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tipo);
			cs.setString(3, espesor);
			cs.setString(4, calidad);
			cs.setString(5, peso);
			ResultSet resultado = cs.executeQuery();
			if(resultado.next()){
				System.out.println(resultado.getString(1));
				return (resultado.getString(1));
			} else {
				System.out.println("no entro");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		finally{
			conexion.close();
		}
	}




	public ArrayList<Vector<Object>> getMaterialesParaTablaModificar() throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_devolver_todos_para_modificar ()}");	
			cs.registerOutParameter(1, Types.OTHER);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getFloat(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, resultado.getFloat(5));
				vector.add(5, resultado.getString(6));
				vector.add(6, resultado.getFloat(7));
				lista.add(vector);
			}		
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		finally{
			conexion.close();
		}
	}




	public ArrayList<String> generarListaDeMaterialesPorCodigoDePieza(int codigo) throws SQLException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		ArrayList<String> lista = new ArrayList<>(); 
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materiales_espesor_lista_por_codigo_de_piezas (?)}");	
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, codigo);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}		
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		finally{
			conexion.close();
		}
	}


}