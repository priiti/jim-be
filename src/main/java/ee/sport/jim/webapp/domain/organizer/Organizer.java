package ee.sport.jim.webapp.domain.organizer;

import ee.sport.jim.webapp.domain.User;
import ee.sport.jim.webapp.domain.UserDateAudit;
import ee.sport.jim.webapp.domain.competition.Competition;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(
	callSuper = false,
	exclude = {"competitions"}
)
@Entity
@Table(name = "organizer")
public class Organizer extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user_id;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "organizer_competition",
		joinColumns = {@JoinColumn(name = "organizer_id")},
		inverseJoinColumns = {@JoinColumn(name = "competition_id")}
	)
	private Set<Competition> competitions = new HashSet<>();
}
