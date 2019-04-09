package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import bd.*;

public class DetalleparteBean {

	private int id_detalleParte;
	private int id_masterParte;
	private String desde;
	private String hasta;

	private int id_obra;
	private int id_paquete;

	// private int codigo_obra;
	// private int numero_paquete;

	private String posicion;
	private int cantidad;
	private boolean terminado;
	private boolean eliminado;
	private boolean esPieza;

	public int getId_detalleParte() {
		return id_detalleParte;
	}

	public void setId_detalleParte(int id_detalleParte) {
		this.id_detalleParte = id_detalleParte;
	}

	public int getId_masterParte() {
		return id_masterParte;
	}

	public void setId_masterParte(int id_masterParte) {
		this.id_masterParte = id_masterParte;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public int getId_obra() {
		return id_obra;
	}

	public void setId_obra(int id_obra) {
		this.id_obra = id_obra;
	}

	public int getId_paquete() {
		return id_paquete;
	}

	public void setId_paquete(int id_paquete) {
		this.id_paquete = id_paquete;
	}

	// public void setCodigo_obra(int codigo_obra) {
	// this.codigo_obra = codigo_obra;
	// }

	// public void setNumero_paquete(int numero) {
	// this.numero_paquete = numero;
	// }

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public boolean isEsPieza() {
		return esPieza;
	}

	public void setEsPieza(boolean esPieza) {
		this.esPieza = esPieza;
	}

	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector[] = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);
		}
		return vector;
	}

	public void getinformacion() {
		System.out.println("*************************");
		System.out.println(" Es pieza -> " + this.isEsPieza());
		System.out.println(" Desde -> " + this.getDesde());
		System.out.println(" Hasta -> " + this.getHasta());
		System.out.println(" Id Obra -> " + this.getId_obra());
		System.out.println(" Id Pq -> " + this.getId_paquete());
		System.out.println(" posicion -> " + this.getPosicion());
		System.out.println(" cantidad -> " + this.getCantidad());
		System.out.println(" Terminado -> " + this.isTerminado());
		System.out.println("************************");

	}

	public String[] getListaDeObra() throws SQLException {
		Conector c;
		Connection conexion = null;

		try {
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.obra_devolver_combobox ()}");

			cs.registerOutParameter(1, Types.OTHER);

			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while (resultado.next()) {
				lista.add(resultado.getString(1) + " - " + resultado.getString(2));
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

	private boolean dateValid(String value) {

		int hora = Integer.parseInt(value.split(":")[0]);
		int minutos = Integer.parseInt(value.split(":")[1]);
		if (!(hora >= 0 && hora < 24)) {
			return false;
		}
		if (!(minutos >= 0 && minutos < 60)) {
			return false;
		}
		return true;
	}

	// Valida que la hora de inicio sea menor que la hora de fin
	public boolean horasRangoValido() {
		SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
		try {
			this.desde = desde.replace('.', ':');
			this.hasta = hasta.replace('.', ':');
			Date datDesde = dt.parse(this.desde);
			Date datHasta = dt.parse(this.hasta);
			return (this.dateValid(this.desde) && this.dateValid(this.hasta) /*&& datDesde.before(datHasta)*/ );
		} catch (ParseException e) {
			return false;
		}

	}

	public void save(Connection conexion) throws Exception {
		try {
			CallableStatement cs = conexion.prepareCall("{call parte_piezas_detalle_insertar(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, this.getId_masterParte());
			cs.setString(2, this.getDesde());
			cs.setString(3, this.getHasta());
			cs.setInt(4, this.getId_obra());
			cs.setInt(5, this.getId_paquete());
			cs.setString(6, this.getPosicion());
			cs.setInt(7, this.getCantidad());
			cs.setBoolean(8, this.isTerminado());
			cs.setBoolean(9, this.isEliminado());
			cs.setBoolean(10, this.isEsPieza());
			cs.registerOutParameter(11, Types.INTEGER);
			cs.execute();
			// return cs.getInt(11);
		} catch (SQLException e) {
			// hacer salta la excepcion porque no se puedo grabar correctamente
			e.printStackTrace();
		}
	}

	public static void borrarDetallesDesdeMaster(Connection conexion, int id_master) throws Exception {

		try {
			CallableStatement cs = conexion.prepareCall("{call parte_piezas_detalle_eliminar_from_master(?)}");
			cs.setInt(1, id_master);
			cs.execute();
			// return cs.getInt(11);
		} catch (SQLException e) {
			// hacer salta la excepcion porque no se puedo grabar correctamente
			e.printStackTrace();
		}

	}

}
