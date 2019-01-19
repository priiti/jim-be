package ee.sport.jim.webapp.domain.competitor;

import ee.sport.jim.webapp.domain.DateAudit;
import ee.sport.jim.webapp.domain.shared.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "competitor")
public class Competitor extends DateAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	@Column(name = "sports_club")
	private String sportsClub;

	@Column(name = "newsletter_subscription")
	private boolean newsletterSubscription;

	@Column(name = "publish_data")
	private boolean publishData;
}
