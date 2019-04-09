package models;

import java.sql.Date;

public class MateriaPrimaBean {
	
	private String codigo;
	private String descripcion;
	private float espesor;
	private String tipo;
	private String calidad;
	private String remitoNum;
	private String empresa;
	private String fechaIngreso;
	private float peso;
	private float cantidad;
	private String colada;
	private String origen;
	private Date fechaCarga;
	private boolean enStock;
	private String padre;
	private String boletaNum;
	private String codigoTango;
	private String codigoBobinaPadre;
	private String observaciones;
	private String nCertificado;
	private String urlCertificado;

	
	
	
	
	public String getnCertificado() {
		return nCertificado;
	}
	public void setnCertificado(String nCertificado) {
		this.nCertificado = nCertificado;
	}
	public String getUrlCertificado() {
		return urlCertificado;
	}
	public void setUrlCertificado(String urlCertificado) {
		this.urlCertificado = urlCertificado;
	}
	public String getCodigoBobinaPadre() {
		return codigoBobinaPadre;
	}
	public void setCodigoBobinaPadre(String codigoBobinaPadre) {
		this.codigoBobinaPadre = codigoBobinaPadre;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getEspesor() {
		return espesor;
	}
	public void setEspesor(float espesor) {
		this.espesor = espesor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public String getRemitoNum() {
		return remitoNum;
	}
	public void setRemitoNum(String string) {
		this.remitoNum = string;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String string) {
		this.fechaIngreso = string;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getColada() {
		return colada;
	}
	public void setColada(String colada) {
		this.colada = colada;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public Date getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public boolean isEnStock() {
		return enStock;
	}
	public void setEnStock(boolean enStock) {
		this.enStock = enStock;
	}
	public String getPadre() {
		return padre;
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	public String getBoletaNum() {
		return boletaNum;
	}
	public void setBoletaNum(String boletaNum) {
		this.boletaNum = boletaNum;
	}
	public String getCodigoTango() {
		return codigoTango;
	}
	public void setCodigoTango(String codigoTango) {
		this.codigoTango = codigoTango;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	

}