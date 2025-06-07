package gala_formuleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class PersonneService {

    private static final String MOT_DE_PASSE = "12345678";

    @Autowired
    private RepositoryPersonne personneRepository;

    public boolean verifierMotDePasse(String motDePasse) {
        return MOT_DE_PASSE.equals(motDePasse);
    }

    public boolean ajouterPersonne(String nom, boolean aPaye) {
        if (personneRepository.existsByNom(nom)) {
            return false; // Personne existe déjà
        }
        personneRepository.save(new Personne(nom, aPaye));
        return true;
    }

    public boolean modifierStatutPaiement(String nom, boolean nouveauStatut) {
        Optional<Personne> personneOpt = personneRepository.findByNom(nom);
        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            personne.setaPaye(nouveauStatut);
            personneRepository.save(personne);
            return true;
        }
        return false;
    }

    public Optional<Boolean> verifierPaiement(String nom) {
        return personneRepository.findByNom(nom).map(Personne::getAPaye); // Utiliser getAPaye()
    }
}