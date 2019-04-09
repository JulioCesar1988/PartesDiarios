package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Vector;

import models.ParteDiarioTareaBean;
import bd.Conector;
import bd.ParametrosConexion;




public class ParteDiarioController {


	Conector c;
	Connection conexion;

	public int insert(ParteDiarioTareaBean part) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call parte_diario_tarea_cargar (?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setString(1, part.getEmpresaEmpleado());
			cs.setInt(2, part.getLegajoElmpleado());
			cs.setString(3, part.getTareaCodigo());
			cs.setInt(4, part.getNumeroObra());
			cs.setString(5, part.getFecha());
			cs.setString(6, part.getHoraInicio());
			cs.setString(7, part.getHoraFin());
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

	public int delete(ParteDiarioTareaBean part) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call parte_diario_tarea_eliminar (?, ?, ?, ?, ?, ?, ?, ?)}");
			cs.setString(1, part.getEmpresaEmpleado());
			System.out.println(part.getEmpresaEmpleado());
			cs.setInt(2, part.getLegajoElmpleado());
			System.out.println(part.getLegajoElmpleado());
			cs.setString(3, part.getTareaCodigo());
			System.out.println(part.getTareaCodigo());
			cs.setInt(4, part.getNumeroObra());
			System.out.println(part.getNumeroObra());
			cs.setString(5, part.getFecha());
			System.out.println(part.getFecha());
			cs.setString(6, part.getHoraInicio());
			System.out.println(part.getHoraInicio());
			cs.setString(7, part.getHoraFin());
			System.out.println(part.getHoraFin());
			
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
	
	public ArrayList<Vector<Object>>getTareasPorFecha(String fecha) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call parte_diario_tarea_devolver_por_fecha (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, fecha);
			ResultSet resultado = cs.executeQuery();		
			ArrayList<Vector<Object>> lista = new ArrayList<>();
			while(resultado.next()){
				Vector<Object> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getInt(3));
				vector.add(resultado.getString(4));
				vector.add(resultado.getInt(5));
				vector.add(resultado.getString(6));
				vector.add(resultado.getString(7));
				vector.add(resultado.getString(8));
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
	
}
