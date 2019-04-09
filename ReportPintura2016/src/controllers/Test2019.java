package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import models.MasterparteBean;

public class Test2019 {

	public static void main(String[] args) throws SQLException {
		
		// OBTENIENDO EL IDENTIFICAR DE MASTER CON PARAMETROS
		MasterparteController mmm = new MasterparteController();
		MasterparteBean mb;
		
		//int n  = mmm.buscarMaster(3,"Despacho","M11","2019-02-14"); // Pendiente 
	
		//System.out.println(" Algo -> " +n);
		
		
		
		
		/*
		//  OBTENIENDO LOS DETALLLES DE MASTER . 
		DetalleparteController dct = new DetalleparteController();
		ArrayList<Vector<Object>> aux = dct.detallesDelMaster(25);  
		System.out.println("tamanio dee elementos : "+aux.size());
		for (int i = 0; i <aux.size(); i++) {
			System.out.println(aux.get(i));
		}
		*/
		
		
	}

}
