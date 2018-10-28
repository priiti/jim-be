package ee.sport.jim.webapp.rest.dto.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionPriceDto {
	private Long id;
	private Date startDate;
	private Date endDate;
	private BigDecimal price;
}
