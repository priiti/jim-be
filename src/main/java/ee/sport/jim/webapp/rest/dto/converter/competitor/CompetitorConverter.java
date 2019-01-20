package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Competitor;
import ee.sport.jim.webapp.rest.dto.competitor.CompetitorDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompetitorConverter extends GenericConverter<Competitor, CompetitorDto> {

	@Override
	public CompetitorDto convertEntity(Competitor entity) {
		return CompetitorDto.builder()
			.id(entity.getId())
			.firstName(entity.getFirstName())
			.lastName(entity.getLastName())
			.email(entity.getEmail())
			.phoneNumber(entity.getPhoneNumber())
			.gender(entity.getGender())
			.dateOfBirth(entity.getDateOfBirth())
			.sportsClub(entity.getSportsClub())
			.newsLetterSubscription(entity.isNewsletterSubscription())
			.publishData(entity.isPublishData())
			.build();
	}

	@Override
	public Competitor convertDto(CompetitorDto dto) {
		Competitor competitor = new Competitor();
		if (dto.getId() != null) {
			competitor.setId(dto.getId());
		}
		competitor.setFirstName(dto.getFirstName());
		competitor.setLastName(dto.getLastName());
		competitor.setEmail(dto.getEmail());
		competitor.setPhoneNumber(dto.getPhoneNumber());
		competitor.setGender(dto.getGender());
		competitor.setDateOfBirth(dto.getDateOfBirth());
		competitor.setSportsClub(dto.getSportsClub());
		competitor.setNewsletterSubscription(dto.isNewsLetterSubscription());
		competitor.setPublishData(dto.isPublishData());
		return competitor;
	}

	@Override
	public List<Competitor> convertDto(List<CompetitorDto> dtos) {
		return super.convertDto(dtos);
	}

	@Override
	public List<CompetitorDto> convertEntity(List<Competitor> entities) {
		return super.convertEntity(entities);
	}
}
