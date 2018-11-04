package ee.sport.jim.webapp.domain.competitor;

import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import ee.sport.jim.webapp.domain.shared.BaseModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"competitor", "competitionDistance"})
@Entity
@Table(name = "competition_participant")
public class Participant extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INTEGER(11)")
	private Long id;

	@Column(name = "participation_count")
	private Integer participationCount;

	@Column(name = "payment_fulfilled")
	private boolean paymentFulfilled;

	@Column(name = "number_printed")
	private boolean numberPrinted;

	@Column(name = "envelope_printed")
	private boolean envelopePrinted;

	@Column(name = "competitor_number")
	private Integer competitorNumber;

	@Column(name = "chip_id", columnDefinition = "INTEGER(11)")
	private Long chipId;

	@OneToOne
	@JoinColumn(name = "competitor_id")
	private Competitor competitor;

	@OneToOne
	@JoinColumn(name = "competition_distance_id")
	private CompetitionDistance competitionDistance;
}
