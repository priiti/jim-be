package ee.sport.jim.webapp.rest.dto.converter.organizer;

import ee.sport.jim.webapp.domain.organizer.Organizer;
import ee.sport.jim.webapp.rest.dto.organizer.OrganizerInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrganizerInfoConverter implements Converter<List<Organizer>, List<OrganizerInfoDto>> {

	private Function<Organizer, OrganizerInfoDto> convertDto = (organizer) -> {
		OrganizerInfoDto dto = new OrganizerInfoDto();
		dto.setName(organizer.getName());
		dto.setEmail(organizer.getEmail());
		dto.setPhone(organizer.getPhone());
		return dto;
	};

	@Override
	public List<OrganizerInfoDto> convert(List<Organizer> source) {
		return source.stream()
			.map(convertDto)
			.collect(Collectors.toList());
	}
}
