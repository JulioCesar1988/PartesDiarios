package models;

public class CamionBean {

	int idCamion;
	String patente;
	String compania;
	
	
	public int getIdCamion() {
		return idCamion;
	}
	public void setIdCamion(int idCamion) {
		this.idCamion = idCamion;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patene) {
		this.patente = patene;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	
	public void setTodo(int idCamion, String patente, String compania) {
		this.idCamion = idCamion;
		this.patente = patente;
		this.compania = compania;
	}
}
