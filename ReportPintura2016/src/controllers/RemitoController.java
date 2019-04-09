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
import models.RemitoBean;
import session.Session;

public class RemitoController {

	
	Conector c;
	Connection conexion;

	public int insert(RemitoBean rem) throws SQLException {

		try {
			String queryString = "INSERT INTO Remitos"
					+ "(id_empleado, id_obra, id_camion, id_cofer, fecha) "
					+ "VALUES(?,?,?,?);";
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			query.setLong(1, rem.getIdEmpleado());
			query.setLong(2, rem.getIdObra());
			query.setLong(3, rem.getIdCamion());
			query.setLong(4, rem.getIdChofer());
			query.setDate(5, rem.getFecha());
			
		
			return query.executeUpdate();
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("No se inserto");
			return -1;
		} finally {

			conexion.close();
			
		}
	}
	
	
	public ArrayList<RemitoBean> getObra(long obraId) throws SQLException{
		try{
			String queryString = "SELECT * FROM Remitos where id_obra=" + obraId;
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			ResultSet resultados = query.executeQuery();
			
			ArrayList<RemitoBean> remitos = new ArrayList<RemitoBean>();
			while(resultados.next()){
				RemitoBean remito = new RemitoBean();
				remito.setTodo(resultados.getInt(1), resultados.getInt(2), resultados.getInt(3), resultados.getInt(4), resultados.getInt(5),
						resultados.getDate(7));
				
				remitos.add(remito);
			}
			return remitos;
		//	return resultados;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
		
	}


	public void crearRemito(RemitoBean remito, String usuario) throws SQLException {
		try {
		/*	ArrayList<Integer> listaDeIdARemitir = new ArrayList<>();
			listaDeIdARemitir = generarListaDeID(remito.getlistaDePiezasTipoCantidadMasCodigo());*/
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call remito_cargar (?, ?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, remito.getIdObra());
			cs.setInt(2, remito.getIdCamion());
			cs.setInt(3, remito.getIdChofer());
			cs.setInt(4, remito.getNumero());
			cs.setString(5, remito.getPatenteSemi());
			cs.setString(6, usuario);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.execute();
			remito.setIdRemito(cs.getInt(7));
			for (int i = 0; i < remito.getlistaDePiezasTipoCantidadMasCodigo().size(); i++) {
				System.out.println("----------->"+remito.getlistaDePiezasTipoCantidadMasCodigo().get(i)+"-" +remito.getIdRemito());
				guardarEntradaEnPiezasRemito(remito.getlistaDePiezasTipoCantidadMasCodigo().get(i), remito.getIdRemito());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		
	}

	public void crearRemitoWarehouse(RemitoBean remito, String usuario) throws SQLException {
		try {
		/*	ArrayList<Integer> listaDeIdARemitir = new ArrayList<>();
			listaDeIdARemitir = generarListaDeID(remito.getlistaDePiezasTipoCantidadMasCodigo());*/
			System.out.println(remito.getIdObra());
			System.out.println(remito.getIdCamion());
			System.out.println(remito.getIdChofer());
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call remito_cargar (?, ?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, remito.getIdObra());
			cs.setInt(2, remito.getIdCamion());
			cs.setInt(3, remito.getIdChofer());
			cs.setInt(4, remito.getNumero());
			cs.setString(5, remito.getPatenteSemi());
			cs.setString(6, usuario);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.execute();
			remito.setIdRemito(cs.getInt(7));
			System.out.println("id remito: "+remito.getIdRemito());
			for (int i = 0; i < remito.getlistaDePiezasTipoCantidadMasCodigo().size(); i++) {
				System.out.println("----------->"+remito.getlistaDePiezasTipoCantidadMasCodigo().get(i)+"-" +remito.getIdRemito());
				guardarEntradaEnElementosWarehouseRemito(remito.getlistaDePiezasTipoCantidadMasCodigo().get(i), remito.getIdRemito());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
		
	}
	
	private void guardarEntradaEnPiezasRemito(Vector<Object> vector, int idRemito) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call piezas_remito_cargarV2 (?, ?, ?, ?)}");
			System.out.println("llama");
			cs.setInt(1, (int) vector.get(0));
			cs.setInt(2, (int) vector.get(1));
			cs.setInt(3, idRemito);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
	}

	private void guardarEntradaEnElementosWarehouseRemito(Vector<Object> vector, int idRemito) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call warehouse_remito_cargarv2 (?, ?, ?, ?)}");
			System.out.println("llama");
			cs.setInt(1, (int) vector.get(0));
			cs.setInt(2, (int) vector.get(1));
			cs.setInt(3, idRemito);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}
	}

	public boolean verificarRemitoNumero(int remitoNumero) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call remito_numero_verificar_existencia (?, ?)}");
			cs.setInt(1, remitoNumero);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			System.out.println();
			if (cs.getInt(2)==0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			conexion.close();
		}
		
	}


	public String[] getListaDeNumerosDeRemito(int obraNumero) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.remito_devolver_por_obra (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNumero);
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


	public ArrayList<Vector<Object>> generarListaElementosRemitidos(int remitoNumero) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call remito_devolver_piezas_remitidasv2 (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, remitoNumero);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<Object>> lista = new ArrayList<Vector<Object>>();
			while(resultado.next()){
				Vector<Object> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getString(3));
				vector.add(resultado.getString(4));
				vector.add(resultado.getString(5));
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


	public Vector<Object> cargarRemito(int remitoNumero) throws SQLException {
		Vector<Object> vector = new Vector<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call remito_devolver (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, remitoNumero);
			ResultSet resultado = cs.executeQuery();
			if (resultado.next()){
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getString(3));
				vector.add(resultado.getString(4));
				vector.add(resultado.getString(5));
				vector.add(resultado.getString(6));
				vector.add(resultado.getString(7));
				vector.add(resultado.getString(8));
				vector.add(resultado.getString(9));
				vector.add(resultado.getInt(10));
			}
			
			return vector;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return vector;
		}
		finally{
			conexion.close();
		}
		
	}


	public int deleteRemito(int remitoId) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ call remito_eliminar (?, ?, ?)}");
			cs.setInt(1, remitoId);
			cs.setString(2, Session.getNombreUsuario());
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			int aux = cs.getInt(3);
			return aux;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return -1;
		}
		finally{
			conexion.close();
		}
	}

	/*private ArrayList<Integer> generarListaDeID(ArrayList<Vector<Object>> listaDeVectores) throws SQLException {
		try {
			ArrayList<Integer> listaDeIdARemitir = new ArrayList<>();
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call remito_devolver_idspieza_por_codigo_cantidad (?, ?)}");

			for (int i = 0; i < listaDeVectores.size(); i++) {
				cs.setInt(1, (Integer) listaDeVectores.get(i).get(0));
				cs.setInt(2, (Integer) listaDeVectores.get(i).get(1));
				System.out.println((Integer) listaDeVectores.get(i).get(0));
				ResultSet resultado = cs.executeQuery();
				while(resultado.next()){
					listaDeIdARemitir.add(resultado.getInt(1));
				}
			}
			return listaDeIdARemitir;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			conexion.close();
		}
	}	*/
	
}

