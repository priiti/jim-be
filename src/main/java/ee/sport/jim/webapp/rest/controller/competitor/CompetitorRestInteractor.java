package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.domain.shared.RegistrationHolder;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.competitor.CompetitorDtoFactory;
import ee.sport.jim.webapp.service.competitor.CompetitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompetitorRestInteractor implements CompetitorRestService {
	private final CompetitorService competitorService;
	private final CompetitorDtoFactory competitorDtoFactory;

	public CompetitorRestInteractor(CompetitorService competitorService, CompetitorDtoFactory competitorDtoFactory) {
		this.competitorService = competitorService;
		this.competitorDtoFactory = competitorDtoFactory;
	}

	@Override
	public void register(ParticipantRegistrationDto participantRegistrationDto) {
		log.info("Registering participant");
		competitorService.register(createRegistrationHolder(participantRegistrationDto),
			participantRegistrationDto.getCompetitionDistanceId());
	}

	private RegistrationHolder createRegistrationHolder(ParticipantRegistrationDto registrationDto) {
		RegistrationHolder registrationHolder = new RegistrationHolder();
		registrationHolder.setCompetitor(competitorDtoFactory.getCompetitor(registrationDto));
		registrationHolder.setParticipant(competitorDtoFactory.getParticipant(registrationDto));
		return registrationHolder;
	}
}
