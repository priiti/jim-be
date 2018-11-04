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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"distances"})
@Entity
@Table(name = "competition")
public class Competition extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INTEGER(11)")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Lob
	@Column(name = "description")
	private String description;

	@Lob
	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "competition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CompetitionDistance> distances;

	public void add(CompetitionDistance tempDistance) {
		if (distances == null) {
			distances = new ArrayList<>();
		}
		distances.add(tempDistance);
		tempDistance.setCompetition(this);
	}
}
