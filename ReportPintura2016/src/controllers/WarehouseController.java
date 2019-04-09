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
import models.WarehousePaqueteElemento;


public class WarehouseController {
	
	Conector c;
	Connection conexion; 
	
	
	public ArrayList<Vector<Object>> getListaElementosNoEmpaquetados(int obra) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call warehouse_devolver_no_tratados (?)}");
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
				vector.add(5, resultado.getString(6));
				vector.add(6, resultado.getString(7));
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
	
	public ArrayList<Vector<Object>> getListaElementosWarehouseParciales(int obra) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call warehouse_devolver_paquetes_cantidad_total (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getString(1));
				vector.add(1, resultado.getString(2));
				vector.add(2, resultado.getString(3));
				vector.add(3, resultado.getString(4));
				vector.add(4, resultado.getInt(5));
				vector.add(5, resultado.getString(6));
				vector.add(6, resultado.getString(7));
				vector.add(7, resultado.getString(8));
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
	
	public ArrayList<Vector<Object>> getListaDeEmpaquetados(int idElementoCantidad) throws SQLException {
		ArrayList<Vector<Object>> lista = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call warehouse_devolver_paquetes_cantidad_parcial (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, idElementoCantidad);
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(0, resultado.getInt(1));
				vector.add(1, resultado.getString(2));
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
	
	public int guardarPaqueteNuevo (WarehousePaqueteElemento wElemento) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call warehouse_guardar_paquete_nuevo (?, ?, ?, ?)}");
			cs.setInt(1, wElemento.getIdPieza());
			cs.setInt(2, wElemento.getCantidadTotal());
			cs.setInt(3, wElemento.getCantidadParcial());
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

	public void insertar(int parseInt, int idElementoCantidad) throws SQLException {
		// TODO Auto-generated method stub
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call warehouse_guardar_paquete_parcial (?, ?)}");
			cs.setInt(1, parseInt);
			cs.setInt(2, idElementoCantidad);
			cs.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			conexion.close();
		}
	}
	
}