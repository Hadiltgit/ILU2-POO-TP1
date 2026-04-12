package histoire;
import personnages.Gaulois;
import villagegaulois.Etal;
public class ScenarioCasDegrade {

	public static void main(String[] args) {
		
		/*Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test");*/
		
		
		
		Etal etal = new Etal();
		Gaulois vendeur = new Gaulois("GOU", 5) ;
		etal.occuperEtal(vendeur,"fleur", 10);
		etal.acheterProduit(10, null);
		System.out.println("Fin du test");
		
		
		
		
		/*Etal etal = new Etal();
		Gaulois vendeur = new Gaulois("VEN", 5) ;
		Gaulois acheteur = new Gaulois("ACH", 5) ;
		etal.occuperEtal(vendeur,"fleur", 10);
		try {
		   etal.acheterProduit(-10, acheteur);
		}catch( IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");*/

		
		/*Etal etal = new Etal();
		Gaulois acheteur = new Gaulois("ACH", 5) ;
		try {
		   etal.acheterProduit(10, acheteur);
		}catch( IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");*/

		
   }

}
