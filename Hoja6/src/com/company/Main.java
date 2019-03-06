package com.company;

import java.io.File;
import java.util.Map;
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
	private JButton ordenar = new JButton("Mostrar ordenado");
	private JButton desordenar = new JButton("Mostar desordenado");
	private JTextArea state = new JTextArea();
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
	private Coleccion coleccion = new Coleccion(map_string);//Creamos una coleccion inicial para evita el NullPointerException
	private ArrayList<String> allCarts	 = new ArrayList<String>();//Nos sera util para el selection box
	private ArrayList<String> types = new ArrayList<String>();

	public static void main(String[] args) {
		/*Imprimimos la ventana en la pantalla*/
    	
    	Main miAplicacion = new Main();
     	miAplicacion.setBounds(10,10,200,200);
    	miAplicacion.pack();
		miAplicacion.setVisible(true);
	}

	public Main(){
		super("Hoja 6");/*Sera el nombre de la ventana*/

		//Necesitamos realizar este proceso ya que es necesario para la interfaz grafica
		String cadena;
		ArrayList<String> operation = new ArrayList<String>();
	    int operator = 0;
	    int operand = 0;
	    try {//si no es un operando
            Stream<String> lines = Files.lines(
                    Paths.get("cards_desc.txt"),//Leemos el archivo
                    StandardCharsets.UTF_8
            );
            lines.forEach(s ->{
                    operation.add(s);
            });
        }catch (IOException exception){
            System.out.println("Error");
        }
        for(int a=0; a<operation.size();a++){
			int index =operation.get(a).indexOf('|')+1;//Creamos dos substrings
			String tipo = operation.get(a).substring(index);
			String nombre = operation.get(a).substring(0,index-1);
			Carta carta = new Carta(nombre, tipo);
			String id = coleccion.generateId(carta);
			coleccion.add(id,carta,true);
			allCarts.add(nombre);
			types.add(tipo);
		}
		
		Object[] selectionList = allCarts.toArray();//Nuestro selection Box o JcomboBox, requiere un array de Objects
		cartas = new JComboBox(selectionList);
		cartas.addActionListener(this);

		empezar.setActionCommand("empezar");
		desordenar.setActionCommand("desordenar");
		agregar.setActionCommand("Agregar");
		ordenar.setActionCommand("ordenar");
		setMap.setActionCommand("MAP");
		cartas.setActionCommand("selection");

		//Ingresadr datos
		globalList.setPreferredSize(new Dimension(400,250));//dimensiones
		localList.setPreferredSize(new Dimension(400,250));//dimensiones
		empezar.addActionListener(this);	
		setMap.addActionListener(this);
		ordenar.addActionListener(this);
		desordenar.addActionListener(this);
		agregar.addActionListener(this);
		cartas.addActionListener(this);
		state.setText("OFF");

		scroll1 = new JScrollPane(globalList);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2 = new JScrollPane(localList);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panelDeLaVentana = (JPanel)this.getContentPane();
		panelEntrada = new Panel();//los siguientes paneles son para poner orden y estetica
		panelCentro = new Panel();
		panelSalida = new Panel();

		map_state.setText(map_string);

		panelEntrada.add(setMap);
		panelEntrada.add(map_state);
		panelEntrada.add(empezar);
		panelEntrada.add(state);

		panelCentro.add(desordenar);
		panelCentro.add(ordenar);
		panelCentro.add(cartas);
		panelCentro.add(tipo);

		panelSalida.add(scroll1);
		panelCentro.add(agregar);
		panelSalida.add(scroll2);

		panelDeLaVentana.add(panelEntrada,BorderLayout.NORTH);//agreamos las ventanas a la interfaz grafica
		panelDeLaVentana.add(panelCentro,BorderLayout.CENTER);
		panelDeLaVentana.add(panelSalida,BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e){
		if("empezar".equals((e.getActionCommand()))){
			if (state.getText().equals("OFF")){
				state.setText("ON");
				Map<String, Carta> new_local = coleccion.getLocal();
				coleccion = new Coleccion(map_string);
				coleccion.setLocal(new_local);
				String cadena;
				ArrayList<String> operation = new ArrayList<String>();
			    int operator = 0;
			    int operand = 0;
			    try {//si no es un operando
		            Stream<String> lines = Files.lines(
		                    Paths.get("cards_desc.txt"),//Leemos el archivo
		                    StandardCharsets.UTF_8
		            );
		            lines.forEach(s ->{
		                    operation.add(s);
		            });
		        }catch (IOException exception){
		            System.out.println("Error");
		        }
		        for(int a=0; a<operation.size();a++){
					int index =operation.get(a).indexOf('|')+1;//Creamos dos substrings
					String tipo = operation.get(a).substring(index);
					String nombre = operation.get(a).substring(0,index-1);
					Carta carta = new Carta(nombre, tipo);
					String id = coleccion.generateId(carta);
					coleccion.add(id,carta,true);
				}

			}
			else{
				state.setText("OFF");
			}
		}
		if(state.getText().equals("ON")){
			String actual = (String)cartas.getSelectedItem();
			if("MAP".equals((e.getActionCommand()))){//Si desea cambiar el map
				map_index++;
				if (map_index==3){
	                map_index=0;
	            }
	            map_string = maps[map_index];
				map_state.setText(map_string);
			}
			else if("desordenar".equals((e.getActionCommand()))){
				globalList.setText(coleccion.mostrar(true));
				localList.setText(coleccion.mostrar(false));
			}
			else if("Agregar".equals((e.getActionCommand()))){
				int position = allCarts.indexOf(actual);
	        	Carta carta1 = new Carta(actual,types.get(position));
	        	String id1 = coleccion.generateId(carta1);
	        	coleccion.add(id1,carta1,false);
			}
			else if("ordenar".equals((e.getActionCommand()))){
				globalList.setText(coleccion.mostrar(true,true));
				localList.setText(coleccion.mostrar(false,true));
			}
			else if("selection".equals((e.getActionCommand()))){
				int pos = allCarts.indexOf(actual);
				tipo.setText(types.get(pos));
			}
		}
	}
}