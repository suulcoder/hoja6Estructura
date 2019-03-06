import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
class Coleccion{
	
	private MapFactory factory = new MapFactory();
	private Map <String, Carta> global;//Coleccion del sistema
	private Map <String, Carta> local;//Coleccion del usuario

	public Coleccion(String needed){
		this.global = factory.getMap(needed);
		this.local = factory.getMap(needed);

	}

	public void setLocal(Map<String, Carta> new_local){
		this.local = new_local;
	}

	public Map<String, Carta> getLocal(){
		return local;
	}

	public int Size(){
		return global.size();
	}

	public String generateId(Carta carta){
		int id = 0;
		//Solo existen tres tipos disponibles
		if (carta.getTipo().equals("Trampa")){
			id = 1;
		}
		else if(carta.getTipo().equals("Hechizo")){
			id = 2;
		}
		else if(carta.getTipo().equals("Monstruo")){
			id = 3;
		}
		return(id+carta.getNombre());//Devolvemos un id unico.
	}

	public void add(String id, Carta carta, boolean useglobal){//Agregar una coleccion
		//Si useglobal es verdadero, se utilizara la coleccion global.
		if(useglobal){
			global.put(id,carta);
		}
		else{
			local.put(id,carta);
		}
	}

	public String mostrar(boolean useglobal){//Devuelve todos los elementos del MAP
		//Si useglobal es verdadera, esta devuelve los elmentos de la coleccion glboal
		String retorno = "";
		if (useglobal){
			Iterator it = global.keySet().iterator();//Iteramos el hash
			while(it.hasNext()){
				Object key = it.next();
				retorno = retorno + ("\nTipo: " + global.get(key).getTipo() + " Nombre: " + global.get(key).getNombre());
			}
		}
		else{
			Iterator it = local.keySet().iterator();//Iteramos el hash
			while(it.hasNext()){
				Object key = it.next();
				retorno = retorno + ("\nTipo: " + local.get(key).getTipo() + " Nombre: " + local.get(key).getNombre());
			}
		}
		return retorno;
	}

	public String mostrar(boolean useglobal, boolean ordenar){
		String retorno = "";
		ArrayList<Carta> actualCollection = new ArrayList<Carta>();
		if (useglobal){
			Iterator it = global.keySet().iterator();//Iteramos el hash
			while(it.hasNext()){
				Object key = it.next();
				actualCollection.add(global.get(key));
			}
		}
		else{
			Iterator it = local.keySet().iterator();//Iteramos el hash
			while(it.hasNext()){
				Object key = it.next();
				actualCollection.add(local.get(key));
			}
		}
		for(int i=0;i<actualCollection.size();i++){
			if (actualCollection.get(i).getTipo().equals("Trampa")){
				retorno = retorno + ("\nTipo: " + actualCollection.get(i).getTipo() + " Nombre: " + actualCollection.get(i).getNombre());
			}
		}
		for(int j=0;j<actualCollection.size();j++){
			if (actualCollection.get(j).getTipo().equals("Hechizo")){
				retorno = retorno + ("\nTipo: " + actualCollection.get(j).getTipo() + " Nombre: " + actualCollection.get(j).getNombre());
			}
		}for(int k=0;k<actualCollection.size();k++){
			if (actualCollection.get(k).getTipo().equals("Monstruo")){
				retorno = retorno + ("\nTipo: " + actualCollection.get(k).getTipo() + " Nombre: " + actualCollection.get(k).getNombre());
			}
		}
		return retorno;
	}

	public String getSpecific(String nombre){
		String tipo = "";
		Iterator it = global.keySet().iterator();//Iteramos el hash
		while(it.hasNext()){//Iteramos en busca del nombre
			Object key = it.next();
			if(global.get(key).getNombre().equals(nombre)){//al cencontrarlo
				tipo = global.get(key).getTipo();//returnar tipo
			}
		}
		return tipo;
	}


}