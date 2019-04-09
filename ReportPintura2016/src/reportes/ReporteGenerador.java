package reportes;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JFileChooser;

import bd.Conector;
import bd.ParametrosConexion;
import controllers.ReporteController;
import herramientas.EtiquetasDatasurce;
import session.Session;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteGenerador {


	Conector c;
	Connection conexion; 
	
	
	/*public void generarReporteParteTareasOriginal(String desde, String hasta) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showSaveDialog(null);
		File selectedPfile2 = chooser.getSelectedFile();
		if (selectedPfile2!=null) {
			String path = selectedPfile2.getAbsolutePath();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("desde", desde);
			parametros.put("hasta", hasta);
			
			JasperReport reporte = (JasperReport) JRLoader.loadObject("reporteParteDiario.jasper");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	
	        JRExporter exporter = new JRXlsExporter();
	        
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(path+".xls"));
	        exporter.exportReport();
		}
	}*/
	
	
	public void generarReporteParteTareas(String desde, String hasta) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		JFileChooser chooser = new JFileChooser();
	
			
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("desde", desde);
		parametros.put("hasta", hasta);
		
        JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteParteDiario.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
	        
		
	}
		
public void generarReporteParteTareasVersionExcel(String desde, String hasta) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		JFileChooser chooser = new JFileChooser();
	
			
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("desde", desde);
		parametros.put("hasta", hasta);
		
        JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteParteDiarioVersionExcel.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
	        
		
	}
	public void generarReportePaquetePiezasSubpiezasPDF(int num_obra, int num_paquete) throws JRException {
			

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
			
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paquete);
		
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePaquete.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
	
	
	}

	public void generarReporteParteTareasPDF(String desde, String hasta) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showSaveDialog(null);
		File selectedPfile2 = chooser.getSelectedFile();
		if (selectedPfile2!=null) {
			String path = selectedPfile2.getAbsolutePath();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("desde", desde);
			parametros.put("hasta", hasta);
			
			JasperReport reporte = (JasperReport) JRLoader.loadObject("reporteParteDiario.jasper");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	
	        JRExporter exporter = new JRPdfExporter();
	         
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(path+".pdf"));
	        exporter.exportReport();
	        
	        
		}		
	}

	public void generarReporteEmpleados(int orden, String filtroEmpresa, String filtroSector) throws JRException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
	
			
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("orden", orden);
		parametros.put("filtroEmpresa", filtroEmpresa);
		parametros.put("filtroSector", filtroSector);
		
        JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteEmpleados.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
			
	}

	public void generarReporteMateriales(int orden, String filtroTipo) throws JRException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("orden", orden);
		parametros.put("filtroTipo", filtroTipo);
		     
        JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteMateriales.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
	}

	public void generarReporteTareas(int orden, String filtroSector) throws JRException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		

		
		Map<String, Object> parametros = new HashMap<String, Object>();
		System.out.println("oredn" + orden);
		parametros.put("orden", orden);
		parametros.put("filtroSector", filtroSector);
        
        JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteTareas.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
			
	}

	public void generarReporteRemito(int idRemito) throws JRException {
			

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idRemito", idRemito);
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteRemito.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
				
	}
	
	public void generarReporteRemitoEMSA(int idRemito) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idRemito", idRemito);
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteRemitoEMSA.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
				
	}
	
	public void generarReporteRemitoWarehouse(int idRemito) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idRemito", idRemito);
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteRemitoWarehouse.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
				
	}

	public void generarReporteTareasPlanta(int num_obra, int num_paquete, String sector, String estado) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
			
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("num_obra", num_obra);
			parametros.put("num_paquete", num_paquete);
			parametros.put("sector", sector);
			parametros.put("estado", estado);
			
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteTareasPlanta.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	
}
	
	
	
	
	
	
	
	
	
	// generarReporteTareasPlantaPinturaPorObraPorFecha
	public void generarReporteTareasPlantaPinturaPorObraPorFecha(int numObra,String fecha1 ,String fecha2  ) throws JRException {
		       c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numObra", numObra);
            parametros.put("fecha1", fecha1);
			parametros.put("fecha2", fecha2);
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/ReporteDeSectorPintura2015ObraXfecha.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	        }
	
	
	
	
	//, String sector, String estado
	   public void generarReporteTareasPlantaPintura(int numObra, int numPaquete ) throws JRException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
			
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("numObra", numObra);
			parametros.put("numPaquete", numPaquete);

			
			
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/ReporteDeSectorPintura2015.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	
}
	
	
	
	
	
	
	//     BUENO VAMOS A TRABAJAR EN  ESTE MODULO 28/03/2016  
	public void generarReporteTareasPlantaPinturaPorFecha(String fecha1 , String fecha2 ) throws JRException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		    JFileChooser chooser = new JFileChooser();
	
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("fecha1", fecha1);
			parametros.put("fecha2", fecha2);
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/PinturaReparacion2016.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        
	        JasperViewer repvis=new JasperViewer(jasperPrint,false);
	        repvis.setTitle("Informe de pintura");
	        repvis.show();
	
}
	
	


	public void generarReporteTareasPlantaDia(String fecha1,String fecha2) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
			
			
			Map<String, Object> parametros = new HashMap<String, Object>();
	
		
			System.out.println("fecha1 "+ fecha1);
			System.out.println("fecha2 "+ fecha2);
			
			parametros.put("fecha1", fecha1);
			parametros.put("fecha2", fecha2);
			
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporte2016.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	
}
	
	public void generarReportePaquetePiezasPDF(int num_obra, int num_paquete) throws JRException {
			

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();	
			
		
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("obra_num", num_obra);
			parametros.put("paquete_num", num_paquete);

			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePaquetePiezas.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
		
	}
	
	public void generarReportePaqueteSubpiezasPDF(int num_obra, int num_paquete) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paquete);

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/ReporteSubpiezas.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
	
}


	public void generarReportePaqueteSubpiezasParaExcel(int num_obra, int num_paquete) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paquete);

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/ReporteSubpiezasVersionExcel.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
	
}

	
	public void generarReportePiezasParaEtiquetas(ArrayList<String[]> piezas, String obraNombre, int obraNumero, int paqueteNumero) throws JRException {
		
		EtiquetasDatasurce datasource = new EtiquetasDatasurce(); 
		datasource.addPiezas(piezas);
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showSaveDialog(null);
		File selectedPfile2 = chooser.getSelectedFile();
		if (selectedPfile2!=null) {
			String path = selectedPfile2.getAbsolutePath();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("obraNombre", obraNombre);
			parametros.put("obraNumero", obraNumero);
			parametros.put("paqueteNumero", paqueteNumero);

			
			JasperReport reporte = (JasperReport) JRLoader.loadObject("Reportes/reporteEtiquetas.jasper");  
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource); 
			
			JRExporter exporter = new JRTextExporter();
	        
	        File file = new File(path+".txt");
	        exporter.setParameter(JRTextExporterParameter.OUTPUT_FILE, file);
	        //exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Float(2500));
	        //exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Float(800.0000));
	        exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(3.5));
	        exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(20));
	     
	        
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(path+".txt"));
			exporter.exportReport();
	       
	
			
			
			
			
		}
	}

	public void generarReporteElementos(Integer orden, String filtroTipo, String filtroArea) throws JRException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("orden", orden);
		parametros.put("filtroTipo", filtroTipo);
		parametros.put("filtroArea", filtroArea);

		     
        JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteElementos.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Remito");
        repvis.show();
	}

	public void generarReportePaqueteSubpiezasSector(int num_obra,	int num_paq, String sector, String subsector) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		System.out.println("el subsector es"+subsector);
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paq);
		parametros.put("sector", sector);
		parametros.put("subsector", subsector);


		JasperReport reporte = JasperCompileManager.compileReport("Reportes/ReporteSubpiezasSector.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
       }

	public void generarFaltantesDeObra(int obra_num) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("paqueteNum", null);
		parametros.put("obraNum", obra_num);
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteFaltantesDeObra.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Reporte Faltantes De Obra");
        repvis.show();
	}

	public void generarRemitidosDeObraPaquete(int obra_num, int paq_num) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("paquete_num", paq_num);
		parametros.put("obra_num", obra_num);
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportFinalizadoObra.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Reporte Finalizados De Obra");
        repvis.show();
	}

	public void generarReporteEstadosDeEnvio(int obraNumero, int paqueteNumero,
			String elemento, int seleccionTipo, boolean incluirWarehouse) throws JRException {
		
	
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("paqueteNum", paqueteNumero);
		parametros.put("obraNum", obraNumero);
		parametros.put("piezaElemento", elemento);
		parametros.put("seleccionTipo", seleccionTipo);
		parametros.put("incluirWarehouse", incluirWarehouse);
		System.out.println(seleccionTipo);
		
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteEstadosDeEnvio.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("Reporte Estados De Envio");
        repvis.show();
        
	}

	public void generarReportePaqueteParaExcel(int num_obra, int num_paq) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paq);

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePaquetePiezasParaExel.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
		
       }
	
public void generarReportePlantaFinalizar(String sector, int num_obra) throws JRException {
		

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
			
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("num_obra", num_obra);
			
			parametros.put("nom_sector", sector);
			
			
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePlantaFinalizar.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	
}

public void generarReportePlantaFinalizarPintura(int num_obra) throws JRException {
	

	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();	
	
		
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obras_num", num_obra);
		
		
		
		
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePlantaFinalizarPintura.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();

}
	
public void generarReporteDeProduccion(int numObra, ArrayList ids, String estado) throws JRException {
	ReporteController rc=new ReporteController();
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();	
	
	if (Session.getNombreUsuario().equals("mbi266")) {
	try {
		rc.borrarTablas();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i=0;i<ids.size();i++){
		String ids2=(String) ids.get(i);
	try {
		rc.setReporteDeProduccion(numObra, ids2);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	if (estado=="pendiente"){
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalSinFinalizados.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();}
		else{
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalFinalizados.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	        }
	}else{
		System.out.println("entro x no ser fab");
		try {
			rc.borrarTablas2();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		for(int i=0;i<ids.size();i++){
			String ids2=(String) ids.get(i);
		try {
			rc.setReporteDeProduccion2(numObra, ids2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		if (estado=="pendiente"){
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalSinFinalizados2.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();}
			else{JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalFinalizados2.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();}
	}
}

public JasperPrint devolverReporteProduccionEnKgPorCategoria(int obraNumero, String desde, String hasta, String obraNombre, boolean todasLasObras, 
															boolean subcategorias, boolean incluyeNoPintadas, String noPintadasDesde) throws JRException {
	

	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();	
	
	Map<String, Object> parametros = new HashMap<String, Object>();
	parametros.put("obraNumero", obraNumero);
	parametros.put("desde", desde);
	parametros.put("hasta", hasta);
	parametros.put("obraNombre", obraNombre);
	parametros.put("todasLasObras", todasLasObras);
	parametros.put("subcategorias", subcategorias);
	parametros.put("incluyeNoPintadas", incluyeNoPintadas);
	parametros.put("noPintadasDesde", noPintadasDesde);



		
	JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProduccionPorKg.jrxml");	
	//JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProduccionPorKg.jrxml");
    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
    /*JasperViewer repvis=new JasperViewer(jasperPrint, false);
    
    repvis.setTitle("muestra del reporte");
   	repvis.show();*/
    return jasperPrint;
	}


public void generarReporteDeProduccionAux(int numObra, ArrayList ids) throws JRException {
	
System.out.println(ids);
ReporteController rc=new ReporteController();
c = new Conector(ParametrosConexion.getParametros());
conexion = c.getConnection();	

if (Session.getNombreUsuario().equals("mbi266")) {
try {
	rc.borrarTablas();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

for(int i=0;i<ids.size();i++){
	String ids2=(String) ids.get(i);
try {
	rc.setReporteDeProduccion(numObra, ids2);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalSinFinalizadosAux2.jrxml");
JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
JasperViewer repvis=new JasperViewer(jasperPrint, false);
repvis.setTitle("muestra del reporte");
repvis.show();
}else{
	System.out.println("entro x no ser fab");
	try {
		rc.borrarTablas2();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	for(int i=0;i<ids.size();i++){
		String ids2=(String) ids.get(i);
	try {
		rc.setReporteDeProduccion2(numObra, ids2);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalSinFinalizadosAux3.jrxml");
    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
    JasperViewer repvis=new JasperViewer(jasperPrint, false);
    repvis.setTitle("muestra del reporte");
    repvis.show();
}
	
		
		
		
		
		
		
		
		}
		


public JasperPrint devolverReporteProduccionEnKgPorCategoriaNoProd(int obraNum,
		String obraNombre, boolean todasLasObras, boolean subcategorias) throws JRException {

	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();	
	
	Map<String, Object> parametros = new HashMap<String, Object>();
	parametros.put("obraNumero", obraNum);
	parametros.put("obraNombre", obraNombre);
	parametros.put("todasLasObras", todasLasObras);
	parametros.put("subcategorias", subcategorias);

	JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProduccionPorKgFaltantes.jrxml");	
    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

    return jasperPrint;
	}

	public void generarReporteObras() throws JRException {
		// TODO Auto-generated method stub
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
						
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteObras.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	}
	
	
	
	
	
	
	
	
	// Reporte Actual con sus detalles  ? 
	public void ParteConDetalles(int legajo, String tarea , String sector , String fecha) throws JRException {
		// TODO Auto-generated method stub
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("legajo", legajo);
			parametros.put("tarea", tarea);
			parametros.put("sector", sector);
			parametros.put("fecha", fecha);
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/ParteConDetalles.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("Detalles de Parte");
	        repvis.show();
	        
	        
	}
	
	
	
	

	public void devolverReporteProduccionListadoProduaccionPorPaqueteYCategoria(int obraNum,
		int listaNum, String elementoPieza, String categoria, int opcionFinalizados) throws JRException {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obraNum", obraNum);
		parametros.put("paqueteNum", listaNum);
		parametros.put("piezaElemento", elementoPieza);
		parametros.put("categoria", categoria);
		parametros.put("opcionFinalizados", opcionFinalizados);
		System.out.println("obraNum"+ obraNum);
		System.out.println( listaNum);
		System.out.println(elementoPieza);
		System.out.println(categoria);
		System.out.println( opcionFinalizados);
	
		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteEstadosDeProduccion.jrxml");	
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
	
	}
	
	public void generarReportePaqueteParaExcelPieSub(int num_obra, int num_paq) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paq);

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePaquetePiezasSubpiezasParaExel.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
		
       }

	public void generarReportePaqueteParaExcelPorObra(int num_obra) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePaquetePiezasPorObraParaExel.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
		
       }
	
	public void generarReporteProtocoloCalidad(int num_obra, int num_paq) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		System.out.println(num_obra +" - " + num_paq);
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paq);

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProtocoloCalidad.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
		
       }
	
	public void generarReporteDeProduccionDias(int numObra, String fecha1,String fecha2, boolean todasLasObras) throws JRException {
		ReporteController rc=new ReporteController();
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		if (Session.getNombreUsuario().equals("mbi266")) {
		try {
			rc.borrarTablas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		try {
			rc.setReporteDeProduccion3(numObra, fecha1,fecha2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("fecha1", fecha1);
		parametros.put("fecha2", fecha2);
		parametros.put("todasLasObras", todasLasObras);
		parametros.put("onum", numObra);
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalSinFinalizadosDiasv3.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
		}else{
			System.out.println("entro x no ser fab");
			try {
				rc.borrarTablas2();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				rc.setReporteDeProduccion4(numObra, fecha1, fecha2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	}
		
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("fecha1", fecha1);
			parametros.put("fecha2", fecha2);
			parametros.put("todasLasObras", todasLasObras);
			parametros.put("onum", numObra);
				JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteDeProduccionFinalSinFinalizadosDiasv4.jrxml");
		        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
		        JasperViewer repvis=new JasperViewer(jasperPrint, false);
		        repvis.setTitle("muestra del reporte");
		        repvis.show();		}
	}
	
	public void devolverReporteProduccionEnKgPorCategoriaConDesglose(int obraNumero, String desde, String hasta, String obraNombre, boolean todasLasObras, 
			boolean subcategorias, boolean incluyeNoPintadas, String noPintadasDesde) throws JRException {

			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("obraNumero", obraNumero);
			parametros.put("desde", desde);
			parametros.put("hasta", hasta);
			parametros.put("obraNombre", obraNombre);
			parametros.put("todasLasObras", todasLasObras);
			parametros.put("subcategorias", subcategorias);
			parametros.put("incluyeNoPintadas", incluyeNoPintadas);
			parametros.put("noPintadasDesde", noPintadasDesde);

		
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProduccionPorKgDesglosado.jrxml");	
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
		
		}
	 public static Date addMinutesToDate(Date date, int days) {  
	       Calendar calendarDate = Calendar.getInstance();  
	       calendarDate.setTime(date);  
	       calendarDate.add(Calendar.MINUTE, days);  
	       return calendarDate.getTime();  
	   }  
	
	
	public void devolverReporteProduccionEnKgPorObraYCategoria(String desde, String hasta, boolean subcategorias) throws JRException {

			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("desde", desde);
			parametros.put("hasta", hasta);
			parametros.put("subcategorias", subcategorias);

		
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProduccionPorKgPorObra.jrxml");	
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
		
		}
	public void generarReporteProtocoloCalidadMaterialesPreestablecidos(int num_obra, int num_paq) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		System.out.println(num_obra +" - " + num_paq);
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		parametros.put("paquete_num", num_paq);

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProtocoloCalidadMaterialesPreestablecidos.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
		
       }
	public void generarReporteAprobadasObraDia(int obra_num, Boolean jc) throws JRException {

			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("obra_num", obra_num);
			parametros.put("jc", jc);
						
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteAprobadasObraDia.jrxml");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
	}
	public void generarReportePaqueteSubpiezasParaExcelPorObra(int num_obra) throws JRException {

		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();	
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("obra_num", num_obra);
		

		JasperReport reporte = JasperCompileManager.compileReport("Reportes/ReporteSubpiezasPorObraVersionExcel.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer repvis=new JasperViewer(jasperPrint, false);
        repvis.setTitle("muestra del reporte");
        repvis.show();
		
       }
	public void devolverReporteProduccionEnKgPorCategoriaConDesglose(int obraNumero, String obraNombre, boolean todasLasObras, 
			boolean subcategorias) throws JRException {

			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();	
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("obraNumero", obraNumero);
			
			parametros.put("obraNombre", obraNombre);
			parametros.put("todasLasObras", todasLasObras);
			parametros.put("subcategorias", subcategorias);
			

		
			JasperReport reporte = JasperCompileManager.compileReport("Reportes/reporteProduccionPorKgDesglosadoFaltantes.jrxml");	
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
	        JasperViewer repvis=new JasperViewer(jasperPrint, false);
	        repvis.setTitle("muestra del reporte");
	        repvis.show();
		
		}
}
