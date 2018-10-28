package ee.sport.jim.system.core;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;

@AllArgsConstructor
public enum YesNo {
	Y,
	N;

	public static String getString(boolean bool) {
		return BooleanUtils.toString(bool, Y.name(), N.name());
	}

	public static String getString(Boolean bool) {
		return BooleanUtils.toString(bool, Y.name(), N.name(), (String) null);
	}

	public static Boolean getBoolean(String value) {
		return BooleanUtils.toBooleanObject(value, Y.name(), N.name(), (String) null);
	}

	public static boolean getBool(String value) {
		Boolean bool = getBoolean(value);
		return bool == null ? false : bool;
	}

}
