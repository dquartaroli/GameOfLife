package gameoflife;

/**
 * Classe Graphic
 * @author Denis Quartaroli, Federica Carloni, Mirko Cracco
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Graphic extends JFrame{
		
	/**
	 * Variabile Land che contiene la situazione delle cellule nella matrice
	 */
	Land l;
	
	//----------------------------------------------|
	//			Variabili per la grafica			|
	//----------------------------------------------|
	
	/**
	 * Matrice dei pulsanti che rappresentano la sitituazione del Game Of Life
	 */
	JButton[][] terrain;	
	
	/**
	 * Pulsante che permette un cambio di generazione
	 */
	JButton next;	
	
	/**
	 * Pulsante che permette l'inizio dell'esecuzione dei thread
	 */
	JButton start;	
	
	/**
	 * Pulsante che permette di terminare l'esecuzione dei thread
	 */
	JButton stop;			
	
	/**
	 * Pulsante che permette l'immissione di una singola cellula
	 */
	JButton cell;
	
	/**
	 * Pulsante che permette l'immissione di un figura blocco
	 */
	JButton block;
	
	/**
	 * Pulsante che permette l'immissione di un figura barca
	 */
	JButton ship;
	
	/**
	 * Pulsante che permette l'immissione di un figura oscillatore
	 */
	JButton blinker;
	
	/**
	 * Pulsante che permette l'immissione di un figura rospo
	 */
	JButton toad;
	
	/**
	 * Pulsante che permette l'immissione di un figura aliante
	 */
	JButton glider;
	
	/**
	 * Pulsante che permette l'immissione di un figura astronave leggera
	 */
	JButton lightShip;
	
	/**
	 * Pulsante che permette l'immissione di un figura die hard
	 */
	JButton dieHard;
	
	/**
	 * Pulsante che permette l'immissione di un figura ghianda
	 */
	JButton acorn;
	
	/**
	 * Pulsante che permette l'immissione di un figura cannone di Gosper
	 */
	JButton gliderGun;
	
	/**
	 * Pulsante che permette la cancellazione della matrice
	 */
	JButton clear;
	
	/**
	 * Campo che contiene i messaggi di servizio
	 */
	private JTextField message;
	
	/**
	 * Etichetta dello spinner
	 */
	private JLabel spinnerLabel;
	
	/**
	 * Etichetta dello slider
	 */
	private JLabel sliderLabel;
	
	/**
	 * Contatore delle generazioni
	 */
	int gCounter = 0;
	
	/**
	 * Etichetta del contatore di generazioni
	 */
	JLabel genCounter;
	
	/**
	 * Variabile che contiene il nome della figura attualmente selezionata
	 */
	String nameButtFigure;
	
	/**
	 * Matrice che contiene il modello della figura attualmente selezionata
	 */
	int[][] figureModel;
	
	/**
	 * Variabile che permetto di controllare se � selezionata la particella o un'altra figura
	 */
	boolean checkfigure = false;
	
	/**
	 * Slider che regola la velocit� di esecuzione
	 */
	JSlider speed;	
	
	/**
	 * Pannello che contiene la matrice dei pulsanti
	 */
	JPanel playground;		
	
	/**
	 * Pannello che contiene i controlli del Game Of Life, quali i pulsanti per l'esecuzione, e il controllo sui thread
	 */
	JPanel tools;			
	
	/**
	 * Pannello che contiene i widget per l'inserimento delle figure
	 */
	JPanel figures;	
	
	/**
	 * Pannello che contiene il pulsante per la pulizia della matrice e il campo dei messaggi
	 */
	JPanel upperPanel;		
	
	/**
	 * Dimensione X della matrice
	 */
	int dimX;
	
	/**
	 * Dimensione Y della matrice
	 */
	int dimY;
	
	/**
	 * Dimensione della cellula
	 */
	int dimCell = 10;
	
	/**
	 * Massima velocita di esecuzione
	 */
	int maxVelocity = 250; 
	
	/**
	 * Permette di controllare se i thread continuano l'esecuzione
	 */
	boolean condition = false;
	
	/**
	 * Numero di thread
	 */
	int numThread;
	
	
	//----------------------------------------------|
	//		 Variabili per controllo Thread			|
	//----------------------------------------------|
	
	/**
	 * Lista che contiene i thread
	 */
	ArrayList<Executor> arrayThread = new ArrayList<Executor>(); //Conterr� gli esecutori, che sono i Thread	
	
	/**
	 * Thread controllore che crea i thread esecutori
	 */
	Controller controller;
	
	/**
	 * Modello numerico per lo spinner
	 */
	SpinnerNumberModel sm = new SpinnerNumberModel(1,1,40,1);
	
	/**
	 * Spinner che regola il numero di thread attivi
	 */
	JSpinner nThread;

	
	//----------------------------------------------|
	//				Action Listener					|
	//----------------------------------------------|
	
	ActionListener startStep = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			nThread.setEnabled(false);
			start.setEnabled(false);
			stop.setEnabled(true);
			next.setEnabled(false);
			message.setText("In esecuzione");
			condition = true;
			numThread = Integer.parseInt(nThread.getValue().toString());
			controller = new Controller();
			controller.start();
		}
	};
	
	ActionListener stopStep = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			condition = false;
			nThread.setEnabled(true);
			start.setEnabled(true);
			stop.setEnabled(false);
			next.setEnabled(true);
			message.setText("Esecuzione terminata");
		}
	};
	
	ActionListener nextStep = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			genCounter.setText("Gen: " + (gCounter++));
			message.setText("Cambio di generazione eseguito");
			l.step();
			repaintColors();
		}
	};
	
	
	ActionListener clearAll = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			message.setText("Pulizia del campo avvenuta");
			gCounter = 0;
			genCounter.setText("Gen: " + gCounter);
			l.clearLand();	
			repaintColors();
		}
	};
	
	
	ActionListener selectFigure = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cell.setBackground(Color.white);
			block.setBackground(Color.white);
			ship.setBackground(Color.white);
			blinker.setBackground(Color.white);
			toad.setBackground(Color.white);
			glider.setBackground(Color.white);
			lightShip.setBackground(Color.white);
			dieHard.setBackground(Color.white);
			acorn.setBackground(Color.white);
			gliderGun.setBackground(Color.white);
			nameButtFigure = ((JButton)e.getSource()).getName();
			if(nameButtFigure.equals("cell")){
				cell.setBackground(Color.green);
				checkfigure = false;
			}else if(nameButtFigure.equals("block")){
				block.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().blocco();
			}else if(nameButtFigure.equals("ship")){
				ship.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().barca();
			}else if(nameButtFigure.equals("blinker")){
				blinker.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().lampeggiatore();
			}else if(nameButtFigure.equals("toad")){
				toad.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().rospo();
			}else if(nameButtFigure.equals("glider")){
				glider.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().aliante();
			}else if(nameButtFigure.equals("lightShip")){
				lightShip.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().astroleggera();
			}else if(nameButtFigure.equals("dieHard")){
				dieHard.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().diehard();
			}else if(nameButtFigure.equals("acorn")){
				acorn.setBackground(Color.green);
				checkfigure = true;
				figureModel = new Figure().ghianda();
			}else if(nameButtFigure.equals("gliderGun")){
				gliderGun.setBackground(Color.green);
		 		checkfigure = true;
		 		figureModel = new Figure().cannon();
			} 
			
		}
	};
	
	
	//ascoltatore cambio colore
	MouseListener changeStatus = new MouseListener() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int coordinates[] = getCoordinates(e.getSource().hashCode());//vettore con le coordinate del punto in questione
			if(!checkfigure){
				if (e.getModifiers() == 4){	// Al codice 4 corrisponde il click con il tasto destro
					l.permanentKill(coordinates[0],coordinates[1]);
				}
				else{
					l.changeStatus(coordinates[0],coordinates[1]);	
				}
				if(l.landNow[coordinates[0]][coordinates[1]].getStatus() == 1){//se particella viva
					terrain[coordinates[0]][coordinates[1]].setBackground(Color.black);
				}else if(l.landNow[coordinates[0]][coordinates[1]].getStatus() == 0){
					terrain[coordinates[0]][coordinates[1]].setBackground(Color.white);
				}else{
					terrain[coordinates[0]][coordinates[1]].setBackground(Color.red);
				}
				repaint();	
			}else{
				if (e.getModifiers() == 4){	// Al codice 4 corrisponde il click con il tasto destro
					l.permanentKill(coordinates[0],coordinates[1]);
					repaintColors();
				}else{
					if(((coordinates[0] + figureModel[0].length <= l.landNow[0].length) && (coordinates[1] + figureModel.length <= l.landNow.length))){
						message.setText("Inserimento avvenuto con successo");
						for(int i = 0; i < figureModel.length; i++){
							for(int j=0; j < figureModel[0].length; j++){
								if((i>=0 && i < l.landNow.length) && (j>=0 && j < l.landNow[0].length)){
									l.landNow[coordinates[0]+j][coordinates[1]+i].setStatus(figureModel[i][j]);	
								}		
							}	
						}	
						repaintColors();
					}else{
						message.setText("ATTENZIONE: Impossibile inserire la figura");
					}
				}
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	};
		
	
	
	//----------------------------------------------|
	//					 Metodi						|
	//----------------------------------------------|
	
	
	/**
	 * Costruttore della classe grafica con parametri.
	 * @param x Dimensione x della matrice
	 * @param y Dimensione y della matrice
	 */
	public Graphic(int x, int y) {
		setTitle("Game of Life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dimX = x;
		dimY = y;
		clear = new JButton(new ImageIcon("immagini/clear.png"));
		next = new JButton(new ImageIcon("immagini/next.png"));
		start = new JButton(new ImageIcon("immagini/start.png"));
		stop = new JButton(new ImageIcon("immagini/stop.png"));
		
		cell = new JButton(new ImageIcon("immagini/cell.png"));
		cell.setName("cell");
		block  = new JButton(new ImageIcon("immagini/block.png"));
		block.setName("block");
		ship  = new JButton(new ImageIcon("immagini/ship.png"));
		ship.setName("ship");
		blinker = new JButton(new ImageIcon("immagini/blinker.png"));
		blinker.setName("blinker");
		toad = new JButton(new ImageIcon("immagini/toad.png"));
		toad.setName("toad");
		glider = new JButton(new ImageIcon("immagini/glider.png"));
		glider.setName("glider");
		lightShip = new JButton(new ImageIcon("immagini/lightship.png"));
		lightShip.setName("lightShip");
		dieHard = new JButton(new ImageIcon("immagini/diehard.png"));
		dieHard.setName("dieHard");
		acorn = new JButton(new ImageIcon("immagini/acorn.png"));
		acorn.setName("acorn");
		gliderGun = new JButton(new ImageIcon("immagini/glidergun.png"));
		gliderGun.setName("gliderGun");
		
		message = new JTextField("Benvenuto in Game Of Life");
		spinnerLabel = new JLabel("Thread:");
		sliderLabel = new JLabel("Velocità:");
		genCounter = new JLabel("Gen: " + gCounter);
		
		speed = new JSlider(JSlider.VERTICAL,0,maxVelocity,0);
		nThread = new JSpinner(sm);
		l = new Land(dimX,dimY);
		terrain = new JButton[dimX][dimY];	//Inizializzo matrice di bottoni che sar� il mio terreno di gioco.
		
		upperPanel = new JPanel();
		tools = new JPanel();
		playground = new JPanel();
		figures = new JPanel();
		
		upperPanel.setBounds(0, 0, 844, 30);
		tools.setBounds(0, 30, 72, 700);
		playground.setBounds(72, 30, 700, 700);
		figures.setBounds(772, 30, 72, 700);

		
		
		for(int i=0; i < dimX; i++){
			for(int j=0; j < dimY; j++){
				terrain[i][j] = new JButton();
				terrain[i][j].setBackground(Color.white);	
			}
		}
		
		
	}
	
	/**
	 * Inizializzatore della grafica.
	 */
	public void init(){
		getContentPane().setLayout(null);
		upperPanel.setLayout(null);
		tools.setLayout(null);
		playground.setLayout(null);
		figures.setLayout(null);
		
		for(int i=0; i < terrain.length; i++){
			for(int j=0; j < terrain[0].length; j++){
				terrain[i][j].addMouseListener(changeStatus);
				terrain[i][j].setBounds(i*dimCell, j*dimCell, dimCell, dimCell);
				playground.add(terrain[i][j]);
			}
		}
		
		clear.setBounds(2, 2, 68, 26);
		clear.addActionListener(clearAll);
		clear.setBackground(Color.white);
		message.setBounds(72, 0, 700, 30);
		message.setEditable(false);
		message.setFont(new Font("serif", Font.PLAIN, 20));
		message.setHorizontalAlignment(JTextField.CENTER);
		genCounter.setBounds(772, 0, 72, 30);
		upperPanel.add(message);
		upperPanel.add(clear);
		upperPanel.add(genCounter);

		start.addActionListener(startStep);
		start.setBounds(2, 2, 68, 68);
		start.setBorder(null);
		start.setContentAreaFilled(false);
		stop.addActionListener(stopStep);
		stop.setBounds(2, 72, 68, 68);
		stop.setBorder(null);
		stop.setContentAreaFilled(false);
		stop.setEnabled(false);
		next.addActionListener(nextStep);
		next.setBorder(null);
		next.setContentAreaFilled(false);
		next.setBounds(2, 142, 68, 68);
		spinnerLabel.setBounds(2, 212, 68, 30);
		nThread.setBounds(2, 242, 68, 30);
		sliderLabel.setBounds(2, 290, 68, 30);
		speed.setBounds(2, 322, 68, 360);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer,JLabel>();
		labelTable.put(0, new JLabel("Lento"));
		labelTable.put(maxVelocity, new JLabel("Veloce"));
		speed.setLabelTable(labelTable);
		speed.setPaintLabels(true);

		tools.add(next);
		tools.add(start);
		tools.add(stop);
		tools.add(speed);
		tools.add(nThread);
		tools.add(spinnerLabel);
		tools.add(sliderLabel);
		
		cell.setBackground(Color.green);
		block.setBackground(Color.white);
		ship.setBackground(Color.white);
		blinker.setBackground(Color.white);
		toad.setBackground(Color.white);
		glider.setBackground(Color.white);
		lightShip.setBackground(Color.white);
		dieHard.setBackground(Color.white);
		acorn.setBackground(Color.white);
		gliderGun.setBackground(Color.white);
		
		cell.addActionListener(selectFigure);
		cell.setBounds(2, 2, 68, 68);
		block.addActionListener(selectFigure);
		block.setBounds(2, 72, 68, 68);
		ship.addActionListener(selectFigure);
		ship.setBounds(2, 142, 68, 68);
		blinker.addActionListener(selectFigure);
		blinker.setBounds(2, 212, 68, 68);
		toad.addActionListener(selectFigure);
		toad.setBounds(2, 282, 68, 68);
		glider.addActionListener(selectFigure);
		glider.setBounds(2, 352, 68, 68);
		lightShip.addActionListener(selectFigure);
		lightShip.setBounds(2, 422, 68, 68);
		dieHard.addActionListener(selectFigure);
		dieHard.setBounds(2, 492, 68, 68);
		acorn.addActionListener(selectFigure);
		acorn.setBounds(2, 562, 68, 68);
		gliderGun.addActionListener(selectFigure);
		gliderGun.setBounds(2, 632, 68, 68);

		figures.add(cell);
		figures.add(block);
		figures.add(ship);
		figures.add(blinker);
		figures.add(toad);
		figures.add(glider);
		figures.add(lightShip);
		figures.add(dieHard);
		figures.add(acorn);
		figures.add(gliderGun);
		
		getContentPane().add(upperPanel);
		getContentPane().add(playground);
		getContentPane().add(tools);
		getContentPane().add(figures);
		
		setVisible(true);
		setSize(850, 757);
		setResizable(false);

	}
	
	/**
	 * Metodo che viene usato per creare i Thread.
	 */
	public void createThread(){
		arrayThread.clear();
		for(int i = 0; i < numThread; i++){
			arrayThread.add(new Executor(i));
		}
	}
	
	/**
	 * Metodo che mi disegna i colori della matrice in base alle particelle vive, 
	 * morte o morte definitivamente.
	 * Colore nero per le particelle vive
	 * Colore bianco per le particelle morte
	 * Colore rosso per le particelle morte definitivamente
	 */
	public void repaintColors(){
		for(int i=0; i < l.landNow.length; i++){
			for(int j=0; j < l.landNow[0].length; j++){
				if(l.landNow[i][j].getStatus() == 1)
					terrain[i][j].setBackground(Color.black);
				else if(l.landNow[i][j].getStatus() == 0)
					terrain[i][j].setBackground(Color.white);
				else
					terrain[i][j].setBackground(Color.red);
			}
		}
		repaint();
	}
	
	/**
	 * Metodo che mi permette di trovare le coordinate del bottone dal suo hashcode
	 * @param hash Hashcode del bottone
	 * @return Coordinate x e y del bottone.
	 */
	private int[] getCoordinates(int hash){
		int coordinates[] = new int[2];
		
		for(int i=0; i < terrain.length; i++){
			for(int j=0; j < terrain[0].length; j++){
				if(terrain[i][j].hashCode() == hash){
					coordinates[0] = i;
					coordinates[1] = j;
					return coordinates;
				}
			}
		}
		return null;	
	}
	
	
	//----------------------------------------|
	//				CLASSI THREAD			  |
	//----------------------------------------|
	
	
	//Thread Slave: Vengono generati e controllati dalla classe Thread controller
	public class Executor extends Thread{
		
		int index; 			//Posizione nell array
		
		/**
		 * Costruttore della classe Executor, ossia di una sola unit� di elaborazione.
		 * @param index indice del thread all'interno del vettore
		 */
		public Executor(int index) {
			this.index = index;
		}
		
		@Override
		public void run() {		
			l.step(index, numThread); //Lavoro sulla martice						
		}
		
	}
	
	
	//Thread Controller: Classe thread che mi istanza i lavoratori (Executor) e li fa lavorare
	public class Controller extends Thread{
		
		@Override
		public void run() {
			
			while(condition){
				genCounter.setText("Gen: " + (gCounter++));
				createThread(); //Ogni volta ricreo i thread, perch� una volta fatto il cambio di generazione
								//cessano di esistere
				for(Executor exe : arrayThread){	//Faccio partire tutti i thread
					exe.start();
				}
				for(Executor exe : arrayThread){	//Aspetto che finiscano il lavoro
					try {
						exe.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				l.scambia();	//Sostituisco la matrice vecchia con quella nuova
				repaintColors();//Ridisegno i colori sulla matrice che viene visualizzata su schermo
				try {
					sleep((maxVelocity - speed.getValue()) + 50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}
