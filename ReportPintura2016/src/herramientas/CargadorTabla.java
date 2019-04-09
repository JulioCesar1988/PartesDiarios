package herramientas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controllers.MateriaPrimaController;
import controllers.ElementoController;
import controllers.EmpleadoController;
import controllers.MaterialController;
import controllers.PaqueteController;
import controllers.ParteDiarioController;
import controllers.PiezasController;
import controllers.RemitoController;
import controllers.TareaController;
import controllers.WarehouseController;

public class CargadorTabla {

	public ArrayList<Vector<Object>>generarContenidoTareasCargadas(String fecha) {
		ParteDiarioController pController = new ParteDiarioController();
		try {
			return pController.getTareasPorFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void generarContenidoEmpleadoCreadorPaquete(int obraNumero, int paqueteNumero, DefaultTableModel modelo) {
		EmpleadoController eController = new EmpleadoController();
		ArrayList<Vector<String>> arreglo = null;
		try {
			arreglo = eController.getListaDeEmpleadoCreador(obraNumero, paqueteNumero);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cant=modelo.getRowCount();
		for (int i = 0; i < cant; i++) {
			modelo.removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}
	}
	
	public void generarContenidoEmpleadoRevicionPaquete(int obraNumero, int paqueteNumero, DefaultTableModel modelo) {
		EmpleadoController eController = new EmpleadoController();
		ArrayList<Vector<String>> arreglo = null;
		try {
			arreglo = eController.getListaDeEmpleadoModificadores(obraNumero, paqueteNumero);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cant=modelo.getRowCount();
		for (int i = 0; i < cant; i++) {
			modelo.removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}
	}

	public void generarContenidoEmpleadoAprovadorPaquete(int obraNumero, int paqueteNumero, DefaultTableModel modelo) {
		EmpleadoController eController = new EmpleadoController();
		ArrayList<Vector<String>> arreglo = null;
		try {
			arreglo = eController.getListaDeEmpleadoAprovador(obraNumero, paqueteNumero);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cant=modelo.getRowCount();
		for (int i = 0; i < cant; i++) {
			modelo.removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}	
	}
	
	public void generarContenidoEmpleadoAprovadorProduccionPaquete(int obraNumero, int paqueteNumero, DefaultTableModel modelo) {
		EmpleadoController eController = new EmpleadoController();
		ArrayList<Vector<String>> arreglo = null;
		try {
			arreglo = eController.getListaDeEmpleadoAprovadorProduccion(obraNumero, paqueteNumero);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cant=modelo.getRowCount();
		for (int i = 0; i < cant; i++) {
			modelo.removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}	
	}
	
	public void generarContenidoEmpleadoenviadorPaquetePaquete(int obraNumero, int paqueteNumero, DefaultTableModel modelo) {
		EmpleadoController eController = new EmpleadoController();
		ArrayList<Vector<String>> arreglo = null;
		try {
			arreglo = eController.getListaDeEmpleadoEnviadorProduccion(obraNumero, paqueteNumero);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cant=modelo.getRowCount();
		for (int i = 0; i < cant; i++) {
			modelo.removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}	
	}
	
	public void generarContenidoPiesasAPintar(int obraNumero, DefaultTableModel modelo) {
		PiezasController pController = new PiezasController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = pController.getListaDePiezasParaPintar(obraNumero);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux=modelo.getRowCount();
		for (int i = 0; i < aux; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}
	
	public void generarContenidoTareasAFinalizar(int obraNumero, String sector, DefaultTableModel modelo) {
		TareaController tController = new TareaController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = tController.getListaDeTareasPendientes(obraNumero, sector);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}
	
	public void generarContenidoEmpaquetados(int idElementoCantidad, DefaultTableModel modelo) {
		WarehouseController wController = new WarehouseController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = wController.getListaDeEmpaquetados(idElementoCantidad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}

	public void generarContenidoPiezasParaRemitir(String listadoDeId, DefaultTableModel modelo) {
		PaqueteController pController = new PaqueteController();
		ArrayList<Vector> listado = null;
		try {
			listado=pController.getListadoDePaquetesSeleccionados(listadoDeId);
		} catch (SQLException e) {
			e.printStackTrace();
	}
		for (int i = 0; i < listado.size(); i++) {
			modelo.addRow(new Object[] {
										(Integer) listado.get(i).get(0), 	//id_Paquete	
										(String) listado.get(i).get(1),		//Fecha FInalizacion Paquete
										(String) listado.get(i).get(2), 	//N de paquete
										(String) listado.get(i).get(3), 	//Posicion pieza
										(Integer) listado.get(i).get(4),	//Codigo pieza
										(String) listado.get(i).get(5),		//Descripcion pieza
										(Integer) listado.get(i).get(6), 	//Total de piezas
										(Integer) listado.get(i).get(7), 	//Cantidad de producidos de pieza
										(Integer) listado.get(i).get(8),	//Cantidad de No producidos
										(Integer) listado.get(i).get(9),	//Cantidad de remitidos de pieza
										(Integer) listado.get(i).get(10)	//Cantidad de piezas disponibles para remitir
			
										});
		}
	}
	
	public void generarContenidoPiezasParaRemitirWarehouse(String listadoDeId, DefaultTableModel modelo) {
		PaqueteController pController = new PaqueteController();
		ArrayList<Vector> listado = null;
		try {
			listado=pController.getListadoDePaquetesSeleccionadosWarehouse(listadoDeId);
		} catch (SQLException e) {
			e.printStackTrace();
	}
		for (int i = 0; i < listado.size(); i++) {
			modelo.addRow(new Object[] {
										(Integer) listado.get(i).get(0), 	//id_Paquete	
										(String) listado.get(i).get(1),		//Fecha FInalizacion Paquete
										(String) listado.get(i).get(2), 	//N de paquete
										(String) listado.get(i).get(3), 	//Posicion pieza
										(Integer) listado.get(i).get(4),	//Codigo pieza
										(String) listado.get(i).get(5),		//Descripcion pieza
										(Integer) listado.get(i).get(6), 	//Total de piezas
										(Integer) listado.get(i).get(7), 	//Cantidad de producidos de pieza
										(Boolean) listado.get(i).get(8),	//Cantidad de No producidos
										false,	//Cantidad de remitidos de pieza
										
			
										});
		}
	}
		
	public void generarContenidoListaDePiezasParaEtiquetas(int obraNumero, int paqueteNumero, DefaultTableModel modelo) {
		PiezasController pController = new PiezasController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = pController.generarListaDePiezasParaEtiquetas(obraNumero, paqueteNumero);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}

	public void generarContenidoElementosPrecargados(DefaultTableModel modelo) {
		ElementoController eController = new ElementoController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = eController.generarListaDeElementosPrecargados();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}
	
	public void generarContenidoElementosPrecargadosConId(DefaultTableModel modelo) {
		ElementoController eController = new ElementoController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = eController.generarListaDeElementosPrecargadosConId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}
	
	
	public void generarContenidoPiezasRemitidas(DefaultTableModel modelo, int remitoNumero) {
		RemitoController rController = new RemitoController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = rController.generarListaElementosRemitidos(remitoNumero);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}

	public void generarContenidoPaquetesParaAprobar(JTable tableAprobar) {
		PaqueteController pController = new PaqueteController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = pController.generarListaPaquetesParaAprobar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DefaultTableModel modelo = (DefaultTableModel) tableAprobar.getModel();
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		System.out.println(arreglo.size());
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	

		
		
	}

	public void generarContenidoPaquetesParaRevisar(JTable tableRevisar) {
		PaqueteController pController = new PaqueteController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = pController.generarListaPaquetesParaRevisar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DefaultTableModel modelo = (DefaultTableModel) tableRevisar.getModel();
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}

	public void generarContenidoElementosWarehouseAEmpaquetar(int obraNumero,	DefaultTableModel modelo) {
		WarehouseController hController = new WarehouseController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = hController.getListaElementosNoEmpaquetados(obraNumero);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}
	public void generarContenidoElementosWarehouseParciales(int obraNumero, DefaultTableModel modelo) {
		WarehouseController hController = new WarehouseController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = hController.getListaElementosWarehouseParciales(obraNumero);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}	
	}

	public void generarContenidoMaterialesAModificar(DefaultTableModel modelo) throws SQLException {
		
		MaterialController mController = new MaterialController();
		ArrayList<Vector<Object>> arreglo = null;
		arreglo = mController.getMaterialesParaTablaModificar();
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			modelo.addRow(arreglo.get(j));
		}
	}

	public void generarContenidoTablaPiezasAsignarMateriaPrima(int obraNum,	int listaNum, TableModel modelo) {
		PiezasController pController = new PiezasController();
		ArrayList<Vector<Object>> arreglo = null;
		try {
			arreglo = pController.generarListaDePiezasParaAsignarMaterial(obraNum, listaNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			((DefaultTableModel) modelo).removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			((DefaultTableModel) modelo).addRow(arreglo.get(j));
		}			
	}

	public void generarContenidoTablaMaterialesPorCodigo(int codigo, DefaultTableModel modelo) {
		
		MaterialController mController = new MaterialController(); 
		ArrayList<String> arreglo = null; 
		try {
			arreglo = mController.generarListaDeMaterialesPorCodigoDePieza(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int j = 0; j < arreglo.size(); j++) {
			Vector<String> vector = new Vector<>();
			vector.add(arreglo.get(j));
			modelo.addRow(vector);
			
		}
		
	}

	public void generarContenidoMateriaPrimaEnStock(String tipo, DefaultTableModel model) {
		
		MateriaPrimaController mController = new MateriaPrimaController(); 
		ArrayList<Vector<Object>> arreglo = null; 
		try {
			arreglo = mController.generarListaDeMateriaPrimaEnStock(tipo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = model.getRowCount();
		for (int i = 0; i < aux ; i++) {
			model.removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			model.addRow(arreglo.get(i));
		}
	}

	public void generarContenidoTablaMateriasPrimasAsignadasPorCodigo(int codigo, DefaultTableModel modelo) {
		MateriaPrimaController mController = new MateriaPrimaController(); 
		ArrayList<Vector<Object>> arreglo = null; 
		try {
			arreglo = mController.generarContenidoTablaMateriasPrimasAsignadasPorCodigo(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			modelo.removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}
	}

	public void generarContenidoMateriaPrimaPorCodigo(DefaultTableModel modelo,	String codigo, boolean incluirFueraStock) {
		MateriaPrimaController mController = new MateriaPrimaController(); 
		ArrayList<Vector<Object>> arreglo = null; 
		try {
			arreglo = mController.generarContenidoTablaMateriasPrimasPorCodigo(codigo, incluirFueraStock);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			( modelo).removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}		
	}

	public void generarContenidoMateriaPrimaPorFechas(DefaultTableModel modelo, String desde, String hasta, boolean incluirFueraStock) {
		MateriaPrimaController mController = new MateriaPrimaController(); 
		ArrayList<Vector<Object>> arreglo = null; 
		try {
			arreglo = mController.generarContenidoTablaMateriasPrimasPorFechas(desde, hasta, incluirFueraStock);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int aux = modelo.getRowCount();
		for (int i = 0; i < aux ; i++) {
			( modelo).removeRow(0);
		}
		for (int i = 0; i < arreglo.size(); i++) {
			modelo.addRow(arreglo.get(i));
		}		
	}
}
