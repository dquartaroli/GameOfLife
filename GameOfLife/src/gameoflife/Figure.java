/**
 * Classe Figure
 * @author Denis Quartaroli, Federica Carloni, Mirko Cracco
 */
package gameoflife;

public class Figure{
	
	/**
	 * Figura Blocco del Game of Life
	 * @return La matrice che rappresenta lo stato di vita delle particelle nel blocco
	 */
	public int[][] blocco(){
		int[][] result = {{1,1},{1,1}};
		return result;
	}

	/**
	 * Figura lampeggiatore
	 * @return Matrice che rappresenta lo stato di vita delle particelle del lampeggiatore (In Verticale)
	 */
	public int[][] lampeggiatore(){
		int[][] result = {{0,1,0},{0,1,0},{0,1,0}};
		return result;
	}
	
	/**
	 * Figura aliante
	 * @return Matrice che rappresenta lo stato di vita delle particelle dell'aliante
	 */
	public int[][] aliante(){
		int[][] result = {{0,1,0},{0,0,1},{1,1,1}};
		return result;
	}
	
	/**
	 * Figura barca
	 * @return Matrice che rappresenta lo stato di vita delle particelle della barca
	 */
	public int[][] barca(){
		int[][] result = {{1,1,0},{1,0,1},{0,1,0}};
		return result;
	}
	
	/**
	 * Figura Astronave Leggera
	 * @return Matrice che rappresenta lo stato di vita delle particelle dell'astronave leggera
	 */
	public int[][] astroleggera(){
		int[][] result = {{0,1,0,0,1},{1,0,0,0,0},{1,0,0,0,1},{1,1,1,1,0}};
		return result;
	}
	
	/**
	 * Figura rospo
	 * @return Matrice che rappresenta lo stato di vita delle particelle del rospo
	 */
	public int[][] rospo(){
		int[][] result = {{0,1,1,1},{1,1,1,0}};
		return result;
	}
	
	/**
	 * Figura Ghianda
	 * @return Matrice che rappresenta lo stato di vita delle particelle della ghianda
	 */
	public int[][] ghianda(){
		int[][] result = {{0,1,0,0,0,0,0},{0,0,0,1,0,0,0},{1,1,0,0,1,1,1}};
		return result;
	}
	
	/**
	 * Figura Diehard
	 * @return Matrice che rappresenta lo stato di vita delle particelle del Diehard
	 */
	public int[][] diehard(){
		int[][] result = {{0,0,0,0,0,0,1,0},{1,1,0,0,0,0,0,0},{0,1,0,0,0,1,1,1}};
		return result;
	}

	/**
	 * Figura Cannone di alianti di Gosper
	 * @return Matrice che rappresenta lo stato di vita delle particelle del cannone
	 */
	public int[][] cannon(){
		int[][] result = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
						  {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
						  {1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		return result;
	}
	
}
