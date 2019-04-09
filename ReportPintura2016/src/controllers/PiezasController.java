package controllers;
import session.Session;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;



import models.PiezaBean;
import models.SubpiezaBean;
import bd.Conector;
import bd.ParametrosConexion;

import session.*;

import session.Session;








public class PiezasController{

	Conector c;
	Connection conexion;
	
	public int insert(PiezaBean pieza, Connection conexion) throws SQLException {
		int aux = 1;
		try {
			
			CallableStatement cs = conexion.prepareCall("{call pieza_cargar (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, pieza.getIdPaquete());
			cs.setString(2, pieza.getElemento());
			cs.setString(3, pieza.getCorrelatividad());
			cs.setString(4, pieza.getDescripcion());
			cs.setString(5, pieza.getDescripcion_extra());
			cs.setInt(6, pieza.getLargo());
			cs.setBoolean(7, pieza.isPintura());
			cs.setString(8, pieza.getColor());
			cs.setString(9, pieza.getArea());
			cs.registerOutParameter(10, Types.INTEGER);
			cs.execute();
			pieza.setIdPieza(cs.getInt(10));
			for (int i = 0; i < pieza.getSubPiesas().size(); i++) {
				pieza.getSubPiesas().get(i).setIdPiezaPadre(pieza.getIdPieza());
				SubpiezaController sController = new SubpiezaController();
				aux = sController.insert(pieza.getSubPiesas().get(i), conexion);
			}
			return aux;
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;	
		}
	}
	
	public int insertConGeneracionDeCodigos(PiezaBean pieza, Connection conexion) throws SQLException {
		int aux = 1;
		try {
			
			CallableStatement cs = conexion.prepareCall("{call pieza_cargar_generador_codigo (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}");
			cs.setInt(1, pieza.getIdPaquete());
			cs.setString(2, pieza.getElemento());
			cs.setString(3, pieza.getCorrelatividad());
			cs.setString(4, pieza.getDescripcion());
			cs.setString(5, pieza.getDescripcion_extra());
			cs.setInt(6, pieza.getLargo());
			cs.setBoolean(7, pieza.isPintura());
			cs.setString(8, pieza.getColor());
			cs.setString(9, pieza.getArea());
			cs.setString(10, pieza.getUbicacion());
			cs.setInt(11, pieza.getCodigo());
			cs.registerOutParameter(12, Types.INTEGER);
			cs.registerOutParameter(13, Types.INTEGER);
			cs.execute();
			pieza.setIdPieza(cs.getInt(12));
			pieza.setCodigo(cs.getInt(13));
			for (int i = 0; i < pieza.getSubPiesas().size(); i++) {
				pieza.getSubPiesas().get(i).setIdPiezaPadre(pieza.getIdPieza());
				SubpiezaController sController = new SubpiezaController();
				for (int j = 0; j < pieza.getSubPiesas().get(i).getCantidad(); j++) {
					aux = sController.insertConGeneracionDeCodigos(pieza.getSubPiesas().get(i), conexion);
				}
			}
			return aux;
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;	
		}
	}

	public int update(PiezaBean pieza, Connection conexion) throws SQLException {
		
		String categoriapieza;
		String categoria;
		String subcategoria;
		if (pieza.getCodigoCategoria()!=null && !pieza.getCodigoCategoria().isEmpty()){

			categoriapieza = (String) pieza.getCodigoCategoria();
			StringTokenizer tk2 = new StringTokenizer(categoriapieza, "-");
			categoria=tk2.nextToken();
			if (tk2.hasMoreTokens()) {
				subcategoria=tk2.nextToken();
			} else {
				subcategoria="";
			}			
		}else {
			categoria="";
			subcategoria="";
		}
		System.out.println("Categoria: " +categoria+ " - Subcategoria: " +subcategoria);
		try {
			
			CallableStatement cs = conexion.prepareCall("{call piezas_actualizar_codigo_categoria (?, ?, ?, ?)}");
			cs.setInt(1, pieza.getCodigo());
			cs.setString(2, categoria);
			cs.setString(3, subcategoria);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			if (cs.getInt(4)==0 && categoria=="" ) {
				return 1; 
			}
			return cs.getInt(4);
			
		}catch (Exception e) {
			e.printStackTrace();
		    return -1;	
		}
	}
	
	public int insertDeTareas(PiezaBean pieza) throws SQLException {
		int aux = 1;
		try {
			SubpiezaController sController = new SubpiezaController();
			for (int i = 0; i < pieza.getSubPiesas().size(); i++) {
				aux = sController.insertDeTareas(pieza.getSubPiesas().get(i), conexion);
			}
			return aux;
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;	
		}
	}
	
	public ArrayList<PiezaBean> getPiezasPorPaquete(int id_paquete) throws SQLException {
		ArrayList<PiezaBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver_piezas (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_paquete);
			
	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				PiezaBean pieza = new PiezaBean();
				pieza.setIdPieza(resultado.getInt(1));
				pieza.setIdPaquete(resultado.getInt(2));
				pieza.setElemento(resultado.getString(3));
				pieza.setCorrelatividad(resultado.getString(4));
				pieza.setDescripcion(resultado.getString(5));
				pieza.setDescripcion_extra(resultado.getString(6));
				pieza.setLargo(resultado.getInt(7));
				pieza.setPintura(resultado.getBoolean(8));
				pieza.setColor(resultado.getString(9));
				pieza.setArea(resultado.getString(10));
				pieza.setCodigo(resultado.getInt(11));
				pieza.setCantidad(resultado.getInt(12));
				pieza.setUbicacion(resultado.getString(13));
				SubpiezaController subp=new SubpiezaController();
				ArrayList<SubpiezaBean> subpiezas=subp.getSubpiezasPorPiezaRecuperadas(pieza.getIdPieza());
				pieza.setSubPiesas(subpiezas);
				
				listado.add(pieza);
				
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}
	}
	
	public ArrayList<PiezaBean> getPiezasPorPaqueteSinTareas(int id_paquete) throws SQLException {
		ArrayList<PiezaBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver_piezas (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_paquete);
			
	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				PiezaBean pieza = new PiezaBean();
				pieza.setIdPieza(resultado.getInt(1));
				pieza.setIdPaquete(resultado.getInt(2));
				pieza.setElemento(resultado.getString(3));
				pieza.setCorrelatividad(resultado.getString(4));
				pieza.setDescripcion(resultado.getString(5));
				pieza.setDescripcion_extra(resultado.getString(6));
				pieza.setLargo(resultado.getInt(7));
				pieza.setPintura(resultado.getBoolean(8));
				pieza.setColor(resultado.getString(9));
				pieza.setArea(resultado.getString(10));
				pieza.setCodigo(resultado.getInt(11));
				pieza.setCantidad(resultado.getInt(12));
				pieza.setUbicacion(resultado.getString(13));
				SubpiezaController subp=new SubpiezaController();
				ArrayList<SubpiezaBean> subpiezas=subp.getSubpiezasPorPiezaSinTareas(pieza.getIdPieza());
				pieza.setSubPiesas(subpiezas);
				
				listado.add(pieza);
				
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector<Object>> cargarPiezasParaAprobar(int obra) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.piezas_devolver_para_finalizarv2 (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, resultado.getString(5));
				vector.add(5, resultado.getInt(6));
				vector.add(6, resultado.getInt(7));
				vector.add(7, false);
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

	public ArrayList<Vector<Object>> getListaDePiezasParaPintar(int obra) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call piezas_devolver_para_finalizar_pintura (?)}");
						cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				System.out.println(" Elemento -> " + resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getInt(4));
				vector.add(4, resultado.getString(5));
				vector.add(5, resultado.getString(6));
				vector.add(6, resultado.getInt(7));
				vector.add(7, 0);
				vector.add(8, false);
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
	
	

	public void ActualizarPinturaDePiezas(ArrayList<Vector<Integer>> lista) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			for (int i = 0; i < lista.size(); i++) {
				CallableStatement cs = conexion.prepareCall("{ call dbo.pieza_actualizar_pintura (?, ?, ?, ?)}");
				cs.setInt(1, lista.get(i).get(0));
				cs.setInt(2, lista.get(i).get(1));
				cs.setString(3, Session.getNombreUsuario());
				cs.registerOutParameter(4, Types.INTEGER);
				cs.execute();
				int aux = cs.getInt(4);				
			}
		}catch (Exception e) {
			System.out.println("Consulta erronea");
			e.printStackTrace();
		} finally {
			conexion.close();
		}
	}
	
	public void ActualizarTerminadoDePiezas(ArrayList<Vector<Integer>> lista) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			for (int i = 0; i < lista.size(); i++) {
				CallableStatement cs = conexion.prepareCall("{ call dbo.pieza_actualizar_terminado (?, ?, ?)}");
				cs.setInt(1, lista.get(i).get(0));
				cs.setString(2, Session.getNombreUsuario());
				cs.registerOutParameter(3, Types.INTEGER);
				cs.execute();
				int aux = cs.getInt(3);
			}
		}catch (Exception e) {
			System.out.println("Consulta erronea");
			e.printStackTrace();
		} finally {
			conexion.close();
		}
			
	}

	public ArrayList<Vector<Object>> generarListaDePiezasParaEtiquetas(int obraNumero, int paqueteNumero) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call paquete_devolver_piezas_para_etiquetas (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNumero);
			cs.setInt(3, paqueteNumero);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, resultado.getString(5));
				vector.add(5, resultado.getBoolean(6));
				vector.add(6, resultado.getString(7));
				vector.add(7, resultado.getInt(8));
				vector.add(8, resultado.getInt(9));
				vector.add(9, resultado.getInt(1));
				vector.add(10,resultado.getString(10));
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
	
	public ArrayList<PiezaBean> getPiezasPorPaqueteParaRevision(int id_paquete) throws SQLException {
		ArrayList<PiezaBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver_piezas (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_paquete);
			
	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				PiezaBean pieza = new PiezaBean();
				pieza.setIdPieza(resultado.getInt(1));
				pieza.setIdPaquete(resultado.getInt(2));
				pieza.setElemento(resultado.getString(3));
				pieza.setCorrelatividad(resultado.getString(4));
				pieza.setDescripcion(resultado.getString(5));
				pieza.setDescripcion_extra(resultado.getString(6));
				pieza.setLargo(resultado.getInt(7));
				pieza.setPintura(resultado.getBoolean(8));
				pieza.setColor(resultado.getString(9));
				pieza.setArea(resultado.getString(10));
				pieza.setCodigo(resultado.getInt(11));
				pieza.setCantidad(resultado.getInt(12));
				pieza.setUbicacion(resultado.getString(13));
				SubpiezaController subp=new SubpiezaController();
				ArrayList<SubpiezaBean> subpiezas=subp.getSubpiezasPorPiezaParaRevision(pieza.getIdPieza());
				pieza.setSubPiesas(subpiezas);
				listado.add(pieza);
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector<Object>> generarListaDePiezasParaAsignarMaterial( int obraNum, int listaNum) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			System.out.println(obraNum);
			System.out.println(listaNum);
			CallableStatement cs = conexion.prepareCall("{ ? = call paquete_devolver_piezas_para_asignar_materia_prima (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNum);
			cs.setInt(3, listaNum);
			ResultSet resultado = cs.executeQuery();

			while (resultado.next()) {
				System.out.println("pasada");

				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getInt(3));
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
	
	
	
	/**	public int insert(PiezaBean p) throws SQLException {

		try {
			String queryString = "INSERT INTO Piezas"
					+ "(id_obra, id_pieza_padre, id_material, descripcion, descripcion_extra,	elemento, correlatividad, ancho, largo, peso, pintura, color, area, obsevaciones) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			query.setLong(1, p.getId_obra());
			query.setLong(2, p.getId_pieza_padre());
			query.setLong(3, p.getId_material());
			query.setString(4, p.getDescripcion ());
			query.setString(5, p.getDescripcion_extra());
			query.setString(6, p.getElemento());
			query.setString(7, p.getCorrelatividad());
			query.setLong(8, p.getAncho());
			query.setLong(9, p.getLargo());
			query.setFloat(10, p.getPeso());
			query.setBoolean(11, p.isPintura());
			query.setString(12, p.getColor());
			query.setString(13, p.getArea());
			query.setString(14, p.getObservaciones());
			
			
			System.out.println("Se inserto");
			return query.executeUpdate();
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("No se inserto");
			return -1;
		} finally {

			conexion.close();
			
		}
	}
	
	
	public ArrayList<PiezaBean> getPorPaquete(long paqId) throws SQLException{
		try{
			String queryString = "SELECT * FROM Piezas where id_paquete=" + paqId;
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			ResultSet resultados = query.executeQuery();
			PiezaBean pieza = new PiezaBean();
			ArrayList<PiezaBean> piesas = new ArrayList<PiezaBean>();
			while(resultados.next()){
				
				pieza.setTodo(resultados.getLong(1), resultados.getLong(2), resultados.getLong(3), resultados.getLong(4), resultados.getString(5),
						resultados.getString(6), resultados.getString(7), resultados.getString(8), resultados.getLong(9), resultados.getLong(10),
						resultados.getFloat(11), resultados.getBoolean(12), resultados.getString(13), resultados.getString(14), resultados.getString(15));
				
				piesas.add(pieza);
			}
			return piesas;
		//	return resultados;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
		
	}

	public ArrayList<PiezaBean> crearListadoMock() {
		
		ArrayList<PiezaBean> listado = new ArrayList<PiezaBean>();
		for (int i = 0; i < 5; i++) {
			ArrayList<SubpiezaBean> subpiezas = new ArrayList<SubpiezaBean>();
			PiezaBean pieza = new PiezaBean();
			for (int j = 0; j < 3; j++) {
				SubpiezaBean subpieza = new SubpiezaBean();
				ArrayList<TareaBean> tareas = new ArrayList<TareaBean>();
				for (int k = 0; k < 2; k++) {
					TareaBean tarea = new TareaBean();
					tarea.setTodo(311, "Tarea"+ k, "Es la tarea"+ k, "Primarios");
					tareas.add(tarea);
				}
				subpieza.setTodo(100+j, 10+j, i, 211, "Es la subpieza " + j, null,"ele " + j,"corr " + j, 0, 0, 0, false, null, null, null);
				subpieza.setTareas(tareas);
				subpiezas.add(subpieza);
			}
		pieza.setSubPiesas(subpiezas);
		pieza.setTodo(i, i, i, 211, "Es la pieza " + i, null,"ele " + i,"corr " + i, 0, 0, 0, false, null, null, null);
		listado.add(pieza);
		}
		return listado;
		
	}

	*/
	
public PiezaBean getPiezaPorObraPaqueteElementoCorrelatividad(int num_obra,int num_paq,String elemento,String correlatividad) throws SQLException {
		
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.paquete_devolver_pieza_XObraPaqueteElementoCorrelatividad (?,?,?,?)}");
			System.out.println("LLEGO!");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, num_obra);
			cs.setInt(3, num_paq);
			cs.setString(4, elemento);
			cs.setString(5, correlatividad);
			System.out.println(num_obra);
			System.out.println(num_paq);
			System.out.println(elemento);
			System.out.println(correlatividad);
			System.out.println("LLEGO2!");
			ResultSet resultado = cs.executeQuery();
			System.out.println("LLEGO3!");
			PiezaBean pieza = new PiezaBean();
			resultado.next();
			System.out.println("LLEGO4!");
				pieza.setIdPieza(resultado.getInt(1));
				System.out.println("LLEGO5!");
				pieza.setIdPaquete(resultado.getInt(2));
				pieza.setElemento(resultado.getString(3));
				pieza.setCorrelatividad(resultado.getString(4));
				pieza.setDescripcion(resultado.getString(5));
				pieza.setDescripcion_extra(resultado.getString(6));
				pieza.setLargo(resultado.getInt(7));
				pieza.setPintura(resultado.getBoolean(8));
				pieza.setColor(resultado.getString(9));
				pieza.setArea(resultado.getString(10));
				pieza.setCodigo(resultado.getInt(11));
				pieza.setCantidad(resultado.getInt(12));
				pieza.setUbicacion(resultado.getString(13));
				SubpiezaController subp=new SubpiezaController();
				ArrayList<SubpiezaBean> subpiezas=subp.getSubpiezasPorPiezaRecuperadas(pieza.getIdPieza());
				pieza.setSubPiesas(subpiezas);
				
				return(pieza);
				
			
		}catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		    
		} finally {
			conexion.close();
		}
	}	
	
}