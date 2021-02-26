package POOPracticaFinal;

import java.util.ArrayList;

public class Localizacion 
{
    private String localizacion;
    private ArrayList<Localizacion> localizacionesAdyacentes = new ArrayList<Localizacion>();
    private ArrayList<String> adyacentes;

    public Localizacion(String localizacion, ArrayList<String> localizacionesAdy)
    {
        this.localizacion = localizacion;
        this.adyacentes = localizacionesAdy;
    }
    
    public void establecerAdyacencias(ArrayList<Localizacion> localizaciones)
    {
    	//this.localizacionesAdyacentes = new ArrayList<Localizacion>();
        int longitud = adyacentes.size();
        int longitud2 = localizaciones.size();
        for(int i = 0; i < longitud2; i++)
        {
        	for(int j = 0; j < longitud; j++) {
        		if(localizaciones.get(i).getLocalizacion().equals(adyacentes.get(j))) {
                    this.localizacionesAdyacentes.add(localizaciones.get(i));
                    //System.out.println(this.localizacionesAdyacentes.get(0));
        		}
        	}
        }
    }
    public String getLocalizacion() 
    {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) 
    {
        this.localizacion = localizacion;
    }

    public ArrayList<Localizacion> getLocalizacionesAdyacentes() 
    {
        return localizacionesAdyacentes;
    }

    public void setLocalizacionesAdyacentes(ArrayList<Localizacion> localizacionesAdyacentes) 
    {
        this.localizacionesAdyacentes = localizacionesAdyacentes;
    }
    public ArrayList<String> getAdyacentes() 
    {
        return adyacentes;
    }

    public void setAdyacentes(ArrayList<String> localizacionesAdyacentes) 
    {
        this.adyacentes = localizacionesAdyacentes;
    }

}