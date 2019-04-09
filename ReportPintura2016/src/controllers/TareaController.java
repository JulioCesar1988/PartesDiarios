package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import session.Session;
import models.TareaBean;
import bd.Conector;
import bd.ParametrosConexion;




public class TareaController {
	
	/*
	 * SP Utilizados:
	 * 
	 * tarea_cargar			
	 * tarea_devolver     	
	 * tarea_modificar		
	 * tarea_eliminar
	 * subpieza_tarea_cargar
	 * devolver_sectores
	 * tareas_planta_devolver
	 * subpieza_tarea_modificar_estadonuevo
	 * subpieza_devolver_tareas	+
	 */
	
	Conector c;
	Connection conexion;

	public int insert(TareaBean tar) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call tarea_cargar (?, ?, ?, ?, ?)}");
			cs.setString(1, tar.getCodigo());
			cs.setString(2, tar.getDescripcion());
			cs.setString(3, tar.getDescripcionExtra());
			cs.setString(4, tar.getSector());
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
	
	public int getTarea(String codigo, TareaBean tarea) throws SQLException{ 
		TareaBean tareaAux = new TareaBean();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.tarea_devolver (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, codigo);
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			System.out.println("ejecuta!" + codigo);
			if (resultado.next()) {
				tarea.setCodigo(resultado.getString(1));
				tarea.setDescripcion(resultado.getString(2));				
				tarea.setDescripcionExtra(resultado.getString(3));
				tarea.setSector(resultado.getString(4));
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

	public int modificar(TareaBean tarea) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call tarea_modificar (?, ?, ?, ?, ?)}");
			cs.setString(1, tarea.getCodigo());
			cs.setString(2, tarea.getDescripcion());
			cs.setString(3, tarea.getDescripcionExtra());
			cs.setString(4, tarea.getSector());
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
	
	public int eliminar(String codigo) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call tarea_eliminar (?, ?)}");
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

	public int asociar(TareaBean tarea, int idSubpieza, Connection conexion) {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call subpieza_tarea_cargar (?, ?, ?)}");
			cs.setInt(1, idSubpieza);
			cs.setInt(2, tarea.getIdTarea());
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			return cs.getInt(3);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		}
	}

	public String[] getListaDeSector() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.sectores_devolver ()}");
			
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
	
	private String[] pasarAVector(ArrayList<String> lista) {
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		return vector;
	}

	public void cargarTareasPlanta(int obra, String sector,DefaultTableModel modeloTabla) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.tareas_planta_devolver (?,?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, sector);
			
			ResultSet resultado = null;
			resultado = cs.executeQuery();
		//	ArrayList<Object[]> tareas = new ArrayList<Object[]>();
			while (resultado.next()) {
				
				Object[] tarea={resultado.getObject(1),resultado.getObject(2)+" - "+
							resultado.getObject(3),resultado.getObject(4)+" - "+resultado.getObject(5),
							resultado.getObject(6)+" - "+resultado.getObject(7),resultado.getObject(8)+" - "+
							resultado.getObject(9),resultado.getObject(10),resultado.getObject(11),false};
				modeloTabla.addRow(tarea);
				
			}
			}catch(Exception e){
			System.out.println("Consulta erronea");
			
		}
		finally{
			conexion.close();
		}
	}
	
	public void cambiarEstadoTarea(int id_sub, int id_tar, String usuario) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.subpieza_tarea_modificar_estadonuevo (?,?,?)}");
			
			
			cs.setInt(1, id_sub);
			cs.setInt(2, id_tar);
			cs.setString(3, usuario);
			System.out.println("ok");
			
			cs.execute();

			}catch(Exception e){
			System.out.println("Consulta erronea");
			
		}
		finally{
			conexion.close();
		}
	}

	public ArrayList<TareaBean> getTareasPorSubpieza(int id_subpieza) throws SQLException {
		ArrayList<TareaBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.subpieza_devolver_tareas (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_subpieza);
			
	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				TareaBean tarea = new TareaBean();
				tarea.setIdTarea(resultado.getInt(1));
				tarea.setCodigo(resultado.getString(2));
				tarea.setDescripcion(resultado.getString(3));
				tarea.setDescripcionExtra(resultado.getString(4));
				tarea.setSector(resultado.getString(5));				
				
				listado.add(tarea);
				
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}
	}

	public int asociarPorCodigo(TareaBean tarea, int codigo) throws SQLException {
		/*
		 * Este metodo es el que guarda las tareas en un momento 
		 * diferente al que se genero el paquete con piezas y subpiezas
		 */
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call subpieza_tarea_cargar_por_codigo (?, ?, ?)}");
			cs.setInt(1, codigo);
			cs.setString(2, tarea.getCodigo());
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

	public String[] getListaTareas(String sector) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.tarea_devolver_por_sector (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, sector);
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

	public String[] getListaTareas() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.tarea_devolver_all_codigos}");
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
	
	public ArrayList<Vector<Object>> getListaDeTareasPendientes(int obra, String sector) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call tareas_planta_devolverv3 (?,?)}");
						cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, sector);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, resultado.getString(5));
				vector.add(5, resultado.getString(6));
				vector.add(6, resultado.getInt(7));
				vector.add(7, resultado.getInt(8));
				vector.add(8, resultado.getInt(9));
				vector.add(9, 0);
				vector.add(10, false);
				vector.add(11, resultado.getInt(10));
				lista.add(vector);
			}		
			return lista;
		}catch (Exception e) {
			e.printStackTrace();
			return lista;
		} finally {
			conexion.close();
		}
	}
	
	public void ActualizarTareasPendientes(ArrayList<Vector<Integer>> lista) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			for (int i = 0; i < lista.size(); i++) {
				CallableStatement cs = conexion.prepareCall("{ call dbo.subpieza_tarea_actualizar_estado (?, ?, ?, ?,?)}");
				cs.setInt(1, lista.get(i).get(0));
				cs.setInt(2, lista.get(i).get(1));
				cs.setInt(3, lista.get(i).get(2));
				cs.setString(4, Session.getNombreUsuario());
				cs.registerOutParameter(5, Types.INTEGER);
				cs.execute();
				int aux = cs.getInt(5);				
			}
		}catch (Exception e) {
			System.out.println("Consulta erronea");
			e.printStackTrace();
		} finally {
			conexion.close();
		}
			
	}
	
	public ArrayList<String> getListaDeCodigosTareas() throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.tarea_devolver_combobox ()}");
			
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
	
	
	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		return vector;
	}	
	
	public ArrayList<TareaBean> getTareasPorSubpiezaParaRevision(int id_subpieza) throws SQLException {
		ArrayList<TareaBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.subpieza_devolver_tareas_para_revision (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_subpieza);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				TareaBean tarea = new TareaBean();
				tarea.setIdTarea(resultado.getInt(1));
				tarea.setCodigo(resultado.getString(2));
				tarea.setDescripcion(resultado.getString(3));
				tarea.setDescripcionExtra(resultado.getString(4));
				tarea.setSector(resultado.getString(5));
				tarea.setFinalizado(resultado.getBoolean(6));
				listado.add(tarea);
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}
	}
	
	public String[] getListaDeSubsector(String sector) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.subsector_devolver_combobox (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, sector);
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
	
	public String[] getListaDeSubsectorXObraPaqueteSector(int num_obra, int num_paq, String sector) throws SQLException {

		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.subsector_devolver_combobox_x_obra_paquete_sector (?,?,?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, num_obra);
			cs.setInt(3, num_paq);
			cs.setString(4, sector);
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
	
	public ArrayList<TareaBean> setTareasPorSubpiezaWarehouse(int id_material) throws SQLException {
		ArrayList<TareaBean> listado = new ArrayList<>();
		
				if(id_material==382){
				TareaBean tarea = new TareaBean();
				tarea.setIdTarea(2);
				tarea.setCodigo("A2");
				tarea.setDescripcion("PREPARACION DE PEDIDOS PARA OBRAS");
				tarea.setDescripcionExtra("LOGISTICA");
				tarea.setSector("Almacen");				
				
				listado.add(tarea);
				
				}
			return listado;
		
	}
	
	

	// Vamos a implementar  Get ID de tarea por Codigo de tarea 
	public int getIdTareaCodigo(String CodTarea) throws Exception {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call parte_piezas_tarea_get_id (?,?)}");
			cs.setString(1, CodTarea);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			return cs.getInt(2);
		}catch (SQLException e) {
		      e.printStackTrace();
		      throw new Exception("No se ha podido recuperar el identificador de la Tarea");
		} finally {
			conexion.close();
		}		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
