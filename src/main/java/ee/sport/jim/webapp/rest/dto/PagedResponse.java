package ee.sport.jim.webapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PagedResponse<T> {
	private List<T> content;
	private int size;
	private int page;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
