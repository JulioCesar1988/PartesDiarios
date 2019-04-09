package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import models.SubpiezaBean;
import models.TareaBean;
import bd.Conector;
import bd.ParametrosConexion;



public class SubpiezaController {

	Conector c;
	Connection conexion;
	
	public int insert(SubpiezaBean subpieza, Connection conexion) throws SQLException {
		int aux = 1;
		try {
			CallableStatement cs = conexion.prepareCall("{call subpieza_cargar (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, subpieza.getIdPiezaPadre());
			cs.setInt(2, subpieza.getIdMaterial());
			cs.setString(3, subpieza.getDescripcion());
			cs.setString(4, subpieza.getElemento());
			cs.setString(5, subpieza.getCorrelatividad());
			cs.setInt(6, subpieza.getAncho());
			cs.setFloat(7, subpieza.getLargo());
			cs.setFloat(8, subpieza.getPeso());
			cs.setString(9, subpieza.getObservaciones());
			cs.registerOutParameter(10, Types.INTEGER);
			cs.execute();
			subpieza.setIdSubpieza(cs.getInt(10));
			for (int i = 0; i < subpieza.getTareas().size(); i++) {
				TareaController tController = new TareaController();
				aux = tController.asociar(subpieza.getTareas().get(i), subpieza.getIdSubpieza(), conexion);
			}
			return aux;
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;	
		}
	}
	
	public int insertConGeneracionDeCodigos(SubpiezaBean subpieza, Connection conexion) throws SQLException {
		int aux = 1;
		try {
			CallableStatement cs = conexion.prepareCall("{call subpieza_cargar_generar_codigo (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)}");
			cs.setInt(1, subpieza.getIdPiezaPadre());
			cs.setInt(2, subpieza.getIdMaterial());
			cs.setString(3, subpieza.getDescripcion());
			cs.setString(4, subpieza.getElemento());
			cs.setString(5, subpieza.getCorrelatividad());
			cs.setInt(6, subpieza.getAncho());
			cs.setFloat(7, subpieza.getLargo());
			cs.setFloat(8, subpieza.getPeso());
			cs.setString(9, subpieza.getObservaciones());
			cs.setInt(10, subpieza.getCodigo());
			cs.registerOutParameter(11, Types.INTEGER);
			cs.registerOutParameter(12, Types.INTEGER);
			
			cs.execute();
			subpieza.setIdSubpieza(cs.getInt(11));
			subpieza.setCodigo(cs.getInt(12));
			for (int i = 0; i < subpieza.getTareas().size(); i++) {
				TareaController tController = new TareaController();
				aux = tController.asociar(subpieza.getTareas().get(i), subpieza.getIdSubpieza(), conexion);
			}
			return aux;
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;	
		}
	}
	
	
	public int insertDeTareas(SubpiezaBean subpieza, Connection conexion) throws SQLException {
		int aux = 1;
		try {
			for (int i = 0; i < subpieza.getTareas().size(); i++) {
				TareaController tController = new TareaController();
				aux = tController.asociarPorCodigo(subpieza.getTareas().get(i), subpieza.getCodigo());
			}
			return aux;
			}catch (Exception e) {
				e.printStackTrace();
				return -1;	
		}
	}
	
	public ArrayList<SubpiezaBean> getSubpiezasPorPiezaRecuperadas(int id_pieza) throws SQLException {
		ArrayList<SubpiezaBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.pieza_devolver_subpiezas_recuperadas (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_pieza);
			
			
			
	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				SubpiezaBean spieza = new SubpiezaBean();
				spieza.setIdSubpieza(resultado.getInt(1));
				spieza.setIdPiezaPadre(resultado.getInt(2));
				spieza.setIdMaterial(resultado.getInt(3));//modificado
				if(resultado.getInt(3)!=0){
				spieza.setCodigoMaterial(resultado.getString(4));//modificado
				}
				spieza.setElemento(resultado.getString(5));
				spieza.setCorrelatividad(resultado.getString(6));
				spieza.setDescripcion(resultado.getString(7));
				spieza.setAncho(resultado.getInt(8));
				spieza.setLargo(resultado.getFloat(9));
				spieza.setPeso(resultado.getFloat(10));//modificado
				spieza.setObservaciones(resultado.getString(11));
				if(resultado.getInt(3)!=0){
				spieza.setDescripcionMaterial(resultado.getString(12)+" - "+resultado.getString(13)+" "+resultado.getString(14)+" - "+resultado.getString(15));//modificado
				}
				spieza.setCodigo(resultado.getInt(16));
				spieza.setCantidad(resultado.getInt(17));
				spieza.setPesoMaterial(resultado.getFloat(18));//modificado
				spieza.setUnidadPeso(resultado.getString(19));//modificado

				TareaController tar=new TareaController();
				ArrayList<TareaBean> tareas=tar.getTareasPorSubpieza(spieza.getIdSubpieza());
				spieza.setTareas(tareas);
				
				
				listado.add(spieza);
				
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}
	}
	
	public ArrayList<SubpiezaBean> getSubpiezasPorPiezaSinTareas(int id_pieza) throws SQLException {
		ArrayList<SubpiezaBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.pieza_devolver_subpiezas (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, id_pieza);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				SubpiezaBean spieza = new SubpiezaBean();
				spieza.setIdSubpieza(resultado.getInt(1));
				spieza.setIdPiezaPadre(resultado.getInt(2));
				spieza.setIdMaterial(resultado.getInt(3));
				spieza.setCodigoMaterial(resultado.getString(4));
				spieza.setElemento(resultado.getString(5));
				spieza.setCorrelatividad(resultado.getString(6));
				spieza.setDescripcion(resultado.getString(7));
				spieza.setAncho(resultado.getInt(8));
				spieza.setLargo(resultado.getFloat(9));
				spieza.setPeso(resultado.getFloat(10));
				spieza.setObservaciones(resultado.getString(11));
				spieza.setDescripcionMaterial(resultado.getString(12)+" - "+resultado.getString(13)+" "+resultado.getString(14)+" - "+resultado.getString(15));
				spieza.setCodigo(resultado.getInt(16));
				spieza.setCantidad(resultado.getInt(17));
				spieza.setPesoMaterial(resultado.getFloat(18));
				spieza.setUnidadPeso(resultado.getString(19));
				TareaController tar=new TareaController();
				ArrayList<TareaBean> tareas=tar.setTareasPorSubpiezaWarehouse(spieza.getIdMaterial());
				spieza.setTareas(tareas);
				
				listado.add(spieza);
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}	
	}
		
		
		
		public ArrayList<SubpiezaBean> getSubpiezasPorPiezaParaRevision(int id_pieza) throws SQLException {
			ArrayList<SubpiezaBean> listado = new ArrayList<>();
			try{
				c = new Conector(ParametrosConexion.getParametros());
				conexion = c.getConnection();
				CallableStatement cs = conexion.prepareCall("{ ? = call dbo.pieza_devolver_subpiezas (?)}");
				cs.registerOutParameter(1, Types.OTHER);
				cs.setInt(2, id_pieza);	
				ResultSet resultado = cs.executeQuery();
				while (resultado.next()) {
					SubpiezaBean spieza = new SubpiezaBean();
					spieza.setIdSubpieza(resultado.getInt(1));
					spieza.setIdPiezaPadre(resultado.getInt(2));
					spieza.setIdMaterial(resultado.getInt(3));
					spieza.setCodigoMaterial(resultado.getString(4));
					spieza.setElemento(resultado.getString(5));
					spieza.setCorrelatividad(resultado.getString(6));
					spieza.setDescripcion(resultado.getString(7));
					spieza.setAncho(resultado.getInt(8));
					spieza.setLargo(resultado.getFloat(9));
					spieza.setPeso(resultado.getFloat(10));
					spieza.setObservaciones(resultado.getString(11));
					spieza.setDescripcionMaterial(resultado.getString(12)+" - "+resultado.getString(13)+" "+resultado.getString(14)+" - "+resultado.getString(15));
					spieza.setCodigo(resultado.getInt(16));
					spieza.setCantidad(resultado.getInt(17));
					spieza.setPesoMaterial(resultado.getFloat(18));
					spieza.setUnidadPeso(resultado.getString(19));
					TareaController tar=new TareaController();
					ArrayList<TareaBean> tareas=tar.getTareasPorSubpiezaParaRevision(spieza.getIdSubpieza());
					System.out.println("busca con el id"+ spieza.getIdSubpieza());
					spieza.setTareas(tareas);	
					listado.add(spieza);	
				}		
				return listado;
			}catch (Exception e) {
			      e.printStackTrace();
			      return listado;
			} finally {
				conexion.close();
			}
		}
}