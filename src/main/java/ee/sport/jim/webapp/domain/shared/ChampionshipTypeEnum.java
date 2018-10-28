package ee.sport.jim.webapp.domain.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChampionshipTypeEnum {
	MARATHON("Maraton"),
	EVERY_MAN_RUN("Igamehejooks");

	private final String description;
}
