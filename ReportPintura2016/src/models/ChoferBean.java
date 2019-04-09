package models;

public class ChoferBean {
	
	int id_chofer;
	int dni;
	String nombre;
	String apellido;
	public int getId_chofer() {
		return id_chofer;
	}
	public void setId_chofer(int id_chofer) {
		this.id_chofer = id_chofer;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setTodo(int id_chofer, int dni, String nombre, String apellido) {
			this.id_chofer = id_chofer;
			this.dni = dni;
			this.nombre = nombre;
			this.apellido = apellido;
		
	}
	public void imprimirChofer() {
		System.out.println("DNI: "+this.dni);
		System.out.println("Nombre: "+this.nombre);
		System.out.println("Apellido: "+this.apellido);
	}	

}
