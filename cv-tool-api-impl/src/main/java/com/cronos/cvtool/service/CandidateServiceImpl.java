package com.cronos.cvtool.service;

import com.cronos.cvtool.dto.candidate.CandidateDto;
import com.cronos.cvtool.dto.candidate.CandidateLanguageDto;
import com.cronos.cvtool.dto.candidate.ProfileDto;
import com.cronos.cvtool.entity.candidate.Candidate;
import com.cronos.cvtool.entity.candidate.CandidateLanguage;
import com.cronos.cvtool.entity.candidate.CandidateLanguageKey;
import com.cronos.cvtool.entity.candidate.Country;
import com.cronos.cvtool.entity.candidate.Gender;
import com.cronos.cvtool.entity.candidate.Language;
import com.cronos.cvtool.entity.candidate.LanguageLevel;
import com.cronos.cvtool.entity.user.User;
import com.cronos.cvtool.mapper.CandidateMapper;
import com.cronos.cvtool.repository.CandidateLanguageRepository;
import com.cronos.cvtool.repository.CandidateRepository;
import com.cronos.cvtool.repository.CountryRepository;
import com.cronos.cvtool.repository.GenderRepository;
import com.cronos.cvtool.repository.LanguageLevelRepository;
import com.cronos.cvtool.repository.LanguageRepository;
import com.cronos.cvtool.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final GenderRepository genderRepository;
    private final LanguageRepository languageRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final CandidateLanguageRepository candidateLanguageRepository;
    private final LanguageLevelRepository languageLevelRepository;
    private final AddressService addressService;
    private final CandidateMapper candidateMapper = Mappers.getMapper(CandidateMapper.class);

    public CandidateServiceImpl(CandidateRepository candidateRepository, 
    		GenderRepository genderRepository, LanguageRepository languageRepository,
                                AddressService addressService, CountryRepository countryRepository,
                                UserRepository userRepository, CandidateLanguageRepository candidateLanguageRepository,
                                LanguageLevelRepository languageLevelRepository) {
        this.candidateRepository = candidateRepository;
        this.genderRepository = genderRepository;
        this.languageRepository = languageRepository;
        this.addressService = addressService;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.candidateLanguageRepository = candidateLanguageRepository;
        this.languageLevelRepository = languageLevelRepository;
    }

    /**
     * Retrieves a paginated list of candidates based on the specified page, size, and sorting criteria.
     *
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A paginated list of CandidateDto objects.
     */
    @Override
    public Page<CandidateDto> getAllCandidates(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Candidate> candidatesPage = candidateRepository.findAll(pageable);
        List<CandidateLanguage> candidateLanguages = candidateLanguageRepository.findByCandidateIds(candidatesPage.get().map(Candidate::getId).toList());
        return candidatesPage.map(candidate -> candidateMapper.entityToDto(candidate,
                candidateLanguages.stream()
                        .filter(candidateLanguage -> candidate.getId().equals(candidateLanguage.getCandidate().getId()))
                        .toList()));
    }

    @Override
    public Page<ProfileDto> getAllProfiles(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Candidate> candidatesPage = candidateRepository.findAll(pageable);
        return candidatesPage.map(candidateMapper::candidateToCandidatesDto);
    }

    /**
     * Retrieves a specific candidate by their ID.
     *
     * @param id The ID of the candidate to retrieve.
     * @return The CandidateDto representing the requested candidate.
     * @throws IllegalArgumentException if no candidate is found with the given ID.
     */
    @Override
    public CandidateDto getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + id));
        List<CandidateLanguage> candidateLanguages = candidateLanguageRepository.findByCandidateId(candidate.getId());
        return candidateMapper.entityToDto(candidate, candidateLanguages);
    }

    /**
     * Creates a new candidate based on the provided CandidateDto.
     *
     * @param candidateDto The data transfer object containing candidate information.
     * @return The created CandidateDto representing the new candidate.
     */
    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {
        // Map CandidateDto to Candidate entity using the mapper
        Candidate candidate = candidateMapper.dtoToEntity(candidateDto);

        // Save the candidate's address and update the candidate with the saved address
        candidate.setAddress(addressService.saveAddress(candidate.getAddress()));

        // Save the candidate's user and update the candidate with the saved user
        candidate.setUser(userRepository.findById(candidateDto.getUserId()).orElseThrow(
                () -> new EntityNotFoundException("User not found!")
        )); candidate.setUser(userRepository.findByEmail(candidateDto.getPersonalInfo().getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User not found!")
        ));
        // Retrieve and set the gender for the candidate
        Gender gender = genderRepository.getGenderByCode(candidateDto.getGenderCode());
        candidate.setGender(gender);

        // Set the candidate's manager if provided in the CandidateDto
        if (candidateDto.getIdManager() != null) {
            Optional<User> manager = userRepository.findById(candidateDto.getIdManager());
            candidate.setManager(manager.orElse(null));
        }

        // Retrieve and set languages for the candidate
        List<Language> languages = new ArrayList<>();
        if (!CollectionUtils.isEmpty(candidateDto.getCandidateLanguages())) {
            candidateDto.getCandidateLanguages().stream().map(CandidateLanguageDto::getCode).forEach(l ->
                    Optional.ofNullable(languageRepository.getLanguageByCode(l)).ifPresent(languages::add));
        }
        candidate.setLanguages(languages);

        // Update additional candidate information, e.g., nationalities
        candidate = this.editCandidateInfos(candidate, candidateDto.getPersonalInfo().getNationalities());

        // Save the candidate
        Candidate savedCandidate = candidateRepository.save(candidate);

        record CandidateLanguageObj(Boolean isMotherTongue, String spokenLevelCode,
                                    String writtenLevelCode, String understoodLevelCode) {
        }
        // Retrieve and set candidate native language and levels for the candidate
        List<CandidateLanguage> candidateLanguages = candidateLanguageRepository.findByCandidateId(candidate.getId());
        List<LanguageLevel> languageLevels = (List<LanguageLevel>) languageLevelRepository.findAll();
        Map<String, CandidateLanguageObj> languagesMap = candidateDto.getCandidateLanguages().stream().collect(
                Collectors.toMap(CandidateLanguageDto::getCode, t -> new CandidateLanguageObj(t.getIsMotherTongue(), t.getLanguageSpokenLevelCode(), t.getLanguageWrittenLevelCode(), t.getLanguageUnderstoodLevelCode()))
        );
        for (CandidateLanguage candidateLanguage: candidateLanguages) {
            candidateLanguage.setIsMotherTongue(languagesMap.get(candidateLanguage.getLanguage().getCode()).isMotherTongue);
            candidateLanguage.setSpokenLevel(languageLevels.stream().filter(lv -> lv.getCode().equals(languagesMap.get(candidateLanguage.getLanguage().getCode()).spokenLevelCode)).findFirst().orElse(null));
            candidateLanguage.setWrittenLevel(languageLevels.stream().filter(lv -> lv.getCode().equals(languagesMap.get(candidateLanguage.getLanguage().getCode()).writtenLevelCode)).findFirst().orElse(null));
            candidateLanguage.setUnderstoodLevel(languageLevels.stream().filter(lv -> lv.getCode().equals(languagesMap.get(candidateLanguage.getLanguage().getCode()).understoodLevelCode)).findFirst().orElse(null));
            candidateLanguage.setId(new CandidateLanguageKey(candidate.getId(), candidateLanguage.getLanguage().getId()));
            candidateLanguageRepository.save(candidateLanguage);
        }

        // Map the saved Candidate entity back to a CandidateDto for response
        return candidateMapper.entityToDto(savedCandidate, candidateLanguages);
    }

    /**
     * Updates an existing candidate based on the provided ID and CandidateDto.
     *
     * @param id           The ID of the candidate to update.
     * @param candidateDTO The updated data transfer object containing candidate information.
     * @return The updated CandidateDto representing the modified candidate.
     */
    @Override
    public CandidateDto updateCandidate(Long id, CandidateDto candidateDTO) {
        // Implementation for updating a candidate goes here (to be completed)
        return null;
    }

    /**
     * Deletes a candidate based on the provided ID.
     *
     * @param id The ID of the candidate to delete.
     */
    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    /**
     * Updates additional information for a candidate, such as nationalities.
     *
     * @param candidate          The candidate entity to update.
     * @param nationalitiesCodes The set of country codes representing the candidate's nationalities.
     * @return The updated candidate entity.
     */
    @Override
    public Candidate editCandidateInfos(Candidate candidate, Set<String> nationalitiesCodes) {
        Set<Country> nationalities = nationalitiesCodes.stream()
                .map(countryRepository::getCountryByCode)
                .collect(Collectors.toSet());
        candidate.setNationalities(nationalities);

        return candidate;
    }

}
