package controllers;

import java.sql.SQLException;
import controllers.*;

public class Prueba {

	public static void main(String[] args) {
		// Vamos a probar el controlador. 
		
		ObrasController obra = new ObrasController();
		
		
	  try {
		boolean ok = 	obra.VerificarPosicionPorObra(1111, "C", "12");
		if(ok){
		System.out.println("posicion  existente en la obra  "); }else {System.out.println(" No posicion  existente en la obra"); }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		

	}

}
