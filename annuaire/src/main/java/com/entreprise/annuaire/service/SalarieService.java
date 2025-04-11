package com.entreprise.annuaire.service;

import com.entreprise.annuaire.dto.SalarieDTO;
import com.entreprise.annuaire.exception.ResourceAlreadyExistsException;
import com.entreprise.annuaire.exception.ResourceNotFoundException;
import com.entreprise.annuaire.model.Departement;
import com.entreprise.annuaire.model.Salarie;
import com.entreprise.annuaire.model.Site;
import com.entreprise.annuaire.repository.SalarieRepository;
import com.entreprise.annuaire.repository.DepartementRepository;
import com.entreprise.annuaire.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalarieService {

    @Autowired
    private SalarieRepository salarieRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private SiteRepository siteRepository;

    public List<SalarieDTO> getAllSalaries() {
        return salarieRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SalarieDTO getSalarieById(Long id) {
        Salarie salarie = salarieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salarié non trouvé avec l'id: " + id));
        return convertToDTO(salarie);
    }

    public List<SalarieDTO> searchSalaries(String searchTerm) {
        return salarieRepository.searchByNomOrPrenom(searchTerm).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SalarieDTO> getSalariesBySiteId(Long siteId) {
        return salarieRepository.findBySiteId(siteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SalarieDTO> getSalariesByServiceId(Long serviceId) {
        return salarieRepository.findByServiceId(serviceId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SalarieDTO createSalarie(SalarieDTO salarieDTO) {
        if (salarieRepository.existsByEmail(salarieDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Un salarié existe déjà avec l'email: " + salarieDTO.getEmail());
        }

        Salarie salarie = convertToEntity(salarieDTO);
        Salarie savedSalarie = salarieRepository.save(salarie);
        return convertToDTO(savedSalarie);
    }

    public SalarieDTO updateSalarie(Long id, SalarieDTO salarieDTO) {
        Salarie existingSalarie = salarieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salarié non trouvé avec l'id: " + id));

        if (!existingSalarie.getEmail().equals(salarieDTO.getEmail()) &&
                salarieRepository.existsByEmail(salarieDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Un salarié existe déjà avec l'email: " + salarieDTO.getEmail());
        }

        updateSalarieFromDTO(existingSalarie, salarieDTO);
        Salarie updatedSalarie = salarieRepository.save(existingSalarie);
        return convertToDTO(updatedSalarie);
    }

    public void deleteSalarie(Long id) {
        if (!salarieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Salarié non trouvé avec l'id: " + id);
        }
        salarieRepository.deleteById(id);
    }

    private SalarieDTO convertToDTO(Salarie salarie) {
        SalarieDTO dto = new SalarieDTO();
        dto.setId(salarie.getId());
        dto.setNom(salarie.getNom());
        dto.setPrenom(salarie.getPrenom());
        dto.setTelephoneFixe(salarie.getTelephoneFixe());
        dto.setTelephonePortable(salarie.getTelephonePortable());
        dto.setEmail(salarie.getEmail());

        if (salarie.getDepartement() != null) {
            dto.setDepartementId(salarie.getDepartement().getId());
            dto.setDepartementNom(salarie.getDepartement().getNom());
        }

        if (salarie.getSite() != null) {
            dto.setSiteId(salarie.getSite().getId());
            dto.setSiteVille(salarie.getSite().getVille());
        }

        return dto;
    }

    private Salarie convertToEntity(SalarieDTO dto) {
        Salarie salarie = new Salarie();
        salarie.setId(dto.getId());
        salarie.setNom(dto.getNom());
        salarie.setPrenom(dto.getPrenom());
        salarie.setTelephoneFixe(dto.getTelephoneFixe());
        salarie.setTelephonePortable(dto.getTelephonePortable());
        salarie.setEmail(dto.getEmail());

        if (dto.getDepartementId() != null) {
            Departement departement = departementRepository.findById(dto.getDepartementId())
                    .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé avec l'id: " + dto.getDepartementId()));
            salarie.setDepartement(departement);
        }

        if (dto.getSiteId() != null) {
            Site site = siteRepository.findById(dto.getSiteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Site non trouvé avec l'id: " + dto.getSiteId()));
            salarie.setSite(site);
        }

        return salarie;
    }

    private void updateSalarieFromDTO(Salarie salarie, SalarieDTO dto) {
        salarie.setNom(dto.getNom());
        salarie.setPrenom(dto.getPrenom());
        salarie.setTelephoneFixe(dto.getTelephoneFixe());
        salarie.setTelephonePortable(dto.getTelephonePortable());
        salarie.setEmail(dto.getEmail());

        if (dto.getDepartementId() != null) {
            Departement departement = departementRepository.findById(dto.getDepartementId())
                    .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé avec l'id: " + dto.getDepartementId()));
            salarie.setDepartement(departement);
        } else {
            salarie.setDepartement(null);
        }

        if (dto.getSiteId() != null) {
            Site site = siteRepository.findById(dto.getSiteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Site non trouvé avec l'id: " + dto.getSiteId()));
            salarie.setSite(site);
        } else {
            salarie.setSite(null);
        }
    }
}