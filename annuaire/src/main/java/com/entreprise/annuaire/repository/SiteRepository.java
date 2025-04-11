package com.entreprise.annuaire.repository;

import com.entreprise.annuaire.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    boolean existsByVille(String ville);
}