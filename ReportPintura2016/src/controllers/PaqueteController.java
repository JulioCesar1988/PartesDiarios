package controllers;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Vector;

import session.Session;
import models.PaqueteBean;
import models.PiezaBean;
import bd.Conector;
import bd.ParametrosConexion;

public class PaqueteController {

	Conector c;
	Connection conexion;

	public int insert(PaqueteBean paq) throws SQLException {
		int aux = 0;
		try {

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquete_cargar (?, ?, ?, ?, ?, ?, ?,?,?)}");
			cs.setInt(1, paq.getId_obra());
			cs.setInt(2, paq.getId_edificio());
			cs.setString(3, paq.getDescripcion());
			cs.setString(4, paq.getSoftDiseno());
			cs.setString(5, paq.getFechaFinalizacion());
			cs.setString(6, paq.getFechaDespacho());
			cs.setString(7, Session.getNombreUsuario());
			cs.registerOutParameter(8, Types.INTEGER);
			cs.registerOutParameter(9, Types.INTEGER);
			cs.execute();
			paq.setId_paquete(cs.getInt(8));
			paq.setNumero(cs.getInt(9));
			for (int i = 0; i < paq.getPiezas().size(); i++) {
				paq.getPiezas().get(i).setIdPaquete(paq.getId_paquete());
				PiezasController piController = new PiezasController();
				for (int j = 0; j < paq.getPiezas().get(i).getCantidad(); j++) { // modificacion para que repita las
																					// cantidades
					piController.insertConGeneracionDeCodigos(paq.getPiezas().get(i), conexion);
				}
			}
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conexion.close();
		}
	}

	public boolean update(PaqueteBean paq) throws SQLException {
		boolean retorno = true;
		try {

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			for (int i = 0; i < paq.getPiezas().size(); i++) {
				PiezasController piController = new PiezasController();
				int aux = piController.update(paq.getPiezas().get(i), conexion);
				if (aux != 1) {
					retorno = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * public int insert2(PaqueteBean paq) throws SQLException { int aux = 0; try {
	 * 
	 * c = new Conector(ParametrosConexion.getParametros()); conexion =
	 * c.getConnection(); CallableStatement cs = conexion.
	 * prepareCall("{call paquete_revision_cargar (?, ?, ?, ?, ?, ?, ? ,?, ?)}");
	 * cs.setInt(1, paq.getId_paquete()); cs.setInt(2, paq.getId_obra());
	 * cs.setInt(3, paq.getNumero()); cs.setInt(4, paq.getNumeroRevision());
	 * cs.setString(5, paq.getDescripcion()); cs.setString(6, paq.getSoftDiseno());
	 * cs.setString(7, paq.getFechaFinalizacion()); cs.setString(8,
	 * Session.getNombreUsuario()); cs.registerOutParameter(9, Types.INTEGER);
	 * cs.execute(); paq.setId_paquete(cs.getInt(9)); for (int i = 0; i <
	 * paq.getPiezas().size(); i++) {
	 * paq.getPiezas().get(i).setIdPaquete(paq.getId_paquete()); PiezasController
	 * piController = new PiezasController();
	 * 
	 * for (int j = 0; j <paq.getPiezas().get(i).getCantidad(); j++) {
	 * //modificacion para que repita las cantidades
	 * piController.insertConGeneracionDeCodigos(paq.getPiezas().get(i), conexion);
	 * } } return aux; }catch (Exception e) { e.printStackTrace(); return -1; }
	 * finally { conexion.close(); } }
	 */

	public int modificarDefinirTareas(PaqueteBean paq) throws SQLException {
		int aux = 0;
		try {

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{call paquete_definir_tareas_modificar (?, ?, ?, ?, ?, ?, ? ,?, ?)}");
			cs.setInt(1, paq.getId_paquete());
			cs.setInt(2, paq.getId_obra());
			cs.setInt(3, paq.getNumero());
			cs.setInt(4, paq.getNumeroRevision());
			cs.setString(5, paq.getDescripcion());
			cs.setString(6, paq.getSoftDiseno());
			cs.setString(7, paq.getFechaFinalizacion());
			cs.setString(8, Session.getNombreUsuario());
			cs.registerOutParameter(9, Types.INTEGER);
			cs.execute();
			paq.setId_paquete(cs.getInt(9));
			for (int i = 0; i < paq.getPiezas().size(); i++) {
				paq.getPiezas().get(i).setIdPaquete(paq.getId_paquete());
				PiezasController piController = new PiezasController();

				for (int j = 0; j < paq.getPiezas().get(i).getCantidad(); j++) { // modificacion para que repita las
																					// cantidades
					piController.insertConGeneracionDeCodigos(paq.getPiezas().get(i), conexion);
				}
			}
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conexion.close();
		}
	}

	public int guardarRevisionDePaquete(PaqueteBean paq) throws SQLException {
		int aux = 0;
		try {

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{call paquete_revision_cargar (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}");
			cs.setInt(1, paq.getId_paquete());
			cs.setInt(2, paq.getId_obra());
			cs.setInt(3, paq.getNumero());
			cs.setInt(4, paq.getNumeroRevision());
			cs.setString(5, paq.getDescripcion());
			cs.setString(6, paq.getSoftDiseno());
			cs.setString(7, paq.getFechaFinalizacion());
			cs.setString(8, paq.getFechaDespacho());
			cs.setString(9, Session.getNombreUsuario());
			cs.setString(10, paq.getNombreEdificio());
			cs.registerOutParameter(11, Types.INTEGER);
			cs.execute();
			paq.setId_paquete(cs.getInt(11));
			for (int i = 0; i < paq.getPiezas().size(); i++) {
				paq.getPiezas().get(i).setIdPaquete(paq.getId_paquete());
				PiezasController piController = new PiezasController();

				for (int j = 0; j < paq.getPiezas().get(i).getCantidad(); j++) { // modificacion para que repita las
																					// cantidades
					piController.insertConGeneracionDeCodigos(paq.getPiezas().get(i), conexion);
				}
			}
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conexion.close();
		}
	}

	public int aprobarPaquete(PaqueteBean paq) throws SQLException {
		int aux = 0;
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.paquete_guardar_aprobar (?, ?)}");
			cs.setInt(1, paq.getId_paquete());
			cs.setString(2, Session.getNombreUsuario());
			cs.execute();
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int aprobarPaqueteControlProduccion(PaqueteBean paq) throws SQLException {
		int aux = 0;
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.paquete_guardar_aprobar_control_produccion (?, ?)}");
			cs.setInt(1, paq.getId_paquete());
			cs.setString(2, Session.getNombreUsuario());
			cs.execute();
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int insertDeTareas(PaqueteBean paq) throws SQLException {
		int aux = 0;
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.paquete_empleado_enviado_cargar (?, ?)}");
			cs.setInt(1, paq.getId_paquete());
			cs.setString(2, Session.getNombreUsuario());
			cs.execute();

			PiezasController piController = new PiezasController();
			for (int i = 0; i < paq.getPiezas().size(); i++) {
				piController.insertDeTareas(paq.getPiezas().get(i));
			}
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Lista de paquetes segun obra .
	public String[] getListaDePaquete(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver_numero (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteParaApobar(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_lista_para_aprobar (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteParaEnviar(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_lista_para_enviar (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteParaModificarCarga(int obra) throws SQLException {
		/*
		 * Metodo utilizado para cargar el combobox de numero de paquete de la ventana
		 * PaqueteIngModificar, a partir de la obra y el usuario, devuelve una arreglo
		 * de string para agregar el modelo del combo box
		 */
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_lista_para_modificar_carga_propia (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, Session.getNombreUsuario());
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
				System.out.println(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	// nuevo metodo
	public String[] getListaDePaqueteParaModificarCargaConDescripcion(int obra) throws SQLException {
		/*
		 * Metodo utilizado para cargar el combobox de numero de paquete de la ventana
		 * PaqueteIngModificar, a partir de la obra y el usuario, devuelve una arreglo
		 * de string para agregar el modelo del combo box
		 */
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_lista_para_modificar_carga_propia_con_descripcion (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, Session.getNombreUsuario());
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
				System.out.println(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	// nuevo metodo para obtener la posiciones de una obra.
	public int getposiciones(int num_obra, int num_paquete, String elemento) throws Exception { /// Version de Julio
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			int cantidad = 0;
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_lista_posicion (?, ?,?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, num_obra);
			cs.setInt(3, num_paquete);
			cs.setString(4, elemento);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				cantidad = resultado.getInt("total");
			}

			return cantidad;
		} catch (SQLException e) {
			throw new IOException("No se ha podido recuperar el ID de la posicion (Pieza/Subpieza)");
		} finally {
			conexion.close();
		}
	}

	// nuevo metodo para obtener la posiciones de una obra.
	public int getCantdadPosicionId(int id_obra, int id_paquete, String elemento, boolean esPieza) throws Exception { /// Version
																														/// De
																														/// Leo
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = null;
			if (esPieza) {
				cs = conexion.prepareCall("{ ? = call dbo.parte_piezas_piezas_cantidad (?, ?,?)}");
			} else {
				cs = conexion.prepareCall("{ ? = call dbo.parte_piezas_subpiezas_cantidad (?, ?,?)}");

			}
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_obra);
			cs.setInt(3, id_paquete);
			cs.setString(4, elemento);
			ResultSet resultado = cs.executeQuery();
			int cantidad = 0;
			while (resultado.next()) {
				cantidad = resultado.getInt("total");
			}
			if (cantidad <= 0) {
				throw new SQLException();
			}
			return cantidad;
		} catch (SQLException e) {
			throw new IOException("No se ha podido recuperar Pieza/Subpieza");
		} finally {
			conexion.close();
		}
	}

	// copiar y modificar .
	public String[] getListaDePaqueteParaRevisarCarga(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_lista_para_modificar_revision_de_carga_no_propia (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, Session.getNombreUsuario());
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	// Metodo que me devuelve los paquetes , para revisar
	public String[] getListaDePaqueteParaRevisarCargaConDescripcion(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall(
					"{ ? = call dbo.paquete_lista_para_modificar_revision_de_carga_no_propia_con_descripcion (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, Session.getNombreUsuario());
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Vector> getListadoConPorcentajes(int numObra, String edificio) throws SQLException {
		ArrayList<Vector> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquete_devolver_porcentaje3 (?, ?)}");
			cs.setInt(1, numObra);
			cs.setString(2, edificio);
			ResultSet resultado = cs.executeQuery();

			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getInt(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, (100 - (resultado.getInt(5) * 100 / resultado.getInt(6))));
				vector.add(5, false);
				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector[] = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);
		}
		return vector;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Vector> getListadoDePaquetesSeleccionados(String listaDeIds) throws SQLException {
		ArrayList<Vector> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call piezas_devolver_paquetes_seleccionados3 (?)}");
			cs.setString(1, listaDeIds);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, resultado.getInt(5));
				vector.add(5, resultado.getString(6));
				vector.add(6, resultado.getInt(7));
				vector.add(7, resultado.getInt(8));
				vector.add(8, resultado.getInt(9));
				vector.add(9, resultado.getInt(10));
				vector.add(10, resultado.getInt(11));
				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Vector> getListadoDePaquetesSeleccionadosWarehouse(String listaDeIds) throws SQLException {
		ArrayList<Vector> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{call warehouse_devolver_elementos_de_paquetes_seleccionados_para_remitir (?)}");
			cs.setString(1, listaDeIds);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, resultado.getInt(5));
				vector.add(5, resultado.getString(6));
				vector.add(6, resultado.getInt(7));
				vector.add(7, resultado.getInt(8));
				vector.add(8, resultado.getBoolean(9));
				vector.add(9, resultado.getInt(10));

				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	public PaqueteBean getPaquetePorObra(int num_obra, int num_paq) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver (?, ?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, num_obra);
			cs.setInt(3, num_paq);

			ResultSet resultado = cs.executeQuery();
			PaqueteBean paq = new PaqueteBean();
			resultado.next();
			paq.setId_paquete(resultado.getInt(1));
			paq.setId_obra(resultado.getInt(2));
			paq.setNumero(resultado.getInt(3));
			paq.setNumeroRevision(resultado.getInt(4) + 1);
			paq.setDescripcion(resultado.getString(5));
			paq.setSoftDiseno(resultado.getString(6));
			paq.setFechaFinalizacion(resultado.getString(7));
			paq.setNombreEdificio(resultado.getString(8));
			paq.setFechaDespacho(resultado.getString(9));
			paq.setNumeroObra(num_obra);
			return paq;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteParaAprobarCarga(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_lista_para_aprobar_carga (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, Session.getNombreUsuario());
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}

	}

	// IMPLEMENTACION DE JULIO .
	public String[] getListaDePaqueteParaAprobarCargaConDescripcion(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			// Implemetar el metodo en la base de datos .
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_lista_para_aprobar_carga_descripcion (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, Session.getNombreUsuario());
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			System.out.print("Controlador de paquete ");
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}

	}

	/*
	 * public String[] getListaDePaqueteConDescripcion(int obra) throws SQLException
	 * { try{ c = new Conector(ParametrosConexion.getParametros()); conexion =
	 * c.getConnection(); CallableStatement cs =
	 * conexion.prepareCall("{ ? = call dbo.paquete_lista_con_descripcion (?)}");
	 * cs.registerOutParameter(1, Types.OTHER); cs.setInt(2, obra); ResultSet
	 * resultado = cs.executeQuery(); ArrayList<String> lista = new
	 * ArrayList<String>(); while(resultado.next()){
	 * lista.add(resultado.getString(1)); } String vector [] = pasarAVerctor(lista);
	 * return vector; }catch(Exception e){ System.out.println("Consulta erronea");
	 * return null; } finally{ conexion.close(); } }
	 * 
	 */

	public String[] getListaDePaqueteParaAprobarCargaControlProduccion(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_lista_para_aprobar_carga_control_produccion (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}

	}

	// Nuevo metodo implementado .
	public String[] getListaDePaqueteParaAprobarCargaControlProduccionConDescripcion(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall(
					"{ ? = call dbo.paquete_lista_para_aprobar_carga_control_produccion_con_descripcion (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}

	}

	public String[] getListaDePaqueteParaDefinirTareas(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_lista_para_asignar_tareas (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteParaModificarTareas(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_lista_para_asignar_tareas_modificar (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteParaRevisionarPaquete(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.[paquete_lista_para_revisionar_paquete] (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteConDescripcion(int obra) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_lista_con_descripcion (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteConDescripcion(int obra, String elemento, String correlatividad)
			throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_lista_con_descripcion_por_elemento_correlatividad (?,?,?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, elemento);
			cs.setString(4, correlatividad);
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public PaqueteBean getPaquetePorObraRevisionActual(int num_obra, int num_paq) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver (?, ?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, num_obra);
			cs.setInt(3, num_paq);

			ResultSet resultado = cs.executeQuery();
			PaqueteBean paq = new PaqueteBean();
			resultado.next();
			paq.setId_paquete(resultado.getInt(1));
			paq.setId_obra(resultado.getInt(2));
			paq.setNumero(resultado.getInt(3));
			paq.setNumeroRevision(resultado.getInt(4));
			paq.setDescripcion(resultado.getString(5));
			paq.setSoftDiseno(resultado.getString(6));
			paq.setFechaFinalizacion(resultado.getString(7));
			paq.setNombreEdificio(resultado.getString(8));
			paq.setFechaDespacho(resultado.getString(9));
			paq.setNumeroObra(num_obra);
			return paq;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Vector<Object>> generarListaPaquetesParaAprobar() throws SQLException {
		ArrayList<Vector<Object>> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquetes_devolver_listado_pendientes_para_aprovar ()}");
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Vector<Object>> generarListaPaquetesParaRevisar() throws SQLException {
		ArrayList<Vector<Object>> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquetes_devolver_listado_pendientes_para_revisar ()}");
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	public PaqueteBean getPaquetePorObraParaRevision(int num_obra, int num_paq) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver (?, ?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, num_obra);
			cs.setInt(3, num_paq);

			ResultSet resultado = cs.executeQuery();
			PaqueteBean paq = new PaqueteBean();
			if (resultado.next()) {
				paq.setId_paquete(resultado.getInt(1));
				paq.setId_obra(resultado.getInt(2));
				paq.setNumero(resultado.getInt(3));
				paq.setNumeroRevision(resultado.getInt(4) + 1);
				paq.setDescripcion(resultado.getString(5));
				paq.setSoftDiseno(resultado.getString(6));
				paq.setFechaFinalizacion(resultado.getString(7));
				paq.setNombreEdificio(resultado.getString(8));
				paq.setFechaDespacho(resultado.getString(9));
				paq.setNumeroObra(num_obra);
				PiezasController piController = new PiezasController();
				ArrayList<PiezaBean> piezas = piController.getPiezasPorPaqueteParaRevision(paq.getId_paquete());
				paq.setPiezas(piezas);
			}

			return paq;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Vector> getPaquetesXObra(int numObra) throws SQLException {
		ArrayList<Vector> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquetes_devolver (?)}");
			cs.setInt(1, numObra);
			ResultSet resultado = cs.executeQuery();

			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, false);
				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector> getPaquetesPendientesXObra(int numObra) throws SQLException {
		ArrayList<Vector> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquetes_devolver_pendientes (?)}");
			cs.setInt(1, numObra);
			ResultSet resultado = cs.executeQuery();

			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, false);
				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector> getPaquetesFinalizadosXObra(int numObra) throws SQLException {
		ArrayList<Vector> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquetes_devolver_finalizados4 (?)}");
			cs.setInt(1, numObra);
			ResultSet resultado = cs.executeQuery();

			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, false);
				listado.add(vector);
			}
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	public String[] getListaDePaqueteDesaprobar(int obra_num) throws SQLException {

		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver_combobox_desaprobar (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra_num);

			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1) + " - " + resultado.getString(2));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public int desaprobarPaquete(int obra_num, int paq_num) throws SQLException {
		int aux = 0;
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.paquete_guardar_desaprobar (?, ?)}");
			cs.setInt(1, obra_num);
			cs.setInt(2, paq_num);
			cs.execute();
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public String[] getListaDePaqueteDesaprobarCtrlProd(int obra_num) throws SQLException {

		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.paquete_devolver_combobox_desaprobar_ctrl_prod (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra_num);

			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1) + " - " + resultado.getString(2));
			}
			String vector[] = pasarAVerctor(lista);
			return vector;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public int desaprobarPaqueteCtrlProd(int obra_num, int paq_num) throws SQLException {
		int aux = 0;
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.paquete_guardar_desaprobar_ctrl_prod (?, ?)}");
			cs.setInt(1, obra_num);
			cs.setInt(2, paq_num);
			cs.execute();
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Vamos a implementar
	public int getIdPaquetePorObra(int numObra, int numPaquete) throws Exception {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call parte_piezas_paquete_get_id (?,?,?)}");
			cs.setInt(1, numObra);
			cs.setInt(2, numPaquete);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			int id = cs.getInt(3);
			if (id <= 0) {
				throw new SQLException();
			}
			return id;
		} catch (SQLException e) {
			throw new IOException("No se ha podido recuperar el ID del paquete");
		} finally {
			conexion.close();
		}

	}

	public ArrayList<Vector> getPaquetesFinalizadosXObraDias(int numObra) throws SQLException {
		ArrayList<Vector> listado = new ArrayList<>();
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call paquetes_devolver_finalizados5 (?)}");
			cs.setInt(1, numObra);
			ResultSet resultado = cs.executeQuery();

			while (resultado.next()) {
				Vector vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, false);
				listado.add(vector);
			}

			return listado;
		} catch (Exception e) {
			e.printStackTrace();
			return listado;
		} finally {
			conexion.close();
		}
	}

	public int retrocederLista(int obra_num, int paq_num) throws SQLException {
		int aux = 0;
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.orden_retroceder (?, ?)}");
			cs.setInt(1, obra_num);
			cs.setInt(2, paq_num);
			cs.execute();
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
