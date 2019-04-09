package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Vector;

import bd.Conector;
import bd.ParametrosConexion;

public class ElementoController {
	Conector c;
	Connection conexion;
	public ArrayList<String> getListaDeElementosPorSistema(String sistema) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.elementos_devolver_combobox (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, sistema);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1)+" - "+resultado.getString(2));
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
	
	public ArrayList<String> getListaDeElementosTodosAuto() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.elementos_devolver_combobox_auto }");
			
			cs.registerOutParameter(1, Types.OTHER);
			System.out.println("entre2");
			
	
			ResultSet resultado = cs.executeQuery();
			System.out.println("entre3");
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1)+" - "+resultado.getString(2));
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
	public String getArea(String elemento,String sistema) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.elemento_devolver_area (?,?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, elemento);
			cs.setString(3, sistema);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1));
				System.out.println(resultado.getString(1));
			}
			String vector [] = pasarAVector(lista);
			return vector[0];
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}
	
	private String[] pasarAVector(ArrayList<String> lista) {
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		return vector;
	}

	public Object[] getListaDeElementosTodos() throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call elementos_devolver_todos_para_combobox}");
			cs.registerOutParameter(1, Types.OTHER);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1));
			}
			String vector [] = pasarAVector(lista);
			return vector;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}

	public ArrayList<Vector<Object>> generarListaDeElementosPrecargados() throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call elementos_todos_para_tabla}");
			cs.registerOutParameter(1, Types.OTHER);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<Object>> lista = new ArrayList<Vector<Object>>();
			while(resultado.next()){
				Vector<Object> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getString(3));
				vector.add(resultado.getString(4));
				lista.add(vector);
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
	
	public ArrayList<Vector<Object>> generarListaDeElementosPrecargadosConId() throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call elementos_todos_para_tabla}");
			cs.registerOutParameter(1, Types.OTHER);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<Object>> lista = new ArrayList<Vector<Object>>();
			while(resultado.next()){
				Vector<Object> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getString(3));
				vector.add(resultado.getString(4));
				vector.add(resultado.getString(6));
				vector.add(resultado.getString(7));
				vector.add(resultado.getString(8));
				vector.add(resultado.getInt(5));
				lista.add(vector);
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

	
	
	public int guardarElementoPrecargado(String elemento, String descripcion,
			String sistema, String area, String codigoCategoria, String codigoSubcategoria) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call elemento_precargado_cargar (?, ?, ?, ?, ?, ?, ?)}");
			cs.setString(1, elemento);
			cs.setString(2, descripcion);
			cs.setString(3, sistema);
			cs.setString(4, area);
			cs.setString(5, codigoCategoria);
			cs.setString(6, codigoSubcategoria);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.execute();
			return cs.getInt(7);
		}catch (Exception e) {
		    e.printStackTrace();
		    return -1;
		} finally {
			conexion.close();
		}
	}

	public int guardarElementoPrecargadoTodos(String elemento, String descripcion, String area, String codigoCategoria, String codigoSubcategoria) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call elemento_precargado_cargar_todos (?, ?, ?, ?, ?, ?)}");
			cs.setString(1, elemento);
			cs.setString(2, descripcion);
			cs.setString(3, area);
			cs.setString(4, codigoCategoria);
			cs.setString(5, codigoSubcategoria);
			cs.registerOutParameter(6, Types.INTEGER);
			cs.execute();
			return cs.getInt(6);
		}catch (Exception e) {
		    e.printStackTrace();
		    return -1;
		} finally {
			conexion.close();
		}
	}
	
	public int modificarElementoPrecargado(Integer id_elemento, String descripcion,
			String sistema, String area, String categoria) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call elemento_precargado_modificar (?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, id_elemento);
			cs.setString(2, descripcion);
			cs.setString(3, sistema);
			cs.setString(4, area);
			cs.setString(5, categoria);
			cs.registerOutParameter(6, Types.INTEGER);
			cs.execute();
			return cs.getInt(6);
		}catch (Exception e) {
		    e.printStackTrace();
		    return -1;
		} finally {
			conexion.close();
		}
		
	}

	public int modificarElementoPrecargadoTodos(String elemento, String descripcion, String area, String categoria) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call elemento_precargado_modificar_todos (?, ?, ?, ?, ?)}");
			cs.setString(1, elemento);
			cs.setString(2, descripcion);
			cs.setString(3, area);
			cs.setString(4, categoria);
			cs.registerOutParameter(5, Types.INTEGER);
			cs.execute();
			return cs.getInt(5);
		}catch (Exception e) {
		    e.printStackTrace();
		    return -1;
		} finally {
			conexion.close();
		}
	}
	
	public String[] getListaDeElementosTodosXObra(int obraNum) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.elementos_devolver_combobox_x_obra (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNum);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1)+" >>>>>> "+resultado.getString(2));
			}

			String vector [] = pasarAVector(lista);
			return vector;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}

	}
	
	public int eliminarElementoPrecargado(int id_elemento) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call elemento_precargado_eliminar (?, ?)}");
			cs.setInt(1, id_elemento);
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
	
	public int eliminarElementoPrecargadoTodos(String codigo) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call elemento_precargado_eliminar_todos (?, ?)}");
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
	public ArrayList<String> getListaDeElementosXObraPaquete(int obra_num, int paq_num) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.elementos_devolver_combobox_auto_XObraPaquete (?,?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra_num);
			cs.setInt(3, paq_num);
			System.out.println("entre2");
			
	
			ResultSet resultado = cs.executeQuery();
			System.out.println("entre3");
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1)+" - "+resultado.getString(2));
			}

			return lista;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}}	
