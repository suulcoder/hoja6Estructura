class Carta{
	
	private nombre;
	private tipo;

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