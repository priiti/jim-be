package ee.sport.jim.webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
	value = {"createdAt", "updatedAt"},
	allowGetters = true
)
public abstract class DateAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Instant createdAt = Instant.now();

	@JsonIgnore
	@LastModifiedDate
	@Column(nullable = false)
	private Instant updatedAt = Instant.now();
}
