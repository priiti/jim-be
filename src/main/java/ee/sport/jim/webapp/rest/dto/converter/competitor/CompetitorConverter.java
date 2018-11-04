package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.rest.dto.competitor.CompParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;

public class CompetitorConverter extends GenericConverter<Competitor, CompParticipantRegistrationDto> {
	@Override
	public CompParticipantRegistrationDto convertEntity(Competitor entity) {
		throw new NotImplementedException("CompParticipantRegistrationDto converter not implemented");
	}

	@Override
	public Competitor convertDto(CompParticipantRegistrationDto dto) {
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
