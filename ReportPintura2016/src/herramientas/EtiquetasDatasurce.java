package herramientas;


import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class EtiquetasDatasurce implements JRDataSource{

	private ArrayList<String[]> listaPiezas = new ArrayList<String[]>();
	private int indiceActual = -1;
	
	
	
	public Object getFieldValue(JRField jrField) throws JRException
	{ 
	    Object valor = null;  

	    if("parametro1".equals(jrField.getName())) 
	    { 
	        valor = (listaPiezas.get(indiceActual))[0]; 
	    } 
	    else if("parametro2".equals(jrField.getName())) 
	    { 
	        valor = (listaPiezas.get(indiceActual))[1]; 
	    } 
	    else if("parametro3".equals(jrField.getName())) 
	    { 
	        valor = (listaPiezas.get(indiceActual))[2];  
	    } 
	    else if("parametro4".equals(jrField.getName())) 
	    { 
	        valor = (listaPiezas.get(indiceActual))[3]; 
	    }
	 
	    return valor; 
	}

	@Override
	public boolean next() throws JRException {
	    return ++indiceActual < listaPiezas.size();
	}

	public void addPieza(String[] pieza){
	    this.listaPiezas.add(pieza);
	}
	
	public void addPiezas(ArrayList<String[]> piezas){
	    this.listaPiezas=piezas;
	}
}
