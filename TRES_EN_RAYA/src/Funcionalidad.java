import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class Funcionalidad implements ActionListener {

	private Ventana v;
	private boolean i, interruptorIA, comprobar, reiniciar;
	private int numero, aleatorio, contadorGanadores;
	Funcionalidad(Ventana v, int i2, boolean interruptorIA)
	{
		this.v=v;
		this.numero=i2;
		this.interruptorIA=interruptorIA;
		comprobar=true;
		contadorGanadores=0;
		reiniciar=false;
		
	}
	public void posibleGanadorFilas(int x, int y, Color dato, int j, String string)
	{
		for (int i=x;i<y;i++)
		{
			if (v.getBotones()[i].getBackground().equals(dato))
			{
				contadorGanadores++;
				if (contadorGanadores==3)
				{
					System.out.println("Entra");
					JOptionPane.showMessageDialog(v, "FIN DE LA PARTIDA");
					if (j==1)
					{
						v.getGanador().setText("¡Jugador 1 ha ganado!");
						v.deshabilitarBotones();
					}
					else if (j==2)
					{
						if (string.equalsIgnoreCase("IA"))
						{
							v.getGanador().setText("¡La computadora te ha ganado!");
							v.deshabilitarBotones();
						}
						else if (string.equalsIgnoreCase("jugador"))
						{
							v.getGanador().setText("¡Jugador 2 ha ganado!");
							v.deshabilitarBotones();
						}	
					}
		
				}
			}
		}
		contadorGanadores=0;
	}

	public void posibleGanadorColumnas(int x, int y, int z, Color dato, int j, String string)
	{
		if (v.getBotones()[x].getBackground().equals(dato) && v.getBotones()[y].getBackground().equals(dato) && v.getBotones()[z].getBackground().equals(dato))
		{
			
			JOptionPane.showMessageDialog(v, "FIN DE LA PARTIDA");
			if (j==1)
			{
				v.getGanador().setText("¡Jugador 1 ha ganado!");
				v.deshabilitarBotones();
			}
			else if (j==2)
			{
				if (string.equalsIgnoreCase("IA"))
				{
					v.getGanador().setText("¡Te ganó la IA!");
					v.deshabilitarBotones();
				}
				else if (string.equalsIgnoreCase("jugador"))
				{
					v.getGanador().setText("¡Jugador 2 ha ganado!");
					v.deshabilitarBotones();
				}

			}
		}
	}
	public void comprobarSiHayGanador(Color dato, int j, String string)
	{
		posibleGanadorFilas(0,3, dato,j,string);
		posibleGanadorFilas(3,6, dato ,j,string);
		posibleGanadorFilas(6,9, dato,j,string);
		posibleGanadorColumnas(0,3,6,dato,j,string);
		posibleGanadorColumnas(1,4,7,dato,j,string);
		posibleGanadorColumnas(2,5,8,dato,j,string);
		posibleGanadorColumnas(0,4,8,dato,j,string);
		posibleGanadorColumnas(2,4,6,dato,j,string);
	
	}
	public void actionPerformed(ActionEvent e) {
		
		
		//AQUI VA EL IF QUE CONTENDRÁ CUÁL RADIO BUTTON ESTÁ SELECCIONADO
		
		if (e.getSource()==v.getReiniciar())
		{
			for (int i=0;i<v.getBotones().length;i++)
			{
				v.getBotones()[i].setEnabled(true);
				v.getBotones()[i].setBackground(Color.white);
				v.getJugadaIA().setEnabled(false);
				v.getGrupoRadios().clearSelection();
			}
			System.out.println("AVER");
			v.getBotones()[0].setEnabled(true);
			reiniciar=true;
			v.setInterruptor(true);//TENGO Q PONER ESTO EN TRUE PQ
			//SINO, AL REINICIAR, LE TOCA SUPUESTAMENTE A LA COMPUTADORA Y EL BOTÓN JUGADA IA ESTÁ
			//DESHABILITADO
			v.getGanador().setText("");
			v.getJugadaIA().setVisible(true);
		}
		if (reiniciar==false)
		{
			//COMPROBAMOS SI QUEDAN BOTONES DISPONIBLES SI AÚN NO HAY GANADOR
			comprobarBotonesDisponibles();
			
			
			if (v.getHumanoHumano().isSelected())
			{
				if (v.isInterruptor()==true)
				{
					System.out.println("Entra true");
					activosJugador1(e.getSource());
					v.setInterruptor(false);
					//NADA MAS TERMINAMOS, DESHABILITAMOS LA CAJA ACTIVA
					v.getBotones()[numero].setEnabled(false);
					//AL FINAL DE CADA JUGADA O PULSADA DE BOTON ,COMPROBAMOS SI HAY GANADOR:
					comprobarSiHayGanador(Color.green, 1, "jugador");
				}
				else if (v.isInterruptor()==false)
				{
					System.out.println("Entra false");
					activoJugador2(e.getSource());
					v.setInterruptor(true);
					v.getBotones()[numero].setEnabled(false);
					//AL FINAL DE CADA JUGADA O PULSADA DE BOTON ,COMPROBAMOS SI HAY GANADOR:
					comprobarSiHayGanador(Color.red, 2, "jugador");
				}
			}	
			else if (v.getHumanoComputadora().isSelected())
			{
				
				
				if (v.isInterruptor()==true)
				{
					System.out.println("Entra true");
					activosJugador1(e.getSource());
					v.setInterruptor(false);
					//NADA MAS TERMINAMOS, DESHABILITAMOS LA CAJA ACTIVA
					v.getBotones()[numero].setEnabled(false);
					
					//AUI DENTRO?
					//HABILITAMOS EL BOTÓN JUGADA IA

					v.getJugadaIA().setEnabled(true);
					v.setInterruptorIA(false);
					comprobarSiHayGanador(Color.green, 1, "jugador");
				}
				else if (v.isInterruptor()==false)
				{
					
					if (e.getSource()==v.getJugadaIA())
					{
						//ELEGIMOS NUMERO ENTRE 0 Y 8
						//REPETIMOS MIENTRAS EL BOTON SEA ENABLED->FALSE
						//DESHABILITAMOS EL BOTON JUGADA IA:
						v.getJugadaIA().setEnabled(false);
						do
						{
							aleatorio=(int) (Math.random()*8);
						} while (v.getBotones()[aleatorio].isEnabled()==false);
							switch (aleatorio)
							{
							case 0:v.getBotones()[0].setBackground(Color.red);					
								break;
							case 1:v.getBotones()[1].setBackground(Color.red);
								break;
							case 2:v.getBotones()[2].setBackground(Color.red);
								break;
							case 3:v.getBotones()[3].setBackground(Color.red);
								break;
							case 4:v.getBotones()[4].setBackground(Color.red);
								break;
							case 5:v.getBotones()[5].setBackground(Color.red);
								break;
							case 6:v.getBotones()[6].setBackground(Color.red);
								break;
							case 7:v.getBotones()[7].setBackground(Color.red);
								break;
							case 8:v.getBotones()[8].setBackground(Color.red);
								break;
							}
						
						
						v.getBotones()[aleatorio].setEnabled(false);
						v.getJugadaIA().setEnabled(false);
						v.setInterruptorIA(true);
						v.setInterruptor(true);
						
						
					}
					comprobarSiHayGanador(Color.red, 2, "IA");
				}
			}
			else
			{
				//NO HABRÁ FUNCIONALIDAD, SALTARÁ EL MENSAJE SI INTENTA PULSAR SOBRE UN BOTÓN
				JOptionPane.showMessageDialog(v, "Debe seleccionar una modalidad (Humano vs humano / Humano vs computadora)");
			}
		}
		
		
	}
	//NECESITO UN MÉTODO PARA EVITAR BUCLES INFINITOS Y QUE LA IA QUEDE DESHABILITADA TRAS NO HABER BOTONES ENABLED:
	
	public void comprobarBotonesDisponibles()
	{
		
		int contador=1;
		for (int i=0;i<v.getBotones().length && comprobar==true;i++)
		{
			if (v.getBotones()[i].isEnabled()==false)
			{
				contador++;
				if (contador>=9)
				{
					comprobar=false;
					JOptionPane.showMessageDialog(v, "¡PARTIDA FINALIZADA!");
					v.getJugadaIA().setVisible(false);
		
				}
			}
		}
	}
	
	public void activosJugador1(Object objeto)
	{
		
		if (objeto==v.getBotones()[0])
		{
			v.getBotones()[0].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[1])
		{
			v.getBotones()[1].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[2])
		{
			v.getBotones()[2].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[3])
		{
			v.getBotones()[3].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[4])
		{
			v.getBotones()[4].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[5])
		{
			v.getBotones()[5].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[6])
		{
			v.getBotones()[6].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[7])
		{
			v.getBotones()[7].setBackground(Color.green);
		}
		else if (objeto==v.getBotones()[8])
		{
			v.getBotones()[8].setBackground(Color.green);
		}
		
	}
	public void activoJugador2(Object objeto)
	{
		
		if (objeto==v.getBotones()[0])
		{
			v.getBotones()[0].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[1])
		{
			v.getBotones()[1].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[2])
		{
			v.getBotones()[2].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[3])
		{
			v.getBotones()[3].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[4])
		{
			v.getBotones()[4].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[5])
		{
			v.getBotones()[5].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[6])
		{
			v.getBotones()[6].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[7])
		{
			v.getBotones()[7].setBackground(Color.red);
		}
		else if (objeto==v.getBotones()[8])
		{
			v.getBotones()[8].setBackground(Color.red);
		}
		
	}

}
