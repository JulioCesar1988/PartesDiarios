package controllers;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bd.Conector;
import bd.ParametrosConexion;
import models.CategoriaCtrlProduccionBean;

public class CategoriaCtrlProduccionController {
	
	Conector c;
	Connection conexion;
	
	public int insert(CategoriaCtrlProduccionBean categoria) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call categoria_ctrl_produccion_carga (?, ?, ?, ?, ?)}");
			cs.setString(1, categoria.getCodigo());
			cs.setString(2, categoria.getCategoria());
			cs.setString(3, categoria.getCodigoSubcategoria());
			cs.setString(4, categoria.getSubcategoria());
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
	
	public String[] getListaDeCategoria() throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.categoria_ctrl_produccion_get_categorias_para_combobox ()}");
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
	

	public String[] getListaDeSubcategoria(String codigoCategoria) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.categoria_ctrl_produccion_get_subcategorias_para_combobox (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, codigoCategoria);
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

	public int eliminar(String codigo) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call categoria_ctrl_produccion_eliminar (?, ?)}");
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
	
	public CategoriaCtrlProduccionBean getCategoria(String codigoCategoria, String codigoSubategoria) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.categoria_ctrl_produccion_devolver (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, codigoCategoria);
			cs.setString(3, codigoSubategoria);
			ResultSet resultado = cs.executeQuery();
			CategoriaCtrlProduccionBean categoria = null;
			if(resultado.next()){
				categoria = new CategoriaCtrlProduccionBean();
				categoria.setCodigo(resultado.getString(1));
				categoria.setCategoria(resultado.getString(2));
				categoria.setCodigoSubcategoria(resultado.getString(3));
				categoria.setSubcategoria(resultado.getString(4));
			}
			return categoria;
		}catch(Exception e){
			return null;
		}
		finally{
			conexion.close();
		}
	}

	public int modificar(CategoriaCtrlProduccionBean categoria) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call categoria_ctrl_produccion_modificar (?, ?, ?, ?)}");
			cs.setString(1, categoria.getCodigo());
			cs.setString(2, categoria.getCategoria());
			cs.setString(3, categoria.getSubcategoria());
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

	public ArrayList<String> getListaDeCodigos() throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.categoria_ctrl_produccion_get_codigos_para_combobox ()}");
			cs.registerOutParameter(1, Types.OTHER);
	
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1));
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

	public String devolverCategoriaPorElemento(String elemento) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{? = call dbo.categoria_ctrl_produccion_get_categoria_por_elemento (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, elemento);
			ResultSet resultado = cs.executeQuery();
			if (resultado.next()) {
				return resultado.getString(1);
			}else {
				return null;
			}
			
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}

	public String[] getListaDeCategoriaYSubcategoria() throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.categoria_ctrl_produccion_get_codigos_para_combobox ()}");
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

	public String getCategoriaPorCodigoYCodgoSub(String categoria) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.categoria_ctrl_produccion_devolver_porcodigo_y_codigo_sub(?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, categoria);
			ResultSet resultado = cs.executeQuery();
			String cat = null;
			if(resultado.next()){
				cat=resultado.getString(1);
			}
			return cat;
		}catch(Exception e){
			return null;
		}
		finally{
			conexion.close();
		}
	}

		
}
