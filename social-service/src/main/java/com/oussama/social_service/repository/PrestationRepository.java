package com.oussama.social_service.repository;

import com.oussama.social_service.entity.Prestation;
import com.oussama.social_service.enums.PrestationCategory;
import com.oussama.social_service.enums.PrestationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long> {

    // Prestations actives
    List<Prestation> findByIsActiveTrueOrderByDisplayOrderAsc();
    Page<Prestation> findByIsActiveTrueOrderByDisplayOrderAsc(Pageable pageable);

    // Recherche par catégorie
    List<Prestation> findByCategoryAndIsActiveTrueOrderByDisplayOrderAsc(PrestationCategory category);
    Page<Prestation> findByCategoryAndIsActiveTrueOrderByDisplayOrderAsc(PrestationCategory category, Pageable pageable);

    // Recherche par type
    List<Prestation> findByPrestationTypeAndIsActiveTrueOrderByDisplayOrderAsc(PrestationType prestationType);
    Page<Prestation> findByPrestationTypeAndIsActiveTrueOrderByDisplayOrderAsc(PrestationType prestationType, Pageable pageable);

    // Recherche combinée catégorie + type
    Page<Prestation> findByCategoryAndPrestationTypeAndIsActiveTrueOrderByDisplayOrderAsc(
            PrestationCategory category, PrestationType prestationType, Pageable pageable);

    // Recherche textuelle
    @Query("SELECT p FROM Prestation p WHERE p.isActive = true AND " +
            "(LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.shortDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY p.displayOrder ASC")
    Page<Prestation> searchActiveByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // Toutes les prestations (admin)
    Page<Prestation> findAllByOrderByDisplayOrderAsc(Pageable pageable);

    // Prestations les plus demandées
    @Query("SELECT p FROM Prestation p LEFT JOIN Demande d ON p.id = d.prestationId " +
            "WHERE p.isActive = true " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(d.id) DESC")
    List<Prestation> findMostRequestedPrestations(Pageable pageable);

    // Ordre d'affichage maximum pour réorganisation
    @Query("SELECT MAX(p.displayOrder) FROM Prestation p")
    Optional<Integer> findMaxDisplayOrder();

    // Prestations par critères d'éligibilité
    @Query("SELECT p FROM Prestation p WHERE p.isActive = true AND " +
            "p.eligibilityCriteria IS NOT NULL AND " +
            "p.eligibilityCriteria != '' " +
            "ORDER BY p.displayOrder ASC")
    List<Prestation> findPrestationsWithEligibilityCriteria();

    // Prestations nécessitant des documents
    List<Prestation> findByRequiresDocumentsTrueAndIsActiveTrueOrderByDisplayOrderAsc();

    // Compter les prestations par catégorie
    @Query("SELECT p.category, COUNT(p) FROM Prestation p WHERE p.isActive = true GROUP BY p.category")
    List<Object[]> countPrestationsByCategory();

    // Compter les prestations par type
    @Query("SELECT p.prestationType, COUNT(p) FROM Prestation p WHERE p.isActive = true GROUP BY p.prestationType")
    List<Object[]> countPrestationsByType();
}

