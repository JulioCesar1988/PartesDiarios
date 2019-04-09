package guis;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import models.PaqueteBean;
import net.sf.jasperreports.engine.JRException;
import herramientas.CargadorCombobox;
import reportes.ReporteGenerador;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;
import controllers.PaqueteController;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ParteDiario extends JFrame {
	
	private JComboBox comboBoxObra;     // PARA SELECCIONAR LA OBRA 
	private JComboBox comboBoxPaquete;  // PARA SELECCIONAR EL PAQUETE 
	JDateChooser dateChooser;            // APLICAR FILTROS DE FECHAS
	JDateChooser dateChooser_1;          // APLICAR FILTROS DE FECHAS
	JRadioButton rdbtnReporteDeTareas_1; // CONTROL DE FILTROS
	JRadioButton rdbtnReporteDeTareas;
	
	private JTextField textField;          // ELEMENTO 
	private JTextField textField_1;        // CORRELATIVIDAD 
	
	
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public ParteDiario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0); 
			}
		});  // Creacion del constructor. 
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo\\logoHeader.png"));
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		//setClosable(true);
		setTitle("Reporte de sector Pintura");
		setBounds(100, 100, 934, 788);
		setExtendedState(MAXIMIZED_BOTH);
		JLabel lblGenerarReporteDe = new JLabel("Generar Reporte de Sector Pintura ");
		lblGenerarReporteDe.setForeground(Color.GRAY);
		lblGenerarReporteDe.setFont(new Font("Arial", Font.PLAIN, 50));
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		// Controlador principal 
		
		JButton btnGenerarPdf = new JButton("Generar reporte");
		btnGenerarPdf.setForeground(SystemColor.inactiveCaptionText);
		btnGenerarPdf.setBackground(SystemColor.window);
		btnGenerarPdf.setFont(new Font("Tahoma", Font.ITALIC, 15));
		btnGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
					if (rdbtnReporteDeTareas.isSelected()) {
				
						Integer filtroPaquete = Integer.parseInt((String) comboBoxPaquete.getSelectedItem());
						String obra = (String) comboBoxObra.getSelectedItem();
						StringTokenizer tk2 = new StringTokenizer(obra, " - ");
						int filtroObra = Integer.parseInt(tk2.nextToken());
					    ReporteGenerador rp = new ReporteGenerador();
	
					    
					try {
						
						
						rp.generarReporteTareasPlantaPintura(filtroObra, filtroPaquete  );
						
						
						
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					    }
			
		        	else{ 
				         
		        	
		        		String formato = dateChooser.getDateFormatString();
		        		Date date      = dateChooser.getDate();
		        		
		        		
			        	SimpleDateFormat sdf = new SimpleDateFormat(formato);
				        String fecha1   = String.valueOf(sdf.format(date));
				        
				        
				        Date date1 = dateChooser_1.getDate();
				       String  fecha2 = String.valueOf(sdf.format(date1));
				        
				       System.out.println(fecha1);

				       System.out.println(fecha2);
				        
				        ReporteGenerador rp = new ReporteGenerador();
					
				        
				    try { 
				    	 rp.generarReporteTareasPlantaPinturaPorFecha(fecha1 , fecha2);
						} catch (JRException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    
				        
						
					}
				
			}
		
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("logo\\logoHeader.png"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(122, Short.MAX_VALUE)
					.addComponent(lblGenerarReporteDe, GroupLayout.PREFERRED_SIZE, 833, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
						.addComponent(btnGenerarPdf)
						.addComponent(btnCerrar))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(lblGenerarReporteDe, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGenerarPdf)
					.addGap(30)
					.addComponent(btnCerrar)
					.addContainerGap())
		);
		CargadorCombobox cc=new CargadorCombobox();
		
		JLabel lblObra = new JLabel("Obra");
		lblObra.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		JLabel lblPaquete = new JLabel("Paquete");
		lblPaquete.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		comboBoxObra = new JComboBox();
		comboBoxObra.setForeground(Color.DARK_GRAY);
		comboBoxObra.setBackground(SystemColor.textHighlight);
		comboBoxObra.setEnabled(false);
		comboBoxObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarComboBoxPaqueteNumero();
			}
		});
		comboBoxObra.setModel(new DefaultComboBoxModel(cc.generarContenidoObra()));
		// Tenemos que cargarlo con las posiciones de las piezas que tengamos en ese paquete . 
		
	// OBRA + PAQUETE = PIEZAS.GETCORRELATIVIDAD + PIEZAS.GETELEMENTO  = POSICION 
		
		//filtroObra, filtroPaquete
         PaqueteController paq = new PaqueteController(); 
         
         
            String obra2 = (String) comboBoxObra.getSelectedItem();
			StringTokenizer tk2 = new StringTokenizer(obra2, " - ");
			int obra_num2=Integer.parseInt(tk2.nextToken());
         
         
         
           String[] lis;
		try {
			lis = paq.getListaDePaquete(obra_num2);
			for (int i = 0; i < lis.length; i++) {
				//System.out.println(lis[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	 JRadioButton rdbtnIncluirFecha = new JRadioButton("incluir fecha");
		rdbtnIncluirFecha.setVisible(false);
		rdbtnIncluirFecha.setEnabled(false);
		
		
		comboBoxPaquete = new JComboBox();
		comboBoxPaquete.setEnabled(false);
		comboBoxPaquete.setBackground(Color.WHITE);
		String obra = (String) comboBoxObra.getSelectedItem();
		if (obra!=null){
			System.out.println("es"+obra);
			cargarComboBoxPaqueteNumero();
		
		}
		
		JLabel lblFecha = new JLabel("Fecha desde");
		lblFecha.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setEnabled(false);
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBackground(Color.WHITE);
		dateChooser_1.setEnabled(false);
		rdbtnReporteDeTareas_1 = new JRadioButton("Por rango de fechas");
		rdbtnReporteDeTareas_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnReporteDeTareas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxObra.setEnabled(false);
				comboBoxPaquete.setEnabled(false);
				
				dateChooser.setEnabled(true);
				dateChooser_1.setEnabled(true);
				rdbtnReporteDeTareas.setSelected(false);
				
				textField.setEditable(false);
				textField_1.setEditable(false);
			
				
				
			   
			}
		});
		rdbtnReporteDeTareas = new JRadioButton("Aplicar filtros");
		rdbtnReporteDeTareas.setFont(new Font("Arial", Font.PLAIN, 13));
		rdbtnReporteDeTareas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			comboBoxObra.setEnabled(true);
			comboBoxPaquete.setEnabled(false);
			textField.setEditable(false);
			textField_1.setEditable(false);
	
			
			dateChooser.setEnabled(false);
			dateChooser_1.setEnabled(false);
			rdbtnReporteDeTareas_1.setSelected(false);}
		});
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblElemento = new JLabel("Elemento");
		lblElemento.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		JLabel labelCorrelatividad = new JLabel("Correlatividad");
		labelCorrelatividad.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		


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
							.addGap(102)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblObra)
								.addComponent(lblPaquete)
								.addComponent(lblFecha)
								.addComponent(lblElemento, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelCorrelatividad, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBoxPaquete, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxObra, Alignment.TRAILING, 0, 390, Short.MAX_VALUE)
								.addComponent(textField)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblFechaHasta)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addComponent(textField_1))
							.addGap(26)
							.addComponent(rdbtnIncluirFecha)))
					.addContainerGap(1128, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(rdbtnIncluirFecha)
						.addGroup(gl_panel.createSequentialGroup()
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
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblElemento))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelCorrelatividad, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFecha)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFechaHasta)
								.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(480, Short.MAX_VALUE))
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
		
		
		
		public static void main(String[] args) {
			TareasPlantaReporte rp = new TareasPlantaReporte();
			rp.setVisible(true);
			
		}
}