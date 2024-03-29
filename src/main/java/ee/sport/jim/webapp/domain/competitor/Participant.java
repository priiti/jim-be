package ee.sport.jim.webapp.domain.competitor;

import ee.sport.jim.webapp.domain.DateAudit;
import ee.sport.jim.webapp.domain.competition.CompetitionDistance;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"competitor", "competitionDistance"})
@Entity
@Table(name = "competition_participant")
public class Participant extends DateAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "participation_count")
	private Integer participationCount;

	@Column(name = "championship_participation")
	private boolean championshipParticipation;

	@Column(name = "payment_fulfilled")
	private boolean paymentFulfilled;

	@Column(name = "number_printed")
	private boolean numberPrinted;

	@Column(name = "envelope_printed")
	private boolean envelopePrinted;

	@Column(name = "competitor_number")
	private Integer competitorNumber;

	@Column(name = "chip_id")
	private Long chipId;

	@OneToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH, REMOVE})
	@JoinColumn(name = "competitor_id")
	private Competitor competitor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "competition_distance_id")
	private CompetitionDistance competitionDistance;
}
