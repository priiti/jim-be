package ee.sport.jim.webapp.service.organizer;

import ee.sport.jim.webapp.domain.competition.Competition;
import ee.sport.jim.webapp.domain.organizer.Organizer;
import ee.sport.jim.webapp.repository.OrganizerRepository;
import ee.sport.jim.webapp.service.competition.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrganizerServiceImpl implements OrganizerService {
	private final OrganizerRepository organizerRepository;
	private final CompetitionService competitionService;

	@Autowired
	public OrganizerServiceImpl(OrganizerRepository organizerRepository,
															CompetitionService competitionService) {
		this.organizerRepository = organizerRepository;
		this.competitionService = competitionService;
	}

	@Override
	public List<Organizer> findAll() {
		return null;
	}

	@Override
	public Optional<Organizer> findById(Long aLong) {
		return Optional.empty();
	}

	@Override
	public Organizer save(Organizer object) {
		return null;
	}

	@Override
	public void delete(Organizer object) {

	}

	@Override
	public void deleteBy(Long aLong) {

	}

	@Override
	public List<Organizer> getOrganizersByCompetitionId(long competitionId) {
		Optional<Competition> competitionOptional = competitionService.findById(competitionId);
		if (competitionOptional.isPresent()) {
			List<Long> organizerIds = competitionOptional.get().getOrganizers().stream()
				.map(Organizer::getId)
				.collect(Collectors.toList());
			return organizerRepository.findOrganizerByIdIn(organizerIds);
		}
		return Collections.emptyList();
	}
}
