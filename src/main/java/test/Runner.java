package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import co.edu.uptc.presentacion.MainWindow;
import co.edu.uptc.logica.control.Registro;
import co.edu.uptc.logica.modelo.DateUses;
import co.edu.uptc.logica.modelo.Producto;
import co.edu.uptc.logica.modelo.ProductosSolicitados;
import co.edu.uptc.persistencia.ProductoPersistence;

public class Runner {

	public static void main(String[] args) throws Exception {
		
		MainWindow myWindow= new MainWindow();
		myWindow.begin();
		myWindow.setVisible(true);
	}

}
