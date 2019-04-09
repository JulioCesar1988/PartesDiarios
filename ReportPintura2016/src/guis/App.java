package guis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.sun.tools.example.debug.expr.ParseException;
import com.toedter.calendar.JDateChooser;
import controllers.DetalleparteController;
import controllers.EmpleadoController;
import controllers.MasterparteController;
import controllers.ObrasController;
import controllers.PaqueteController;
import controllers.SectorController;
import controllers.TareaController;
import herramientas.CargadorCombobox;
import models.DetalleparteBean;
import models.MasterparteBean;
import net.sf.jasperreports.engine.JRException;
import reportes.ReporteGenerador;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JSeparator;

public class App extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JDateChooser fecha;
	private JTextField desde = new JTextField();
	private JComboBox comboBoxOperario;
	private JComboBox comboBoxSector;
	private JComboBox comboBoxTarea;
	private boolean modoEdicion = false;
	private boolean erroresEnLaValidacionDetalle = false;
	private int id_master;
	private JButton btnSave;
	private JButton btnValidar;
	private JButton btnNuevoDuplicar;

	private static App instance;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					instance = new App();
					instance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws java.text.ParseException
	 */

	public static App getSingleton() {
		return App.instance;
	}

	@SuppressWarnings("null")
	public App() throws java.text.ParseException, ParseException {
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setTitle("Carga de Parte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 586);
		contentPane = new JPanel();
		contentPane.setForeground(Color.ORANGE);
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setForeground(Color.ORANGE);
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(10, 10, 1360, 92);
		contentPane.add(panel);
		panel.setLayout(null);

		fecha = new JDateChooser();
		fecha.setForeground(Color.WHITE);
		fecha.setBackground(Color.ORANGE);
		fecha.setDate(yesterday());
		fecha.getCalendarButton().setBackground(SystemColor.textHighlight);
/*		fecha.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});*/
		fecha.setBounds(814, 55, 126, 23);
		panel.add(fecha);

		final CargadorCombobox cc = new CargadorCombobox();

		//comboBoxOperario = new JComboBox(cc.generarContenidoEmpleadoLegajoNombre("MBI"));
		comboBoxOperario = new JComboBox(cc.generarContenidoEmpleadoLegajoNombreAll());
		
		
		comboBoxOperario.setForeground(Color.BLACK);
		comboBoxOperario.setBackground(Color.WHITE);

		comboBoxOperario.setBounds(62, 11, 490, 20);
		panel.add(comboBoxOperario);

		comboBoxSector = new JComboBox(cc.generarContenidoSector());
		comboBoxSector.setForeground(Color.BLACK);
		comboBoxSector.setBackground(Color.WHITE);
		comboBoxSector.setBounds(62, 55, 448, 20);
		panel.add(comboBoxSector);

		final JLabel lblObra = new JLabel("Operario");
		lblObra.setFont(new Font("Dialog", Font.BOLD, 12));
		lblObra.setBackground(Color.BLACK);
		lblObra.setForeground(Color.BLACK);
		lblObra.setBounds(10, 14, 61, 14);
		panel.add(lblObra);

		JLabel lblSector = new JLabel("Sector");
		lblSector.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSector.setForeground(Color.BLACK);
		lblSector.setBounds(6, 58, 46, 14);
		panel.add(lblSector);

		JLabel lblFecha = new JLabel("fecha");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setBounds(756, 61, 46, 14);
		panel.add(lblFecha);

		comboBoxTarea = new JComboBox(cc.generarContenidoTareas());
		comboBoxTarea.setForeground(Color.BLACK);
		comboBoxTarea.setBackground(Color.WHITE);
		comboBoxTarea.setToolTipText("Selecciona la terea a realizar");
		comboBoxTarea.setBounds(571, 55, 92, 20);
		panel.add(comboBoxTarea);

		JLabel lblNewLabel = new JLabel("Tarea");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(522, 58, 46, 14);
		panel.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Report");
		btnNewButton_1.setToolTipText("Generacion de reporte ");
		btnNewButton_1.setIcon(
				null);
		btnNewButton_1.setBounds(1232, 32, 107, 46);
		panel.add(btnNewButton_1);
		btnNewButton_1.setBackground(Color.WHITE);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String empleado = (String) comboBoxOperario.getSelectedItem();
					String tarea = comboBoxTarea.getSelectedItem().toString();
					String sector = comboBoxSector.getSelectedItem().toString();
					StringTokenizer tk2 = new StringTokenizer(empleado, " - ");
					int num_legajo = Integer.parseInt(tk2.nextToken());
					// VERIFICACION DE FECHA .
					String fecha1;
					String formato = fecha.getDateFormatString();
					Date date = fecha.getDate();

					// FECHA
					if (date == null) {
						throw new IOException();
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat(formato);
						fecha1 = String.valueOf(sdf.format(date));
						System.out.println(" fecha -> " + fecha1);
					}

					MasterparteController mmm = new MasterparteController();
					int id_aux_master = mmm.buscarMaster(num_legajo, tarea, sector, fecha1);
					this.cargarTabla(id_aux_master);
					id_master = id_aux_master;
					modoEdicion = true;
					btnSave.setText("Actualizar");
					btnSave.setEnabled(true);
					btnValidar.setEnabled(true);
					btnNuevoDuplicar.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "No se ha encontrado el parte");
					btnSave.setEnabled(false);
					btnValidar.setEnabled(false);
					btnNuevoDuplicar.setEnabled(false);
				}
			}

			private void cargarTabla(int id_master) throws Exception {

				DetalleparteController dct = new DetalleparteController();
				ArrayList<Vector<Object>> aux = dct.getDetalle(id_master);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				// Varia la lista de items
				while (model.getRowCount() > 0) {
					model.removeRow(0);
				}
				// Carga los items
				for (int k = 0; k < aux.size(); k++) {
					model.addRow(new Object[] { (boolean) aux.get(k).get(0), aux.get(k).get(1), aux.get(k).get(2),
							Integer.parseInt(aux.get(k).get(3).toString()),
							Integer.parseInt(aux.get(k).get(4).toString()), aux.get(k).get(5),
							Integer.parseInt(aux.get(k).get(6).toString()), (boolean) aux.get(k).get(7) });
				}
			}

		});
		btnBuscar.setIcon(null);
		btnBuscar.setBounds(1113, 32, 107, 46);
		panel.add(btnBuscar);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// GENERACION DEL REPORTE
				String empleado = (String) comboBoxOperario.getSelectedItem();
				String tarea = comboBoxTarea.getSelectedItem().toString();
				String sector = comboBoxSector.getSelectedItem().toString();
				StringTokenizer tk2 = new StringTokenizer(empleado, " - ");
				int num_legajo = Integer.parseInt(tk2.nextToken());

				// VERIFICACION DE FECHA .
				String fecha1;
				String formato = fecha.getDateFormatString();
				Date date = fecha.getDate();

				// FECHA
				boolean fechaIngresada = false;
				if (date == null) {
					fecha1 = "SIN FECHA";

				} else {
					SimpleDateFormat sdf = new SimpleDateFormat(formato);
					fecha1 = String.valueOf(sdf.format(date));
					System.out.println(" fecha -> " + fecha1);
					fechaIngresada = true;
				}

				ReporteGenerador rr = new ReporteGenerador();
				try {
					rr.ParteConDetalles(num_legajo, tarea, sector, fecha1);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		Panel panel_3 = new Panel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBounds(1253, 116, 117, 428);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		// AGREGAMOS UNA FILA MAS .
		JButton button = new JButton("+");
		button.setToolTipText("Agregar fila en blanco");
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("agregando una fila mas ");

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { null, null, null, null, null, null, null, null});
			}

		});
		button.setBounds(32, 19, 53, 36);
		panel_3.add(button);
		// PARA ELIMINAR UNA FILA TENEMOS QUE SELECCIONARLA.
		JButton button_1 = new JButton("-");
		button_1.setToolTipText("Elimina la fila seleccionada");
		button_1.setBackground(Color.WHITE);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int[] rows = table.getSelectedRows();
				for (int i = 0; i < rows.length; i++) {
					model.removeRow(rows[i] - i);
				}
			}
		});

		button_1.setBounds(32, 56, 53, 36);
		panel_3.add(button_1);

		btnSave = new JButton("Grabar");
		btnSave.setBounds(6, 376, 107, 46);
		panel_3.add(btnSave);
		btnSave.setIcon(null);
		btnSave.setBackground(Color.WHITE);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				while (model.getRowCount() > 0) {
					model.removeRow(0);
				}
				modoEdicion = false;
				btnSave.setText("Grabar");
				btnSave.setEnabled(true);
				btnNuevoDuplicar.setEnabled(false);
			}
		});
		btnNuevo.setBounds(6, 202, 107, 46);
		panel_3.add(btnNuevo);

		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setBounds(10, 6, 75, 14);
		panel_3.add(lblOperaciones);
		lblOperaciones.setForeground(Color.WHITE);

		btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MasterparteBean mb = App.getSingleton().getEncabezadoValido();
					if (erroresEnLaValidacionDetalle) {
						JOptionPane.showMessageDialog(null, "Parte CON ERRORES.");
					} else {
						JOptionPane.showMessageDialog(null, "Parte valido");
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Se produjo un error al momento de grabar");
					e.printStackTrace();
				}
			}
		});
		btnValidar.setBounds(6, 318, 107, 46);
		panel_3.add(btnValidar);

		JButton btnDuplicar = new JButton("D");
		btnDuplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int[] rows = table.getSelectedRows();
				for (int i = 0; i < rows.length; i++) {
					model.addRow(new Object[] { table.getValueAt(i, 0), table.getValueAt(i, 1), table.getValueAt(i, 2),
							table.getValueAt(i, 3), table.getValueAt(i, 4), table.getValueAt(i, 5),
							table.getValueAt(i, 6) });
				}
			}
		});
		btnDuplicar.setToolTipText("Duplicar fila seleccionada");
		btnDuplicar.setBackground(Color.WHITE);
		btnDuplicar.setBounds(32, 91, 53, 36);
		panel_3.add(btnDuplicar);
		
		btnNuevoDuplicar = new JButton("Duplicar");
		btnNuevoDuplicar.setEnabled(false);
		btnNuevoDuplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoEdicion = false;
				btnSave.setText("Grabar");
				btnSave.setEnabled(true);
			}
		});
		btnNuevoDuplicar.setBounds(6, 260, 107, 46);
		panel_3.add(btnNuevoDuplicar);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					MasterparteBean mb = App.getSingleton().getEncabezadoValido();
					mb.save();
					String mensaje = "Se ha generado el parte ";
					if (erroresEnLaValidacionDetalle) {
						mensaje += " CON ERRORES, genere un reporte para verificar sus datos ingresado.";
					} else {
						mensaje += " con exito!";
					}
					JOptionPane.showMessageDialog(null, mensaje);
					modoEdicion = true;
					btnSave.setText("Actualizar");
					id_master = mb.getId_masterParte();

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Ya existe un parte con los mismas datos del encabezado!");
					e.printStackTrace();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Se produjo un error al momento de grabar");
					e.printStackTrace();
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(UIManager.getBorder("ToolBar.border"));
		scrollPane.setBounds(10, 116, 1237, 428);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBorder(null);
		table.setFont(new Font("Dialog", Font.BOLD, 14));
		// table.setForeground(Color.WHITE);
		// table.setBackground(Color.ORANGE);

		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
				new String[] { "Es Pieza", "Inicio HH:MM", "Fin HH:MM", "Obra", "Lista", "Posicion", "Cantidad",
						"Terminado", "ERRORES" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Integer.class, Integer.class,
					String.class, Integer.class, Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {

				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		// table.getColumnModel().getColumn(3).setMinWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(10);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
		table.getActionMap().put("Action.NextCell", new NextCellActioin());

		JLabel lblDetalles = new JLabel("Detalles");
		lblDetalles.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDetalles.setBounds(23, 206, 59, 16);
		contentPane.add(lblDetalles);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 108, 1350, 2);
		contentPane.add(separator);
	}

	public void addNewRow() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { null, null, null, null, null, null, null, null });
	}

	private MasterparteBean getEncabezadoValido() throws Exception {
		String empleado = (String) comboBoxOperario.getSelectedItem();
		StringTokenizer tk2 = new StringTokenizer(empleado, " - ");
		int num_legajo = Integer.parseInt(tk2.nextToken());
		EmpleadoController ec = new EmpleadoController();
		int id_empleado = ec.getIdEmpleadoPorLegajo(num_legajo);
		// TAREA
		String tarea = comboBoxTarea.getSelectedItem().toString();
		TareaController tc = new TareaController();
		int id_tarea = tc.getIdTareaCodigo(tarea);
		// SECTOR
		String sector = comboBoxSector.getSelectedItem().toString();
		SectorController sc = new SectorController();
		int id_sector = sc.getIdSector(sector);
		// VERIFICACION DE FECHA .
		String fecha1 = null;
		String formato = fecha.getDateFormatString();
		Date date = fecha.getDate();
		// FECHA
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			fecha1 = String.valueOf(sdf.format(date));
		}
		// Verifica que ya no exista un Parte para la misma fecha con los mismas datos
		// de encabezado
		if (!modoEdicion && MasterparteBean.YaExiste(id_empleado, id_sector, id_tarea, fecha1)) {
			throw new IOException("Ya existe un parte con los mismas datos del encabezado!");
		} else {
			MasterparteBean mb;
			if (modoEdicion) {
				mb = new MasterparteBean(id_empleado, id_sector, id_tarea, fecha1, false, this.getDetalleValido(),
						!modoEdicion, id_master);
			} else {
				mb = new MasterparteBean(id_empleado, id_sector, id_tarea, fecha1, false, this.getDetalleValido(),
						!modoEdicion);
			}
			return mb;
		}
	}

	private ArrayList<DetalleparteBean> getDetalleValido() throws Exception {
		ArrayList<DetalleparteBean> detallePartes = new ArrayList<DetalleparteBean>();
		erroresEnLaValidacionDetalle = false;
		for (int i = 0; i < table.getRowCount(); i++) {
			table.setValueAt("", i, 8);// Blanqueo errores
			DetalleparteBean dpb = new DetalleparteBean();
			boolean fila_cumple = true;

			String horaDesde = (String) table.getValueAt(i, 1);
			if (horaDesde == null || horaDesde.equals("")) {
				continue;
			}
			// Pieza / Subpieza
			boolean esPieza = table.getValueAt(i, 0) == null ? false : (boolean) table.getValueAt(i, 0);
			dpb.setEsPieza(esPieza);

			// Verifica horas
			dpb.setDesde(horaDesde);
			dpb.setHasta((String) table.getValueAt(i, 2));
			if (!dpb.horasRangoValido()) {
				fila_cumple = false;
				table.setValueAt("Formato o rango horas - ", i, 8);
			}

			// Verifica Obra
			try {
				int num_obra = 0;
				if (table.getValueAt(i, 3) != null) {
					num_obra = (int) table.getValueAt(i, 3);
					ObrasController oc = new ObrasController();
					int id_obra = oc.getIdObraPorId(num_obra);
					dpb.setId_obra(id_obra);

					// Verifica Paquete
					try {
						int num_paq = 0;
						PaqueteController pqc = new PaqueteController();
						if (table.getValueAt(i, 4) != null) {
							num_paq = (int) table.getValueAt(i, 4);
							int id_paquete = pqc.getIdPaquetePorObra(num_obra, num_paq);
							dpb.setId_paquete(id_paquete);

							// Verifica la posicion (Pieza o Subpieza segun corresponda)
							try {
								if (table.getValueAt(i, 5) != null) {
									String posicion = (String) table.getValueAt(i, 5);
									if (pqc.getCantdadPosicionId(id_obra, id_paquete, posicion, esPieza) > 0) {
										dpb.setPosicion(posicion);
									}
								} else {
									throw new IOException("No se ingreso la posicion");
								}

							} catch (IOException e3) {
								fila_cumple = false;
								table.setValueAt(table.getValueAt(i, 8) + "Posicion - ", i, 8);
							}
						} else {
							throw new IOException("No se ingreso el Paquete");
						}

					} catch (IOException e3) {
						fila_cumple = false;
						table.setValueAt(table.getValueAt(i, 8) + "Lista - ", i, 8);
					}

				} else {
					throw new IOException("No se ingreso la obra");
				}
			} catch (IOException e) {
				fila_cumple = false;
				table.setValueAt(table.getValueAt(i, 8) + "Obra - ", i, 8);
			}

			if (table.getValueAt(i, 6) != null) {
				int cantidad = (int) table.getValueAt(i, 6);
				dpb.setCantidad(cantidad);
			} else {
				fila_cumple = false;
				table.setValueAt(table.getValueAt(i, 8) + "Cantidad - ", i, 8);
			}

			boolean terminado = table.getValueAt(i, 7) == null ? false : (boolean) table.getValueAt(i, 7);
			dpb.setTerminado(terminado);

			if (fila_cumple) {
				detallePartes.add(dpb);
			} else {
				erroresEnLaValidacionDetalle = true;
			}
		}
		return detallePartes;
	}

	private Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
	
	private class NextCellActioin extends AbstractAction {

		public NextCellActioin() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int col = table.getSelectedColumn();
			int row = table.getSelectedRow();
			int colCount = table.getColumnCount();
			int rowCount = table.getRowCount();
			col++;
			if (col >= colCount) {
				col = 0;
				row++;
			}
			if (row >= rowCount) {
				row = 0;
			}
			table.getSelectionModel().setSelectionInterval(row, row);
			table.getColumnModel().getSelectionModel().setSelectionInterval(col, col);
			table.editCellAt(row, col);
			if (col == 8 && row == rowCount - 1) {
				addNewRow();
				table.scrollRectToVisible(table.getCellRect(rowCount, 0, true));
			}

		}
	}

}
