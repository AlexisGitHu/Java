package POOPracticaFinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class Partida 
{
	public static void main(String[] args) 
	{
		//Inicializamos arrayList de strings para almacenar todos los datos leídos de los ficheros:
		ArrayList<String> Localizaciones = new ArrayList<String>();					//Lista de localizaciones
	    ArrayList<String> Personajes = new ArrayList <String>();					//Lista de personajes
	    ArrayList<String> Objetos = new ArrayList <String>();						//Lista de objetos
	    ArrayList <String> LocObjetivo = new ArrayList <String>();					//Lista de las localizaciones objetivo
	    ArrayList <String> PosObjetivo = new ArrayList <String>();					//Lista de los objeto objetivo
	    ArrayList <ArrayList<String>> AdyLoc = new ArrayList<ArrayList<String>>();	//Lista de listas de las adyacencias de cada localizacion
	    ArrayList <String> AdyPer = new ArrayList<String>();						//Lista de las palabras adyacentes a las personas
	    ArrayList <String> AdyObj = new ArrayList<String>();						//Lista de las palabras adyacentes a los objetos
	    ArrayList <String> ParLocObjetivo = new ArrayList<String>();				//Lista de las palabras adyacentes de las localizaciones objetivo
	    ArrayList <String> ParPosObjetivo = new ArrayList<String>();				//Lista de las palabras adyacentes de los objeto objetivo
	    try 
	    {
	    	LeeTexto lector = new LeeTexto(); //Leemos ambos ficheros
	    	File myObj = new File("filename.txt");
	    	Scanner myReader = new Scanner(myObj);
	    	File myObj2 = new File("filename2.txt");
	    	Scanner myReader2 = new Scanner(myObj2);
	    	int opcion = 0;
	    	int opcion2 = 0;
	    	int contador = 0;
	    	int contador2 = 0;
			//Bucle que mira si el fichero se ha terminado
			//Bucle para el primer fichero de texto, el que establece el mundo
	    	while (myReader.hasNextLine())
	    	{
	    		String data = myReader.nextLine();
	    		opcion = lector.objetos(data, myReader);
	    		if(opcion > 0) 
	    		{
	    			opcion2 = opcion;
	    		}	
	    		if(opcion == 1 || opcion == 2 || opcion == 3) 
	    		{
					//Contador para ver si se tendrá que producir una excepción
	    			contador++;
	    		}	
	    		if (opcion2 == 1 && opcion == 0) 
	    		{
	    			Localizaciones.add(lector.listaObjetos(data, myReader));
	    			AdyLoc.add(lector.Adyacentes(data,myReader));
	    		}
	    		if (opcion2 == 2 && opcion == 0) 
	    		{
	    			Personajes.add(lector.listaObjetos(data, myReader));
	    			AdyPer.add(lector.listaObjetos2(data,myReader));
	    		}
	    		if (opcion2 == 3 && opcion == 0) 
	    		{
	    			Objetos.add(lector.listaObjetos(data, myReader));
		        	AdyObj.add(lector.listaObjetos2(data,myReader));
	    		}
	    	}
			//Bucle para el segundo fichero de texto, el que establece los objetivos
	    	while (myReader2.hasNextLine()) 
	    	{
	    		String data = myReader2.nextLine();
	    		opcion = lector.objetos(data, myReader);
	    		if(opcion > 0) 
	    		{
	    			opcion2 = opcion;
	    		}
	    		if(opcion == 4 || opcion == 5)
	    		{
					//Contador para ver si se tendrá que producir una excepción
	    			contador2++;
	    		}
	    		if (opcion2 == 4 && opcion == 0) 
	    		{
	    			LocObjetivo.add(lector.listaObjetos(data, myReader2));
	    			ParLocObjetivo.add(lector.listaObjetos2(data,myReader2));
	    		}
	    		if (opcion2 == 5 && opcion == 0) 
	    		{
	    			PosObjetivo.add(lector.listaObjetos(data, myReader2));
	    			ParPosObjetivo.add(lector.listaObjetos2(data,myReader2));
	    		}
	    	}
			//Tratamiento de la variable contador para crear las excepciones
	    	if (contador > 3 || contador2 > 2) 
	    	{
	    		ExcessException exception = new ExcessException("Exceso de argumentos en el fichero");
	    		throw exception;
	    	}	
	    	if (contador < 3 || contador2 < 2)
	    	{
	    		NotEnoughException exception = new NotEnoughException("Falta de argumentos en el fichero"); 
	    		throw exception;
	    	} 
	    	myReader.close();
	    	myReader2.close();
	    }
		//Tratamiento de excepciones
		//Excepcion de fichero de texto no encontrado
	    catch (FileNotFoundException e) 
	    {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    	System.exit(1);
	    }
		//Excepciones de falta o exceso de argumentos
	    catch (ExcessException a) 
	    {
	    	System.out.println(a.getMessage());
	    	System.exit(1);
	    }
	    catch (NotEnoughException a) 
	    {
	    	System.out.println(a.getMessage());
	    	System.exit(1);
	    }
	    //Pasamos al gestor toda la informacion recogida de los ficheros
		GameManager manager = new GameManager(Localizaciones,Personajes,Objetos,LocObjetivo,PosObjetivo,AdyLoc,AdyPer,AdyObj,ParLocObjetivo,ParPosObjetivo);
    	//Seteamos todas las localizaciones, objetos, personajes y objetivos finales del mundo
    	manager.setLocalizaciones();
    	manager.setObjetos();
    	manager.setPersonaje();
    	manager.setFinales();
    	//Creamos un nuevo jugador y lo seteamos tanto para el gestor (para su gestion) como a la gui (para la interfaz y su gestion)
    	Jugador jugador = new Jugador("David", null, GameManager.getListaLocalizaciones().get(3));
    	manager.setJugador(jugador);
    	GUI gui = new GUI(jugador);
    	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gui.setVisible(true);
    	
    	//Llevamos la cuenta de rondas para poder mostrar el numero de ronda
    	int cont = 0;
    	int size = 0;	//Variable que usaremos para mostrar las creencias del personaje ajustada a la ronda
    	while(manager.TodoTerminado() == false) 						//Mientras que alguien no haya terminado que sigan pasando las rondas
    	{
    		manager.VerSiTodoTerminado();								//Antes de dar acciones a los personajes debemos comprobar si han terminado
            manager.AccionesPersonajes();								//Damos acciones a todos los personajes
            gui.añadirTexto("Acción:\n");								
            manager.dameAccion(jugador, gui);							//Damos accion al jugador
            size = jugador.getCreencias().size();						//Vemos de donde tenemos que empezar a leer de las creencias del jugador
    		manager.comprobarLocalizaciones();							//Comprobamos las localizaciones entre personajes (para ver personajes y objetos) para setear las creencias
    		manager.comprobarLocalizacionesJugador(jugador);			//Comprobamos la localizacion del jugador (para ver si hay personjes u objetos) para setear las creencias del jugador
    		manager.mostrarCreenciasJugador(jugador, gui,cont,size);	//Mostramos las creencias del jugador, donde mostrará en la interfaz el número de ronda, y solo las creencias de esa ronda
            System.out.println("Mostramos datos: ");			
            manager.enseñarDatosPersonajes();							//Mostramos los datos de los personajes
            System.out.println("Mostramos registros: ");
            manager.enseñarRegistroPersonajes();						//Mostramos las acciones de los personajes
            System.out.println("Mostramos creencias: ");
            manager.enseñarCreencias();									//Mostramos las creencias de los personajes
            
            cont++;														//Pasamos de ronda
    	}
    	System.exit(0);													//Si salimos del bucle significa que hemos terminado y salimos con 0 errores
	}
}
