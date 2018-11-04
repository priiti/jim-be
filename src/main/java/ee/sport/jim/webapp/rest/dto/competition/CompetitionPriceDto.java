package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionPriceDto {
	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private BigDecimal price;
}
