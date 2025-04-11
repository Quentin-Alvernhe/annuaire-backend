package com.entreprise.annuaire.repository;

import com.entreprise.annuaire.model.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalarieRepository extends JpaRepository<Salarie, Long> {
    List<Salarie> findByNomContainingIgnoreCase(String nom);

    List<Salarie> findBySiteId(Long siteId);

    List<Salarie> findByServiceId(Long serviceId);

    @Query("SELECT s FROM Salarie s WHERE LOWER(s.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.prenom) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Salarie> searchByNomOrPrenom(@Param("searchTerm") String searchTerm);

    boolean existsByEmail(String email);
}