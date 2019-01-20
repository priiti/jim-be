package ee.sport.jim.webapp.rest.dto.converter.organizer;

import ee.sport.jim.webapp.domain.organizer.Organizer;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import ee.sport.jim.webapp.rest.dto.organizer.OrganizerDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class OrganizerConverter extends GenericConverter<Organizer, OrganizerDto> {

	@Override
	public OrganizerDto convertEntity(Organizer entity) {
		return OrganizerDto.builder()
			.id(entity.getId())
			.name(entity.getName())
			.email(entity.getEmail())
			.phone(entity.getPhone())
			.build();
	}

	@Override
	public Organizer convertDto(OrganizerDto dto) {
		Organizer entity = new Organizer();
		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		return entity;
	}

	@Override
	public List<Organizer> convertDto(List<OrganizerDto> dtos) {
		return super.convertDto(dtos);
	}

	@Override
	public List<OrganizerDto> convertEntity(List<Organizer> entities) {
		return super.convertEntity(entities);
	}
}
