
package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import models.AreaBean;    //despue lo tengo que eliminar.
import models.ParteBean;
import bd.Conector;
import bd.ParametrosConexion;

public class ParteController {
	Conector c;
	Connection conexion; 
	  
	

	
	
	// PENDIENTE 
	public int modificarParte(AreaBean area,String arCom) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call parte_modificar (?, ?, ?)}");
			cs.setString(1, area.getNombre());
			cs.setString(2, arCom);
			
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
	
	
	// PENDIENTE 
	public int eliminarParte(String parte) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call parte_eliminar (?, ?)}");
			cs.setString(1, parte);
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