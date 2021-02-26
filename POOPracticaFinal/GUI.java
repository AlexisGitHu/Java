package POOPracticaFinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends JFrame implements ActionListener
{
	private JPanel panel_izq;
	private JTextArea area;
	private Jugador jugador;
	private boolean accion_perf = false;
	
	private static final long serialVersionUID = 1L; //se añade para quitar el warning
	private static final int width = 500; //900
	private static final int height = 500; //700
	private static final int coord_x = 200;
	private static final int coord_y = 100;

	public GUI(Jugador jugador)//constructor que incializa la ventana de la GUI y crea los paneles correspondientes
	{
		setTitle("Ventana de Jugador");
		setSize(width,height);
		setLocation(coord_x,coord_y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createPannels();
		area.setText("Registro:\n");
		this.jugador = jugador;
	}

	private boolean getAccion()
	{
		return accion_perf;
	}
	
	private void setAccion(boolean valor)
	{
		synchronized(this) //Sincronizamos setAccion() con dameAccion() del jugador para avisar de cuándo se ha cambiado la accion
		{
			accion_perf = valor;
			notify();
		}
	}
	private void createPannels() //método creador de los paneles y botones de la GUI, se llama en el constructor
	{
		panel_izq = createPanIzq();
		area = createTextArea();
		JScrollPane scroll = new JScrollPane(area);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel_izq, BorderLayout.CENTER);
		getContentPane().add(scroll, BorderLayout.NORTH);
	}
	private void addButtonsCen(JPanel panel) //creamos los botones y les añadimos el action listener que es la propia GUI
	{
		JButton irBoton = new JButton("Cambiar de lugar");
		irBoton.addActionListener(this);
		JButton pedirBoton = new JButton("Pedir objeto");
		pedirBoton.addActionListener(this);
		JButton darObjeto = new JButton("Dar objeto");
		darObjeto.addActionListener(this);
		JButton cogerObjeto = new JButton("Coger objeto");
		cogerObjeto.addActionListener(this);
		JButton dejarObjeto = new JButton("Dejar objeto");
		dejarObjeto.addActionListener(this);
		JButton nada = new JButton("Pasar de ronda");
		nada.addActionListener(this);
		panel.add(irBoton);
		panel.add(pedirBoton);
		panel.add(darObjeto);
		panel.add(cogerObjeto);
		panel.add(dejarObjeto);
		panel.add(nada);
	}
	private JPanel createPanIzq() //método para crear el panel izquierdo de la interfaz gráfica
	{
		JPanel panel_izq = new JPanel();
		panel_izq.setLayout(new GridLayout(3,2));
		addButtonsCen(panel_izq);
		return panel_izq;
	}
	private JTextArea createTextArea() //crea el registro de acciones del jugador
	{
		JTextArea area1 = new JTextArea();
		area1.setColumns(20);
		area1.setRows(20);
		area1.setBorder(BorderFactory.createLineBorder(Color.black));
		return area1;
	}
	public JTextArea getArea()
	{
		return area;
	}
	public void añadirTexto(String texto) //método que permite añadir texto al registro de la GUI
	{
		area.append(texto);
	}
	public void actionPerformed(ActionEvent event) //escuchador de eventos, obtiene la raíz del evento, evalúa que acción se pide y ejecuta el código correspondiente
	{
		JButton clickedButton = (JButton) event.getSource();
		//System.out.println(accion_perf);
		Personajes pj_aux;
		Objeto obj_aux;
		accion_perf = false;
		
		switch(clickedButton.getText())
		{
			case "Cambiar de lugar":
				ventanaDestinos();
				break;
			case "Pedir objeto"://llama a la función pedir objeto de personaje y añade el texto adecuado
				pj_aux = ventanaPersonaje();
				obj_aux = ventanaObjeto();
				try {
					jugador.pedirObjeto(pj_aux, obj_aux);
				} catch (ErrorActionException e) {
					e.printStackTrace();
				}
				if(pj_aux != null && obj_aux != null)
					{
					if(jugador.getPedir()==true)
					{
						area.append("El jugador pidio a "+pj_aux.getNombre()+ " el objeto "+obj_aux.getNombreObjeto()+"\n");
					}
					else
					{
						area.append("El jugador no pudo pedir el objeto\n");
					}
				}
				else
				{
					area.append("El jugador no pudo realizar esta accion\n");
				}
				break;
			case "Dar objeto"://llama a la función dar objeto de personaje y añade el texto adecuado
				if(jugador.getObjeto() != null)	
				{
					pj_aux = ventanaPersonaje();
					try {
						jugador.darObjetoPedido(pj_aux); 
					} catch (ErrorActionException e) {
						e.printStackTrace();
					}
					if(pj_aux != null)
					{
						if(jugador.getObjeto()==null)
						{
							area.append("El jugador le dio su objeto a "+pj_aux.getNombre()+"\n");
						}
						else
						{
							area.append("El jugador no le pudo dar su objeto\n");
						}
					}
					else
					{
						area.append("El jugador no pudo realizar su accion\n");
					}
				}
				else
				{
					area.append("El jugador no tiene objeto\n");
				}
				break;
			case "Coger objeto"://llama a la función coger objeto de personaje y añade el texto adecuado
				obj_aux=ventanaObjeto();
				try {
					jugador.cogerObjeto(obj_aux);
				} catch (ErrorActionException e1) {
					e1.printStackTrace();
				}
				if(obj_aux != null && jugador.getObjeto()!=null)
				{
					if(jugador.getObjeto().equals(obj_aux) && obj_aux.getLocalizacion()!=null)
					{
						area.append("El jugador cogió el objeto: " +obj_aux.getNombreObjeto() +" de "+jugador.getLoc().getLocalizacion()+"\n");
						obj_aux.setLocalizacion(null);
					}
					else
					{
						area.append("El jugador no pudo coger el objeto\n");
					}
				}
				else
				{
					area.append("El jugador no pudo realizar esta accion\n");
				}
				break;
			case "Dejar objeto"://llama a la función dejar objeto de personaje y añade el texto adecuado
				if(jugador.getObjeto()!=null)
				{
					try {
						jugador.dejarObjeto();
					} catch (ErrorActionException e) {
						e.printStackTrace();
					}
					area.append("El jugador dejó su objeto\n");
				}
				else
				{
					area.append("El jugador no pudo dejar su objeto, porque no tenía ninguno\n");
				}
				break;
			case "Pasar de ronda":
				jugador.nada();
				area.append("El jugador no hizo nada esta ronda\n");
				break;
		}
		setAccion(true);
		//System.out.println(accion_perf);
	}
	private void ventanaDestinos() //método que evalúa si el jugador puede y cambia de lugar o no
	{
		String destino = JOptionPane.showInputDialog(null,"A dónde quieres ir?: ");
		Localizacion loc;
		int contador = 0;
		for(Localizacion l: GameManager.getListaLocalizaciones())
		{
			if(l.getLocalizacion().equals(destino))
			{
				loc = l;
				try {
					jugador.irLugar(loc);
				} catch (ErrorActionException e) {
					e.printStackTrace();
				}
				if(loc.equals(jugador.getLoc()))
				{
					area.append("El jugador se movio a "+jugador.getLoc().getLocalizacion() + "\n");
				}
				else
				{
					area.append("La localización "+loc.getLocalizacion()+" no es adyacente\n");
				}
				contador++;
			}
		}
		if(contador==0)
		{
			area.append("No existe dicha localización\n");
		}
	}
	private Personajes ventanaPersonaje() //método que evalúa el personaje pedido por el jugador, solo se utiliza dentro de la GUI para el accionador
	{
		String personaje = JOptionPane.showInputDialog(null,"Introduzca el nombre del personaje");
		Personajes pj_aux = null;
		for(Personajes p:GameManager.getListaPersonajes())
		{
			if(p.getNombre().equals(personaje))
			{
				pj_aux=p;
			}
		}
		return pj_aux;	
	}
	private Objeto ventanaObjeto() //método que evalúa el objeto pedido por el jugador, solo se utiliza dentro de la GUI para el accionador
	{
		String objeto = JOptionPane.showInputDialog(null,"Introduzca el nombre del objeto");
		Objeto obj_aux = null;
		for(Objeto obj:GameManager.getListaObjetos())
		{
			if(obj.getNombreObjeto().equals(objeto))
			{
				obj_aux = obj;
			}
		}
		return obj_aux;
	}
}