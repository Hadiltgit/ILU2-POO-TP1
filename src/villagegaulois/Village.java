package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private int nbrE;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbrE) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nbrE = nbrE;
		marche = new Marche(nbrE);

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

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) {
			throw new VillageSansChefException("Le village n'a pas de chef !");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef ");
			chaine.append(chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	// __________________

	private static class Marche {

		private Etal[] etale;

		private Marche(int nbrE) {
			etale = new Etal[nbrE];
			for (int i = 0; i < nbrE; i++) {
				etale[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal >= 0 && indiceEtal < etale.length) {
				if (etale[indiceEtal].isEtalOccupe()) {
					System.out.println("Desole , etale est deja ocupe");
				} else {
					etale[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
				}
			} else {
				System.out.println("Desole , etale est non trouve");
			}
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etale.length; i++) {
				if (!etale[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int taille = 0;
			for (int i = 0; i < etale.length; i++) {
				if (etale[i].contientProduit(produit)) {
					taille++;
				}
			}

			Etal[] etal_ex = new Etal[taille];

			for (int i = 0, j = 0; i < etale.length; i++) {
				if (etale[i].contientProduit(produit)) {
					etal_ex[j] = etale[i];
					j++;
				}
			}
			return etal_ex;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			String nomG = gaulois.getNom();
			for (int i = 0; i < etale.length; i++) {
				String nomV = etale[i].getVendeur().getNom();
				if (nomG.equals(nomV)) {
					return etale[i];
				}
			}
			return null;
		}

		private String afficherMarche() {
			StringBuilder s = new StringBuilder();
			int j = 0;
			for (int i = 0; i < etale.length; i++) {
				if (etale[i].isEtalOccupe()) {
					s.append(etale[i].afficherEtal() + " \n");
					j++;
				}
			}
			if (j == etale.length) {
				return s.toString();
			} else {
				int nbEtaVide = etale.length - j;
				s.append("il rest " + nbEtaVide + " étals non utilisés dans le marché.\n");
				return s.toString();
			}
		}
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder s = new StringBuilder(
				vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		int indiceEtal = marche.trouverEtalLibre();
		if (indiceEtal != -1) {
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			int indice = indiceEtal + 1;
			s.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + indice + ".\n");
		}
		return s.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder s = new StringBuilder();
		Etal[] etalVendeur = marche.trouverEtals(produit);
		if (etalVendeur.length == 0) {
			s.append("Il n'y a pas de vendeur qui propose des " + produit + "s au marché.\n");
		} else {
			if (etalVendeur.length == 1) {
				s.append("Seul le vendeur " + etalVendeur[0].getVendeur().getNom() + " propose des " + produit
						+ " au marché\n");
			} else {
				s.append("Les vendeurs qui proposent des " + produit + "s sont : \n");
				for (int i = 0; i < etalVendeur.length; i++) {
					s.append(etalVendeur[i].getVendeur().getNom() + "\n");
				}
			}
		}
		return s.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		int l = marche.etale.length;
		for (int i = 0; i < l; i++) {
			if (vendeur.getNom().equals(marche.etale[i].getVendeur().getNom())) {
				return marche.etale[i];
			}
		}
		return null;
	}

	public String partirVendeur(Gaulois vendeur) {
		Etal j = marche.trouverVendeur(vendeur);
		if (j != null) {
			return j.libererEtal() + " qu'il voulait vendre.";
		}
		return "Ce vendeur n'a pas d'étal.\n";
	}

	public String afficherMarche() {
		StringBuilder s = new StringBuilder("Le marché du village " + nom + " possède plusieurs étals :\r");
		s.append("\r" + marche.afficherMarche());
		return s.toString();
	}
}