public class Executable {
    public static void main(String[] args) {
        System.out.println("------------");
        //on créer des auteurs
        Auteur auteur1 = new Auteur("Hugo", "Victor");
        Auteur auteur2 = new Auteur("Dumas", "Alexandre");
        System.out.println("Il y a " + auteur1.getNom() + " " + auteur1.getPrenom() + " et " + auteur2.getNom() + " " + auteur2.getPrenom());
        System.out.println("------------");
        //on créer des éditeurs
        Editeur editeur1 = new Editeur("Gallimard");
        Editeur editeur2 = new Editeur("Folio");
        System.out.println("Les éditeurs sont : " + editeur1.getNom() + " et " + editeur2.getNom());
        System.out.println("------------");
        //on créer des livres
        Livre livre1 = new Livre("Les Misérables", auteur1, 300, 12.99, editeur1);
        Livre livre2 = new Livre("Les Trois Mousquetaires", auteur2, 1844, 10.99, editeur2);
        System.out.println("Les livres sont : " + livre1.getTitre() + " et " + livre2.getTitre());
        System.out.println("Le livre " + livre1.getTitre() + " a été écrit par " + livre1.getAuteur().getNom() + " " + livre1.getAuteur().getPrenom() + " et publié par " + livre1.getEditeur().getNom());
        System.out.println("Le livre " + livre2.getTitre() + " a été écrit par " + livre2.getAuteur().getNom() + " " + livre2.getAuteur().getPrenom() + " et publié par " + livre2.getEditeur().getNom());
        System.out.println("------------");
        //on créer un magasin
        Magasin magasin = new Magasin("Librairie de Paris", "Paris");
        System.out.println("Le magasin est : " + magasin.getNomMag() + " et il est situé à " + magasin.getVilleMag());
        System.out.println("------------");
        //on ajoute les livres au magasin
        magasin.ajouterLivre(livre1);
        magasin.ajouterLivre(livre2);
        //on affiche les livres du magasin
        System.out.println("Livres disponibles dans le magasin :"); 
        for (Livre livre : magasin.getLivres()) {
            System.out.println(livre.getTitre() + " de " + livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom());
        }
        /*
        // on crée des clients
        Client client1 = new Client("Dupont", "Jean");
        Client client2 = new Client("Martin", "Marie");
        Client client3 = new Client("Durand", "Pierre");
        // on crée des commandes
        Commande commande1 = new Commande(client1);
        Commande commande2 = new Commande(client2);
        Commande commande3 = new Commande(client3);
        */
    }
}