import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
public class Ventana extends JFrame{

	private JPanel panelIzq, panelDch; 
	private JButton[] botones;
	private JPanel[] panelBotones;
	private JRadioButton humanoComputadora, humanoHumano;
	private ButtonGroup grupoRadios;
	private JLabel ganador;
	private JButton reiniciar, jugadaIA;

	private boolean interruptor, interruptorIA;
	
	Ventana()
	{
		this.setLayout(new GridLayout(1,2));
		this.setLocation(500,300);
		this.setSize(400,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		panelIzq=new JPanel(new GridLayout(3,3));
		panelDch=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		interruptor=true; // EN INICIO ES TRUE
		interruptorIA=false;
		
		panelIzq();
		panelDch();
		
		jugadaIA=new JButton("Jugada IA");
		panelDch.add(jugadaIA);
		jugadaIA.addActionListener(new Funcionalidad(this, 0, interruptorIA));
		jugadaIA.setEnabled(false);
		
		this.add(panelIzq);
		this.add(panelDch);
		
		reiniciar.addActionListener(new Funcionalidad(this, 0, interruptorIA));
		
		
	}
	public void deshabilitarBotones()
	{
		System.out.println("ENTRA");
		for (int i=0;i<botones.length;i++)
		{
			botones[i].setEnabled(false);
		}
	}
	
	public void panelIzq()
	{
		botones=new JButton[9];
		panelBotones=new JPanel[9];
		
		for (int i=0;i<botones.length;i++)
		{
			
			botones[i]=new JButton("");
			botones[i].setPreferredSize(new Dimension(30,30));
			panelBotones[i]=new JPanel();
			panelBotones[i].setPreferredSize(new Dimension(50,50));
			panelBotones[i].add(botones[i]);
			panelIzq.add(panelBotones[i]);
			//TAMBIEN HAGO ESCUCHADORES A TODOS LOS BOTONES
			botones[i].addActionListener(new Funcionalidad(this, i, interruptorIA));
		}
	}
	public void panelDch()
	{
		humanoComputadora=new JRadioButton("Humano vs computadora");
		humanoComputadora.setPreferredSize(new Dimension(170,20));
		humanoHumano=new JRadioButton("Humano vs humano");
		humanoHumano.setPreferredSize(new Dimension(170,20));
		grupoRadios=new ButtonGroup();
		grupoRadios.add(humanoComputadora);
		grupoRadios.add(humanoHumano);
		ganador=new JLabel(""); //VACIO EN SU INICIO
		ganador.setPreferredSize(new Dimension(150,60));
		reiniciar=new JButton("Reiniciar");
		panelDch.add(humanoComputadora);
		panelDch.add(humanoHumano);
		panelDch.add(ganador);
		panelDch.add(reiniciar);
	}
	

	public boolean isInterruptorIA() {
		return interruptorIA;
	}

	public void setInterruptorIA(boolean interruptorIA) {
		this.interruptorIA = interruptorIA;
	}

	public JPanel getPanelIzq() {
		return panelIzq;
	}

	public void setPanelIzq(JPanel panelIzq) {
		this.panelIzq = panelIzq;
	}

	public JPanel getPanelDch() {
		return panelDch;
	}

	public void setPanelDch(JPanel panelDch) {
		this.panelDch = panelDch;
	}

	public JButton[] getBotones() {
		return botones;
	}

	public void setBotones(JButton[] botones) {
		this.botones = botones;
	}

	public JPanel[] getPanelBotones() {
		return panelBotones;
	}

	public void setPanelBotones(JPanel[] panelBotones) {
		this.panelBotones = panelBotones;
	}

	public JRadioButton getHumanoComputadora() {
		return humanoComputadora;
	}

	public void setHumanoComputadora(JRadioButton humanoComputadora) {
		this.humanoComputadora = humanoComputadora;
	}

	public JRadioButton getHumanoHumano() {
		return humanoHumano;
	}

	public void setHumanoHumano(JRadioButton humanoHumano) {
		this.humanoHumano = humanoHumano;
	}

	public ButtonGroup getGrupoRadios() {
		return grupoRadios;
	}

	public void setGrupoRadios(ButtonGroup grupoRadios) {
		this.grupoRadios = grupoRadios;
	}

	public JLabel getGanador() {
		return ganador;
	}

	public void setGanador(JLabel ganador) {
		this.ganador = ganador;
	}

	public JButton getReiniciar() {
		return reiniciar;
	}

	public void setReiniciar(JButton reiniciar) {
		this.reiniciar = reiniciar;
	}



	public boolean isInterruptor() {
		return interruptor;
	}

	public void setInterruptor(boolean interruptor) {
		this.interruptor = interruptor;
	}

	public JButton getJugadaIA() {
		return jugadaIA;
	}

	public void setJugadaIA(JButton jugadaIA) {
		this.jugadaIA = jugadaIA;
	}
	
	
}
