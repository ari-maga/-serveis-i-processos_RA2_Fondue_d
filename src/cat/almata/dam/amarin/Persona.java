package cat.almata.dam.amarin;

public class Persona implements Runnable{
	
	private int tempsCoccio;
	private GestorForquilles forquillaEnUs;
	private GestorForquilles forquillaCarn=null;
	private GestorForquilles forquillaFormatge=null;
	private int numPers;
	private int nConsumicio;
	private String tipusFoundie;
	private boolean vaAlesDos=false;
	
	public Persona(int tempsCarn, GestorForquilles forquilla,int num,String tipusFoundie) {
		//aquest constructor s'usa només quan es va a una sola foundie.
		this.tempsCoccio = tempsCarn;
		this.forquillaEnUs=forquilla;
		this.numPers=num;
		this.tipusFoundie = tipusFoundie;
		nConsumicio = 0;
		vaAlesDos=false;
	}
	public Persona(int tempsCarn, GestorForquilles forquillaCarn,GestorForquilles forquillaFormatge, int num) {
		//Aquest constructor s'usa només quan es van a les dos foundies
		this.tempsCoccio = tempsCarn;
		this.forquillaCarn=forquillaCarn;
		this.numPers=num;
		this.forquillaFormatge = forquillaFormatge;
		nConsumicio = 0;
		vaAlesDos = true;
	}
	
	@Override
	public void run() {
		while(true){
			//Primer he de fer que les persones que van a les dos foundies triin una on anar
			//La manera com afronto el fet de on va cada persona serà que cada vegada triaran una foundie aleatoria on anar
			if(vaAlesDos)triaFoundie();
			forquillaEnUs.agafaForquilla(numPers);
			System.out.println("Persona "+numPers+" agafa una forquilla de la foundie de "+tipusFoundie);
			cuina();
			forquillaEnUs.tornaForquilla(numPers);
			System.out.println("Persona "+numPers +" torna la forquilla de la foundie de "+tipusFoundie);
			menja();
		}
	}
	private void triaFoundie() {
		//En aquest metode la s'assignarà aleatoriament una foundie a cada persona que vulgui anar a les dos
		//Genero aleatoriament un nombre 0 o 1 per decidir a quina foundie va
		if((int) Math.round( Math.random() * 1)==0) {
			//va a la fondie de carn
			tipusFoundie= "carn";
			forquillaEnUs=forquillaCarn;
			
		}else {
			// va a la fondie de formatge
			tipusFoundie="formatge";
			forquillaEnUs=forquillaFormatge;
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
