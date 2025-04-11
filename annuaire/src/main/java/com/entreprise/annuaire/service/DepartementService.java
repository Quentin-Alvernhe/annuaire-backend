package com.entreprise.annuaire.service;

import com.entreprise.annuaire.dto.DepartementDTO;
import com.entreprise.annuaire.exception.ResourceAlreadyExistsException;
import com.entreprise.annuaire.exception.ResourceNotFoundException;
import com.entreprise.annuaire.model.Departement;
import com.entreprise.annuaire.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<DepartementDTO> getAllDepartements() {
        return departementRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DepartementDTO getDepartementById(Long id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departement non trouvé avec l'id: " + id));
        return convertToDTO(departement);
    }

    public DepartementDTO createDepartement(DepartementDTO departementDTO) {
        if (departementRepository.existsByNom(departementDTO.getNom())) {
            throw new ResourceAlreadyExistsException("Un departement existe déjà avec le nom: " + departementDTO.getNom());
        }

        Departement departement = convertToEntity(departementDTO);
        Departement savedDepartement = departementRepository.save(departement);
        return convertToDTO(savedDepartement);
    }

    public DepartementDTO updateDepartement(Long id, DepartementDTO departementDTO) {
        Departement existingDepartement = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departement non trouvé avec l'id: " + id));

        if (!existingDepartement.getNom().equals(departementDTO.getNom()) &&
                departementRepository.existsByNom(departementDTO.getNom())) {
            throw new ResourceAlreadyExistsException("Un departement existe déjà avec le nom: " + departementDTO.getNom());
        }

        existingDepartement.setNom(departementDTO.getNom());
        Departement updatedDepartement = departementRepository.save(existingDepartement);
        return convertToDTO(updatedDepartement);
    }

    public void deleteDepartement(Long id) {
        if (!departementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Departement non trouvé avec l'id: " + id);
        }
        departementRepository.deleteById(id);
    }

    private DepartementDTO convertToDTO(Departement departement) {
        return new DepartementDTO(departement.getId(), departement.getNom());
    }

    private Departement convertToEntity(DepartementDTO departementDTO) {
        Departement departement = new Departement();
        departement.setId(departementDTO.getId());
        departement.setNom(departementDTO.getNom());
        return departement;
    }
}