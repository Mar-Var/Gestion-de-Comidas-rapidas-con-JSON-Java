package co.edu.uptc.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

import co.edu.uptc.logica.control.Registro;
import co.edu.uptc.logica.modelo.DateUses;
import co.edu.uptc.logica.modelo.Domiciliario;
import co.edu.uptc.logica.modelo.Pedido;
import co.edu.uptc.logica.modelo.Preferencias;
import co.edu.uptc.logica.modelo.Producto;
import co.edu.uptc.logica.modelo.ProductosSolicitados;
import co.edu.uptc.persistencia.DomiciliariosPersistence;
import co.edu.uptc.persistencia.PedidoPersistence;
import co.edu.uptc.persistencia.ProductoPersistence;
/**
 * Clase que permite gestionar lo eventos de la interfaz grafica
 * @author Marcos Esteban Vargas Avella
 *
 */

public class HandlingEvents implements ActionListener,MouseListener {
	
	static final String ADD_DOMICILIARIO="Agregar domiciliario ";
	static final String DELETE_DOMICILIARIO="Eliminar domiciliario";
	static final String UPDATE_DOMICILIARIO="Actualizar domiciliario";
	
	static final String ADD_PEDIDO="Agregar al carrito";
	
	static final String ADD_PRODUCTO="Agregar producto";
	static final String DELETE_PRODUCTO="Eliminar producto";
	static final String UPDATE_PRODUCTO="Actualizar producto";
	
	static final String GET_LIQUIDACION="Obtener Liquidacion";
	static final String GET_PREFERENCIAS="Obtener Preferencias";
	static final String GET_POR_FECHAS="Buscar";
	
	static final String SEND_PEDIDO="Terminar";
	static final String CLEAR_FIELDS="Limpiar";
	
	private MainWindow mainWindow;
	private Registro r = new Registro();
	ArrayList<ProductosSolicitados> ps=new ArrayList<ProductosSolicitados>();;
	
	/**
	 * Constructor de la clase
	 * @param mainWindow parametro de dato tipo {@link MainWindow}. Permite usar los elementos de la clase en esta.
	 */
	public HandlingEvents(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		switch (e.getActionCommand()) {
		
		case ADD_DOMICILIARIO:
			

			
			if(mainWindow.getTxtnombreDomiciliario().getText().equals("")||
					mainWindow.getTxtApellidosDomiciliario().getText().equals("")||
					mainWindow.getTxtCedula().getText().equals("")||
					mainWindow.getTxtDireccion().getText().equals("")||
					mainWindow.getTxtTelefono().getText().equals("")||
					mainWindow.getDcFechaNacimiento().getDate()==null) {
				JOptionPane.showMessageDialog(null, "Hay campos vacios");
				break;
				
			}
			
			if(r.encontrarDomiciliario(Long.parseLong(mainWindow.getTxtCedula().getText()))!=null){
				JOptionPane.showMessageDialog(null, "El usuario ya existe");
				break;
			}
			
			Date fecha=mainWindow.getDcFechaNacimiento().getDate();
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			String[] fechaSeparadas=formateador.format(fecha).split("/");
			DateUses date = new DateUses(Integer.parseInt(fechaSeparadas[2]),Integer.parseInt(fechaSeparadas[1]), Integer.parseInt(fechaSeparadas[0]));
			r.agregarDomiciliario(mainWindow.getTxtnombreDomiciliario().getText()
					, mainWindow.getTxtApellidosDomiciliario().getText()
					, Integer.parseInt(mainWindow.getTxtCedula().getText()),
					date,
					mainWindow.getTxtTelefono().getText(),
					mainWindow.getTxtDireccion().getText());
			llenarTablaDomiciliario(mainWindow.getDftblDomiciliario(), r.mostrarTodoslosDomiciliarios());
			actualizarComboBoxdomiciliarios(mainWindow.getCbDomiciliario(), r.mostrarTodoslosDomiciliarios());
			
			break;
		case DELETE_DOMICILIARIO:
			if(	mainWindow.getTxtCedula().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Ingrese un valor en la cedula");
				break;
				
			}
			
			r.eliminarDomiciliario(Integer.parseInt(mainWindow.getTxtCedula().getText()));
			llenarTablaDomiciliario(mainWindow.getDftblDomiciliario(), r.mostrarTodoslosDomiciliarios());
			actualizarComboBoxdomiciliarios(mainWindow.getCbDomiciliario(), r.mostrarTodoslosDomiciliarios());
			break;
		case UPDATE_DOMICILIARIO:
			if(mainWindow.getTxtnombreDomiciliario().getText().equals("")||
					mainWindow.getTxtApellidosDomiciliario().getText().equals("")||
					mainWindow.getTxtCedula().getText().equals("")||
					mainWindow.getTxtDireccion().getText().equals("")||
					mainWindow.getTxtTelefono().getText().equals("")||
					mainWindow.getDcFechaNacimiento().getDate()==null) {
				JOptionPane.showMessageDialog(null, "Hay campos vacios");
				break;
				
			}
			
			if(r.encontrarDomiciliario(Long.parseLong(mainWindow.getTxtCedula().getText()))== null){
				JOptionPane.showMessageDialog(null, "");
			}
			
			try {
				
				Date fechanacimiento=mainWindow.getDcFechaNacimiento().getDate();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String[] fechaPartidas=format.format(fechanacimiento).split("/");
				DateUses dateupdate = new DateUses(Integer.parseInt(fechaPartidas[2]),Integer.parseInt(fechaPartidas[1]), Integer.parseInt(fechaPartidas[0]));
				
				r.actualizarDomiciliario(mainWindow.getTxtnombreDomiciliario().getText()
						, mainWindow.getTxtApellidosDomiciliario().getText()
						, Integer.parseInt(mainWindow.getTxtCedula().getText()),
						dateupdate,
						mainWindow.getTxtTelefono().getText(),
						mainWindow.getTxtDireccion().getText());
				llenarTablaDomiciliario(mainWindow.getDftblDomiciliario(), r.mostrarTodoslosDomiciliarios());
				actualizarComboBoxdomiciliarios(mainWindow.getCbDomiciliario(), r.mostrarTodoslosDomiciliarios());
				
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case ADD_PEDIDO:
			if(mainWindow.getTxtCantidadProductos().getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida de producto");
				break;
			}
			if(mainWindow.getDfltProductospedido().getSize()==0) {
				ps.clear();
			}
			ProductosSolicitados productos = new ProductosSolicitados(mainWindow.getCbProductos().getSelectedItem().toString()
					, Integer.parseInt(mainWindow.getTxtCantidadProductos().getText()));

			this.ps.add(productos);

			llenarListaRroductosPedidos(mainWindow.getDfltProductospedido(), ps);

			break;
		case ADD_PRODUCTO:
			if(r.encontrarProducto(mainWindow.getTxtNombreProducto().getText())!=null) {
				JOptionPane.showMessageDialog(null, "Este producto ya esta registrado.");
				break;
			}
			
			if(mainWindow.getTxtNombreProducto().getText().equals("")||
			mainWindow.getTxtPrecioProducto().getText().equals("")
			) {
				JOptionPane.showMessageDialog(null, "Hay campos vacios.");
				break;
				
			}
			
			r.agregarProducto(mainWindow.getTxtNombreProducto().getText(),Double.parseDouble( mainWindow.getTxtPrecioProducto().getText()));
			llenarTablasProductos(mainWindow.getDftblProductos(), r.mostrarTodoslosProductos());
			actualizarComboBoxProductos(mainWindow.getCbProductos(), r.mostrarTodoslosProductos());
			
			mainWindow.getTxtNombreProducto().setText("");
			mainWindow.getTxtPrecioProducto().setText("");
			break;
		case DELETE_PRODUCTO:
			if(mainWindow.getTxtNombreProducto().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Ingrese un nombre de producto.");
				break;
				
			}
			
			r.eliminarProducto(mainWindow.getTxtNombreProducto().getText());
			llenarTablasProductos(mainWindow.getDftblProductos(), r.mostrarTodoslosProductos());
			actualizarComboBoxProductos(mainWindow.getCbProductos(), r.mostrarTodoslosProductos());
			mainWindow.getTxtNombreProducto().setText("");
			mainWindow.getTxtPrecioProducto().setText("");
			
			break;
		case UPDATE_PRODUCTO:
			if(mainWindow.getTxtNombreProducto().getText().equals("")||
			mainWindow.getTxtPrecioProducto().getText().equals("")
			) {
				JOptionPane.showMessageDialog(null, "Hay campos vacios.");
				break;
				
			}
			try {
				r.actualizarProducto(mainWindow.getTxtNombreProducto().getText()
						,Double.parseDouble( mainWindow.getTxtPrecioProducto().getText()));
				
				llenarTablasProductos(mainWindow.getDftblProductos(), r.mostrarTodoslosProductos());
				actualizarComboBoxProductos(mainWindow.getCbProductos(), r.mostrarTodoslosProductos());
				mainWindow.getTxtNombreProducto().setText("");
				mainWindow.getTxtPrecioProducto().setText("");
			} catch (NumberFormatException | IOException e1) {
				System.out.println("Porblemas");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case SEND_PEDIDO:
			
			if(mainWindow.getTxtNombreCliente().getText().equals("")||
			mainWindow.getTxtApellidoCliente().getText().equals("")||
			mainWindow.getTxtTelefonoCliente().getText().equals("")||
			mainWindow.getTxtDireccioncliente().getText().equals("")
			) {
				JOptionPane.showMessageDialog(null, "Hay campos vacios.");
				break;
				
			}
			
			
			ArrayList<ProductosSolicitados> pro= new ArrayList<ProductosSolicitados>();
			
			for (int i = 0; i < mainWindow.getDfltProductospedido().getSize(); i++) {
				String[] datos = String.valueOf(mainWindow.getDfltProductospedido().getElementAt(i)).split(":");
				ProductosSolicitados p= new ProductosSolicitados(datos[0],Integer.parseInt(datos[1]));
				pro.add(p);
			}
			if(pro.size()==0 || pro==null) {
				JOptionPane.showMessageDialog(null, "Debe ingresar por lo menos un producto");
				break;
				
			}
			
			if(	!r.agregarPedido(mainWindow.getTxtNombreCliente().getText()
					, mainWindow.getTxtApellidoCliente().getText()
					, mainWindow.getTxtTelefonoCliente().getText()
					, mainWindow.getTxtDireccioncliente().getText()
					, mainWindow.getCbDomiciliario().getSelectedItem().toString()
					, pro)) {
			JOptionPane.showInputDialog(null,"No se pudo crear el pedido");
		}
			mainWindow.getTxtNombreCliente().setText("");
			mainWindow.getTxtApellidoCliente().setText("");
			mainWindow.getTxtTelefonoCliente().setText("");
			mainWindow.getTxtDireccioncliente().setText("");
			mainWindow.getTxtCantidadProductos().setText("");
			mainWindow.getDfltProductospedido().removeAllElements();
			break;
		case CLEAR_FIELDS:
			mainWindow.getTxtNombreCliente().setText("");
			mainWindow.getTxtApellidoCliente().setText("");
			mainWindow.getTxtTelefonoCliente().setText("");
			mainWindow.getTxtDireccioncliente().setText("");
			mainWindow.getTxtCantidadProductos().setText("");
			
			break;
		case GET_LIQUIDACION:
			Date fechaliquidacion=mainWindow.getDcFechaVentas().getDate();
			SimpleDateFormat formatL = new SimpleDateFormat("dd/MM/yyyy");
			String[] fechaPartidasL=formatL.format(fechaliquidacion).split("/");
			DateUses datedateL = new DateUses(Integer.parseInt(fechaPartidasL[2]),Integer.parseInt(fechaPartidasL[1]), Integer.parseInt(fechaPartidasL[0]));
			llenarTablaLiquidaciones(mainWindow.getDftblLiquidaciones(), r.obtenerLiquidaciones(datedateL));
			break;
		case GET_PREFERENCIAS:
			mainWindow.getTxtProductoPreferente().setText(r.mostrarPreferenciaproducto());
			llenarTablaPreferencias(mainWindow.getDtblPreferencias(), r.mostrarPreferenciasListado());
			
			break;
		case GET_POR_FECHAS:
			if(mainWindow.getDcFechaVentas().getDate()==null ) {
				JOptionPane.showMessageDialog(null, "Debe ingresar una fecha");
				break;	
			}
			Date fechanacimiento=mainWindow.getDcFechaVentas().getDate();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String[] fechaPartidas=format.format(fechanacimiento).split("/");
			DateUses datedate = new DateUses(Integer.parseInt(fechaPartidas[2]),Integer.parseInt(fechaPartidas[1]), Integer.parseInt(fechaPartidas[0]));
			llenarTablaVentasporFecha(mainWindow.getDftblVentasPorFecha(), datedate);
			mainWindow.getTxtTotalventas()
			.setText(String.valueOf(r.obtenerGananciaPorFecha(new PedidoPersistence().TraerTodoslosPedidios())));
			break;
			

		default:
			break;
		}
		
	}
	
	/**
	 * Metodo que permite actualizar el {@link JComboBox} de los Domiciliarios
	 * @param cbx parametro con un dato de tipo {@link JComboBox} que representa el cual se va a actualizar/modificar
	 * @param arr Parametro de tipo {@link ArrayList} de tipo {@link Domiciliario} que contiene todos los Domiciliarios del establecimiento.
	 */
	public void actualizarComboBoxdomiciliarios(JComboBox<Integer> cbx, ArrayList<Domiciliario> arr) {
		cbx.removeAllItems();
		for (Domiciliario domiciliario : arr) {
			cbx.addItem((int) domiciliario.getIdentification());
			
		}
		
	}
	/**
	 * Metodo que permite actualizar el {@link JComboBox} de los productos.
	 * @param cbx parametro de tipo {@link JComboBox} de tipo String que se quiere actualizar
	 * @param arr parametro de tipo {@link ArrayList} de tipo {@link Producto} que contiene todos los productos disponibles
	 */
	public void actualizarComboBoxProductos(JComboBox<String> cbx, ArrayList<Producto> arr) {
		cbx.removeAllItems();
		for (Producto producto : arr) {
			cbx.addItem(producto.getName());
		}
	}
	/**
	 * metodo que permite añadir datos al {@link JComboBox} de Productos
	 * @param cbproductos parametro tipo {@link JComboBox} a añadir items
	 */
	public void addCompenentsComboBoxProductos(JComboBox cbproductos) {
		ProductoPersistence pp= new ProductoPersistence();
		ArrayList<Producto> p = pp.TraerTodosloProductos();
		for (Producto producto : p) {
			cbproductos.addItem(producto.getName());
		}
	}
	/**
	 * metodo que permite añadir datos al {@link JComboBox} de Domiciliarios
	 * @param cbDomiciliarios parametro tipo {@link JComboBox} a añadir items a Domiciliario
	 */
	public void addCompenentsComboBoxDomiciliarios(JComboBox cbDomiciliarios) {
		DomiciliariosPersistence dp = new DomiciliariosPersistence();
		ArrayList<Domiciliario> d = dp.TraerTodoslosdomiciliarios();
		for (Domiciliario domiciliario : d) {
			cbDomiciliarios.addItem(domiciliario.getIdentification());
		}
	}
	/**
	 * Metodo que permite llenar la tabla de productos con informacion suminsitrada por un {@link ArrayList} de tipo {@link Producto}
	 * @param dftbl Parametro tipo {@link DefaultTableModel} el cual va a ser modificado
	 * @param datos Parametro tipo {@link ArrayList} de tipo {@link Producto} que suministrara la informacion a la tabla
	 */
	public void llenarTablasProductos(DefaultTableModel dftbl,ArrayList<Producto> datos) {
		dftbl.setRowCount(0);
		for (Producto producto : datos) {
			dftbl.addRow(new Object[] {producto.getName(),producto.getPrice()});
			
		}
	}
	/**
	 * Metodo que permite llenar la tabla de Domiciliario con informacion suminsitrada por un {@link ArrayList} de tipo {@link Domiciliario}
	 * @param dftbl Parametro tipo {@link DefaultTableModel} el cual va a ser modificado
	 * @param datos Parametro tipo {@link ArrayList} de tipo {@link Domiciliario} que suministrara la informacion a la tabla
	 */
	public void llenarTablaDomiciliario(DefaultTableModel dftbl,ArrayList<Domiciliario> datos) {
		dftbl.setRowCount(0);
		for (Domiciliario domiciliario : datos) {
			dftbl.addRow(new Object[] {domiciliario.getName()
					,domiciliario.getLastName()
					,domiciliario.getIdentification()
					,domiciliario.getBirthday().toString()
					,domiciliario.getCel()
					,domiciliario.getAdress()});
		}
	}
	/**
	 * metodo para llenar la tabla de liquidaciones con informacion traida desde un {@link ArrayList}
	 * @param dftbl Modelo de tabla a la cual se le quieren asignar los valores
	 * @param datos Es el arreglo de datos que va a ser introducido a la tabla
	 */
	public void llenarTablaLiquidaciones(DefaultTableModel dftbl,ArrayList<String[]> datos) {
		dftbl.setRowCount(0);
		if(datos!=null) {
			for (String[] strings : datos) {
				dftbl.addRow(new Object[] {strings[0],strings[2]});
			}
		}

	}
	/**
	 * Metodo que permite llenar la tabla de Domiciliario con informacion suminsitrada por un {@link ArrayList} de tipo {@link Preferencias}
	 * @param dftbl Parametro tipo {@link DefaultTableModel} el cual va a ser modificado
	 * @param datos Parametro tipo {@link ArrayList} de tipo {@link Preferencias} que suministrara la informacion a la tabla
	 */
	
	public void llenarTablaPreferencias(DefaultTableModel dftbl,ArrayList<Preferencias> datos) {
		dftbl.setRowCount(0);
		for (Preferencias preferencias : datos) {
			dftbl.addRow(new Object[] {preferencias.getNombre(),preferencias.getCantidad()});
		}
	}
	/**
	 * Metodo que permite llenar la Lista de productos el el pedido con informacion suminsitrada por un {@link ArrayList} de tipo {@link ProductosSolicitados}
	 * @param dflst Parametro tipo {@link DefaultTableModel} el cual va a ser modificado
	 * @param productosSolicitados Parametro tipo {@link ArrayList} de tipo {@link ProductosSolicitados} que suministrara la informacion a la tabla
	 */
	
	public void llenarListaRroductosPedidos(DefaultListModel<String> dflst,ArrayList<ProductosSolicitados> productosSolicitados) {
			dflst.removeAllElements();
			for (ProductosSolicitados productosSolicitados2 : productosSolicitados) {
				dflst.addElement(productosSolicitados2.getName()+":"+productosSolicitados2.getAmount());
			}
			
		
	}
	
	/**
	 * Metodo que permite llenar la tabla de Pedidos con informacion segun la fecha
	 * @param dtbl Parametro tipo {@link DefaultTableModel} el cual va a ser modificado
	 * @param du Parametro tipo {@link DateUses} de tipo {@link Preferencias} que suministrara la informacion a la tabla
	 */
	public void llenarTablaVentasporFecha(DefaultTableModel dtbl,DateUses du) {
		dtbl.setRowCount(0);
		for (Pedido pedido : r.listarFechasGanancias(du)) {
			dtbl.addRow(new Object[] {pedido.getClientName()
					,pedido.getClientLastName()
					,pedido.getClientCell()
					,pedido.getDirection()
					,pedido.getDomiciliarioid()
					,pedido.getOrderedProducts().toString()
					,pedido.getDate()
					,pedido.getCost()});
		}
		
	}


/**
 * Metodos de gestion de eventos...
 */
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		int seleccionarDomiciliario=mainWindow.getTblDomiciliario().rowAtPoint(e.getPoint());
		mainWindow.getTxtnombreDomiciliario()
		.setText(String.valueOf(mainWindow.getTblDomiciliario().getValueAt(seleccionarDomiciliario, 0)));
		mainWindow.getTxtApellidosDomiciliario()
		.setText(String.valueOf(mainWindow.getTblDomiciliario().getValueAt(seleccionarDomiciliario, 1)));
		mainWindow.getTxtCedula()
		.setText(String.valueOf(mainWindow.getTblDomiciliario().getValueAt(seleccionarDomiciliario, 2)));
		mainWindow.getDcFechaNacimiento()
		.setToolTipText(String.valueOf(mainWindow.getTblDomiciliario().getValueAt(seleccionarDomiciliario, 3)));
		mainWindow.getTxtDireccion()
		.setText(String.valueOf(mainWindow.getTblDomiciliario().getValueAt(seleccionarDomiciliario, 4)));
		mainWindow.getTxtTelefono()
		.setText(String.valueOf(mainWindow.getTblDomiciliario().getValueAt(seleccionarDomiciliario, 5)));
		int seleccionarProducto=mainWindow.getTblProductos().rowAtPoint(e.getPoint());
		mainWindow.getTxtPrecioProducto()
		.setText(String.valueOf(mainWindow.getTblProductos().getValueAt(seleccionarProducto,1)));
		mainWindow.getTxtNombreProducto()
		.setText(String.valueOf(mainWindow.getTblProductos().getValueAt(seleccionarProducto,0)));
		
		
	}



	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ProductosSolicitados> getPs() {
		return ps;
	}

	public void setPs(ArrayList<ProductosSolicitados> ps) {
		this.ps = ps;
	}
	
	

	
	
}
