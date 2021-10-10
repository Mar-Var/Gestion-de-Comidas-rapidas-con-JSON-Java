package co.edu.uptc.presentacion;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


public class MainWindow extends JFrame {
	
	private JScrollPane spVentana;
	private JTabbedPane tabbedPane;
	private JPanel panelDomiciliario;

	
	private JPanel panelInternoDomiciliario;
	private JLabel lbnombreDomiciliario;
	private JTextField txtnombreDomiciliario;
	private JLabel lbApellidosDomiciliario;
	private JTextField txtApellidosDomiciliario;
	private JLabel lbCedula;
	private JTextField txtCedula;
	private JLabel lbfechaNacimiento;
	private JDateChooser dcFechaNacimiento;
	private JLabel lbDireccion;
	private JTextField txtDireccion;
	private JLabel lbTelefono;
	private JTextField txtTelefono;
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTable tblDomiciliario;
	private DefaultTableModel dftblDomiciliario;
	private JScrollPane spDomiciliario;
	
	private JPanel panelProductos;
	
	private JPanel panelInternoProductos;
	private JLabel lbNombreProducto;
	private JTextField txtNombreProducto;
	private JLabel lbPrecioProducto;
	private JTextField txtPrecioProducto;
	private JTable tblProductos;
	private DefaultTableModel dftblProductos;
	private JScrollPane spProductos;
	private JButton btnAgregarproducto;
	private JButton btnActualizarProducto;
	private JButton btnBuscarProducto;
	private JButton btnEliminarProducto;
	
	private JPanel panelpedidos;
	
	private JPanel panelInternoPedidos;
	private JLabel lbCedulaCliente;
	private JTextField txtCedulaCliente;
	private JLabel lbNombreCiente;
	private JLabel lbDireccionCliente;
	private JLabel lbTelefonoCliente;
	private JLabel lbDomiciliario;
	private JComboBox cbDomiciliario;
	private JComboBox cbProductos;
	private JLabel lbCantidadProductos;
	private JTextField txtCantidadProductos;
	private JButton btnAgregarCarrito;
	private JButton btnTerminarpedido;
	private JButton btnLimpiarPedidos;
	
	private JPanel panelGestionesExtras;
	private JPanel panelSuperiorgestionesExtras;
	private JLabel lbFechaVenta;
	private JLabel lbTotalVentas;
	private JDateChooser dcFechaVentas;
	private JTextField txtTotalventas;
	private JTable tblventasporFecha;
	private JPanel panelInferiorIgestionesExtras;
	private JButton btnObtenerLiquidacion;
	private JTable tblLiquidaciones;
	private DefaultTableModel dftblLiquidaciones;
	private JScrollPane spLiquidaciones;
	
	private JPanel panelInferiorDgestionesExtras;
	private JButton btnObtenerPreferencia;
	private JTextField txtProductoPreferente;
	private JTable tblPreferencias;
	private DefaultTableModel dtblPreferencias;
	private JScrollPane spPreferencias;
	
	
	public MainWindow() {
		super("Rapidos y Furiosos");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);// Decir que pasa cuando se cierre la ventana
		setResizable(false);
		setLayout(new GridBagLayout());
	}
	
	public void begin() {
		createComponents();
		addComponents();
		
	}

	public void createComponents() {
		spVentana=new JScrollPane(this);
		tabbedPane=new JTabbedPane();
		panelDomiciliario= new JPanel();

		
		panelInternoDomiciliario=new JPanel();;
		lbnombreDomiciliario=new JLabel("Nombre");
		txtnombreDomiciliario=new JTextField();
		lbApellidosDomiciliario=new JLabel("Apellidos");
		txtApellidosDomiciliario= new JTextField();
		lbCedula=new JLabel("Cedula");
		txtCedula=new JTextField();
		lbfechaNacimiento=new JLabel("Fecha nacimiento");
		dcFechaNacimiento= new JDateChooser();
		lbDireccion= new JLabel("Direccion");
		txtDireccion=new JTextField();
		lbTelefono= new JLabel("Telefono");
		txtTelefono= new JTextField();
		btnAgregar=new JButton("Agregar");
		btnActualizar=new JButton("Actualizar");
		btnEliminar=new JButton("Eliminar");
		dftblDomiciliario= new DefaultTableModel(new Object[] {"Nombre","Apellido","Identificacion","Nacimiento"},0);
		tblDomiciliario= new JTable(dftblDomiciliario);
		spDomiciliario = new JScrollPane(tblDomiciliario);
		
		panelProductos=new JPanel();
		panelProductos.setLayout(new GridBagLayout());
		
		panelInternoProductos=new JPanel();
		panelInternoProductos.setLayout(new GridBagLayout());
		lbNombreProducto= new JLabel("Nombre");
		txtNombreProducto= new JTextField();
		lbPrecioProducto=;
		txtPrecioProducto;
		tblProductos;
		dftblProductos;
		spProductos;
		btnAgregarproducto;
		btnActualizarProducto;
		btnBuscarProducto;
		btnEliminarProducto;
		
		panelpedidos;
		
		panelInternoPedidos;
		lbCedulaCliente;
		txtCedulaCliente;
		lbNombreCiente;
		lbDireccionCliente;
		lbTelefonoCliente;
		lbDomiciliario;
		cbDomiciliario;
		cbProductos;
		lbCantidadProductos;
		txtCantidadProductos;
		btnAgregarCarrito;
		btnTerminarpedido;
		btnLimpiarPedidos;
		
		panelGestionesExtras;
		panelSuperiorgestionesExtras;
		lbFechaVenta;
		lbTotalVentas;
		dcFechaVentas;
		txtTotalventas;
		tblventasporFecha;
		panelInferiorIgestionesExtras;
		btnObtenerLiquidacion;
		tblLiquidaciones;
		dftblLiquidaciones;
		spLiquidaciones;
		
		panelInferiorDgestionesExtras;
		btnObtenerPreferencia;
		txtProductoPreferente;
		tblPreferencias;
		dtblPreferencias;
		spPreferencias;
		
		
	}

	
	public void addComponents() {
		// TODO Auto-generated method stub
		
	}
}
