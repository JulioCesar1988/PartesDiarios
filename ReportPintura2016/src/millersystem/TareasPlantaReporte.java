package millersystem;


import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

import net.sf.jasperreports.engine.JRException;
import herramientas.CargadorCombobox;
import reportes.ReporteGenerador;

import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;

import java.awt.Color;

public class TareasPlantaReporte extends JFrame {
	private JComboBox comboBoxSector;
	private JComboBox comboBoxObra;
	private JComboBox comboBoxPaquete;
	private JComboBox comboBoxEstado;
	JDateChooser dateChooser;
	JDateChooser dateChooser_1;
	JRadioButton rdbtnReporteDeTareas_1;
	JRadioButton rdbtnReporteDeTareas;
	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public TareasPlantaReporte() {
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		//setClosable(true);
		setTitle("Reporte de tareas de planta");
		setBounds(100, 100, 850, 600);
		
		JLabel lblGenerarReporteDe = new JLabel("Generar Reporte de Tareas de Planta");
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnGenerarPdf = new JButton("Generar reporte");
		btnGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnReporteDeTareas.isSelected()) {
					
				
				 

				
				String filtroSector=(String) comboBoxSector.getSelectedItem();
				System.out.println(filtroSector);
				String filtroEstado=(String) comboBoxEstado.getSelectedItem();
				System.out.println(filtroEstado);
				Integer filtroPaquete=Integer.parseInt((String) comboBoxPaquete.getSelectedItem());
				System.out.println(filtroPaquete);
				String obra = (String) comboBoxObra.getSelectedItem();
				StringTokenizer tk2 = new StringTokenizer(obra, " - ");
				int filtroObra=Integer.parseInt(tk2.nextToken());
				System.out.println(filtroObra);
								
				ReporteGenerador rp = new ReporteGenerador();
				try {
				
					rp.generarReporteTareasPlanta(filtroObra,filtroPaquete,filtroSector,filtroEstado);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}else{
					if (rdbtnReporteDeTareas_1.isSelected()) {
					String filtroSector=(String) comboBoxSector.getSelectedItem();
					System.out.println(filtroSector);
					
					String formato = dateChooser.getDateFormatString();
					Date date = dateChooser.getDate();
					SimpleDateFormat sdf = new SimpleDateFormat(formato);
					String filtroFecha=String.valueOf(sdf.format(date));
					System.out.println(filtroFecha);
					
					date = dateChooser_1.getDate();
					String filtroFecha2=String.valueOf(sdf.format(date));
					System.out.println(filtroFecha2);
					ReporteGenerador rp = new ReporteGenerador();
					try {
						rp.generarReporteTareasPlantaDia(filtroFecha,filtroFecha2);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					
					
					}}
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCerrar, Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE))
						.addComponent(lblGenerarReporteDe)
						.addComponent(btnGenerarPdf, Alignment.TRAILING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGenerarReporteDe)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnGenerarPdf)
					.addGap(139)
					.addComponent(btnCerrar)
					.addContainerGap())
		);
		CargadorCombobox cc=new CargadorCombobox();

		
		comboBoxSector = new JComboBox();
		comboBoxSector.setEnabled(false);
		comboBoxSector.setModel(new DefaultComboBoxModel(cc.generarContenidoSector()));
		
		JLabel lblSector = new JLabel("Sector");
		
		JLabel lblObra = new JLabel("Obra");
		
		JLabel lblPaquete = new JLabel("Paquete");
		
		JLabel lblEstado = new JLabel("Estado");
		
		comboBoxObra = new JComboBox();
		comboBoxObra.setEnabled(false);
		comboBoxObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarComboBoxPaqueteNumero();
			}
		});
		comboBoxObra.setModel(new DefaultComboBoxModel(cc.generarContenidoObra()));
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setEnabled(false);
		comboBoxEstado.setModel(new DefaultComboBoxModel(cc.generarContenidoEstado()));
		
		comboBoxPaquete = new JComboBox();
		comboBoxPaquete.setEnabled(false);
		String obra = (String) comboBoxObra.getSelectedItem();
		if (obra!=null){
			System.out.println("es"+obra);
			cargarComboBoxPaqueteNumero();
		
		}
		
		JLabel lblFecha = new JLabel("Fecha desde");
		
		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setEnabled(false);
		rdbtnReporteDeTareas_1 = new JRadioButton("Reporte de tareas finalizadas por d\u00EDas");
		rdbtnReporteDeTareas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxObra.setEnabled(false);
				comboBoxPaquete.setEnabled(false);
				comboBoxEstado.setEnabled(false);
				comboBoxSector.setEnabled(true);
				dateChooser.setEnabled(true);
				dateChooser_1.setEnabled(true);
				rdbtnReporteDeTareas.setSelected(false);}
		});
		rdbtnReporteDeTareas = new JRadioButton("Reporte de tareas");
		rdbtnReporteDeTareas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			comboBoxObra.setEnabled(true);
			comboBoxPaquete.setEnabled(true);
			comboBoxEstado.setEnabled(true);
			comboBoxSector.setEnabled(true);
			dateChooser.setEnabled(false);
			dateChooser_1.setEnabled(false);
			rdbtnReporteDeTareas_1.setSelected(false);}
		});
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		

		
	
		
		
		
		
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(23)
							.addComponent(rdbtnReporteDeTareas)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnReporteDeTareas_1))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(145)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblObra)
								.addComponent(lblPaquete)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblSector, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblEstado, Alignment.TRAILING))
								.addComponent(lblFecha))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBoxPaquete, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxObra, Alignment.TRAILING, 0, 390, Short.MAX_VALUE)
									.addComponent(comboBoxEstado, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxSector, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblFechaHasta)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(192, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnReporteDeTareas)
						.addComponent(rdbtnReporteDeTareas_1))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxObra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblObra))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPaquete)
						.addComponent(comboBoxPaquete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEstado)
						.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxSector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSector))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFecha)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechaHasta)
						.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
		private void cargarComboBoxPaqueteNumero() {
			String obra = (String) comboBoxObra.getSelectedItem();
			StringTokenizer tk2 = new StringTokenizer(obra, " - ");
			int obra_num=Integer.parseInt(tk2.nextToken());
			CargadorCombobox cc = new CargadorCombobox();
			comboBoxPaquete.setModel(new DefaultComboBoxModel(cc.generarContenidoPaquetesPorObra(obra_num)));
		}
		
		
		
		TareasPlantaReporte rp = new TareasPlantaReporte();
		
		
		
		
		
		
}
