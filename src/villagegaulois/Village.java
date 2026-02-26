package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	//__________________
	private static class Marche{
		
		private Etal[] etale;
		
		private Marche(int nbr) {
			etale = new Etal[nbr];
		}
		
	    private void utiliserEtal (int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
				etale[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
	    
		private int trouverEtalLibre() {
			   for(int i = 0 ; i < etale.length ; i++) {
				  if( etale[i].isEtalOccupe() ) {
					   return i;
				  }
			   }
				return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int taille = 0;
			for(int i = 0 ; i < etale.length ; i++) {
				  if(etale[i].contientProduit( produit)  ) {
					  taille++;
				  }
			}
			
			Etal[] etal_ex = new Etal[taille];
			int j=0;
			for(int i = 0 ; i < etale.length ; i++) {
				  if(etale[i].contientProduit( produit)  ) {
					  etal_ex[j]= etale[i];
					  j++;
				  }
			}
			return etal_ex;
		}
		
		private Etal trouverVendeur (Gaulois gaulois) {
			for(int i = 0 ; i < etale.length ; i++) {
				if(gaulois.equals( etale[i].getVendeur() ) ) {
					return etale[i];
				} 
		    }
			return null;
     	}
	
		private String afficherMarche() {
			String s = "voila les etals disponibles : \n";
			int j=0;
			for(int i = 0 ; i < etale.length ; i++) {
				if( etale[i].isEtalOccupe() ) {
					s = s + etale[i].afficherEtal()+" \n";
					j++;
				  }
			}
			if(j  == etale.length) {
				return s;
			}else {
				int nbEtaVide = etale.length - j; 
				return s + "il rest "+nbEtaVide+"étals non utilisés dans le marché.\n";
			}
		}
	
	
	
	
	
	
	
	
}