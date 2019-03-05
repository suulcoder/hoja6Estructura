import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;


public class Main extends JFrame implements ActionListener{

	//Todos los botones y entradas de texto para la interfaz gráfia
	private String[] maps = {"HashMap","TreeMap","LinkedHashMap"};
    private int map_index = 0;
    private String map_string = maps[map_index];
    private JButton setMap = new JButton("Cambiar Map");
    private JButton agregar = new JButton("Agregar");
	private JButton empezar = new JButton("Comenzar");;//boton que permitira que se realice la accion
	private JButton ordenar = new JButton("Ordenar");
	private JTextArea  map_state = new JTextArea();//nos dice el map preferido
	private JTextArea globalList = new JTextArea();
	private JTextArea localList = new JTextArea();
	private JTextArea tipo = new JTextArea();
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private JComboBox cartas;
	private Panel panelEntrada, panelCentro, panelSalida;
	private JPanel panelDeLaVentana;
	//Coleccion con los metodos para realizar acciones
	private Coleccion coleccion;

	public static void main(String[] args) {
		/*Imprimimos la ventana en la pantalla*/
    	
    	Main miAplicacion = new Main();
     	miAplicacion.setBounds(10,10,200,200);
    	miAplicacion.pack();
		miAplicacion.setVisible(true);
	}

	public Main(){
		super("Hoja 6");/*Sera el nombre de la ventana*/
		String[] allCarts = new String[8861];
		String[] types = new String[8861]; 
		String cadena;
		FileReader f = new FileReader("cards_desc.txt");
		BufferedReader b = new BufferedReader(f);
		int index = 0;
		while((cadena = b.readLine())!=null) {
			int pos=cadena.indexOf('|');
			String tipo = cadena.substring(pos);
			String nombre = cadena.substring(0,pos);
			Carta carta = new Carta(nombre, tipo);
			String id = coleccion.generateId(carta);
			coleccion.add(id,carta,true);
			allCarts[index] = nombre;
			types[index] = tipo;
		}
		b.close();
		cartas= new JComboBox(allCarts);
		cartas.setSelectedIndex(8861);
		cartas.addActionListener(this);


		empezar.setActionCommand("empezar");
		agregar.setActionCommand("Agregar");
		ordenar.setActionCommand("ordenar");
		setMap.setActionCommand("MAP");


		//Ingresadr datos
		globalList.setPreferredSize(new Dimension(1000,250));//dimensiones
		localList.setPreferredSize(new Dimension(1000,250));//dimensiones
		empezar.addActionListener(this);
		setMap.addActionListener(this);
		ordenar.addActionListener(this);
		agregar.addActionListener(this);
		cartas.addActionListener(this);


		scroll1 = new JScrollPane(globalList);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2 = new JScrollPane(localList);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panelDeLaVentana = (JPanel)this.getContentPane();
		panelEntrada = new Panel();//los siguientes paneles son para poner orden y estetica
		panelCentro = new Panel();
		panelSalida = new Panel();

		setMap.setText(map_string);

		panelEntrada.add(map_index);
		panelEntrada.add(setMap);
		panelEntrada.add(empezar);

		panelCentro.add(cartas);
		panelCentro.add(tipo);

		panelSalida.add(scroll1,BoxLayout.X_AXIS);
		panelCentro.add(agregar);
		panelSalida.add(scroll2,BoxLayout.X_AXIS);


		panelDeLaVentana.add(panelEntrada,BorderLayout.NORTH);//agreamos las ventanas a la interfaz grafica
		panelDeLaVentana.add(panelCentro,BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e){
		JComboBox cb = (JComboBox)e.getSource();
        String actual = (String)cb.getSelectedItem();
        updateLabel(actual);
		if("MAP".equals((e.getActionCommand()))){
			if (map_index==3){
                map_index=0;
            }
            map_string = maps[map_index];
			map_state.setText(map_string);
		}
		else if("empezar".equals((e.getActionCommand()))){
			globalList.setText(coleccion.mostrar(true));
			localList.setText(coleccion.mostrar(false));
			coleccion = new Coleccion(map_string);
		}
		else if("Agregar".equals((e.getActionCommand()))){
			cb = (JComboBox)e.getSource();
        	actual = (String)cb.getSelectedItem();
        	int position = cartas.indexOf(actual);
        	Carta carta1 = new Carta(actual,types[position]);
        	String id1 = coleccion.generateId();
        	coleccion.add(id1,carta1,false);
		}
		else if("ordenar".equals((e.getActionCommand()))){
			globalList.setText(coleccion.mostrar(true,true));
			globalList.setText(coleccion.mostrar(false,true));
		}
	}
}