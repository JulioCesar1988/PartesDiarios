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
import models.EmpleadoBean;

public class EmpleadoController {

	Conector c;
	Connection conexion;

	public int insert(EmpleadoBean empleado) throws SQLException {

		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call empleados_cargar ( ?, ?, ?, ?,?,?,?)}");

			cs.setString(1, empleado.getNombre());
			cs.setString(2, empleado.getApellido());
			cs.setString(3, empleado.getSector());

			cs.setString(4, empleado.getEmpresa());
			cs.setInt(5, empleado.getLegajo());
			cs.setString(6, empleado.getFechaIngreso());
			cs.registerOutParameter(7, Types.INTEGER);
			cs.execute();
			return cs.getInt(7);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conexion.close();
		}
	}

	public int getPorEmpresaLegajo(String empresaLegajo, EmpleadoBean empleado) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.empleados_devolver (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, empresaLegajo);
			ResultSet resultado = null;
			resultado = cs.executeQuery();
			if (resultado.next()) {
				empleado.setNombre(resultado.getString(1));
				empleado.setApellido(resultado.getString(2));
				empleado.setSector(resultado.getString(3));
				empleado.setEmpresa(resultado.getString(4));
				empleado.setLegajo(resultado.getInt(5));
				empleado.setFechaIngreso(resultado.getString(6));
				empleado.setFechaEgreso(resultado.getString(7));
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return -1;
		} finally {
			conexion.close();
		}

	}

	public int getEmpresaLegajo2(String empresaLegajo, EmpleadoBean empleado) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.empleados_devolver_para_crear_usuario (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, empresaLegajo);
			ResultSet resultado = null;
			resultado = cs.executeQuery();
			if (resultado.next()) {
				empleado.setNombre(resultado.getString(1));
				empleado.setApellido(resultado.getString(2));
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return -1;
		} finally {
			conexion.close();
		}
	}

	public int getPorDni3(String empresaLegajo, EmpleadoBean empleado) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.empleados_devolver_para_modificar_usuario (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, empresaLegajo);
			ResultSet resultado = null;
			resultado = cs.executeQuery();
			if (resultado.next()) {
				empleado.setNombre(resultado.getString(1));
				empleado.setApellido(resultado.getString(2));
				empleado.setNivel(resultado.getInt(3));
				empleado.setContrasenia(resultado.getString(4));
				empleado.setUsuario(resultado.getBoolean(5));

				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return -1;
		} finally {
			conexion.close();
		}
	}

	public int modificar(EmpleadoBean empleado) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call empleados_modificar (?, ?, ?, ?, ?,?,?,?)}");

			cs.setString(1, empleado.getNombre());
			cs.setString(2, empleado.getApellido());
			cs.setString(3, empleado.getSector());
			cs.setString(4, empleado.getEmpresa());
			cs.setInt(5, empleado.getLegajo());
			cs.setString(6, empleado.getFechaIngreso());
			cs.setString(7, empleado.getFechaEgreso());

			cs.registerOutParameter(8, Types.INTEGER);
			cs.execute();
			return cs.getInt(8);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conexion.close();
		}
	}

	public int eliminar(String empresaLegago) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call empleados_eliminar (?, ?)}");
			cs.setString(1, empresaLegago);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			return cs.getInt(2);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conexion.close();
		}
	}

	public int modificarDatosUsuario(EmpleadoBean empleado, String empresaLegajo) throws SQLException {

		try {

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call empleados_generar_usuario (?, ?, ?, ?, ?)}");
			cs.setString(1, empresaLegajo);
			cs.setInt(2, empleado.getNivel());
			cs.setString(3, empleado.getContrasenia());
			cs.setBoolean(4, empleado.getUsuario());
			cs.registerOutParameter(5, Types.INTEGER);
			cs.execute();
			return cs.getInt(5);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			conexion.close();
		}
	}

	public String getNivelFull(int nivel) throws SQLException {
		try {

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{?=call niveles_devolver_para_combobox (?)}");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, nivel);
			ResultSet resultado = null;
			resultado = cs.executeQuery();
			if (resultado.next()) {
				String aux = resultado.getInt(1) + " - " + resultado.getString(2);
				return aux;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conexion.close();
		}

	}

	public String[] getListaDeLegajoNombre(String empresa) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.empleados_devolver_lista_por_empresa (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, empresa);
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
	
	public String[] getListaDeLegajoNombreAll() throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.empleados_devolver_lista_por_empresaAll()}");
			cs.registerOutParameter(1, Types.OTHER);
			///cs.setString(2, empresa);
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
	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector[] = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);
		}
		return vector;
	}

	public EmpleadoBean getEmpleadoPorEmpresaLegajo(Integer legajo, String empresa) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{?=call empleados_devolver_por_empresa_legajo (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, legajo);
			cs.setString(3, empresa);
			ResultSet resultado = null;
			resultado = cs.executeQuery();
			if (resultado.next()) {
				EmpleadoBean empleado = new EmpleadoBean();
				empleado.setNombre(resultado.getString(1));
				empleado.setApellido(resultado.getString(2));
				empleado.setSector(resultado.getString(3));
				empleado.setEmpresa(resultado.getString(4));
				empleado.setLegajo(resultado.getInt(5));
				return empleado;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector<String>> getListaDeEmpleadoCreador(int obraNumero, int paqueteNumero) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.empleados_devolver_nombre_apellido_de_creador_paquete (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNumero);
			cs.setInt(3, paqueteNumero);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<String>> lista = new ArrayList<>();
			while (resultado.next()) {
				Vector<String> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				lista.add(vector);
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector<String>> getListaDeEmpleadoModificadores(int obraNumero, int paqueteNumero)
			throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.empleados_devolver_nombre_apellido_de_revizador_paquete (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNumero);
			cs.setInt(3, paqueteNumero);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<String>> lista = new ArrayList<>();
			while (resultado.next()) {
				Vector<String> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				lista.add(vector);
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector<String>> getListaDeEmpleadoAprovador(int obraNumero, int paqueteNumero)
			throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.empleados_devolver_nombre_apellido_de_aprobador_paquete (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNumero);
			cs.setInt(3, paqueteNumero);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<String>> lista = new ArrayList<>();
			while (resultado.next()) {
				Vector<String> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				lista.add(vector);
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector<String>> getListaDeEmpleadoAprovadorProduccion(int obraNumero, int paqueteNumero)
			throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall(
					"{ ? = call dbo.empleados_devolver_nombre_apellido_de_aprobador_produccion_paquete (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNumero);
			cs.setInt(3, paqueteNumero);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<String>> lista = new ArrayList<>();
			while (resultado.next()) {
				Vector<String> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				lista.add(vector);
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<Vector<String>> getListaDeEmpleadoEnviadorProduccion(int obraNumero, int paqueteNumero)
			throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion
					.prepareCall("{ ? = call dbo.empleados_devolver_nombre_apellido_de_enviador_paquete (?, ?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obraNumero);
			cs.setInt(3, paqueteNumero);
			ResultSet resultado = cs.executeQuery();
			ArrayList<Vector<String>> lista = new ArrayList<>();
			while (resultado.next()) {
				Vector<String> vector = new Vector<>();
				vector.add(resultado.getString(1));
				vector.add(resultado.getString(2));
				lista.add(vector);
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public ArrayList<String> getCorreosXAccionNivel(String nivel) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			System.out.println("llego1");
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.empleados_devolver_mail3 (?)}");
			System.out.println("llego2");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, nivel);
			System.out.println("llego3");
			ResultSet resultado = cs.executeQuery();
			System.out.println("llego4");
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {

				lista.add(resultado.getString(1));
				System.out.println("el tamaño de la lista es:" + lista.size());
			}
			return lista;
		} catch (Exception e) {
			System.out.println("Consulta erronea");
			return null;
		} finally {
			conexion.close();
		}
	}

	public int getIdEmpleadoPorLegajo(int numLegajo) throws Exception {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call parte_piezas_empleado_get_id (?)}");
			cs.setInt(1, numLegajo);
			ResultSet resultado = cs.executeQuery();
			if (resultado.next()) {
				return resultado.getInt(1);
			} else {
				throw new Exception("No se ha podido recuperar el emplead");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No se ha podido recuperar el empleado");
		} finally {
			conexion.close();
		}

	}

}
