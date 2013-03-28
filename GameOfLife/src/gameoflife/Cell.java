package gameoflife;

/**
 * Classe Cell
 * @author Denis Quartaroli, Federica Carloni, Mirko Cracco
 */
public class Cell {
	
	/**
	 * Posizione sulla riga della cellula nella matrice
	 */
	int riga;
	
	/**
	 * Posizione sulla colonna della cellula nella matrice
	 */
	int colonna;
	
	/**
	 * Stato di vita della particella: 0 = Morto; 1 = Vivo; 2 = Definitivamente Morto;
	 */
	int status;
	
	/**
	 * Metodo costruttore per una cellula del Game Of Life.
	 * @param riga  Riga della cellula
	 * @param colonna Colonna della cellula
	 * @param status Indicatore dello stato della cellula (0 morta; 1 viva; 2 morta definitivamente)
	 */
	public Cell(int riga, int colonna, int status) {
		this.riga = riga;
		this.colonna = colonna;
		this.status = status;
	}
	
	/**
	 * Restituisce la riga della cellula.
	 * @return Riga della cellula
	 */
	public int getRiga() {
		return riga;
	}
	
	/**
	 * Restituisce la colonna della cellula.
	 * @return Colonna della cellula
	 */
	public int getColonna() {
		return colonna;
	}
	 /**
	  * Restituisce lo stato della cellula.
	  * @return Stato della cellula
	  */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Imposta una nuova riga per la cellula.
	 * @param riga Nuova riga della cellula
	 */
	public void setRiga(int riga) {
		this.riga = riga;
	}
	
	/**
	 * Imposta una nuova colonna per la cellula.
	 * @param colonna Nuova colonna della cellula
	 */
	public void setColonna(int colonna) {
		this.colonna = colonna;
	}
	
	/**
	 * Imposta lo stato della cellula.
	 * @param status Nuovo stato della cellula
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
