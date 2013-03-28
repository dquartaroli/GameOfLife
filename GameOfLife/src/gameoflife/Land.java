package gameoflife;

/**
 * Classe Land
 * @author Denis Quartaroli, Federica Carloni, Mirko Cracco
 */

public class Land{
	
	/**
	 * Matrice di cellule che rappresenta la situazione attuale
	 */
	public Cell[][] landNow;
	
	/**
	 * Matrice di cellule che rappresenta la situazione nella generazione successiva
	 */
	public Cell[][] landNext;
	
	
	
	/**
	 * Costruttore della classe Land con parametri
	 * @param dimX dimensione x della matrice (Quindi numero di colonne)
	 * @param dimY dimensione y della matrice (Quindi numero di righe)
	 */
	public Land(int dimX, int dimY) {
		landNow = new Cell[dimX][dimY];
		landNext = new Cell[dimX][dimY];
		for(int i=0; i < this.landNow.length; i++){
			for(int j=0; j < this.landNow[i].length; j++){
				this.landNow[i][j] = new Cell(i, j, 0);
				this.landNext[i][j] = new Cell(i, j, 0);
			}
		}
	}
	
	
	/**
	 * Metodo che cambia lo stato della particella da viva a morta e viceversa
	 * @param x coordinata x, che rappresenta la riga, della matrice
	 * @param y coordinata y, che rappresenta la colonna, della matrice
	 */
	public void changeStatus(int x, int y){
		if(landNow[x][y].getStatus() == 1)
			landNow[x][y].setStatus(0);
		else if(landNow[x][y].getStatus() == 0)
			landNow[x][y].setStatus(1);
	}

	
	/**
	 * Uccide definitivamente una particella.
	 * @param x coordinata x, che rappresenta la riga, della matrice
	 * @param y coordinata y, che rappresenta la colonna, della matrice
	 */
	public void permanentKill(int x, int y){
		if(landNow[x][y].getStatus() == 0 || landNow[x][y].getStatus() == 1)	//Nel caso in cui la particella sia morta 
			landNow[x][y].setStatus(2);									//oppure viva, la uccidiamo definitivamente
		else if(landNow[x][y].getStatus() == 2)
			landNow[x][y].setStatus(0);	//Nel caso in cui  la particella sia morta definitivamnte la portiamo  ad essere morta
	} 
	
	
	/**
	 * Metodo step che vale per un thread solo.
	 * Effettua il cambio di generazione della matrice
	 */
	public void step(){
		for(int i=0; i < this.landNow.length; i++){
			for(int j=0; j < this.landNow[0].length; j++){
				landNext[i][j] = new Cell(i, j, 0);
			}
		}
		
		for(int i=0; i < landNext.length; i++){
			for(int j=0; j < landNext[0].length; j++){
				if (landNow[i][j].getStatus() == 2 )
					landNext[i][j].setStatus(2);
				else{
					if(hasNeighbors(i,j)){
						landNext[i][j].setStatus(1);
					}else{
						landNext[i][j].setStatus(0);
					}
				}
			}
		}
		scambia();
		
	}
	
	
	
	/**
	 * Metodo step che vale per pi� thread.
	 * Effettua il cambio di generazione della matrice
	 */
	public void step(int index, int offset){
		
		//Matrice che conterr� il prossimo passaggio.  					//i righe j colonne
		for (int i = 0; i < landNow.length;){
			for (int j = 0  + index; j < landNow[0].length;){
				
				if (landNow[i][j].getStatus() == 2 )
					landNext[i][j].setStatus(2);
				else{
					if(hasNeighbors(i,j)){
						landNext[i][j].setStatus(1);
					}else{
						landNext[i][j].setStatus(0);
					}
				}
				
				if((j + offset) >= landNow[0].length){
					i++;
					if(i == landNow.length){
						break;
					}
					j = offset - (landNow[0].length - j);	// calcolo del punto di partenza
																	// dopo il cambio di riga
				}
				else{
					j += offset;
				}
			}
		}
	}
	
	/**
	 * Sostituisce la matrice corrente con quella del passo successivo
	 */
	public void scambia(){
		for(int i = 0; i<landNow.length; i++){
			for(int j=0; j<landNow[0].length; j++){
				landNow[i][j] = landNext[i][j];
			}
			
		}
		for(int i=0; i < this.landNow.length; i++){
			for(int j=0; j < this.landNow[0].length; j++){
				landNext[i][j] = new Cell(i, j, 0);
			}
		}
	}
	
	
	/**
	 * Controlla se il numero di vicini della cellula corrente � 
	 * sufficiente alla cellula per sopravvivere.
	 * @param landNow Matrice delle cellule
	 * @param x coordinata x, che rappresenta la riga, della matrice
	 * @param y coordinata y, che rappresenta la colonna, della matrice
	 * @return True se ha il numero di vicini necessario, False altrimenti
	 */
	public boolean hasNeighbors(int x, int y){
		int nNeighbor = howManyNeighbor(x, y);
		
		//Guardo se � viva o morta la particella
		if(landNow[x][y].getStatus() == 1){		//Se � viva
			return (nNeighbor == 2 || nNeighbor == 3)?true:false;
		}else if(landNow[x][y].getStatus() == 0){	//Morta
			return (nNeighbor == 3)?true:false;
		}else{									//Morta definitivamente
			return false;
		}
	}

	
	/**
	 * Metodo che ritorna il numero di cellule vive adiacenti alla cellula
	 * corrente.
	 * @param landNow Matrice delle cellule
	 * @param x coordinata x, che rappresenta la riga, della matrice
	 * @param y coordinata y, che rappresenta la colonna, della matrice
	 * @return Numero delle cellule vive adiacenti alla cellula corrente
	 */
	private int howManyNeighbor(int x, int y){
		int counter = 0;
		
		for(int i=x-1; i <= x+1; i++){
			for(int j=y-1; j <= y+1; j++){
				
				if(!(x == i && y == j)){
					if((i>=0 && i < landNow.length) && (j>=0 && j < landNow[0].length)){
						if(landNow[i][j].getStatus() == 1)
							counter++;
					}
				}
			}
		}
		return counter;
	}
	
	/**
	 * Metodo che pulisce le matrici.
	 */
	public void clearLand(){
		for(int i=0; i < this.landNow.length; i++){
			for(int j=0; j < this.landNow[i].length; j++){
				this.landNow[i][j].setStatus(0);
				this.landNext[i][j].setStatus(0);
			}
		}
	}
	
	
}