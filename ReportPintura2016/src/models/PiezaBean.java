package models;

import java.util.ArrayList;


public class PiezaBean implements Cloneable{
		
	 
	int idPieza;
	int idPaquete;
	String elemento = new String("");;
	String correlatividad;
	String descripcion = new String("");
	String descripcion_extra;
	int largo;
	boolean pintura;
	String color;
	String area;
	ArrayList<SubpiezaBean> subPiezas = new ArrayList<SubpiezaBean>();
	int cantidad;
	float pesoUnitario;
	float pesoTotal;
	int codigo;
	String ubicacion;
	String codigoCategoria;
	ArrayList<MateriaPrimaBean> materiaPrimasUsadas = new ArrayList<MateriaPrimaBean>();

	
	
	public ArrayList<MateriaPrimaBean> getMateriaPrimasUsadas() {
		return materiaPrimasUsadas;
	}

	public void setMateriaPrimasUsadas(ArrayList<MateriaPrimaBean> materiaPrimasUsadas) {
		this.materiaPrimasUsadas = materiaPrimasUsadas;
	}
	
	public void agregarMateriaPrima (MateriaPrimaBean mat) {
		materiaPrimasUsadas.add(mat);
	}

	public void eliminarMateriaPrima (String codigoMat) {

		for (int i = 0; i < materiaPrimasUsadas.size(); i++) {
			if (materiaPrimasUsadas.get(i).getCodigo().equals(codigoMat)) {
				materiaPrimasUsadas.remove(i);
			}
		}
		
	}

	public String getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}


	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void añadirSubpieza(SubpiezaBean subpieza) {
		subPiezas.add(subpieza);
	}
	
	public ArrayList<SubpiezaBean> getSubPiesas() {
		return subPiezas;
	}
	public void setSubPiesas(ArrayList<SubpiezaBean> subPiesas) {
		this.subPiezas = subPiesas;
	}
	
	public int getIdPieza() {
		return idPieza;
	}
	public void setIdPieza(int id_pieza) {
		this.idPieza = id_pieza;
	}
	public int getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(int id_obra) {
		this.idPaquete = id_obra;
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
	public String getDescripcion_extra() {
		return descripcion_extra;
	}
	public void setDescripcion_extra(String descripcion_extra) {
		this.descripcion_extra = descripcion_extra;
	}
	public String getElemento() {
		return elemento;
	}
	public void setElemento(String elemento) {
		if (elemento==null) {
			this.elemento ="";			
		} else {
			this.elemento = elemento;
		}
		
	}
	public String getCorrelatividad() {
		return correlatividad;
	}
	public void setCorrelatividad(String correlatividad) {
		if (descripcion==null) {
			this.correlatividad = "";
		} else {
			this.correlatividad = correlatividad;
		}
	}
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	public boolean isPintura() {
		return pintura;
	}
	public void setPintura(boolean pintura) {
		this.pintura = pintura;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	
	public float getPesoUnitario() {
		return pesoUnitario;
	}

	public void setPesoUnitario(float pesoUnitario) {
		this.pesoUnitario = pesoUnitario;
	}

	public void setTodo(int id_pieza, int id_paquete, int id_material, String descripcion,
			String descripcion_extra, String elemento, String correlatividad, int largo,
			boolean pintura, String color, String area) {
		this.idPieza = id_pieza;
		this.idPaquete = id_paquete;
		this.descripcion = descripcion;
		this.descripcion_extra = descripcion_extra;
		this.elemento = elemento;
		this.correlatividad = correlatividad;
		this.largo = largo;
		this.pintura = pintura;
		this.color = color;
		this.area = area;

	}
	
	public void imprimirDatos() {
	
		System.out.println("elemento" + elemento);
		System.out.println("correlatividad" + correlatividad);
		System.out.println("descripcion" + descripcion);
		System.out.println("descripcionextra" + descripcion_extra);
		System.out.println("pintura: " + pintura);
		System.out.println("Color: " + color);
		System.out.println("Largo: " + largo);
		System.out.println("area: " + area);
		
	/*	for (int i = 0; i < subPiesas.size(); i++) {
			subPiesas.get(i).imprimirDatos();	
				
		}
		
		*/
		
	}

	public PiezaBean clone() {
		try
		 {
			PiezaBean pieza = (PiezaBean) super.clone();
			 
			 ArrayList<SubpiezaBean> subpiezasCopy = new ArrayList<SubpiezaBean>();
			 for (int i = 0; i < pieza.getSubPiesas().size(); i++) {
				 SubpiezaBean subpieza = new SubpiezaBean();
				 subpieza= (SubpiezaBean) pieza.getSubPiesas().get(i).clone();
				 subpiezasCopy.add(subpieza);
			}
			 pieza.setSubPiesas(subpiezasCopy);
			 pieza.setCodigo(0);
			 return pieza;
			 
		 }
		 catch(Exception e){ return null; }
		 }
}