package ee.sport.jim.webapp.domain.competition;

import ee.sport.jim.webapp.domain.shared.BaseModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"competition", "prices", "championshipType"})
@Entity
@Table(name = "competition_distance")
public class CompetitionDistance extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INTEGER(11)")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "length")
	private BigDecimal length;

	@Lob
	@Column(name = "special_notes")
	private String specialNotes;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@Column(name = "start_numbering")
	private Integer startNumbering;

	@ManyToOne(fetch = FetchType.LAZY,
		cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "competition_id")
	private Competition competition;

	@OneToMany(mappedBy = "distance",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CompetitionPrice> prices;

	public void add(CompetitionPrice tempPrice) {
		if (prices == null) {
			prices = new ArrayList<>();
		}
		prices.add(tempPrice);
		tempPrice.setDistance(this);
	}

	@OneToOne
	@JoinColumn(name = "championship_type_id")
	private ChampionshipType championshipType;
}
