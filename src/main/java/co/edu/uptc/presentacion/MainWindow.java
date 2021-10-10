package co.edu.uptc.presentacion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

	private JButton btnEliminarProducto;
	
	private JPanel panelpedidos;
	
	private JPanel panelInternoPedidos;
	private JLabel lbCedulaCliente;
	private JTextField txtCedulaCliente;
	private JLabel lbNombreCiente;
	private JTextField txtNombreCliente;
	private JLabel lbDireccionCliente;
	private JTextField txtDireccioncliente;
	private JLabel lbTelefonoCliente;
	private JTextField txtTelefonoCliente;
	private JLabel lbDomiciliario;
	private JComboBox cbDomiciliario;
	private JComboBox cbProductos;
	private JLabel lbCantidadProductos;
	private JTextField txtCantidadProductos;
	private JList ltProductosPedido = new  JList();
	private DefaultListModel dfltProductospedido = new  DefaultListModel();
	private JScrollPane jsProductosPedido = new JScrollPane();
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
	private JButton btnBuscarProducto;
	
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
		setMinimumSize(new Dimension(1000,700));
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
		
		lbnombreDomiciliario=new JLabel("Identificacion del Domiciliario");
		txtnombreDomiciliario=new JTextField(20);
		lbApellidosDomiciliario=new JLabel("Apellidos");
		txtApellidosDomiciliario= new JTextField(20);
		lbCedula=new JLabel("Cedula");
		txtCedula=new JTextField(20);
		lbfechaNacimiento=new JLabel("Fecha nacimiento");
		dcFechaNacimiento= new JDateChooser();
		lbDireccion= new JLabel("Direccion");
		txtDireccion=new JTextField(20);
		lbTelefono= new JLabel("Telefono");
		txtTelefono= new JTextField(20);
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
		txtNombreProducto= new JTextField(20);
		lbPrecioProducto= new JLabel("Precio");
		txtPrecioProducto= new JTextField(20); 
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
		panelInternoPedidos.setBorder(new TitledBorder("Datos del Pedido"));
		
		lbCedulaCliente=new JLabel("Cedula Cliente");
		txtCedulaCliente=new JTextField(20);
		lbNombreCiente=new JLabel("Nombre Cliente");
		lbDireccionCliente=new JLabel("Direccion");
		lbTelefonoCliente=new JLabel("Telefono");
		lbDomiciliario=new JLabel("Domiciliario");
		cbDomiciliario= new JComboBox();
		cbProductos=new JComboBox<>();
		lbCantidadProductos=new JLabel("Cantidad");
		txtCantidadProductos= new JTextField(10);
		
		dfltProductospedido = new  DefaultListModel();
		ltProductosPedido = new  JList(dfltProductospedido);
		jsProductosPedido = new JScrollPane(ltProductosPedido);
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
		txtTotalventas= new JTextField(20);
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
		txtProductoPreferente= new JTextField(20);
		txtProductoPreferente.setEnabled(false);
		dtblPreferencias= new DefaultTableModel(new Object[] {"Nombre","Cantidad"},0);
		tblPreferencias= new JTable(dtblPreferencias);
		spPreferencias= new JScrollPane(tblPreferencias);
		txtNombreCliente= new JTextField(20);
		txtDireccioncliente= new JTextField(20);
		txtTelefonoCliente = new JTextField(20);
		btnObtenerPreferencia=new JButton("Obtener");
		
		
	}

	
	public void addComponents() {
		
		addcomponentsTopanelDomiciliario();
		tabbedPane.add("Domiciliario",panelDomiciliario);
		addComponentToProducts();
		tabbedPane.add("Productos",panelProductos);
		addComponentsToPanelPedidos();
		tabbedPane.add("Pedidos",panelpedidos);
		addComponentGestiones();
		tabbedPane.add("Procesos",panelGestionesExtras);


		add(tabbedPane);
		
	}
	
	public void addcomponentsTopanelDomiciliario() {
		GridBagConstraints g = new GridBagConstraints();
		g.insets= new Insets(10, 10, 10, 10);
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


		g.weightx=0.1;
		g.gridx=0;
		g.gridy=0;
		g.fill=GridBagConstraints.BOTH;
		g.insets= new Insets(10, 0, 10, 20);
		panelInternoDomiciliario.add(lbnombreDomiciliario,g);
		g.gridy=1;
		panelInternoDomiciliario.add(lbApellidosDomiciliario,g);
		g.gridy=2;
		panelInternoDomiciliario.add(lbCedula,g);
		g.gridy=0;
		g.gridx=1;

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
		g.insets= new Insets(10, 10, 10, 10);
	//	g.weightx=1.0;
		g.gridx=0;
		g.gridy=0;
		panelProductos.add(new JLabel("Gestion de productos"),g);
		//g.fill=GridBagConstraints.HORIZONTAL;
		g.anchor=GridBagConstraints.CENTER;
		g.gridy=1;
		addComponentToProductsInternalPanel();
		panelProductos.add(panelInternoProductos,g);
		g.gridy=2;
	//	g.fill=GridBagConstraints.NONE;
		panelProductos.add(spProductos,g);
	}
	public void addComponentToProductsInternalPanel() {
		GridBagConstraints g = new GridBagConstraints();

		g.weightx=1.0;
		g.gridx=0;
		g.gridy=0;
		g.anchor=GridBagConstraints.CENTER;
		g.insets= new Insets(10, 10, 10, 10);
		panelInternoProductos.add(lbNombreProducto,g);
		g.gridy=1;
		g.fill=GridBagConstraints.BOTH;
		panelInternoProductos.add(btnAgregarproducto,g);
		g.gridx=1;
		g.gridy=0;

		panelInternoProductos.add(txtNombreProducto,g);
		g.gridy=1;
		g.fill=GridBagConstraints.NONE;
		panelInternoProductos.add(btnActualizarProducto,g);
		g.gridx=3;
		g.gridy=0;
		panelInternoProductos.add(lbPrecioProducto,g);
		g.gridy=1;
		g.fill=GridBagConstraints.BOTH;
		g.weightx=0.0;
		panelInternoProductos.add(btnEliminarProducto,g);
		g.gridx=4;
		g.gridy=0;

		panelInternoProductos.add(txtPrecioProducto,g);
		g.fill=GridBagConstraints.NONE;
	}
	
	public void addComponentsToPanelPedidos() {
		GridBagConstraints g = new GridBagConstraints();
		g.insets=new Insets(10, 10, 10, 10);
		g.gridx=0;
		g.gridy=0;
		g.gridwidth=2;
		g.fill=GridBagConstraints.HORIZONTAL;
		addComponentsToPanelPedidosInternalPlanel();
		panelpedidos.add(panelInternoPedidos,g);
		g.insets=new Insets(0, 0, 0, 0);
		g.gridwidth=1;
		g.gridy=1;
		g.fill=GridBagConstraints.NONE;
	//	g.anchor=GridBagConstraints.CENTER;
	//	panelpedidos.add(btnTerminarpedido,g);
	//	g.gridx=1;
	//	panelpedidos.add(btnLimpiarPedidos,g);
		
	}
	public void addComponentsToPanelPedidosInternalPlanel() {
		
		GridBagConstraints g = new GridBagConstraints();
		g.insets= new Insets(10, 10, 10, 10);
		g.fill= GridBagConstraints.HORIZONTAL;
		g.weightx=1.0;
		g.anchor=GridBagConstraints.WEST;
		g.gridx=0;
		g.gridy=0;
		panelInternoPedidos.add(lbCedulaCliente,g);
		g.gridx=1;

		panelInternoPedidos.add(txtCedulaCliente,g);
		//g.fill= GridBagConstraints.NONE;
		g.gridx=2;
		panelInternoPedidos.add(new JLabel("Identificacion del Domiciliario"),g);
		g.gridx=3;
		g.weightx=2;
		//g.fill= GridBagConstraints.NONE;
		panelInternoPedidos.add(cbDomiciliario,g);

		g.weightx=1;
		g.gridy=1;
		g.gridx=0;
		panelInternoPedidos.add(lbNombreCiente,g);
		g.gridx=1;
		//g.fill= GridBagConstraints.BOTH;
		panelInternoPedidos.add(txtNombreCliente,g);
		g.gridx=2;
		//g.fill=GridBagConstraints.NONE;
		panelInternoPedidos.add(cbProductos,g);
		g.gridx=3;
		panelInternoPedidos.add(lbCantidadProductos,g);
		g.gridx=4;
		//g.fill=GridBagConstraints.BOTH;
		panelInternoPedidos.add(txtCantidadProductos,g);
		//g.fill=GridBagConstraints.NONE;
		g.gridy=2;
		g.gridx=0;
		//g.fill= GridBagConstraints.HORIZONTAL;
		panelInternoPedidos.add(lbDireccionCliente,g);
		g.gridx=1;
		
		panelInternoPedidos.add(txtDireccioncliente,g);
		//g.fill= GridBagConstraints.NONE;
		g.gridx=2;
		g.weighty=2;
		panelInternoPedidos.add(jsProductosPedido,g);
		g.weighty=1;
		g.gridx=3;
		panelInternoPedidos.add(btnAgregarCarrito,g);
		g.gridy=3;
		g.gridx=3;
		g.fill=GridBagConstraints.NONE;
		g.anchor=GridBagConstraints.CENTER;
		panelInternoPedidos.add(btnTerminarpedido,g);
		g.gridx=4;
		panelInternoPedidos.add(btnLimpiarPedidos,g);
		
	}
	public void addComponentGestiones() {
		GridBagConstraints g = new GridBagConstraints();
		g.insets=new Insets(10, 10, 10, 10);
		g.weightx=1.0;
		g.weighty=1.0;
		g.gridwidth=2;
		g.gridx=0;
		g.gridy=0;
		g.fill=GridBagConstraints.BOTH;
		addComponentGestionespanelSuperior();
		panelGestionesExtras.add(panelSuperiorgestionesExtras,g);
		g.gridwidth=1;
		g.gridy=1;
		g.gridx=0;
		addComponentGestionespanelinferiorI();
		panelGestionesExtras.add(panelInferiorIgestionesExtras,g);
		g.gridx=1;
		addComponentGestionespanelinferiorD();
		panelGestionesExtras.add(panelInferiorDgestionesExtras,g);
		
	}
	public void addComponentGestionespanelSuperior() {
		GridBagConstraints g = new GridBagConstraints();
		g.weightx=1.0;

		g.gridx=0;
		g.gridy=0;
		g.fill=GridBagConstraints.BOTH;
		g.insets=new Insets(10, 10, 10, 10);
		panelSuperiorgestionesExtras.add(lbFechaVenta,g);
		g.gridx=1;
		panelSuperiorgestionesExtras.add(dcFechaVentas,g);
		g.gridx=2;
		panelSuperiorgestionesExtras.add(btnBuscarProducto,g);
		g.gridx=3;
		panelSuperiorgestionesExtras.add(lbTotalVentas,g);
		g.gridx=4;
		panelSuperiorgestionesExtras.add(txtTotalventas,g);
		g.gridwidth=5;
		g.gridx=0;
		g.gridy=1;
		g.weighty=1.0;
		panelSuperiorgestionesExtras.add(scVentasPorfecha,g);
		
		
		
		
	}
	public void addComponentGestionespanelinferiorI() {
		GridBagConstraints g = new GridBagConstraints();
		g.insets=new Insets(10, 10, 10, 10);
		g.gridx=1;
		g.gridy=0;
		g.weightx=1.0;

		panelInferiorIgestionesExtras.add(btnObtenerLiquidacion,g);
		g.fill=GridBagConstraints.BOTH;
		g.weightx=0.0;
		g.gridy=1;
		g.gridwidth=2;
		g.weighty=1.0;
		panelInferiorIgestionesExtras.add(spLiquidaciones,g);
		
		
		
	}
	public void addComponentGestionespanelinferiorD() {
		GridBagConstraints g = new GridBagConstraints();
		g.insets=new Insets(10, 10, 10, 10);
		g.gridx=0;
		g.gridy=0;
		g.weightx=1.0;
		panelInferiorDgestionesExtras.add(btnObtenerPreferencia,g);
		g.gridx=1;
		g.fill= GridBagConstraints.HORIZONTAL;
		panelInferiorDgestionesExtras.add(txtProductoPreferente,g);
		g.fill=GridBagConstraints.BOTH;
		g.weightx=0.0;
		g.gridy=1;
		g.gridx=0;
		g.gridwidth=2;
		g.weighty=1.0;
		panelInferiorDgestionesExtras.add(spPreferencias,g);
	}
	
	
	
}
