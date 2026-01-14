package cat.almata.dam.amarin;

public class Persona implements Runnable{
	
	private int tempsCoccio;
	private GestorForquilles forquilla;
	private int numPers;
	private int nConsumicio;
	private String tipusFoundie;
	
	public Persona(int tempsCarn, GestorForquilles forquilla,int num,String tipusFoundie) {
		this.tempsCoccio = tempsCarn;
		this.forquilla=forquilla;
		this.numPers=num;
		this.tipusFoundie = tipusFoundie;
		nConsumicio = 0;
	}
	
	@Override
	public void run() {
		while(true){
			forquilla.agafaForquilla(numPers);
			System.out.println("Persona "+numPers+" agafa una forquilla de la foundie de "+tipusFoundie);
			cuina();
			forquilla.tornaForquilla(numPers);
			System.out.println("Persona "+numPers +" torna la forquilla de la foundie de "+tipusFoundie);
			menja();
		}
		
	}
	private void cuina() {
		try {
			Thread.sleep(tempsCoccio*1000);
			if(tipusFoundie.equals("carn"))System.out.println("Persona "+numPers+" ha cuinat la carn i ha tardat "+tempsCoccio+" segons.");
			else System.out.println("Persona "+numPers+" ha sucat al formatge i ha tardat "+tempsCoccio+" segons.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void menja() {
		nConsumicio++;
		try {
			Thread.sleep(2000);
			System.out.println("Persona "+numPers+" de la foundie de "+tipusFoundie+" ha acabat de menjar la consumició nº "+nConsumicio);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
