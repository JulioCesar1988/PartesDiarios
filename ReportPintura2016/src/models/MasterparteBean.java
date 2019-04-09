package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import bd.Conector;
import bd.ParametrosConexion;

public class MasterparteBean {
	private boolean esNuevo;
	private int id_masterParte;
	private int id_empleado;
	private int id_sector;
	private int id_tarea;
	private String fecha;
	private boolean eliminado;
	ArrayList<DetalleparteBean> detallePartes;

	public MasterparteBean(int id_empleado, int id_sector, int id_tarea, String fecha, boolean eliminado,
			ArrayList<DetalleparteBean> detallePartes, boolean esNuevo) {
		super();

		this.id_empleado = id_empleado;
		this.id_sector = id_sector;
		this.id_tarea = id_tarea;
		this.fecha = fecha;
		this.eliminado = eliminado;
		this.detallePartes = detallePartes;
		this.esNuevo = esNuevo;
	}

	public MasterparteBean(int id_empleado, int id_sector, int id_tarea, String fecha, boolean eliminado,
			ArrayList<DetalleparteBean> detallePartes, boolean esNuevo, int id_master) {
		super();

		this.id_empleado = id_empleado;
		this.id_sector = id_sector;
		this.id_tarea = id_tarea;
		this.fecha = fecha;
		this.eliminado = eliminado;
		this.detallePartes = detallePartes;
		this.esNuevo = esNuevo;
		this.id_masterParte = id_master;
	}

	public void AgregarDetalle(DetalleparteBean dpb) {
		this.detallePartes.add(dpb);

	}

	public int getId_masterParte() {
		return id_masterParte;
	}

	private void setId_masterParte(int id_masterParte) {
		this.id_masterParte = id_masterParte;
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}

	public int getId_sector() {
		return id_sector;
	}

	public void setId_sector(int id_sector) {
		this.id_sector = id_sector;
	}

	public int getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(int id_tarea) {
		this.id_tarea = id_tarea;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public static boolean YaExiste(int id_empleado, int id_sector, int id_tarea, String fecha) throws Exception {
		Conector c = new Conector(ParametrosConexion.getParametros());
		Connection conexion = c.getConnection();
		try {
			CallableStatement cs = conexion.prepareCall("{call [parte_piezas_master_existe](?,?,?,?,?)}");
			cs.setInt(1, id_empleado);
			cs.setInt(2, id_sector);
			cs.setInt(3, id_tarea);
			cs.setString(4, fecha);
			cs.registerOutParameter(5, Types.INTEGER);
			cs.execute();
			return (cs.getInt(5) != 0);
		} catch (SQLException e) {
			throw new Exception("Error al ejecutar la verificacion de existencia de un maestro con los datos");
		} finally {
			conexion.close();
		}

	}

	public void save() throws Exception {
		Conector c = new Conector(ParametrosConexion.getParametros());
		Connection conexion = c.getConnection();

		try {
			if (this.esNuevo) {
				CallableStatement cs = conexion.prepareCall("{call parte_piezas_master_insertar(?,?,?,?,?,?)}");
				cs.setInt(1, this.getId_empleado());
				cs.setInt(2, this.getId_sector());
				cs.setInt(3, this.getId_tarea());
				cs.setString(4, this.getFecha());
				cs.setBoolean(5, this.isEliminado());
				cs.registerOutParameter(6, Types.INTEGER);
				cs.execute();
				this.setId_masterParte(cs.getInt(6));
			} else {
				CallableStatement cs = conexion.prepareCall("{call parte_piezas_master_actualizar(?,?,?,?,?,?)}");
				cs.setInt(1, this.getId_masterParte());
				cs.setInt(2, this.getId_empleado());
				cs.setInt(3, this.getId_sector());
				cs.setInt(4, this.getId_tarea());
				cs.setString(5, this.getFecha());
				cs.setBoolean(6, this.isEliminado());
				cs.execute();
			}

			this.saveDetail(conexion);

		} catch (SQLException e) {
			// hacer salta la excepcion porque no se puedo grabar correctamente
			e.printStackTrace();
		} finally {
			conexion.close();
		}
	}

	private void saveDetail(Connection conexion) throws Exception {
		if (!this.esNuevo) {
			// Elimina todos los items existentes
			DetalleparteBean.borrarDetallesDesdeMaster(conexion, this.id_masterParte);
		}
		for (int j = 0; j < this.detallePartes.size(); j++) {
			this.detallePartes.get(j).setId_masterParte(this.id_masterParte);
			this.detallePartes.get(j).save(conexion);
		}
	}
}
