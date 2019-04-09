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
import models.MasterparteBean;
import models.PiezaBean;
import models.SubpiezaBean;

public class MasterparteController {
	Conector c;
	Connection conexion;

	public int buscarMaster(int num_legajo, String tarea, String sector, String fecha) throws Exception {

		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.parte_piezas_master_buscar(?,?,?,?)}");
			cs.setInt(1, num_legajo);
			cs.setString(2, sector);
			cs.setString(3, tarea);
			cs.setString(4, fecha);
			ResultSet resultado = cs.executeQuery();
			if (resultado.next()) {
				return resultado.getInt(1);
			} else {
				throw new Exception("No se encontro el Maestro");
			}
		} catch (Exception e) {
			throw new SQLException("Error al buscar el Master");
		} finally {
			conexion.close();
		}
	}

}
