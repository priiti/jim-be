package ee.sport.jim.webapp.domain.shared;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TypeDefs({
	@TypeDef(name = "json", typeClass = JsonStringType.class),
	@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel implements Serializable {
	@Column(name = "CREATED", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime created;

	@Column(name = "CREATED_BY", nullable = false, updatable = false)
	@CreatedBy
	private String creator;

	@Column(name = "MODIFIED", nullable = false)
	@LastModifiedDate
	private LocalDateTime modified;

	@Column(name = "MODIFIED_BY", nullable = false)
	@LastModifiedBy
	private String modifier;
}
