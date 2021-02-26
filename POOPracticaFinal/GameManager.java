package POOPracticaFinal;

import java.util.ArrayList;
import java.util.Random;

public class GameManager implements Accionable{
	//Atributos para tratar el fichero .txt
    //Objetivos
	private ArrayList<String> Localizaciones;		//Lista de localizaciones
    private ArrayList<String> Personajes;			//Lista de personajes
    private ArrayList<String> Objetos;				//Lista de objetos
    private ArrayList <String> LocObjetivo;			//Lista de las localizaciones objetivo
    private ArrayList <String> PosObjetivo;			//Lista de los objeto objetivo
    private ArrayList <ArrayList<String>> AdyLoc;	//Lista de listas de las adyacencias de cada localizacion
    private ArrayList <String> AdyPer; 				//Lista de las palabras adyacentes a las personas
    private ArrayList <String> AdyObj;				//Lista de las palabras adyacentes a los objetos
    private ArrayList <String> ParLocObjetivo;		//Lista de las palabras adyacentes de las localizaciones objetivo
    private ArrayList <String> ParPosObjetivo;		//Lista de las palabras adyacentes de los objeto objetivo
    //Objetos
    private static ArrayList<Localizacion> listaLocalizaciones = new ArrayList<Localizacion>();
    private static ArrayList<Objeto> listaObjetos = new ArrayList<Objeto>();
    private static ArrayList<Personajes> listaPersonajes = new ArrayList<Personajes>();
    private Jugador jugador;
    
    //Leemos y cargamos todos los campos de donde tendremos posteriormente ir leyendo para cargarlos en el "mapa"
    public GameManager (ArrayList<String> Localizaciones,ArrayList<String> Personajes, ArrayList<String> Objetos,ArrayList <String> LocObjetivo,ArrayList <String> PosObjetivo,ArrayList <ArrayList<String>> AdyLoc,ArrayList <String> AdyPer,ArrayList <String> AdyObj,ArrayList <String> ParLocObjetivo,ArrayList <String> ParPosObjetivo) 
    {
    	this.Localizaciones=Localizaciones;
    	this.Personajes=Personajes;
    	this.Objetos=Objetos;
    	this.LocObjetivo=LocObjetivo;
    	this.PosObjetivo=PosObjetivo;
    	this.AdyLoc = AdyLoc;
    	this.AdyPer=AdyPer;
    	this.AdyObj=AdyObj;
    	this.ParLocObjetivo=ParLocObjetivo;
    	this.ParPosObjetivo=ParPosObjetivo;
    }
    
    //Seteamos todas las localizaciones con sus debidas adyacencias
    public void setLocalizaciones() 
    {
    	int lon = this.Localizaciones.size();
    	for(int i = 0; i < lon; i++) 
    	{
    		Localizacion localizacion = new Localizacion(this.Localizaciones.get(i), this.AdyLoc.get(i));
    		listaLocalizaciones.add(localizacion);
    	}
    	for(int j = 0; j < lon; j++) 
    	{
    		listaLocalizaciones.get(j).establecerAdyacencias(listaLocalizaciones);
    	}
    }
    
    public void setJugador(Jugador jugador)
    {
    	this.jugador=jugador;
    }
    //Vemos si el constructor de objeto tendra localizacion o personaje
    public int objComprobarSiLocalizacion(String AdyObj)
    {
    	int lon = listaLocalizaciones.size();
    	for(int i = 0; i < lon; i++) 
    	{
    		if(listaLocalizaciones.get(i).getLocalizacion().equals(AdyObj)) 
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
    //Seteamos los objetos en base a si tiene localizacion o personaje
    public void setObjetos() 
    {
    	int lon = this.Objetos.size();
    	int posicion;
    	for(int i = 0; i < lon; i++) 
    	{
    		posicion=objComprobarSiLocalizacion(AdyObj.get(i));
    		if(posicion != -1) 
    		{
    			Objeto objeto = new Objeto(Objetos.get(i), listaLocalizaciones.get(posicion));
    			listaObjetos.add(objeto);
    		}
    		else 
    		{
    			Objeto objeto = new Objeto(Objetos.get(i), AdyObj.get(i));
    			listaObjetos.add(objeto);
    		}	
    	}
    }
    
    //Comprobamos para personaje qué localizacion tiene y devolvemos dónde está
    public int perComprobarSiLocalizacion(String AdyPer) {
    	int lon = listaLocalizaciones.size();
    	for(int i = 0; i < lon; i++) 
    	{
    		if(listaLocalizaciones.get(i).getLocalizacion().equals(AdyPer)) 
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
    //Comprobamos para personaje si tiene un objeto y devolvemos qué objeto es
    public int perComprobarSiObjeto(String nombrePersonajes) 
    {
    	int lon = listaObjetos.size();
    	for(int i = 0; i < lon; i++) 
    	{
    		if(listaObjetos.get(i).getPersonaje() != null && listaObjetos.get(i).getPersonaje().equals(nombrePersonajes)) 
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
    //Comprobamos si personaje ya está en la localizacion final
    public int perComprobarSiLocalizacionFinal(int indice) 
    {
    	int tam = listaLocalizaciones.size();
    	
    	for(int i = 0; i < tam; i++)
    	{
	        if(ParLocObjetivo.get(indice).equals(listaLocalizaciones.get(i).getLocalizacion()))
	            return i;
    	}
    	return -1;
    }
    
    //Comprobamos si personaje ya tiene su objeto final
    public int perComprobarSiObjetoFinal(int indice) 
    {
    	int tam = listaObjetos.size();
    	
    	for(int i = 0; i < tam; i++)
    	{
    		if(PosObjetivo.get(indice).equals(listaObjetos.get(i).getNombreObjeto()))
    		{
    			return i;
    		}
    	}
    	return -1;
    }
    
    //Seteamos los personajes en base a si tienen o no objetos
    public void setPersonaje() 
    {
    	int lon = this.Personajes.size();
    	int posLocalizacion;
    	int posObjeto;
    	
    	for(int i = 0; i < lon; i++) 
    	{
    		posLocalizacion = perComprobarSiLocalizacion(AdyPer.get(i));
    		posObjeto = perComprobarSiObjeto(Personajes.get(i));
     		if(posObjeto > -1) 
     		{
        		Personajes personaje = new Personajes(this.Personajes.get(i), listaObjetos.get(posObjeto), listaLocalizaciones.get(posLocalizacion));
        		listaPersonajes.add(personaje);
    		}
    		else 
    		{
        		Personajes personaje = new Personajes(this.Personajes.get(i), listaLocalizaciones.get(posLocalizacion));
        		listaPersonajes.add(personaje);
        	}
    	}
    }
    
    //Seteamos los objetivos finales del mundo
    public void setFinales() 
    {
    	int tamano = listaPersonajes.size();
    	int tamano2 = LocObjetivo.size();
    	int tamano3 = PosObjetivo.size();

    	int posLocalizacionFinal;
    	int posObjetoFinal;
    	
    	for(int i = 0; i < tamano; i++)
    	{
    		for(int j = 0; j < tamano2; j++)
    		{
    			if(listaPersonajes.get(i).getNombre().equals(LocObjetivo.get(j)))
    			{
    				posLocalizacionFinal = perComprobarSiLocalizacionFinal(j);
    				listaPersonajes.get(i).setLocObjetivo(listaLocalizaciones.get(posLocalizacionFinal).getLocalizacion());
    			}
    		}
    		for(int j = 0; j < tamano3; j++)
    		{
    			if(listaPersonajes.get(i).getNombre().equals(ParPosObjetivo.get(j)))
    			{
    				posObjetoFinal = perComprobarSiObjetoFinal(j);
    				listaPersonajes.get(i).setObjetoObjetivo(listaObjetos.get(posObjetoFinal).getNombreObjeto());
    			}
    		}
    	}
    }
    
    //Getters
	public static ArrayList<Localizacion> getListaLocalizaciones() {
		return listaLocalizaciones;
	}

	public static ArrayList<Objeto> getListaObjetos() {
		return listaObjetos;
	}

	public static ArrayList<Personajes> getListaPersonajes() {
		return listaPersonajes;
	}
    
	//Comprobamos personaje por personaje si ha terminado
    public void VerSiTerminado(int indice) 
    {
    	if(listaPersonajes.get(indice).getObjeto() != null &&
    		listaPersonajes.get(indice).getLoc().getLocalizacion().equals(listaPersonajes.get(indice).getLocObjetivo()) && //Vemos si la localizacion objetivo  es igual al actual
    		listaPersonajes.get(indice).getObjeto().getNombreObjeto().equals(listaPersonajes.get(indice).getObjetoObjetivo())) //Además de si el objeto objetivo es igual al actual
    	{
    		listaPersonajes.get(indice).setTerminado(true); //Entonces el personaje ha terminado
    	}
    	else 
    	{
    		listaPersonajes.get(indice).setTerminado(false); //Si no, no ha terminado
    	}
    }
    
    //Comprobamos si han terminado los personajes
    public void VerSiTodoTerminado() 
    {
    	for(int i = 0; i < listaPersonajes.size(); i++)
    	{
    		VerSiTerminado(i);
    	}
    }
    
    //Actuamos en base a si todos han terminado
    public boolean TodoTerminado() 
    {
    	int size = listaPersonajes.size();
    	int contador = 0;
    	for(int i = 0; i < size; i++) 
    	{
    		if(listaPersonajes.get(i).getTerminado() == true)
    		{
    			contador++;
    		}
    	}
    	if(contador == size) 
    	{
    		System.out.println("Hemos terminado");
    		return true;
    	}
    	else
    		return false;
    }
    
    //dameAccion de la interfaz Accionable
    public void dameAccion(int indice)
    {
    	if(listaPersonajes.get(indice).getTerminado() == false) //Si el personaje no ha terminado damos accion:
    	{
	    	Random random = new Random(); //Hay 3 posibles opciones: moverse de sitio (A), Coger(B1)/Dejar objeto(B2), Pedir(C1)/Dejar objeto(C2);
	    	int randomNumber = random.nextInt(3);
	    	switch(randomNumber) 
	    	{
		    	case 0:
		    		System.out.println("- A");
		    		Random r = new Random(); //Nuevo random para randomizar la localizacion a la que va
		        	int randomIndex = r.nextInt(listaLocalizaciones.size());
		        	Localizacion locTemp = listaLocalizaciones.get(randomIndex);
					try { //Vemos posibles excepciones
						listaPersonajes.get(indice).irLugar(locTemp);
					} catch (ErrorActionException e1) {
						e1.getMessage();
					}
		        	break;
		    	case 1:
		    		Random r2 = new Random(); //Nuevo random para elegir sobre qué objeto actuar
		    		int randomIndex2 = r2.nextInt(listaObjetos.size());
		    		Objeto objetoAux = listaObjetos.get(randomIndex2);
		    		Random r3 = new Random(); //Nuevo rando para randomizar si coge o deja objetos
		    		int randomIndex3 = r3.nextInt(2);
		    		switch(randomIndex3) 
		    		{
			    		case 0:
			    			System.out.println("- B1");
			    			try { //Vemos posibles excepciones
			    				listaPersonajes.get(indice).cogerObjeto(objetoAux);
			    			} catch (ErrorActionException e2) {
			    				e2.getMessage();
			    			}
			        		break;
			    		case 1:
			    			System.out.println("- B2");
							try { //Vemos posibles excepciones
								listaPersonajes.get(indice).dejarObjeto();
							} catch (ErrorActionException e1) {
								e1.getMessage();
							}
			        		break;
		    		}
		    		break;
		    	case 2:
		    		Random r4 = new Random(); //Nuevo random para elegir sobre qué objeto actuar
		    		int randomIndex4 = r4.nextInt(listaObjetos.size()); 
		    		Random r5 = new Random(); //Nuevo random para elegir sobre qué personaje actuar
		    		int randomIndex5 = r5.nextInt(listaPersonajes.size());
		    		Objeto objetoAux2 = listaObjetos.get(randomIndex4);
		    		Personajes personajeAux = listaPersonajes.get(randomIndex5);
		    		Random r6 = new Random(); //Nuevo random para elegir si pedimos o damos objeto
		    		int randomIndex6 = r6.nextInt(2);
		    		switch(randomIndex6) 
		    		{
			    		case 0:
			    			System.out.println("- C1");
							try { //Vemos posibles excepciones
								listaPersonajes.get(indice).pedirObjeto(personajeAux, objetoAux2);
							} catch (ErrorActionException e) {
								e.getMessage();
							}
			    			break;
			    		case 1: 
			    			System.out.println("- C2");
							try { //Vemos posibles excepciones
								listaPersonajes.get(indice).darObjetoPedido(personajeAux);
							} catch (ErrorActionException e) {
								e.getMessage();
							}
			    			break;
		    		}
		    		break;
		    	case 3:
		    		System.out.println("- D");
		    		listaPersonajes.get(indice).nada();
		    		break;
	    	}
	    }
    	else 
    	{ //No hace nada el personaje que haya terminado
    		listaPersonajes.get(indice).nada();
    		System.out.println(listaPersonajes.get(indice).getNombre() + " ha terminado");
    	}
    }
    
    public void dameAccion(Jugador jugador,GUI gui)
    {
		synchronized(gui) //Sincronizamos con setAccion de personajes para esperar respuesta
		{
    		try 
    		{
				gui.wait();
			} catch (InterruptedException e) 
    		{
				System.out.println("Ha ocurrido algo inesperado");
				e.printStackTrace();
				System.exit(1);
			}
		}
    }
    //Cada personaje pedirá dameAccion()
    public void AccionesPersonajes() 
    {
    	int lon = listaPersonajes.size();
    	for(int i = 0; i < lon; i++) 
    	{
    		dameAccion(i);
    	}
    }
    //Comprobamos si dos personajes o más comparten localizacion para setear sus creencias
    public void comprobarLocalizaciones() //Seteamos creencias: de lo que tiene cada personaje(localizacion, objeto), en el caso de que tenga algo, sino solo el personje y su localizacion
    {
    	int contador = 0; //Llevamos un contador para también poder decir que no hay nadie o nada en la localizacion x
    	int size1 = listaPersonajes.size();
    	for(int i = 0; i < size1; i++) {//Tenemos que recorrer todos los personajes e ir comparando su localizacion con los demás personajes, por eso hay un bucle anidado 
    		contador = 0;
    		for(int j = 0; j < size1; j++) 
    		{
    			if(listaPersonajes.get(i).getLoc().equals(listaPersonajes.get(j).getLoc()) && i != j)
    			{
    				listaPersonajes.get(i).setCreencias(listaPersonajes.get(j).toString());
    				contador++;
    			}
    		}
    		if(contador == 0) 
    		{
    			listaPersonajes.get(i).setCreencias("No hay nadie");
    		}
    	}
    	
    	int size2 = listaObjetos.size(); //Ahora hacemos lo mismo pero viendo los objetos
    	
    	for(int i = 0; i < size1; i++)  //Recorriendo personaje a personaje y viendo si la localizacion de cada uno de los objetos coincide
    	{
    		contador = 0;
    		for(int j = 0; j < size2; j++)
    		{
    			if(listaPersonajes.get(i).getLoc().equals(listaObjetos.get(j).getLocalizacion()))
    			{
    				listaPersonajes.get(i).setCreencias(listaObjetos.get(j).toString());
    				contador++;
    			}
    		}
    		if(contador == 0)
    		{
    			listaPersonajes.get(i).setCreencias("No hay ningun objeto");
    		}
    	}
    }
    
    //Comprobamos si el jugador comparte localizacion con algun personaje para setear sus creencias
    public void comprobarLocalizacionesJugador(Jugador jugador) //Seteamos creencias: de lo que tiene cada personaje(localizacion, objeto), en el caso de que el personaje tenga un objeto
    {
    	int size1 = listaPersonajes.size();
    	int contador = 0;
    	int size2 = listaObjetos.size();
    	
    	for(int i = 0; i < size1; i++) //Recorremos toda la lista de personajes para ver si alguno comparte localizacion con el jugador
    	{
    		if(jugador.getLoc().equals(listaPersonajes.get(i).getLoc()))
    		{
    			jugador.setCreencias(listaPersonajes.get(i).toString());
    			listaPersonajes.get(i).setCreencias(jugador.toString());
    			contador++;
    		}
    	}
    	if(contador == 0) {
    		jugador.setCreencias("No hay nadie");
    	}
    	contador = 0;
    	for(int i = 0; i < size2; i++)  //Recorremos toda la lista de objetos para ver si alguno comparte localizacion con el jugador
    	{
    		if(jugador.getLoc().equals(listaObjetos.get(i).getLocalizacion()))
    		{
    			jugador.setCreencias(listaObjetos.get(i).toString());
    			contador++;
    		}
    	}
    	if(contador == 0)
    	{
    		jugador.setCreencias("No hay nada aqui");
    	}
    }
    
    //Enseñamos las creencias personaje por personaje **NO SE SI DEBERÍAMOS QUITAR EL IF, YO CREO QUE SÍ
    public void enseñarCreencias() 
    {
    	int size = listaPersonajes.size();
    	for (int i = 0; i < size; i++)
    	{
    		if(listaPersonajes.get(i).getCreencias().isEmpty() == false) 
    		{
    			System.out.println(listaPersonajes.get(i).getCreencias());
    		}
    	}
    }
    
    //Enseñamos los datos personales de cada personaje
    public void enseñarDatosPersonajes()
    {
    	int size = listaPersonajes.size();
    	for(int i = 0; i < size; i++) 
    	{
    		System.out.println(listaPersonajes.get(i).toString());
    	}
    }
    
    //Enseñamos el registro de acciones de cada personaje
    public void enseñarRegistroPersonajes()
    {
    	int size = listaPersonajes.size();
    	for(int i = 0; i < size; i++) 
        {
        	System.out.println(listaPersonajes.get(i).getRegistro());
        }
    }
    //Mostramos creencias del jugador por la interfaz gráfica
    public void mostrarCreenciasJugador(Jugador jugador, GUI gui,int ronda,int indice) 
    {
    	int i = 0;
    	gui.añadirTexto("Ronda "+ronda+"\nCreencias:\n");
    	for(i=indice;i<jugador.getCreencias().size();i++)
    	{
    		gui.añadirTexto(jugador.getCreencias().get(i)+"\n");
    	}
    }
}