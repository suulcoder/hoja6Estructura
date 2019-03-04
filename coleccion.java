import java.util.HashMap;
import java.util.Map;
class coleccion{
	
	private Map <String, Carta> global = new HashMap<String,Carta>();
	private Map <String, Carta> local = new HashMap<String,Carta>();

	public String generateid(Carta carta){
		int id = 0;
		if carta.getTipo()=="Trampa"{
			id = 1
		}
		else if carta.getTipo()=="Hechizo"{
			id = 2
		}
		else if carta.getTipo()=="Monstruo"{
			id = 3
		}
		return(id+carta.getNombre())
	}

	public void add(String id, Carta carta, boolean global){
		if global{
			global.put(id,carta)
		}
		else{
			local.put(id,carta)
		}
	}

	
}