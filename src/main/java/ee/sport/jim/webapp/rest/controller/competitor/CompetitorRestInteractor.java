package ee.sport.jim.webapp.rest.controller.competitor;

import ee.sport.jim.webapp.domain.shared.RegistrationHolder;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.competitor.CompetitorDtoFactory;
import ee.sport.jim.webapp.service.competitor.CompetitorService;
import org.springframework.stereotype.Service;

@Service
public class CompetitorRestInteractor implements CompetitorRestService {
	private final CompetitorService competitorService;

	public CompetitorRestInteractor(CompetitorService competitorService) {
		this.competitorService = competitorService;
	}

	@Override
	public void register(ParticipantRegistrationDto participantRegistrationDto) {
		competitorService.register(createRegistrationHolder(participantRegistrationDto),
			participantRegistrationDto.getCompetitionDistanceId());
	}

	private RegistrationHolder createRegistrationHolder(ParticipantRegistrationDto registrationDto) {
		RegistrationHolder registrationHolder = new RegistrationHolder();
		registrationHolder.setCompetitor(CompetitorDtoFactory.getCompetitor(registrationDto));
		registrationHolder.setParticipant(CompetitorDtoFactory.getParticipant(registrationDto));
		return registrationHolder;
	}
}
