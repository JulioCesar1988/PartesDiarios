package session;

public class Permiso {


	
	private boolean cargar_obra;
	private boolean modificar_obra;
	private boolean cargar_edificio;
	private boolean modificar_edificio;
	private boolean cargar_orden;
	private boolean modificar_orden;
	private boolean revisar_orden;
	private boolean aprobar_orden;
	private boolean aprobar_produccion_orden;
	private boolean definir_tareas_piezas;
	private boolean modificar_tareas_piezas;
	private boolean aprobar_piezas_finalizadas;
	private boolean generar_reporte_piezas;
	private boolean generar_archivo_etiquetas;
	private boolean nuevo_elemento_precargado;
	private boolean cargar_remito;
	private boolean remito_cargados;
	private boolean cargar_camion;
	private boolean modificar_camion;
	private boolean cargar_chofer;
	private boolean modificar_chofer;
	private boolean tareas_pendiente;
	private boolean pintura;
	private boolean cargar_tareas;
	private boolean modificar_tareas;
	private boolean reporte_tareas;
	private boolean reporte_tareas_planta;
	private boolean cargar_parte_diario_tareas;
	private boolean gestionar_parte_diario;
	private boolean cargar_empleados;
	private boolean modificar_empleados;
	private boolean reporte_empleados;
	private boolean cargar_materiales;
	private boolean modificar_materiales;
	private boolean reporte_materiales;
	private boolean dar_alta_usuario;
	private boolean modificar_usuario;
	private boolean cargar_sector;
	private boolean modificar_sector;
	private boolean cargar_sistema_diseño;
	private boolean modificar_sistema_diseño;
	private boolean cargar_area;
	private boolean modificar_area;
	private boolean cambiar_contrasena;
	private boolean cargar_categoria;
	private boolean modificar_categoria;
	private boolean cargar_sector_empleado;
	private boolean modificar_sector_empleado;
	private boolean warehouse_Preparar;
	private boolean warehouse_Preparar_Parcial;
	private boolean modificar_elemento_precargado;
	private boolean reporte_tareas_planta_pendientes;
	private boolean reporte_produccion;
	private boolean produccion_Por_Kg_Reporte;
	private boolean obras_reporte;
	private boolean calidad_cargar_materia_prima;
	private boolean calidad_asignar_materia_prima;
	private boolean calidad_reporte;
	private boolean aprobadas_por_dia;
	private boolean retroceder_lista;
	
	/* declaracion de la variable para visualizar la pantalla */
	private boolean edificio_informacion;
	private boolean modificar_informacion;

	// esta clase la usamos para cargar los datos de la base de datos , dependiendo del usuario determinadas ventanas van a ser visualizadas . 
	
	public boolean edificio_informacion() {	
		return edificio_informacion;		
	}
	
	public void setedificio_informacion(boolean edificio_informacion) {
		this.edificio_informacion = edificio_informacion;
	}
	
	
	// agregamos la nueva variable 
	public boolean modificar_informacion() {	
		return modificar_informacion;		
	}
	
	public void setmodificar_informacion(boolean modificar_informacion) {
		this.modificar_informacion = modificar_informacion;
	}
	
	
	
	
	/* Cosas necesarias para la carga de la sesion*/
	
	
	public boolean isRetroceder_lista() {
		return retroceder_lista;
	}
	public void setRetroceder_lista(boolean retroceder_lista) {
		this.retroceder_lista = retroceder_lista;
	}
	
	public boolean isAprobadas_por_dia() {
		return aprobadas_por_dia;
	}
	public void setAprobadas_por_dia(boolean aprobadas_por_dia) {
		this.aprobadas_por_dia = aprobadas_por_dia;
	}
	public boolean isReporte_produccion() {
		return reporte_produccion;
	}
	public void setReporte_produccion(boolean reporte_produccion) {
		this.reporte_produccion = reporte_produccion;
	}
	public boolean isCargar_sector_empleado() {
		return cargar_sector_empleado;
	}
	public void setCargar_sector_empleado(boolean cargar_sector_empleado) {
		this.cargar_sector_empleado = cargar_sector_empleado;
	}
	public boolean isModificar_sector_empleado() {
		return modificar_sector_empleado;
	}
	public void setModificar_sector_empleado(boolean modificar_sector_empleado) {
		this.modificar_sector_empleado = modificar_sector_empleado;
	}
	public boolean isCargar_categoria() {
		return cargar_categoria;
	}
	public void setCargar_categoria(boolean cargar_categoria) {
		this.cargar_categoria = cargar_categoria;
	}
	public boolean isModificar_categoria() {
		return modificar_categoria;
	}
	public void setModificar_categoria(boolean modificar_categoria) {
		this.modificar_categoria = modificar_categoria;
	}
	public boolean isCargar_area() {
		return cargar_area;
	}
	public void setCargar_area(boolean cargar_area) {
		this.cargar_area = cargar_area;
	}
	public boolean isModificar_area() {
		return modificar_area;
	}
	public void setModificar_area(boolean modificar_area) {
		this.modificar_area = modificar_area;
	}
	public boolean isCambiar_contrasena() {
		return cambiar_contrasena;
	}
	public void setCambiar_contrasena(boolean cambiar_contrasena) {
		this.cambiar_contrasena = cambiar_contrasena;
	}
	public boolean isCargar_obra() {
		return cargar_obra;
	}
	public void setCargar_obra(boolean cargar_obra) {
		this.cargar_obra = cargar_obra;
	}
	public boolean isModificar_obra() {
		return modificar_obra;
	}
	public void setModificar_obra(boolean modificar_obra) {
		this.modificar_obra = modificar_obra;
	}
	public boolean isCargar_edificio() {
		return cargar_edificio;
	}
	public void setCargar_edificio(boolean cargar_edificio) {
		this.cargar_edificio = cargar_edificio;
	}
	public boolean isModificar_edificio() {
		return modificar_edificio;
	}
	public void setModificar_edificio(boolean modificar_edificio) {
		this.modificar_edificio = modificar_edificio;
	}
	public boolean isCargar_orden() {
		return cargar_orden;
	}
	public void setCargar_orden(boolean cargar_orden) {
		this.cargar_orden = cargar_orden;
	}
	public boolean isModificar_orden() {
		return modificar_orden;
	}
	public void setModificar_orden(boolean modificar_orden) {
		this.modificar_orden = modificar_orden;
	}
	public boolean isRevisar_orden() {
		return revisar_orden;
	}
	public void setRevisar_orden(boolean revisar_orden) {
		this.revisar_orden = revisar_orden;
	}
	public boolean isAprobar_orden() {
		return aprobar_orden;
	}
	public void setAprobar_orden(boolean aprobar_orden) {
		this.aprobar_orden = aprobar_orden;
	}
	public boolean isAprobar_produccion_orden() {
		return aprobar_produccion_orden;
	}
	public void setAprobar_produccion_orden(boolean aprobar_produccion_orden) {
		this.aprobar_produccion_orden = aprobar_produccion_orden;
	}
	public boolean isDefinir_tareas_piezas() {
		return definir_tareas_piezas;
	}
	public void setDefinir_tareas_piezas(boolean definir_tareas_piezas) {
		this.definir_tareas_piezas = definir_tareas_piezas;
	}
	public boolean isModificar_tareas_piezas() {
		return modificar_tareas_piezas;
	}
	public void setModificar_tareas_piezas(boolean modificar_tareas_piezas) {
		this.modificar_tareas_piezas = modificar_tareas_piezas;
	}
	public boolean isAprobar_piezas_finalizadas() {
		return aprobar_piezas_finalizadas;
	}
	public void setAprobar_piezas_finalizadas(boolean aprobar_piezas_finalizadas) {
		this.aprobar_piezas_finalizadas = aprobar_piezas_finalizadas;
	}
	public boolean isGenerar_reporte_piezas() {
		return generar_reporte_piezas;
	}
	public void setGenerar_reporte_piezas(boolean generar_reporte_piezas) {
		this.generar_reporte_piezas = generar_reporte_piezas;
	}
	public boolean isGenerar_archivo_etiquetas() {
		return generar_archivo_etiquetas;
	}
	public void setGenerar_archivo_etiquetas(boolean generar_archivo_etiquetas) {
		this.generar_archivo_etiquetas = generar_archivo_etiquetas;
	}
	public boolean isNuevo_elemento_precargado() {
		return nuevo_elemento_precargado;
	}
	public void setNuevo_elemento_precargado(boolean nuevo_elemento_precargado) {
		this.nuevo_elemento_precargado = nuevo_elemento_precargado;
	}
	public boolean isCargar_remito() {
		return cargar_remito;
	}
	public void setCargar_remito(boolean cargar_remito) {
		this.cargar_remito = cargar_remito;
	}
	public boolean isRemito_cargados() {
		return remito_cargados;
	}
	public void setRemito_cargados(boolean remito_cargados) {
		this.remito_cargados = remito_cargados;
	}
	public boolean isCargar_camion() {
		return cargar_camion;
	}
	public void setCargar_camion(boolean cargar_camion) {
		this.cargar_camion = cargar_camion;
	}
	public boolean isModificar_camion() {
		return modificar_camion;
	}
	public void setModificar_camion(boolean modificar_camion) {
		this.modificar_camion = modificar_camion;
	}
	public boolean isCargar_chofer() {
		return cargar_chofer;
	}
	public void setCargar_chofer(boolean cargar_chofer) {
		this.cargar_chofer = cargar_chofer;
	}
	public boolean isModificar_chofer() {
		return modificar_chofer;
	}
	public void setModificar_chofer(boolean modificar_chofer) {
		this.modificar_chofer = modificar_chofer;
	}
	public boolean isTareas_pendiente() {
		return tareas_pendiente;
	}
	public void setTareas_pendiente(boolean tareas_pendiente) {
		this.tareas_pendiente = tareas_pendiente;
	}
	public boolean isPintura() {
		return pintura;
	}
	public void setPintura(boolean pintura) {
		this.pintura = pintura;
	}
	public boolean isCargar_tareas() {
		return cargar_tareas;
	}
	public void setCargar_tareas(boolean cargar_tareas) {
		this.cargar_tareas = cargar_tareas;
	}
	public boolean isModificar_tareas() {
		return modificar_tareas;
	}
	public void setModificar_tareas(boolean modificar_tareas) {
		this.modificar_tareas = modificar_tareas;
	}
	public boolean isReporte_tareas() {
		return reporte_tareas;
	}
	public void setReporte_tareas(boolean reporte_tareas) {
		this.reporte_tareas = reporte_tareas;
	}
	public boolean isReporte_tareas_planta() {
		return reporte_tareas_planta;
	}
	public void setReporte_tareas_planta(boolean reporte_tareas_planta) {
		this.reporte_tareas_planta = reporte_tareas_planta;
	}
	public boolean isCargar_parte_diario_tareas() {
		return cargar_parte_diario_tareas;
	}
	public void setCargar_parte_diario_tareas(boolean cargar_parte_diario_tareas) {
		this.cargar_parte_diario_tareas = cargar_parte_diario_tareas;
	}
	public boolean isGestionar_parte_diario() {
		return gestionar_parte_diario;
	}
	public void setGestionar_parte_diario(boolean gestionar_parte_diario) {
		this.gestionar_parte_diario = gestionar_parte_diario;
	}
	public boolean isCargar_empleados() {
		return cargar_empleados;
	}
	public void setCargar_empleados(boolean cargar_empleados) {
		this.cargar_empleados = cargar_empleados;
	}
	public boolean isModificar_empleados() {
		return modificar_empleados;
	}
	public void setModificar_empleados(boolean modificar_empleados) {
		this.modificar_empleados = modificar_empleados;
	}
	public boolean isReporte_empleados() {
		return reporte_empleados;
	}
	public void setReporte_empleados(boolean reporte_empleados) {
		this.reporte_empleados = reporte_empleados;
	}
	public boolean isCargar_materiales() {
		return cargar_materiales;
	}
	public void setCargar_materiales(boolean cargar_materiales) {
		this.cargar_materiales = cargar_materiales;
	}
	public boolean isModificar_materiales() {
		return modificar_materiales;
	}
	public void setModificar_materiales(boolean modificar_materiales) {
		this.modificar_materiales = modificar_materiales;
	}
	public boolean isReporte_materiales() {
		return reporte_materiales;
	}
	public void setReporte_materiales(boolean reporte_materiales) {
		this.reporte_materiales = reporte_materiales;
	}
	public boolean isDar_alta_usuario() {
		return dar_alta_usuario;
	}
	public void setDar_alta_usuario(boolean dar_alta_usuario) {
		this.dar_alta_usuario = dar_alta_usuario;
	}
	public boolean isModificar_usuario() {
		return modificar_usuario;
	}
	public void setModificar_usuario(boolean modificar_usuario) {
		this.modificar_usuario = modificar_usuario;
	}
	public boolean isCargar_sector() {
		return cargar_sector;
	}
	public void setCargar_sector(boolean cargar_sector) {
		this.cargar_sector = cargar_sector;
	}
	public boolean isModificar_sector() {
		return modificar_sector;
	}
	public void setModificar_sector(boolean modificar_sector) {
		this.modificar_sector = modificar_sector;
	}
	public boolean isCargar_sistema_diseño() {
		return cargar_sistema_diseño;
	}
	public void setCargar_sistema_diseño(boolean cargar_sistema_diseño) {
		this.cargar_sistema_diseño = cargar_sistema_diseño;
	}
	public boolean isModificar_sistema_diseño() {
		return modificar_sistema_diseño;
	}
	public void setModificar_sistema_diseño(boolean modificar_sistema_diseño) {
		this.modificar_sistema_diseño = modificar_sistema_diseño;
	}
	public boolean isWarehouse_Preparar() {
		return warehouse_Preparar;
	}
	public void setWarehouse_Preparar(boolean warehouse_Preparar) {
		this.warehouse_Preparar = warehouse_Preparar;
	}
	public boolean isWarehouse_Preparar_Parcial() {
		return warehouse_Preparar_Parcial;
	}
	public void setWarehouse_Preparar_Parcial(boolean warehouse_Preparar_Parcial) {
		this.warehouse_Preparar_Parcial = warehouse_Preparar_Parcial;
	}
	public boolean isModificar_elemento_precargado() {
		return modificar_elemento_precargado;
	}
	public void setModificar_elemento_precargado(boolean modificar_elemento_precargado) {
		this.modificar_elemento_precargado = modificar_elemento_precargado;
	}
	
	public boolean isReporte_tareas_planta_pendientes() {
		return reporte_tareas_planta_pendientes;
	}
	public void setReporte_tareas_planta_pendientes(boolean reporte_tareas_planta_pendientes) {
		this.reporte_tareas_planta_pendientes = reporte_tareas_planta_pendientes;
	}
	public boolean isProduccion_Por_Kg_Reporte() {
		return produccion_Por_Kg_Reporte;
	}
	public void setProduccion_Por_Kg_Reporte(boolean produccion_Por_Kg_Reporte) {
		this.produccion_Por_Kg_Reporte = produccion_Por_Kg_Reporte;
	}
	public boolean isObras_reporte() {
		return obras_reporte;
	}
	public void setObras_reporte(boolean obras_reporte) {
		this.obras_reporte = obras_reporte;
	}
	public boolean isCalidad_cargar_materia_prima() {
		return calidad_cargar_materia_prima;
	}
	public void setCalidad_cargar_materia_prima(boolean calidad_cargar_materia_prima) {
		this.calidad_cargar_materia_prima = calidad_cargar_materia_prima;
	}
	public boolean isCalidad_asignar_materia_prima() {
		return calidad_asignar_materia_prima;
	}
	public void setCalidad_asignar_materia_prima(
			boolean calidad_asignar_materia_prima) {
		this.calidad_asignar_materia_prima = calidad_asignar_materia_prima;
	}
	public boolean isCalidad_reporte() {
		return calidad_reporte;
	}
	public void setCalidad_reporte(boolean calidad_reporte) {
		this.calidad_reporte = calidad_reporte;
	}
    

    


}
