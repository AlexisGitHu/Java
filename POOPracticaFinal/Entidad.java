package POOPracticaFinal;

public abstract class Entidad 
{
	private String nombre;
	private Objeto objeto;
	private Localizacion loc;
	//Una entidad tiene un nombre y una localizacion, pero también puede tener un objeto
	public Entidad(String nombre, Objeto objeto, Localizacion loc)
	{
		this.nombre=nombre;
		this.objeto = objeto;
		this.loc = loc;
	}
	
	public Entidad(String nombre, Localizacion loc)
	{
		this.nombre=nombre;
		this.loc=loc;
		objeto = null;
	}
	
	//Getters y setters
	final public String getNombre()
	{
		return nombre;
	}
	
	final public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public Objeto getObjeto()
	{
		return objeto;
	}
	
	public void setObjeto(Objeto objeto)
	{
		this.objeto = objeto;
	}
	
	final public Localizacion getLoc()
	{
		return loc;
	}
	
	final public void setLoc(Localizacion loc)
	{
		this.loc = loc;
	}
}