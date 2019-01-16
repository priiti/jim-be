package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.rest.dto.competitor.ParticipantDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class CompetitorConverter extends GenericConverter<Competitor, ParticipantDto> {

	@Override
	public ParticipantDto convertEntity(Competitor entity) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public Competitor convertDto(ParticipantDto dto) {
		Competitor entity = new Competitor();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setGender(dto.getGender());
		entity.setDateOfBirth(dto.getDateOfBirth());
		entity.setSportsClub(dto.getSportsClub());
		entity.setNewsletterSubscription(dto.getNewsletterSubscription().isValue());
		entity.setPublishData(dto.getPublishData().isValue());
		return entity;
	}
}
