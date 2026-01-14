
package cat.almata.dam.amarin;

public class GestorForquilles {
	
	private int forquillesTotal;
	private int forquillesOcupades;
	
	public GestorForquilles(int forquillesTotal) {
		this.forquillesTotal=forquillesTotal;
		forquillesOcupades = 0;
	}
	
	public synchronized void agafaForquilla(int numPer) {
			try {
				while(forquillesOcupades>=forquillesTotal)wait();
				forquillesOcupades++;
				notifyAll();
			} catch (InterruptedException e) {
				
			}
	}
	public synchronized void tornaForquilla(int numPer) {
		forquillesOcupades--;
		notifyAll();
}

}
