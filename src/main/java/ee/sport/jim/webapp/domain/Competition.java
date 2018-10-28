package ee.sport.jim.webapp.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Competition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Lob
	@Column(name = "description")
	private String description;

	@Lob
	@Column(name = "address")
	private String address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
	private Set<CompetitionDistance> competitionDistances = new HashSet<>();
}
