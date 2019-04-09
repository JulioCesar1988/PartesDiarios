package models;

public class MaterialBean {

	int idmaterial;
	
	String tipo;
	String codigo;
	float pesoEspecifico;
	float espesor;
	String calidad;
	String unidadPeso;
	int ancho;
	
	

	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public String getUnidadPeso() {
		return unidadPeso;
	}
	public void setUnidadPeso(String unidadPeso) {
		this.unidadPeso = unidadPeso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getPesoEspecifico() {
		return pesoEspecifico;
	}
	public void setPesoEspecifico(float pesoEspecifico) {
		this.pesoEspecifico = pesoEspecifico;
	}
	public float getEspesor() {
		return espesor;
	}
	public void setEspesor(float espesor) {
		this.espesor = espesor;
	}
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public void setTodo(int idMaterial, String tipo, float peso, float espesor, String calidad, String unidadPeso) {
		this.idmaterial = idMaterial;
		this.tipo = tipo;
		this.pesoEspecifico = peso;
		this.espesor = espesor;
		this.calidad = calidad;
		this.unidadPeso = unidadPeso;
		
	}
	public int getIdmaterial() {
		return idmaterial;
	}
	public void setIdmaterial(int idmaterial) {
		this.idmaterial = idmaterial;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}


