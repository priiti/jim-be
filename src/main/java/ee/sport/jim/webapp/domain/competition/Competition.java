package ee.sport.jim.webapp.domain.competition;

import ee.sport.jim.webapp.domain.UserDateAudit;
import ee.sport.jim.webapp.domain.organizer.Organizer;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(
	callSuper = false,
	exclude = {"distances", "organizers"}
)
@Entity
@Table(name = "competition")
public class Competition extends UserDateAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Lob
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Lob
	@Column(name = "address", columnDefinition = "TEXT")
	private String address;

	@OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CompetitionDistance> distances = new HashSet<>();

	@ManyToMany(mappedBy = "competitions")
	private Set<Organizer> organizers = new HashSet<>();

	public void add(CompetitionDistance tempDistance) {
		if (distances == null) {
			distances = new HashSet<>();
		}
		distances.add(tempDistance);
		tempDistance.setCompetition(this);
	}
}
