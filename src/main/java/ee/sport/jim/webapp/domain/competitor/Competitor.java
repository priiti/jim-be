package ee.sport.jim.webapp.domain.competitor;

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
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "competitor")
public class Competitor extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INTEGER(11)")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phoneNumber;

	@Column(name = "gender")
	private String gender;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	@Column(name = "sports_club")
	private String sportsClub;

	@Column(name = "newsletter_subscription")
	private boolean newsletterSubscription;

	@Column(name = "publish_data")
	private boolean publishData;
}
