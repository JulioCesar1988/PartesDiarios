package models;
public class TareaBean {

	int idTarea;
	String codigo = new String("");
	String descripcion;
	String descripcionExtra;
	String sector;
	boolean finalizado;
	
	
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		if (codigo==null) {
			this.codigo="";			
		} else {
			this.codigo = codigo;
		};
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionExtra() {
		return descripcionExtra;
	}
	public void setDescripcionExtra(String descripcionExtra) {
		this.descripcionExtra = descripcionExtra;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	
	
	public void setTodo(int idTarea, String descripcion, String descripcionExtra, String sector) {
		
		this.idTarea = idTarea;
		this.descripcion = descripcion;
		this.descripcionExtra = descripcionExtra;
		this.sector = sector;
	
	}
	public void imprimirTarea() {
		System.out.println("                id tarea " + this.idTarea);
		System.out.println("                idescripcion " + this.descripcion);
		System.out.println("                descripcion estra " + this.descripcionExtra);
		System.out.println("                sector " + this.sector);
	}
	
	public void imprimirDatos() {
		
		System.out.println("codigo" + codigo);
		System.out.println("descripcion" + descripcion);
		
		
	/*	for (int i = 0; i < subPiesas.size(); i++) {
			subPiesas.get(i).imprimirDatos();	
				
		}
		
		*/
		
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	
	
}
	
	
	

