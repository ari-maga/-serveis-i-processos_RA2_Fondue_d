package cat.almata.dam.amarin;

public class App {

	public static void main(String[] args) {
		/*
		 * Un grup de N amics estem fent una fondue de carn. Però tenim solament M
		 * tenedors (menor que N) per a utilitzar. Per tal caldrà que algú esperi mentre
		 * els altres es fan la carn. Tots nosaltres tardem 2 segons en menjar la carn.
		 * Per fer-la cada persona tarda un temps diferent entre 4 i 7 segons (segons li
		 * agradi més o menys feta). Aquest temps li passarem en el constructor a cada
		 * persona i per tant serà sempre el mateix per aquella persona. Suposem que els
		 * bocins de carn són il·limitats. Ha de quedar clar en els missatge exposats
		 * quan una persona esta cuinant la seva carn, menjant-la o esperant.
		 * a) Modeleu la simulació de la situació exposada.
		 * 
		 * b) Afegiu una segona fondue de formatge
		 * amb P tenedors (menor que el nombre de comensals, i diferent dels altres
		 * tenedors), i feu que a l’atzar una persona empri una o una altra fondue. Si
		 * la que ha triat està ocupada s’esperarà. Canvieu els missatges mostrats per a
		 * indicar la nova situació.
		 * 
		 */
		int numForquillesCarn = 4;
		int numForquillesFormatge = 3;
		int numAmics = 12;
		int tempsCarnPersona;
		Persona[] persones = new Persona[numAmics];
		Thread[] thPersones = new Thread[numAmics];
		GestorForquilles gfFoundieCarn = new GestorForquilles(numForquillesCarn);
		GestorForquilles gfFoundieFormatge = new GestorForquilles(numForquillesFormatge);
		
		
		/* Aquí em dedico a crear les persones i els threads, he de crear numAmics persones i també a cada persona li assigno aleatoriament una foundie,
		 * el seu gertor de forquilles corresponent i també he de definir el temps en que cada persona li agrada la carn feta. Finalment he d'activar cada persona i
		 * posar-ho tot en arrays per poder-ho controlar més endavant.
		 */
		for (int i = 0; i < numAmics; i++) {
			//Genera un nombre aleatori entre 4 i 7 que son els segons que tarda la persona en fer la carn
			tempsCarnPersona = (int) Math.round(4 + Math.random() * 3);
			//Genero aleatoriament un nombre 0 o 1 per decidir a quina foundie va
			if((int) Math.round( Math.random() * 1)==0) {
				//va a la fondie de carn
				System.out.println("Persona "+String.valueOf(i+1)+" va a la fondie de carn");
				persones[i] = new Persona(tempsCarnPersona, gfFoundieCarn, i + 1,"carn");
			}else {
				// va a la fondie de formatge
				System.out.println("Persona "+String.valueOf(i+1)+" va a la fondie de formatge");
				//Assumeixo que el temps de fer el formatge són 2 segons que és sucar i treure
				persones[i] = new Persona(tempsCarnPersona, gfFoundieFormatge, i + 1,"formatge");
			}
			//Poso la persona a l'array de threads i l'inicio.
			thPersones[i] = new Thread(persones[i]);
			thPersones[i].start();
			
		}
		/*
		 * Aquest bucle és per parar als threads de les persones un cop hagin acabat de fer-se el primer tros pero això ara no ens interessa.
		 * Tot i això ho deixo posat.
		for (int i = 0; i < numAmics; i++) {
			try {
				thPersones[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Fi programa");
		*/
	}

}
