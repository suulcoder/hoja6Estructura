import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

class Main extends JFrame implements ActionListener{{
	
	private String[] maps = {"HashMap","TreeMap","LinkedHashMap"};
    private int map_index = 0;
    private String map_string = maps[map_index];
    private JButton setMap = new JButton("Cambiar Map");
    private JButton agregar = new JButton("Agregar");
	private JButton empezar = new JButton("Comenzar");;//boton que permitira que se realice la accion
	

	public Main(){

	}

	public actionPerformed(ActionEvent e){
		Coleccion coleccion = new Coleccion(needed);
	}
	public static void main(String[] args) {
		
	}
}