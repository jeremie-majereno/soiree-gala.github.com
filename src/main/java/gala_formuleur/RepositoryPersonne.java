package gala_formuleur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryPersonne extends JpaRepository<Personne, String> {
    Optional<Personne> findByNom(String nom);

    boolean existsByNom(String nom);
}
