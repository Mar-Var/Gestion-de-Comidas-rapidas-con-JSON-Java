package co.edu.uptc.presentacion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


public class MainWindow extends JFrame {

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
	private DefaultTableModel dftblVentasPorFecha;
	private JScrollPane scVentasPorfecha;
	
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
		setMinimumSize(new Dimension(800,700));
		setResizable(true);
		
	}
	
	public void begin() {
		createComponents();
		addComponents();
		
	}

	public void createComponents() {
		tabbedPane=new JTabbedPane();
		panelDomiciliario= new JPanel();
		
		panelDomiciliario.setLayout(new GridBagLayout());
		
		panelInternoDomiciliario=new JPanel();;
		panelInternoDomiciliario.setBorder(new TitledBorder("Informacion del domiciliario"));
		panelInternoDomiciliario.setLayout(new GridBagLayout());
		
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
		panelInternoProductos.setBorder(new TitledBorder("Informacion de productos"));
		
		lbNombreProducto= new JLabel("Nombre");
		txtNombreProducto= new JTextField();
		lbPrecioProducto= new JLabel("Precio");
		txtPrecioProducto= new JTextField(); 
		dftblProductos= new DefaultTableModel(new Object[] {"Nombre","Costo"},0);
		tblProductos= new JTable(dftblProductos);
		spProductos= new JScrollPane(tblProductos);
		btnAgregarproducto = new JButton("Agregar");
		btnActualizarProducto=new JButton("Actualizar");
		btnBuscarProducto= new JButton("Buscar");
		btnEliminarProducto=new JButton("Eliminar");
		
		panelpedidos=new JPanel();
		panelpedidos.setLayout(new GridBagLayout());
		
		panelInternoPedidos=new JPanel();
		panelInternoPedidos.setLayout(new GridBagLayout());
		panelInternoPedidos.setBorder(new TitledBorder("Pedidos"));
		
		lbCedulaCliente=new JLabel("Cedula Cliente");
		txtCedulaCliente=new JTextField();
		lbNombreCiente=new JLabel("Nombre Cliente");
		lbDireccionCliente=new JLabel("Direccion");
		lbTelefonoCliente=new JLabel("Telefono");
		lbDomiciliario=new JLabel("Domiciliario");
		cbDomiciliario= new JComboBox<>();
		cbProductos=new JComboBox<>();
		lbCantidadProductos=new JLabel("Cantidad");
		txtCantidadProductos= new JTextField();
		btnAgregarCarrito=new JButton("Agregar al carrito");
		btnTerminarpedido= new JButton("Terminar pedido");
		btnLimpiarPedidos= new JButton("Limpiar campos");
		
		panelGestionesExtras=new JPanel();
		panelGestionesExtras.setLayout(new GridBagLayout());
		panelGestionesExtras.setSize(new Dimension(MAXIMIZED_HORIZ,MAXIMIZED_VERT));;
		
		panelSuperiorgestionesExtras=new JPanel();
		panelSuperiorgestionesExtras.setBorder(new TitledBorder("Bucador de pedidos por fecha"));
		panelSuperiorgestionesExtras.setLayout(new GridBagLayout());
		panelSuperiorgestionesExtras.setSize(new Dimension(MAXIMIZED_HORIZ,400));;
		lbFechaVenta= new JLabel("Fecha de venta");
		lbTotalVentas = new JLabel("Total de venta");
		dcFechaVentas= new JDateChooser();
		txtTotalventas= new JTextField();
		txtTotalventas.setEnabled(false);
		dftblVentasPorFecha = new DefaultTableModel(new Object[] {"Nombre Cliente","Apellido Cliente"
				,"Celular","Direccion","Identificacion Domiciliario","Productos Ordenados","Fecha","Costo"},0);
		tblventasporFecha = new JTable(dftblVentasPorFecha);
		scVentasPorfecha = new JScrollPane(tblventasporFecha);
				
		panelInferiorIgestionesExtras= new JPanel();
		panelInferiorIgestionesExtras.setBorder(new TitledBorder("liquidacion"));
		panelInferiorIgestionesExtras.setLayout(new GridBagLayout());
		panelInferiorIgestionesExtras.setSize(400, 400);
		btnObtenerLiquidacion= new JButton("Obtener");

		dftblLiquidaciones= new DefaultTableModel(new Object[] {"Nombre","Valor Liquidacion"},0);
		tblLiquidaciones = new JTable(dftblLiquidaciones);
		spLiquidaciones= new JScrollPane(tblLiquidaciones);
		
		panelInferiorDgestionesExtras= new JPanel();
		panelInferiorDgestionesExtras.setLayout(new GridBagLayout());
		panelInferiorDgestionesExtras.setBorder(new TitledBorder("Producto con mayor preferencia"));
		panelInferiorDgestionesExtras.setSize(400, 400);
		txtProductoPreferente= new JTextField();
		dtblPreferencias= new DefaultTableModel(new Object[] {"Nombre","Cantidad"},0);
		tblPreferencias= new JTable(dtblPreferencias);
		spPreferencias= new JScrollPane(tblPreferencias);
		
		
	}

	
	public void addComponents() {
		
		addcomponentsTopanelDomiciliario();
		tabbedPane.add("Domiciliario",panelDomiciliario);
		addComponentToProducts();
		tabbedPane.add("Productos",panelProductos);
		
		tabbedPane.add("Pedidos",panelpedidos);
		
		tabbedPane.add("Procesos",panelGestionesExtras);


		add(tabbedPane);
		
	}
	
	public void addcomponentsTopanelDomiciliario() {
		GridBagConstraints g = new GridBagConstraints();
		g.weightx=1.0;
		g.gridx=0;
		g.gridy=0;
		g.fill=GridBagConstraints.HORIZONTAL;
		
		addcomponentsTopanelInternalDomiciliario();
		panelDomiciliario.add(panelInternoDomiciliario,g);
		g.gridy=1;
		panelDomiciliario.add(spDomiciliario,g);
	}
	public void addcomponentsTopanelInternalDomiciliario() {
		GridBagConstraints g = new GridBagConstraints();

		g.anchor=GridBagConstraints.CENTER;
		g.weightx=1.0;
		g.gridx=0;
		g.gridy=0;
		
		g.insets= new Insets(10, 0, 10, 20);
		panelInternoDomiciliario.add(lbnombreDomiciliario,g);
		g.gridy=1;
		panelInternoDomiciliario.add(lbApellidosDomiciliario,g);
		g.gridy=2;
		panelInternoDomiciliario.add(lbCedula,g);
		g.gridy=0;
		g.gridx=1;
		g.fill=GridBagConstraints.BOTH;
		panelInternoDomiciliario.add(txtnombreDomiciliario,g);
		g.gridy=1;
		panelInternoDomiciliario.add(txtApellidosDomiciliario,g);
		g.gridy=2;
		panelInternoDomiciliario.add(txtCedula,g);
		g.fill=GridBagConstraints.NONE;
		
		g.gridy=0;
		g.gridx=2;
		panelInternoDomiciliario.add(lbfechaNacimiento,g);
		g.gridy=1;
		panelInternoDomiciliario.add(lbDireccion,g);
		g.gridy=2;
		panelInternoDomiciliario.add(lbTelefono,g);
		g.gridy=0;
		g.gridx=3;
		g.fill=GridBagConstraints.BOTH;
		panelInternoDomiciliario.add(dcFechaNacimiento,g);
		g.gridy=1;
		panelInternoDomiciliario.add(txtDireccion,g);
		g.gridy=2;
		panelInternoDomiciliario.add(txtTelefono,g);
		g.gridy=0;
		g.gridx=4;
		panelInternoDomiciliario.add(btnAgregar,g);
		g.gridy=1;
		panelInternoDomiciliario.add(btnActualizar,g);
		g.gridy=2;
		panelInternoDomiciliario.add(btnEliminar,g);
		
	}
	
	public void addComponentToProducts() {
		GridBagConstraints g = new GridBagConstraints();

		g.weightx=1.0;
		g.gridx=0;
		g.gridy=0;
		panelProductos.add(new JLabel("Gestion de productos"),g);
		g.fill=GridBagConstraints.HORIZONTAL;
		g.anchor=GridBagConstraints.CENTER;
		g.gridy=1;
		addComponentToProductsInternalPanel();
		panelProductos.add(panelInternoProductos,g);
		g.gridy=2;
		g.weightx=0.0;
		g.fill=GridBagConstraints.NONE;
		panelProductos.add(spProductos,g);
	}
	public void addComponentToProductsInternalPanel() {
		GridBagConstraints g = new GridBagConstraints();

		g.weightx=1.0;
		g.gridx=0;
		g.gridy=0;
		g.insets=new Insets(10, 20, 10, 20);
		g.anchor=GridBagConstraints.CENTER;
		
		panelInternoProductos.add(lbNombreProducto,g);
		g.gridy=1;
		g.fill=GridBagConstraints.BOTH;
		panelInternoProductos.add(btnAgregarproducto,g);
		g.gridx=1;
		g.gridy=0;

		panelInternoProductos.add(txtNombreProducto,g);
		g.gridy=1;
		//g.fill=GridBagConstraints.NONE;
		panelInternoProductos.add(btnActualizarProducto,g);
		g.gridx=3;
		g.gridy=0;
		g.fill=GridBagConstraints.NONE;
		panelInternoProductos.add(lbPrecioProducto,g);
		g.gridy=1;
		g.fill=GridBagConstraints.BOTH;
		panelInternoProductos.add(btnEliminarProducto,g);
		g.gridx=4;
		g.gridy=0;

		panelInternoProductos.add(txtPrecioProducto,g);
		g.fill=GridBagConstraints.NONE;
	}
	
	public void addComponentsToPanelPedidos() {
		GridBagConstraints g = new GridBagConstraints();
	}
	
	
}
