package ee.sport.jim.webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"competition", "prices", "championshipType"})
@Entity
public class CompetitionDistance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "length")
	private BigDecimal length;

	@Column(name = "special_notes")
	@Lob
	private String specialNotes;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "start_numbering")
	private Integer startNumbering;

	@OneToOne
	private ChampionshipType championshipType;

	@ManyToOne
	@JsonIgnore
	private Competition competition;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "competitionDistance")
	private Set<CompetitionPrice> prices = new HashSet<>();
}
