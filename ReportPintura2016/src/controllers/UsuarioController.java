package controllers;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import session.Permiso;
import bd.Conector;
import bd.ParametrosConexion;


public class UsuarioController {
	Conector c;
	Connection conexion; 
	
	
	
	public int getUsuario(int usuario, String password) throws SQLException{
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.empleados_devolver_login (?,?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, usuario);
			cs.setString(3, password);
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			if (resultado.next()) {
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

	
	public int generarPermisos(String usuario, String password, Permiso permisos) throws SQLException{
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call empleados_devolver_permisos (?,?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setString(2, usuario);
			cs.setString(3, password);
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			if (resultado.next()) {
				permisos.setCargar_obra(resultado.getBoolean(1));
				permisos.setModificar_obra(resultado.getBoolean(2));
				permisos.setCargar_edificio(resultado.getBoolean(3));
				permisos.setModificar_edificio(resultado.getBoolean(4));
				permisos.setCargar_orden(resultado.getBoolean(5));
				permisos.setModificar_orden(resultado.getBoolean(6));
				permisos.setRevisar_orden(resultado.getBoolean(7));
				permisos.setAprobar_orden(resultado.getBoolean(8));
				permisos.setAprobar_produccion_orden(resultado.getBoolean(9));
				permisos.setDefinir_tareas_piezas(resultado.getBoolean(10));
				permisos.setModificar_tareas_piezas(resultado.getBoolean(11));
				permisos.setAprobar_piezas_finalizadas(resultado.getBoolean(12));
				permisos.setGenerar_reporte_piezas(resultado.getBoolean(13));
				permisos.setGenerar_archivo_etiquetas(resultado.getBoolean(14));
				permisos.setNuevo_elemento_precargado(resultado.getBoolean(15));
				permisos.setCargar_remito(resultado.getBoolean(16));
				permisos.setRemito_cargados(resultado.getBoolean(17));
				permisos.setCargar_camion(resultado.getBoolean(18));
				permisos.setModificar_camion(resultado.getBoolean(19));
				permisos.setCargar_chofer(resultado.getBoolean(20));
				permisos.setModificar_chofer(resultado.getBoolean(21));
				permisos.setTareas_pendiente(resultado.getBoolean(22));
				permisos.setPintura(resultado.getBoolean(23));
				permisos.setCargar_tareas(resultado.getBoolean(24));
				permisos.setModificar_tareas(resultado.getBoolean(25));
				permisos.setReporte_tareas(resultado.getBoolean(26));
				permisos.setReporte_tareas_planta(resultado.getBoolean(27));
				permisos.setCargar_parte_diario_tareas(resultado.getBoolean(28));
				permisos.setGestionar_parte_diario(resultado.getBoolean(29));
				permisos.setCargar_empleados(resultado.getBoolean(30));
				permisos.setModificar_empleados(resultado.getBoolean(31));
				permisos.setReporte_empleados(resultado.getBoolean(32));
				permisos.setCargar_materiales(resultado.getBoolean(33));
				permisos.setModificar_materiales(resultado.getBoolean(34));
				permisos.setReporte_materiales(resultado.getBoolean(35));
				permisos.setDar_alta_usuario(resultado.getBoolean(36));
				permisos.setModificar_usuario(resultado.getBoolean(37));
				permisos.setCargar_sector(resultado.getBoolean(38));
				permisos.setModificar_sector(resultado.getBoolean(39));
				permisos.setCargar_sistema_diseño(resultado.getBoolean(40));
				permisos.setModificar_sistema_diseño(resultado.getBoolean(41));
				permisos.setCargar_area(resultado.getBoolean(42));
				permisos.setModificar_area(resultado.getBoolean(43));
				permisos.setCambiar_contrasena(resultado.getBoolean(44));
				permisos.setCargar_categoria(resultado.getBoolean(45));
				permisos.setModificar_categoria(resultado.getBoolean(46));
				permisos.setCargar_sector_empleado(resultado.getBoolean(47));
				permisos.setModificar_sector_empleado(resultado.getBoolean(48));
				permisos.setWarehouse_Preparar(resultado.getBoolean(49));
				permisos.setWarehouse_Preparar_Parcial(resultado.getBoolean(50));
				permisos.setModificar_elemento_precargado(resultado.getBoolean(51));
				permisos.setReporte_tareas_planta_pendientes(resultado.getBoolean(52));
				permisos.setReporte_produccion(resultado.getBoolean(53));
				permisos.setProduccion_Por_Kg_Reporte(resultado.getBoolean(54));
				permisos.setObras_reporte(resultado.getBoolean(55));
				permisos.setCalidad_cargar_materia_prima(resultado.getBoolean(56));
				permisos.setCalidad_asignar_materia_prima(resultado.getBoolean(57));
				permisos.setCalidad_reporte(resultado.getBoolean(58));
				permisos.setAprobadas_por_dia(resultado.getBoolean(59));
				permisos.setRetroceder_lista(resultado.getBoolean(60));
				/*agregado de julio */
				permisos.setedificio_informacion(resultado.getBoolean(61));
				permisos.setmodificar_informacion(resultado.getBoolean(62));
				
				
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


	public int getNivel(int usuario) throws SQLException {
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ call dbo.empleados_devolver_nivel (?,?)}");
			cs.setInt(1, usuario);
			cs.registerOutParameter(2, Types.INTEGER);		
			cs.execute();
			return cs.getInt(2);
			
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return -1;
		}
		finally{
			conexion.close();
		}
		
		
	}
	
	public int modificarContrasenia(String usuario, String contraseniaVieja, String contraseniaNueva) throws SQLException {
		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call usuario_cambiar_contrasenia (?, ?, ?, ?)}");
			cs.setString(1, usuario);
			cs.setString(2, contraseniaVieja);
			cs.setString(3, contraseniaNueva);
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
}