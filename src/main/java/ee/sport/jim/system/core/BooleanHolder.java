package ee.sport.jim.system.core;

import lombok.Getter;

@Getter
public class BooleanHolder {
	private boolean value;

	public BooleanHolder(boolean value) {
		this.value = value;
	}
}
