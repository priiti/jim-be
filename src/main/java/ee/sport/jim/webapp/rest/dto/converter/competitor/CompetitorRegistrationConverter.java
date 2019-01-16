package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantRegistrationDto;
import org.springframework.stereotype.Component;

@Component
public class CompetitorRegistrationConverter {

	public Participant convertDtoToParticipant(ParticipantRegistrationDto dto) {
		Participant participant = new Participant();
		participant.setParticipationCount(dto.getParticipationCount());
		participant.setChampionshipParticipation(dto.isChampionshipParticipation());
		participant.setPaymentFulfilled(false);
		participant.setNumberPrinted(false);
		participant.setEnvelopePrinted(false);
		return participant;
	}

	public Competitor convertDtoToCompetitor(ParticipantRegistrationDto dto) {
		Competitor competitor = new Competitor();
		competitor.setFirstName(dto.getFirstName());
		competitor.setLastName(dto.getLastName());
		competitor.setEmail(dto.getEmail());
		competitor.setPhoneNumber(dto.getPhoneNumber());
		competitor.setGender(dto.getGender());
		competitor.setDateOfBirth(dto.getDateOfBirth());
		competitor.setSportsClub(dto.getSportsClub());
		competitor.setNewsletterSubscription(dto.isNewsletterSubscription());
		competitor.setPublishData(dto.isPublishData());
		return competitor;
	}
}
