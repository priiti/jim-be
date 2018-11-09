package ee.sport.jim.webapp.domain.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "v_comp_participant_list")
public class ParticipantItem {
	@Id
	@Column(name = "competition_id", columnDefinition = "INTEGER(11)")
	private Long competitionId;
	@Column(name = "competition_name")
	private String competitionName;
	@Lob
	@Column(name = "description")
	private String description;
	@Column(name = "distance_name")
	private String distanceName;
	@Column(name = "length")
	private BigDecimal length;
	@Column(name = "type_name")
	private String typeName;
	@Column(name = "competitor_number")
	private Integer competitorNumber;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
}
