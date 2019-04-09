package models;

public class EmpleadoBean {
	
	int id_empleado;
	int dni;
	String nombre;
	String apellido;
	String sector;

	int legajo;
	String fechaIngreso;
	String fechaEgreso;
	

	

	String empresa;
	String contrasenia;

	Boolean usuario;

	int nivel;
	
	
	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
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

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public void setTodo(int id_empleado, int dni, String nombre, String apellido, String sector) {
			this.id_empleado = id_empleado;
			this.dni = dni;
			this.nombre = nombre;
			this.apellido = apellido;
			this.sector = sector;
		
	}

	public void imprimirEmpleado() {
		System.out.println("DNI: "+this.getDni());
		System.out.println("Nombre: "+this.getNombre());
		System.out.println("Apellido: "+this.getApellido());
		System.out.println("Sector: "+this.getSector());
		
	}	
	
	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public Boolean getUsuario() {
		return usuario;
	}

	public void setUsuario(Boolean usuario) {
		this.usuario = usuario;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(String fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
}
