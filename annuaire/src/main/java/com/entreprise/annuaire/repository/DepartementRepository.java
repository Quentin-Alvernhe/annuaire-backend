package com.entreprise.annuaire.repository;

import com.entreprise.annuaire.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
    boolean existsByNom(String nom);
}