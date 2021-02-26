package POOPracticaFinal;

import java.util.ArrayList;
//public class Jugador extends Personajes
public class Personajes extends Entidad
{
	public ArrayList<String> creencias; //Array dinámico de creencias, solo hace falta un array
	private ArrayList<String> registro;
	private boolean pedir;
	private Personajes quien_pide;
	private String locObjetivo;
	private String objetoObjetivo;
	private boolean terminado;
	
	//Un personaje estará definido siempre por su nombre y su localizacion, pero podrá tener objetos
	public Personajes(String nombre, Objeto objeto, Localizacion loc)
	{
		super(nombre, objeto, loc);
		creencias=new ArrayList<String>();
		pedir = false;
		registro = new ArrayList<String>();
	}
	public Personajes(String nombre, Localizacion loc)
	{
		super(nombre, loc);
		creencias=new ArrayList<String>();
		pedir = false;
		registro = new ArrayList<String>();
	}
	//Getters y setters
	public ArrayList<String> getCreencias()
	{
		return creencias;
	}
	
	public void setCreencias(String creencias)
	{
		this.creencias.add(creencias);
	}
	
	public ArrayList<String> getRegistro() 
	{
		return registro;
	}
	
	public void setRegistro(ArrayList<String> registro) 
	{
		this.registro = registro;
	}
	
	final public void setPedir(boolean vf)
	{
		pedir = vf;
	}
	
	final public boolean getPedir()
	{
		return pedir;
	}
	
	final public Personajes getQuienPide()
	{
		return quien_pide;
	}
	
	final public void setQuienPide(Personajes pj1)
	{
		quien_pide=pj1;
	}
	
	final public String getLocObjetivo()
	{
		return locObjetivo;
	}
	
	final public void setLocObjetivo(String objetivo)
	{
		locObjetivo = objetivo;
	}
	
	final public String getObjetoObjetivo()
	{
		return objetoObjetivo;
	}
	
	final public void setObjetoObjetivo(String objetivo)
	{
		objetoObjetivo=objetivo;
	}
	
	public boolean getTerminado() 
	{
		return terminado;
	}
	
	public void setTerminado(boolean terminado) 
	{
		this.terminado = terminado;
	}
	
	//Método que permite comprobar si la localización buscada es adyacente y permite ir a ella. Además actualizamos su registro y comprobamos si es una excepcion
	public void irLugar(Localizacion loc) throws ErrorActionException
	{
		int contadorAdyacenciasCoincidentes = getLoc().getLocalizacionesAdyacentes().size();
		
		for(Localizacion l:getLoc().getLocalizacionesAdyacentes())
		{
			if(l.getLocalizacion().equals(loc.getLocalizacion()))
			{
				setLoc(loc);
				actualizarRegistro(getNombre()+" se fue a "+loc.getLocalizacion());
			}
			else if(loc.equals(getLoc()))
			{
				actualizarRegistro(getNombre()+" se quedó en la misma localización");
			}
			else
			{
				contadorAdyacenciasCoincidentes--;
			}
		}
		
		if(contadorAdyacenciasCoincidentes == 0)
		{
			System.out.println("No es una localización que se encuentre adyacente");
			actualizarRegistro(getNombre()+" no pudo ir a "+loc.getLocalizacion()+" porque no es adyacente");
			throw new ErrorActionException("Localizacion inalcanzable");
		}
		
	}
	//Método que permite pedir objetos a los personajes y actualizar los registros
	public void pedirObjeto(Personajes personaje, Objeto objeto) throws ErrorActionException
	{

		if(personaje != null && objeto != null)
		{
			if(personaje.getLoc().equals(getLoc()))
			{
				if(personaje.getObjeto() != null && personaje.getObjeto().getNombreObjeto().equals(objeto.getNombreObjeto()))
				{
					pedir = true;
					quien_pide=personaje;
					actualizarRegistro(getNombre()+" pidio el objeto "+objeto.getNombreObjeto()+"a"+personaje.getNombre());
					System.out.println(this.getNombre()+" pidio el objeto "+objeto.getNombreObjeto()+" a "+personaje.getNombre());
				}
				else
				{
					System.out.println(personaje.getNombre()+" no tiene ese objeto");
					actualizarRegistro(getNombre()+" pidio el objeto " +objeto.getNombreObjeto()+" a "+personaje.getNombre()+" pero no lo tenía");
				}
			}
			else
			{
				System.out.println(personaje.getNombre()+" no está en la misma localización");
				actualizarRegistro(getNombre()+" pidio el objeto " +objeto.getNombreObjeto()+" a "+personaje.getNombre()+" pero no estaba en la misma localización");
			}
		}
		else
		{
			System.out.println("No se ha podido realizar esta accion");
			actualizarRegistro(getNombre()+"no pudo realizar la acción");
			throw new ErrorActionException("Personaje o objeto inadecuados");
		}
	}
	//Método que permite dar objetos pedidos a los personajes y actualizar los registros
	public void darObjetoPedido(Personajes pj1) throws ErrorActionException
	{
		if(pj1 != null && getObjeto() != null)
		{
			if(this.getNombre().equals(pj1.getNombre()) == false)
			{
				if(pj1.getPedir()==true) 
				{
					if(pj1.getQuienPide().equals(this))
					{
						pj1.setObjeto(getObjeto());
						actualizarRegistro(getNombre()+" dio el objeto "+getObjeto().getNombreObjeto()+" a "+pj1.getNombre());
						System.out.println(this.getNombre()+" dio el objeto "+getObjeto().getNombreObjeto()+" a "+pj1.getNombre());
						setObjeto(null);
					}
					else
					{
						System.out.println("Él no te ha pedido ningún objeto");
						actualizarRegistro(pj1.getNombre()+"no te ha pedido ningún objeto");
					}
				}
				else
				{
					System.out.println("Especifica el objeto o asegúrate de que te lo pidan");
					actualizarRegistro(getNombre()+"no especificó el objeto");
				}
			}
			else
			{
				System.out.println("No se lo puede pedir a sí mismo");
				actualizarRegistro(getNombre()+"no se puede pedir el objeto a sí mismo");
			}
		}
		else
		{
			System.out.println("Esta acción no se puede realizar");
			actualizarRegistro("Esta acción no se puede realizar");
			throw new ErrorActionException("Personaje u objeto inadecuados");
		}
	}
	//Método que permite coger objetos de la localizacion y actualizar los registros
	public void cogerObjeto(Objeto objeto1) throws ErrorActionException
	{
		if(objeto1 != null && getObjeto() == null)
		{
			if(getLoc().equals(objeto1.getLocalizacion()))
			{
				if(objeto1.getPoss()==false)
				{
					setObjeto(objeto1);
					getObjeto().setPoss(true);
					getObjeto().setPersonaje(getNombre());
					actualizarRegistro(getNombre()+" cogió el objeto "+getObjeto().getNombreObjeto()+" de "+getLoc().getLocalizacion());
					getObjeto().setLocalizacion(getLoc());
				}
				else
				{
					System.out.println("No puedes coger el objeto, es de alguien");
					actualizarRegistro(getNombre()+" no pudo coger el objeto ");
				}
			}
			else
			{
				System.out.println("No estás en la localización del objeto");
				actualizarRegistro(getNombre()+" no pudo coger el objeto porque no estaba en la localización correcta");
			}
		}
		else
		{
			System.out.println("No se ha podido realizar la acción");
			actualizarRegistro(getNombre()+" no pudo realizar la acción");
			throw new ErrorActionException("Objeto inadecuado");
		}
	}
	//Método que permite dejar objetos en una localizacion y actualizar los registros
	public void dejarObjeto() throws ErrorActionException
	{
		if(getObjeto()!=null)
		{
			getObjeto().setLocalizacion(getLoc());
			getObjeto().setPersonaje(null);
			getObjeto().setPoss(false);
			actualizarRegistro(getNombre()+" dejó el objeto "+getObjeto().getNombreObjeto()+" en "+getLoc().getLocalizacion());
			setObjeto(null);
		}
		else
		{
			System.out.println("No tienes ese objeto");
			actualizarRegistro(getNombre()+" no tiene ese objeto");
			throw new ErrorActionException("No existe el objeto a dejar");
		}
	}
	//Método para no hacer nada y actualizar los registros
	public void nada()
	{
		System.out.println("No hace nada");
		actualizarRegistro(getNombre()+"no hizo nada");
	}
	
	public void actualizarRegistro(String texto)
	{
		registro.add(texto);
	}
	//To string adaptado a si el personaje tiene un objeto o no
	public String toString() 
	{
		if(getObjeto() != null) 
		{
			return "El personaje " + getNombre() + " tiene el objeto " + getObjeto().getNombreObjeto() + " y esta en " + getLoc().getLocalizacion();
		}
		else 
		{
			return "El personaje " + getNombre() + " esta en " + getLoc().getLocalizacion();
		}
	}
}