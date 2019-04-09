package models;

import java.util.ArrayList;

public class SubpiezaBean implements Cloneable{
	
	int idSubpieza;
	int idPiezaPadre;
	String codigoMaterial = new String("");
	String descripcion = new String("");
	String elemento = new String("");
	String correlatividad;
	int ancho;
	float largo;
	float peso;
	int codigo;
	float pesoMaterial;
	String unidadPeso;
	String observaciones;
	int IdMaterial;
	ArrayList<TareaBean> tareas = new ArrayList<TareaBean>();
	int cantidad;
	float pesoTotal;
	String descripcionMaterial;

	
	public String getUnidadPeso() {
		return unidadPeso;
	}
	public void setUnidadPeso(String unidadPeso) {
		this.unidadPeso = unidadPeso;
	}

	public float getPesoMaterial() {
		return pesoMaterial;
	}
	public void setPesoMaterial(float pesoMaterial) {
		this.pesoMaterial = pesoMaterial;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getIdMaterial() {
		return IdMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		IdMaterial = idMaterial;
	}
	
	public void añadirTarea(TareaBean tarea) {
		tareas.add(tarea);
	}

	public int getIdSubpieza() {
		return idSubpieza;
	}

	public void setIdSubpieza(int idSubpieza) {
		this.idSubpieza = idSubpieza;
	}

	public int getIdPiezaPadre() {
		return idPiezaPadre;
	}

	public void setIdPiezaPadre(int idPiezaPadre) {
		this.idPiezaPadre = idPiezaPadre;
	}

	public String getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(String codigoMaterial) {
		if (codigoMaterial==null) {
			this.codigoMaterial="";			
		} else {
			this.codigoMaterial = codigoMaterial;
		}
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (descripcion==null) {
			this.descripcion="";			
		} else {
			this.descripcion = descripcion;
		}
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		if (elemento==null) {
			this.elemento="";			
		} else {
			this.elemento = elemento;
		}
	}

	public String getCorrelatividad() {
		return correlatividad;
	}

	public void setCorrelatividad(String correlatividad) {
		this.correlatividad = correlatividad;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public float getLargo() {
		return largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public ArrayList<TareaBean> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<TareaBean> tareas) {
		this.tareas = tareas;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(float pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public String getDescripcionMaterial() {
		return descripcionMaterial;
	}

	public void setDescripcionMaterial(String descripcionMaterial) {
		this.descripcionMaterial = descripcionMaterial;
	}

	public void setTodo(int id_pieza, int id_obra, int id_pieza_padre, int id_material, String descripcion,
			String descripcion_extra, String elemento, String correlatividad, int ancho, int largo, float peso,
			boolean pintura, String color, String area, String observaciones) {
		this.idSubpieza = id_pieza;
		this.idPiezaPadre = id_pieza_padre;
		this.descripcion = descripcion;
		this.elemento = elemento;
		this.correlatividad = correlatividad;
		this.ancho = ancho;
		this.largo = largo;
		this.peso = peso;
		this.observaciones = observaciones;
		
	}

	public void imprimirDatos() {
	
		System.out.println("        descripcion" + descripcion);
		System.out.println("        elemento" + elemento);
		System.out.println("        correlatividad" + correlatividad);
		/*for (int i = 0; i < tareas.size(); i++) {
			tareas.get(i).imprimirTarea();
		}*/
	}
	
	 
	 public Object clone() {
	 try
	 {
		 SubpiezaBean subPieza = (SubpiezaBean) super.clone();
		 subPieza.setCodigo(0);
		 return subPieza;
	 }
	 catch(Exception e){ return null; }
	 }

	


}
