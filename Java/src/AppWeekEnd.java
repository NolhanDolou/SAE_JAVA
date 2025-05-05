import java.util.ArrayList;
import java.util.List;

class AppWeekEnd {

    WeekEnd we;
    boolean quitter;
	private Personne personne_selectionnee;

    AppWeekEnd(WeekEnd we) {
	this.we = we;
	this.quitter = false;
	this.personne_selectionnee = null;
    }

	public static void restConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'effacement du terminal.");
        }
    }

    public void run() {
	bienvenue();
	boolean continuer = true;
	while(!quitter) {
	    menu();
	}
	au_revoir();
    }

    public void menu() {
	boolean commande_faite = false;
	while(!commande_faite) {
	    List<String> p = new ArrayList<>();
		p.add(" Q: quitter              ");
		p.add(" D: Menu dépenses        ");
		p.add(" P: Menu personnes       ");
		p.add(" T: Total dépenses       ");
		p.add(" M: Moyenne des dépenses ");
		menuAffiche("Menu principal          " , p);
	    String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
	    if(commande.equals("q")) {
		quitter = true;
		commande_faite = true;
	    } 

		else if(commande.equals("p")){
			menu_personne();
		}

		else if(commande.equals("d")){
			System.out.println(we.getDepenses());
		}

		else if(commande.equals("t")){
			System.out.println(we.totalDepenses());
		}

		else if(commande.equals("m")){
			System.out.println(we.depensesMoyenne());
		}

		else {
		System.out.println("Commande '" + commande_brute + "' invalide.");
	    }
	}
    }

	public void menu_personne(){
		restConsole();
		List<String> amis = new ArrayList<>();
		amis.add(" L: liste                ");
		amis.add(" S: sélection            ");
		amis.add(" Q: quitter              ");
		menuAffiche("Menu amis               " , amis);
		String amie_brute = System.console().readLine();
		String amie = amie_brute.strip().toLowerCase();
	
		if(amie.equals("l")){
			System.out.println(we.getAmis());
		} 
		else if(amie.equals("s")){
			selection_de_personne();
		}
		else if (amie.equals("+")) {
			ajouter_personne();
		}
	}

	public void menu_personne_selectionner(){
		restConsole();
		List<String> per_select = new ArrayList<>();
		per_select.add(" T: Total des dépenses   ");
		per_select.add(" A: Avoir                ");
		per_select.add(" Q: quitter              ");
		menuAffiche("personne selectionnée   " , per_select);
		String select_brute = System.console().readLine();
	    String select = select_brute.strip().toLowerCase();
		if(select.equals("t")){
			System.out.println(we.totalDepensesPersonne(personne_selectionnee));
		}
		if(select.equals("a")){
			System.out.println(we.avoirPersonne(personne_selectionnee));
		}
	}

	public void ajouter_personne(){
    	try {
    	    System.out.println("Entrez le prénom de la nouvelle personne : ");
    	    String prenom = System.console().readLine().strip();
    	    System.out.println("Entrez le nom de la nouvelle personne : ");
    	    String nom = System.console().readLine().strip();
    	    if (prenom.isEmpty() || nom.isEmpty()) {
    	        throw new IllegalArgumentException("Le prénom et le nom ne peuvent pas être vides.");
    	    }
    	    Personne nouvellePersonne = new Personne(nom, prenom);
    	    we.ajouterPersonne(nouvellePersonne);
    	    System.out.println(nouvellePersonne + " a été ajouté(e) au week-end !");
    	} catch (IllegalArgumentException e) {
    	    System.out.println("Erreur : " + e.getMessage());
    	} catch (Exception e) { 
    	    System.out.println("Une erreur inattendue est survenue.");
    	}
    	menu_personne();
	}

	public void selection_de_personne(){
		try {
			System.out.println("Sélectionner la personne numéro combien?");
			String personne_brute = System.console().readLine();
			int personne = Integer.parseInt(personne_brute); 
			if(personne < 0 || personne >= we.getAmis().size()) {
				System.out.println("Erreur : Veuillez entrer un nombre valide entre 0 et " + (we.getAmis().size() - 1));
				menu_personne();
				return;
			}
			this.personne_selectionnee = we.getAmis().get(personne);
			System.out.println("Vous avez sélectionné " + this.personne_selectionnee);
			menu_personne_selectionner();

		} 
		catch (NumberFormatException e) {
			System.out.println("Erreur : Veuillez entrer un nombre valide." + e);
			menu_personne(); 
		} 
		catch(IndexOutOfBoundsException e) {
			System.out.println("Erreur : Le numéro sélectionné est hors limites." + e);
			menu_personne(); 
		}
	}



    /// Affiche un message de bienvenue
    public void bienvenue() {
	restConsole();
	System.out.println("╭───────────────────────────────────────────────────────────────────────╮");
	System.out.println("│ Bienvenue dans notre site pour le groupe de librairie Livre Express ! │");
	System.out.println("╰───────────────────────────────────────────────────────────────────────╯");
    }

	public void menuAffiche(String titre, List<String> option){
		System.out.println("╭──────────────────────────╮");
		System.out.println("│ " + titre +    " │");
		System.out.println("╰──────────────────────────╯");
		for(String op : option){
			System.out.println("│ " + op + "│");
		}
		System.out.println("╰──────────────────────────╯");
	}

    /// Affiche un message d'au revoir
    public void au_revoir() {
		restConsole();
		System.out.println("╭───────────╮");
		System.out.println("│ Au revoir │");
		System.out.println("╰───────────╯");
    }

}
