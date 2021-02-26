package POOPracticaFinal;

public class Objeto {
	private String nombreObjeto;
	private Localizacion localizacion = null;
	private String personaje = null;
	private boolean posesion;
	
	//Un objeto puede estar en una localizacion o pertenecer a un personaje
	public Objeto(String objeto, Localizacion localizacion) 
	{
		this.nombreObjeto = objeto;
		this.localizacion = localizacion;
		this.posesion = false;
	}
	public Objeto(String objeto, String personaje) 
	{
		this.nombreObjeto = objeto;
		this.personaje = personaje;
		this.posesion = true;
	}
	
	//Getters y setters
	public String getNombreObjeto() 
	{
		return this.nombreObjeto;
	}
	public void setNombreObjeto(String nombreObjeto) 
	{
		this.nombreObjeto = nombreObjeto;
	}
	public Localizacion getLocalizacion() 
	{
		return this.localizacion;
	}
	public void setLocalizacion(Localizacion localizacion)
	{
		this.localizacion = localizacion;
	}
	public boolean getPoss()
	{
		return this.posesion;
	}
	public void setPoss(boolean pos) 
	{
		this.posesion = pos;
	}
	public String getPersonaje() 
	{
		return this.personaje;
	}
	public void setPersonaje(String personaje)
	{
		this.personaje = personaje;
	}
	
	//To string adaptandolo a si esta poseido o no
	public String toString()
	{
		if(this.posesion == true)
		{
			return ("El objeto "+ this.nombreObjeto +" lo tiene " + this.personaje);
		}
		else 
		{
			if(this.localizacion != null)
			{
				return("En la localizacion " + localizacion.getLocalizacion() + " esta el objeto: " + nombreObjeto);
			}
			else 
			{
				return("Objeto: "+ this.nombreObjeto);
			}
		}
	}
}