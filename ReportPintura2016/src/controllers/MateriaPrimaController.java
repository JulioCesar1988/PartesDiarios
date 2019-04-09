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
import models.MateriaPrimaBean;
import session.Session;

public class MateriaPrimaController {


	Conector c;
	Connection conexion; 
	
	public int insertMateriaPrima(MateriaPrimaBean materiaPrima) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.materia_prima_insertar (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			cs.setString(1, materiaPrima.getTipo());
			cs.setString(2, materiaPrima.getDescripcion());
			cs.setFloat(3, materiaPrima.getEspesor());
			cs.setString(4, materiaPrima.getCalidad());
			cs.setString(5, materiaPrima.getRemitoNum());
			cs.setString(6, materiaPrima.getEmpresa());
			cs.setString(7, materiaPrima.getFechaIngreso());
			cs.setFloat(8, materiaPrima.getPeso());
			cs.setString(9, materiaPrima.getColada());
			cs.setString(10, materiaPrima.getOrigen());
			cs.setString(11, materiaPrima.getPadre());
			cs.setString(12, materiaPrima.getBoletaNum());
			cs.setString(13, materiaPrima.getCodigoTango());
			cs.setString(14, materiaPrima.getCodigoBobinaPadre());
			cs.setString(15, materiaPrima.getObservaciones());
			cs.setString(16, Session.getNombreUsuario());
			cs.setString(17, materiaPrima.getnCertificado());
			cs.setString(18, materiaPrima.getUrlCertificado());
			cs.setFloat(19, materiaPrima.getCantidad());
			cs.registerOutParameter(20, Types.VARCHAR);
			cs.registerOutParameter(21, Types.DATE);
			cs.registerOutParameter(22, Types.VARCHAR);
			cs.registerOutParameter(23, Types.FLOAT);
			cs.registerOutParameter(24, Types.INTEGER);
			cs.execute();
			if (cs.getInt(24)==1) {
				materiaPrima.setCodigo(cs.getString(20));
				materiaPrima.setFechaCarga(cs.getDate(21));
				materiaPrima.setDescripcion(cs.getString(22));
				materiaPrima.setEspesor(cs.getFloat(23));
			}
			System.out.println(cs.getInt(24));
			return cs.getInt(24);
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return 0;
		}
		finally{
			conexion.close();
		}
	}

	public ArrayList<Vector<Object>> generarListaDeMateriaPrimaEnStock(String tipo) throws SQLException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		ArrayList<Vector<Object>> lista = new ArrayList<>(); 
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materia_prima_lista_en_stock (?)}");	
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, tipo);
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		finally{
			conexion.close();
		}
	}
	
	public int asignarMateriaPrimaEnStock(String codigoMateriaPrima, int codigoPieza, MateriaPrimaBean mPrima) throws SQLException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		MateriaPrimaBean materia = new MateriaPrimaBean();
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.materia_prima_asignar_a_pieza (?, ?, ?, ?)}");	
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, codigoPieza);
			cs.setString(3, codigoMateriaPrima);
			cs.setString(4, Session.getNombreUsuario());
			cs.registerOutParameter(5, Types.INTEGER);
			ResultSet resultado = cs.executeQuery();
			if (resultado.next()) {
				mPrima.setCodigo(resultado.getString(1));
				mPrima.setDescripcion(resultado.getString(2));
				mPrima.setEspesor(resultado.getFloat(3));
				mPrima.setCalidad(resultado.getString(4));
				mPrima.setEmpresa(resultado.getString(5));
				mPrima.setRemitoNum(resultado.getString(6));
				mPrima.setFechaIngreso(resultado.getString(7));
				
			}
			
			mPrima=materia;
			return cs.getInt(5);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} 
		finally{
			conexion.close();
		}
	}

	public ArrayList<Vector<Object>> generarContenidoTablaMateriasPrimasAsignadasPorCodigo(int codigo) throws SQLException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		ArrayList<Vector<Object>>  lista = new ArrayList<>(); 
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.[materia_prima_lista_por_codigo_de_piezas] (?)}");	
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, codigo);		
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getFloat(3));
				vector.add(resultado.getString(4));
				vector.add(resultado.getString(5));
				vector.add(resultado.getString(6));
				vector.add(resultado.getString(7));
				lista.add(vector);
			}		
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		finally{
			conexion.close();
		}	
	}

	public ArrayList<Vector<Object>> generarContenidoTablaMateriasPrimasPorCodigo(String codigo, boolean incluirFueraStock) throws SQLException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		ArrayList<Vector<Object>>  lista = new ArrayList<>(); 
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.[materia_prima_por_codigo] (?, ?)}");	
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, codigo);
			cs.setBoolean(3, incluirFueraStock);	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getString(3));
				lista.add(vector);
			}		
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		finally{
			conexion.close();
		}		
	}

	public ArrayList<Vector<Object>> generarContenidoTablaMateriasPrimasPorFechas(String desde, String hasta, boolean incluirFueraStock) throws SQLException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		ArrayList<Vector<Object>>  lista = new ArrayList<>(); 
		try {
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.[materia_prima_por_fechas] (?, ?, ?)}");	
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, desde);
			cs.setString(3, hasta);		
			cs.setBoolean(4, incluirFueraStock);	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				Vector<Object> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				vector.add(resultado.getString(3));
				lista.add(vector);
			}		
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		finally{
			conexion.close();
		}	
	}
	
	public MateriaPrimaBean devolverMateriaPrimaPorCodigo(String codigo) throws SQLException {
		MateriaPrimaBean materiaPrima = new MateriaPrimaBean();
		try{
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{? = call dbo.materia_prima_devolver_por_codigo (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, codigo);

			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				materiaPrima.setCodigo(resultado.getString(1));
				materiaPrima.setTipo(resultado.getString(2));
				materiaPrima.setDescripcion(resultado.getString(3));
				materiaPrima.setEspesor(resultado.getFloat(4));
				materiaPrima.setCalidad(resultado.getString(5));
				materiaPrima.setRemitoNum(resultado.getString(6));
				materiaPrima.setEmpresa(resultado.getString(7));
				materiaPrima.setFechaIngreso(resultado.getString(8));
				materiaPrima.setColada(resultado.getString(9));
				materiaPrima.setOrigen(resultado.getString(10));
				materiaPrima.setPadre(resultado.getString(11));
				materiaPrima.setCodigoTango(resultado.getString(12));
				materiaPrima.setBoletaNum(resultado.getString(13));
				materiaPrima.setCodigoBobinaPadre(resultado.getString(14));
				materiaPrima.setObservaciones(resultado.getString(15));
				materiaPrima.setEnStock(resultado.getBoolean(16));
				materiaPrima.setnCertificado(resultado.getString(17));
				materiaPrima.setUrlCertificado(resultado.getString(18));
				materiaPrima.setCantidad(resultado.getFloat(19));
				materiaPrima.setPeso(resultado.getFloat(20));

			}		
			return materiaPrima;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}

	public int updateMateriaPrima(MateriaPrimaBean materiaPrima) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.materia_prima_actualizar (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			cs.setString(1, materiaPrima.getCodigo());
			System.out.println("lalaal");
			System.out.println(materiaPrima.getCodigo());
			cs.setString(2, materiaPrima.getDescripcion());
			cs.setFloat(3, materiaPrima.getEspesor());
			cs.setString(4, materiaPrima.getCalidad());
			cs.setString(5, materiaPrima.getRemitoNum());
			cs.setString(6, materiaPrima.getEmpresa());
			cs.setString(7, materiaPrima.getFechaIngreso());
			cs.setFloat(8, materiaPrima.getPeso());
			cs.setString(9, materiaPrima.getColada());
			cs.setString(10, materiaPrima.getOrigen());
			cs.setString(11, materiaPrima.getPadre());
			cs.setString(12, materiaPrima.getBoletaNum());
			cs.setString(13, materiaPrima.getCodigoTango());
			cs.setString(14, materiaPrima.getCodigoBobinaPadre());
			cs.setString(15, materiaPrima.getObservaciones());
			cs.setBoolean(16, materiaPrima.isEnStock());
			cs.setString(17, materiaPrima.getnCertificado());
			cs.setString(18, materiaPrima.getUrlCertificado());
			cs.setFloat(19, materiaPrima.getCantidad());
			cs.setString(20, Session.getNombreUsuario());
			cs.registerOutParameter(21, Types.INTEGER);
			cs.execute();
			System.out.println("ejecuta ");
			return cs.getInt(21);
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return 0;
		}
		finally{
			conexion.close();
		}
	}
	
		public int deleteMateriaPrima(String codigo) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.materia_prima_eliminar (?, ?, ?)}");
			
			cs.setString(1, codigo);
			cs.setString(2, Session.getNombreUsuario());
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			System.out.println("ejecuta ");
			return cs.getInt(3);
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return 0;
		}
		finally{
			conexion.close();
		}
	}

	public int quitarAsignacion(String codigoAsignacion, int codigo) throws SQLException {
		System.out.println(codigoAsignacion);
		System.out.println(codigo);
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call dbo.materia_prima_quitar_asignacion (?, ?, ?)}");
			cs.setString(1, codigoAsignacion);
			cs.setInt(2, codigo);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			return cs.getInt(3);
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return 0;
		}
		finally{
			conexion.close();
		}
	}
	
	
	
	
}
