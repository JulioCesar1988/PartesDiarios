package guis;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class Mod extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mod frame = new Mod();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mod() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Modificacion de parte");
		setBounds(100, 100, 988, 792);
		getContentPane().setLayout(null);
		
		JLabel lblBuscarElParte = new JLabel("Filtros");
		lblBuscarElParte.setBounds(12, 11, 264, 14);
		getContentPane().add(lblBuscarElParte);
		
		JLabel lblOperario = new JLabel("Operario");
		lblOperario.setBounds(12, 45, 64, 14);
		getContentPane().add(lblOperario);
		
		JButton btnBuscar = new JButton("buscar");
		btnBuscar.setBounds(754, 76, 97, 23);
		getContentPane().add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 110, 820, 2);
		getContentPane().add(separator);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(69, 42, 193, 20);
		getContentPane().add(comboBox);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(22, 70, 39, 14);
		getContentPane().add(lblFecha);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setBackground(SystemColor.textHighlight);
		dateChooser.setForeground(Color.WHITE);
		dateChooser.setBackground(Color.ORANGE);
		dateChooser.setBounds(69, 70, 126, 23);
		getContentPane().add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("Sector");
		lblNewLabel.setBounds(281, 45, 53, 14);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(330, 42, 155, 20);
		getContentPane().add(comboBox_1);
		
		JLabel lblTarea = new JLabel("Tarea");
		lblTarea.setBounds(495, 45, 53, 14);
		getContentPane().add(lblTarea);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(560, 42, 155, 20);
		getContentPane().add(comboBox_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(UIManager.getBorder("ToolBar.border"));
		scrollPane.setBounds(31, 178, 893, 541);
		getContentPane().add(scrollPane);

	}
}
