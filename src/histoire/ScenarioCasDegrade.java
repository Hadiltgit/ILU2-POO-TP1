package histoire;
import personnages.Gaulois;
import villagegaulois.Etal;
public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		try {
			etal.libererEtal();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin du test");
		
		Gaulois obelix = new Gaulois("Obélix", 25);
		try {
		 System.out.println(etal.acheterProduit(1, obelix)  );
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		
   }

}
