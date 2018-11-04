package ee.sport.jim.webapp.rest.dto.converter.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.rest.dto.competitor.CompParticipantRegistrationDto;
import ee.sport.jim.webapp.rest.dto.converter.GenericConverter;
import org.apache.commons.lang3.NotImplementedException;

public class ParticipantConverter extends GenericConverter<Participant, CompParticipantRegistrationDto> {
	@Override
	public CompParticipantRegistrationDto convertEntity(Participant entity) {
		throw new NotImplementedException("CompParticipantRegistrationDto conversion not implemented");
	}

	@Override
	public Participant convertDto(CompParticipantRegistrationDto dto) {
		Participant participant = new Participant();
		participant.setParticipationCount(dto.getParticipationCount());
		participant.setPaymentFulfilled(false);
		participant.setNumberPrinted(false);
		participant.setEnvelopePrinted(false);
		return participant;
	}
}
