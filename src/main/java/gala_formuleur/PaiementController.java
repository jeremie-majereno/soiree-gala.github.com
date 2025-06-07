package gala_formuleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PaiementController {

    @Autowired
    private PersonneService personneService;

    // Page d'accueil
    @GetMapping("/")
    public String accueil() {
        return "index";
    }

    // Page d'ajout
    @GetMapping("/ajouter")
    public String afficherAjouter() {
        return "ajouter";
    }

    // Traitement de l'ajout
    @PostMapping("/ajouter")
    public String ajouterPersonne(
            @RequestParam String motDePasse,
            @RequestParam String nom,
            @RequestParam String paiement,
            Model model) {

        if (!personneService.verifierMotDePasse(motDePasse)) {
            model.addAttribute("erreurAjout", "Mot de passe incorrect");
            return "confirmation_ajout";
        }

        boolean aPaye = "oui".equals(paiement);
        personneService.ajouterPersonne(nom, aPaye);
        model.addAttribute("succesAjout", "Personne ajoutée avec succès: " + nom);
        return "confirmation_ajout";
    }

    // Page de modification
    @GetMapping("/modifier")
    public String afficherModifier() {
        return "modification";
    }

    // Traitement de la modification
    @PostMapping("/modifier")
    public String modifierStatutPaiement(
            @RequestParam String motDePasseModif,
            @RequestParam String nomModif,
            @RequestParam String nouveauStatut,
            Model model) {

        if (!personneService.verifierMotDePasse(motDePasseModif)) {
            model.addAttribute("erreurModif", "Mot de passe incorrect");
            return "confirmation_modification";
        }

        boolean statut = "oui".equals(nouveauStatut);
        boolean modificationReussie = personneService.modifierStatutPaiement(nomModif, statut);

        if (modificationReussie) {
            model.addAttribute("succesModif", "Statut de paiement modifié avec succès pour: " + nomModif);
        } else {
            model.addAttribute("erreurModif", "Personne non trouvée: " + nomModif);
        }

        return "confirmation_modification";
    }

    // Page de vérification
    @GetMapping("/verifier")
    public String afficherVerifier() {
        return "verification1";
    }

    // Traitement de la vérification
    @PostMapping("/verifier")
    public String verifierPaiement(
            @RequestParam String nomVerif,
            Model model) {

        Optional<Boolean> resultat = personneService.verifierPaiement(nomVerif);

        if (resultat.isPresent()) {
            String statut = resultat.get() ? "Oui" : "Non";
            model.addAttribute("resultatVerif", nomVerif + " a payé: " + statut);
        } else {
            model.addAttribute("erreurVerif", "Personne non trouvée: " + nomVerif);
        }

        return "verification1";
    }
}