package ee.sport.jim.webapp.service.competitor;

import ee.sport.jim.webapp.domain.competitor.Participant;
import ee.sport.jim.webapp.domain.shared.RegistrationHolder;

public interface CompetitorService {

	Participant register(RegistrationHolder registrationHolder, long distanceId);

}
