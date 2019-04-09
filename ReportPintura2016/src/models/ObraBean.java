package models;


public class ObraBean {
	
	int id_obra;
	int numero;
	String nombre;
	String firma;
	String cuit;
	String calle;
	String localidad;
	String provincia;
	String pais;
	
	
	public int getId_obra() {
		return id_obra;
	}
	public void setId_obra(int id_obra) {
		this.id_obra = id_obra;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setTodo(int id_obra, int numero, String nombre, String calle, 
			String localidad, String provincia, String pais) {
		this.id_obra = id_obra;
		this.numero = numero;
		this.nombre = nombre;
		this.calle = calle;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}
	
	public void imprimirObra() {
		System.out.println("Numero de obra: " + this.numero);
		System.out.println("Nombre de obra: " + this.nombre);
		System.out.println("Dirección de obra: " + this.calle);
		System.out.println("Localidad obra: " + this.localidad);
		System.out.println("Provincia de obra: " + this.provincia);
		System.out.println("País de obra: " + this.pais);
		
		
	}
	
	

}