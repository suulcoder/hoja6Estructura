public class Carta{
	
	private String nombre;
	private String tipo;

	public Carta(String nombre, String tipo){
		this.nombre=nombre;
		this.tipo=tipo;
	}

	public String getTipo(){
		return this.tipo;
	}

	public String getNombre(){
		return this.nombre;
	}
}