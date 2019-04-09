package reportes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class EtiquetasGenerador {
	
	
	 public void generarArchivoEtiquetas(ArrayList<String[]> listaPiezas, String obraNombre, 
			 							int obraNumero, int paqueteNumero, String edificioNombre){
	        
		 ArrayList<String> nuevaLista = generarStrings(listaPiezas);
		 
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.showSaveDialog(null);
			File selectedPfile2 = chooser.getSelectedFile();
			if (selectedPfile2!=null) {        
				String path = selectedPfile2.getAbsolutePath();
				try
		        {
		            fichero = new FileWriter(path+".txt");
		            pw = new PrintWriter(fichero);
	
	                pw.println(obraNumero);
	                pw.println(obraNombre + "("+edificioNombre+")");
	                pw.println(paqueteNumero);

		            for (int i = 0; i < nuevaLista.size(); i++)
		                pw.println(nuevaLista.get(i));
	
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           try {
		           // Nuevamente aprovechamos el finally para 
		           // asegurarnos que se cierra el fichero.
		           if (null != fichero)
		        	   	fichero.close();
		           		javax.swing.JOptionPane.showMessageDialog(null, "Archivo generado correctamente");
		           } catch (Exception e2) {
		              e2.printStackTrace();
		           }
		        }   
			}
	 	}
	 
	 public ArrayList<String> generarStrings(ArrayList<String[]> listaPiezas){
		 ArrayList<String> nuevoListado = new ArrayList<>();
		 for (int i = 0; i < listaPiezas.size(); i++) {
			String renglon = new String();
			String pos1 = new String();
			pos1=listaPiezas.get(i)[0];
			if (pos1.length()>50) {
				pos1=pos1.substring(0,50);
			}
			pos1=pos1+"@";
			renglon=pos1;
			renglon=completar(renglon, 81);
			renglon=renglon+listaPiezas.get(i)[1];
			renglon=completar(renglon, 93);
			renglon=renglon+"@";	
			renglon=completar(renglon, 97);
			renglon=renglon+listaPiezas.get(i)[2]+"@";
			renglon=completar(renglon, 112);
			renglon=renglon+listaPiezas.get(i)[3];
			renglon=completar(renglon, 121);
			renglon=renglon+"@";
			nuevoListado.add(renglon);
			
			
		}
		 
		 
		 return nuevoListado;
	 }
	 
	 public String completar(String renglon, int total) {
		 int faltantes = 0;
		 if (renglon.length()<total) {
			faltantes = total-renglon.length();
		 }
		 for (int i = 0; i < faltantes; i++) {
			String renglon2=renglon.concat(" ");
			renglon=renglon2;
		 }
		 
		 return renglon;
		 
	 
	 
	 }
	 
}
