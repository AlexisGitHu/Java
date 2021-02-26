package POOPracticaFinal;

import java.util.ArrayList;
import java.util.Scanner;

//Clase que se encarga de leer los ficheros
public class LeeTexto {
	//Función que distingue entre los parametros (<Localizacion>, <Objetos>, ...)
	public int objetos(String data, Scanner myReader) 
	{
		int lon = data.length() - 2;
        char[] palabra = new char[lon];
		if(data.charAt(0) == '<') 
		{
	        int indice = 1;
	        for(int i = 0; i<lon; i++) 
	        {
	        	palabra[i] = data.charAt(indice);
	        	indice++;
	        }
		}
		String string = String.valueOf(palabra);
        if(string.equals("Localizaciones") == true) 
        {
        	return 1;
        }
        else if(string.equals("Personajes") == true) 
        {
        	return 2;
        }
        else if(string.equals("Objetos") == true)
        {
        	return 3;
        }
        else if(string.equals("Localización Personajes") == true) 
        {
        	return 4;
        }
        else if(string.equals("Posesión Objetos") == true)
        {
        	return 5;
        }
        else
        	return 0;
	}
	//Funcion que lee los argumentos que no estan entre paréntesis
	public String listaObjetos(String data, Scanner myReader) 
	{
		String dato1 = "";
		try {
			int len = data.length();
			int indice = 0;
			for(int i = 0; i < len; i++) 
			{
				if (data.charAt(i) == '(')
				{
        			indice = i;
				}
			}
			//Excepción de que no hay argumentos
			if(indice == 0) 
			{
				ArgMissingException exception = new ArgMissingException("Arguments Missing in Text File");
				throw exception;
			}
			char[] palabra = new char[indice];
			for(int i = 0; i < indice; i++) 
			{
				palabra[i] = data.charAt(i);
			}
			dato1 = String.valueOf(palabra);
		}
		catch(ArgMissingException a) 
		{
			System.out.println(a.getMessage());
			System.exit(1);
		}
		return dato1;
	}
	//Función que lee los argumentos que están entre paréntesis en el .txt si hay solo uno
	public String listaObjetos2(String data, Scanner myReader) 
	{
		String dato1 = "";
		try 
		{
			int len = data.length();
			int indice = 0;
			int indice2 = 0;
			for(int i = 0; i < len; i++) 
			{
				if (data.charAt(i) == '(') 
				{
        			indice = i;
				}
				if (data.charAt(i) == ')')
				{
					indice2 = i;
				}
			}
			//Excepción de que no hay argumentos
			if(indice == 0) 
			{
				ArgMissingException exception = new ArgMissingException("Arguments Missing in Text File");
				throw exception;
			}
			char[] palabra = new char[indice2-indice-1];
			int indPalabra = 0;
			for(int i = indice+1; i < indice2; i++) 
			{
				palabra[indPalabra] = data.charAt(i);
				indPalabra++;
			}
			dato1 = String.valueOf(palabra);
		}
		catch(ArgMissingException a) 
		{
			System.out.println(a.getMessage());
			System.exit(1);
		}
		return dato1;
	}
	//Función que lee los argumentos que están entre paréntesis en el .txt si hay más de uno
	public ArrayList<String> Adyacentes(String data, Scanner myReader)
	{
		ArrayList <String> datos = new ArrayList <String>();
		int len = data.length();
		int lon = 0;
		int indice = 0;
        for(int i = 0; i < len; i++) 
        {
        	if (data.charAt(i) == '(') 
        	{
        		indice = i;
        		lon = (len-2) - indice;
        	}
        }
		char[] palabra2 = new char[lon];
		for(int i = 0; i < lon; i++) 
		{
        	indice++;
        	palabra2[i] = data.charAt(indice); 
        }
		String string = String.valueOf(palabra2);
	    String[] arrayStrings = string.split(", " , 10);
	    for(String dato2: arrayStrings) 
	    {
	    	datos.add(dato2);
	    }
	    return datos; 
	}
}