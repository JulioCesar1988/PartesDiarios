package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import bd.Conector;
import bd.ParametrosConexion;
import models.CamionBean;


public class CamionController {

	Conector c;
	Connection conexion; 
	
	public int insert(CamionBean camion) throws SQLException {
	
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call carga_camionesV2 (?, ?, ?)}");
			cs.setString(1, camion.getPatente());
			cs.setString(2, camion.getCompania());
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
	
	public CamionBean getPorPatente(String patente) throws SQLException{
		try{
			String queryString = "SELECT * FROM Camiones WHERE patente='"+patente+"' and activado=1;";			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			ResultSet resultados = query.executeQuery();
			resultados.next();
			CamionBean camion = new CamionBean();
			camion.setTodo(resultados.getInt(1), resultados.getString(2), resultados.getString(3));
			return camion;
		//	return resultados;
		}catch(Exception e){
			System.out.println("Consulta erronea pat");
				return null;
			}
			finally{
				conexion.close();
			}
		}
	public int getPorPat(String pat, CamionBean camion) throws SQLException{
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.devolver_camion (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, pat);
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			if (resultado.next()) {
				camion.setCompania(resultado.getString(1));
				
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
public int modificar(CamionBean camion) throws SQLException {
		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call camiones_modificar (?, ?, ?)}");
			cs.setString(1, camion.getPatente());
			cs.setString(2, camion.getCompania());
			
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
public int eliminar(String patente) throws SQLException {
	try {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		CallableStatement cs = conexion.prepareCall("{call camiones_eliminar (?, ?)}");
		cs.setString(1, patente);
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
}
