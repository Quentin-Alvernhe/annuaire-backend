package com.entreprise.annuaire.service;

import com.entreprise.annuaire.dto.SiteDTO;
import com.entreprise.annuaire.exception.ResourceAlreadyExistsException;
import com.entreprise.annuaire.exception.ResourceNotFoundException;
import com.entreprise.annuaire.model.Site;
import com.entreprise.annuaire.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public List<SiteDTO> getAllSites() {
        return siteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SiteDTO getSiteById(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site non trouvé avec l'id: " + id));
        return convertToDTO(site);
    }

    public SiteDTO createSite(SiteDTO siteDTO) {
        if (siteRepository.existsByVille(siteDTO.getVille())) {
            throw new ResourceAlreadyExistsException("Un site existe déjà avec la ville: " + siteDTO.getVille());
        }

        Site site = convertToEntity(siteDTO);
        Site savedSite = siteRepository.save(site);
        return convertToDTO(savedSite);
    }

    public SiteDTO updateSite(Long id, SiteDTO siteDTO) {
        Site existingSite = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site non trouvé avec l'id: " + id));

        if (!existingSite.getVille().equals(siteDTO.getVille()) &&
                siteRepository.existsByVille(siteDTO.getVille())) {
            throw new ResourceAlreadyExistsException("Un site existe déjà avec la ville: " + siteDTO.getVille());
        }

        existingSite.setVille(siteDTO.getVille());
        Site updatedSite = siteRepository.save(existingSite);
        return convertToDTO(updatedSite);
    }

    public void deleteSite(Long id) {
        if (!siteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Site non trouvé avec l'id: " + id);
        }
        siteRepository.deleteById(id);
    }

    private SiteDTO convertToDTO(Site site) {
        return new SiteDTO(site.getId(), site.getVille());
    }

    private Site convertToEntity(SiteDTO siteDTO) {
        Site site = new Site();
        site.setId(siteDTO.getId());
        site.setVille(siteDTO.getVille());
        return site;
    }
}