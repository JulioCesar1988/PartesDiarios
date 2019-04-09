package herramientas;

import java.sql.SQLException;
import java.util.ArrayList;

import controllers.AreaController;
import controllers.CategoriaCtrlProduccionController;
import controllers.ChoferController;
import controllers.EdificioController;
import controllers.ElementoController;
import controllers.EmpleadoController;
import controllers.EmpresaController;
import controllers.EstadoController;
import controllers.MaterialController;
import controllers.ObrasController;
import controllers.PaqueteController;
import controllers.RemitoController;
import controllers.SectorController;
import controllers.SectorEmpleadoController;
import controllers.SistemaController;
import controllers.TareaController;
import controllers.CalidadMateriaPrimaController;
import controllers.*;


public class CargadorCombobox {
	
	 // Vamos a cargar nuestro titulos .... 

	public String[] generarContenidoTitulo() {
		EdificioController ed = new EdificioController();
		try {
			return ed.getListaDeTitulos(); // ya lo implemente
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	// Cargamos la descripcion de un titulo dado . 
	public String generarContenidoDescripcion(String titulo , int numObra , String edificio ) {
		EdificioController ed = new EdificioController();
		try {
			return ed.getDescripcion(titulo , numObra , edificio ); // Vamos a implementar
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sin comentarios";
	}
	

	
	public String[] generarContenidoTitulo(int numObra,String nombreEdificio) {
		
		EdificioController ed = new EdificioController();
		try {
			 // ahora vamos a implementar en el controlador de edificio 
			return ed.getListaDeTitulos2(numObra , nombreEdificio); // ya lo implemente
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	


	public String[] generarContenidoEmpresa() {
		EmpresaController eController = new EmpresaController();
		try {
			return eController.getListaDeEmpresa();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
	

	public String[] generarContenidoSector() {
		SectorController sController = new SectorController();
		try {
			return sController.getListaDeSector();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoSectorEmpleado() {
		SectorEmpleadoController seController = new SectorEmpleadoController();
		try {
			return seController.getListaDeSector();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoSistemas() {
		SistemaController sController = new SistemaController();
		try {
			return sController.getListaDeSistemas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoEmpleadoLegajoNombre(String empresa) {
		EmpleadoController eController = new EmpleadoController();
		try {
			return eController.getListaDeLegajoNombre(empresa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoEmpleadoLegajoNombreAll() {
		EmpleadoController eController = new EmpleadoController();
		try {
			return eController.getListaDeLegajoNombreAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoObra() {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObra();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoObraRemitosDespachadas() {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObraRemitosDespachadas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoObraTareasPendientes() {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObraTareasPendientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoObraTareasFinalizadas() {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObraTareasFinalizadas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoObra(String elemento, String correlatividad) {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObra(elemento,correlatividad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> generarContenidoMaterial() {
		MaterialController mController = new MaterialController();
		try {
			return mController.getListaDeMateriales();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoTareas(String sector) {
		TareaController tController = new TareaController();
		try {
			return tController.getListaTareas(sector);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> generarContenidoElemento(String sistema) {
		ElementoController eController = new ElementoController();
		try {
			return eController.getListaDeElementosPorSistema(sistema);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public String[] generarContenidoTareas() {
		TareaController tController = new TareaController();
		try { 
			return tController.getListaTareas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoPaquetesParaModificar(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaModificarCarga(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//nuevo metodo 
	public String[] generarContenidoPaquetesParaModificarConDescripcion(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaModificarCargaConDescripcion(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	// Metodo que me retorna los paquetes que son posibles revisar . 
	public String[] generarContenidoPaquetesParaRevisar(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaRevisarCarga(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Bueno tenemos que modificar esto para que me retorne con la descripcion .
	public String[] generarContenidoPaquetesParaRevisarConDescripcion(int obra) {
		// vamos a tener que agregar un metodo a el controlador de paquetes , para que pueda darme la descripcion . 
		PaqueteController pController = new PaqueteController();
		try {
			
			// Bueno aca vamos a tener que modificar el metodo . 
			return pController.getListaDePaqueteParaRevisarCargaConDescripcion(obra);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	public String[] generarContenidoEdificiosParaModificar(int obra) {
		EdificioController eController = new EdificioController();
		try {
			return eController.getListaDeEdificiosParaModificar(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoPaquetesParaArobar(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaAprobarCarga(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}  
	
	// IMPLEMENTACION DE JULIO . 
	public String[] generarContenidoPaquetesParaArobarConDescripcion(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaAprobarCargaConDescripcion(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	
	
	
	

	public String[] generarContenidoPaquetesParaArobarControlProduccion(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaAprobarCargaControlProduccion(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	// Nuevo metodo para implementar .  
	public String[] generarContenidoPaquetesParaArobarControlProduccionConDescripcion(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaAprobarCargaControlProduccionConDescripcion(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	public String[] generarContenidoPaquetesParaDefinirTareas(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaDefinirTareas(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoPaquetesParaModificarTareas(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaModificarTareas(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoPaquetesParaRevisionarPaquete(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteParaRevisionarPaquete(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoPaquetesConDescripcion(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteConDescripcion(obra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoPaquetesConDescripcion(int obra,String elemento,String correlatividad) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaqueteConDescripcion(obra,elemento,correlatividad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoTipoMaterial() {
		MaterialController mController = new MaterialController();
		try {
			return mController.getListaDeTipo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoEstado() {
		EstadoController eController = new EstadoController();
		try {
			return eController.getListaDeEstado();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoPaquetesPorObra(int obra) {
		PaqueteController pController = new PaqueteController();
		try {
			return pController.getListaDePaquete(obra);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> generarCodigoTareas() {
			TareaController tController = new TareaController();
			try {
				return tController.getListaDeCodigosTareas();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	public String[] generarContenidoArea() {
		AreaController aController = new AreaController();
		try {
			return aController.getListaDeAreas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoChoferes() {
		ChoferController cController = new ChoferController();
		try {
			return cController.getListaDeChoferes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] generarContenidoElementosTodos() {
		ElementoController eController = new ElementoController();
		try {
			return eController.getListaDeElementosTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarNumeroRemito(int obraNumero) {
		RemitoController rController = new RemitoController();
		try {
			return rController.getListaDeNumerosDeRemito(obraNumero);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoCategoria() {
		CategoriaCtrlProduccionController cController = new CategoriaCtrlProduccionController();
		try {
			return cController.getListaDeCategoria();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public String[] generarContenidoSubcategoria(String codigoCategoria) {
		CategoriaCtrlProduccionController cController = new CategoriaCtrlProduccionController();
		try {
			return cController.getListaDeSubcategoria(codigoCategoria);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	
	public ArrayList<String> generarContenidoCategoriaCodigo() {
		CategoriaCtrlProduccionController cController = new CategoriaCtrlProduccionController();
		try {
			return cController.getListaDeCodigos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	public String[] generarContenidoElementosXObra(int obraNum) {
		ElementoController eController = new ElementoController();
		try {
			return eController.getListaDeElementosTodosXObra(obraNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	public ArrayList<String> generarContenidoElementosTodosAuto() {
		ElementoController eController = new ElementoController();
		try {
			System.out.println("entre1");
			return eController.getListaDeElementosTodosAuto();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoSubsector(String sector) {
		System.out.println("llego1");
		TareaController tController = new TareaController();
		try {
			return tController.getListaDeSubsector(sector);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoSectorXObraPaquete(int num_obra, int num_paquete) {
		SectorController sController = new SectorController();
		try {
			return sController.getListaDeSectorXObraPaquete(num_obra,num_paquete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String[] generarContenidoSubsectorXObraPaqueteSector(int num_obra, int num_paquete, String sector) {
		TareaController tController = new TareaController();
		try {
			return tController.getListaDeSubsectorXObraPaqueteSector(num_obra,num_paquete, sector);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String[] generarContenidoObrasDesaprobar() {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObraDesaprobar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoPaqueteDesaprobar(int obra_num) {
		PaqueteController mController = new PaqueteController();
		try {
			return mController.getListaDePaqueteDesaprobar(obra_num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String[] generarContenidoObrasDesaprobarCtrlProd() {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObraDesaprobarCtrlProd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] generarContenidoPaqueteDesaprobarCtrlProd(int obra_num) {
		PaqueteController mController = new PaqueteController();
		try {
			return mController.getListaDePaqueteDesaprobarCtrlProd(obra_num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String[] generarContenidoObraTareasFinalizadasDias() {
		ObrasController mController = new ObrasController();
		try {
			return mController.getListaDeObraTareasFinalizadasDias();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public ArrayList<String> generarContenidoElementosXObraPaquete(int obra_num, int paq_num) {
		ElementoController eController = new ElementoController();
		try {
			System.out.println("entre1");
			return eController.getListaDeElementosXObraPaquete(obra_num,paq_num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoTipoMateriaPrima() {
		MateriaPrimaTipoController mController = new MateriaPrimaTipoController();
		try {
			return mController.devolverArregloTodosLosTipos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public String[] generarContenidoEmpresaproveedoraMateriaPrima() {
		EmpresaProveedorController eController = new EmpresaProveedorController();
		try {
			return eController.devolverArregloTodosLasEmpresasProveedor();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String[] generarContenidoCalidadMateriaPrima() {
		CalidadMateriaPrimaController cController = new CalidadMateriaPrimaController();
		try {
			return cController.devolverArregloTodasLasCalidades();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object[] generarContenidoCategoriaYsubcategoria() {
		CategoriaCtrlProduccionController cController = new CategoriaCtrlProduccionController();
		try {
			return cController.getListaDeCategoriaYSubcategoria();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
}