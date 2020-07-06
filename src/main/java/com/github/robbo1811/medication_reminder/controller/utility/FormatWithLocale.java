package com.github.robbo1811.medication_reminder.controller.utility;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class FormatWithLocale {

	public static final DateTimeFormatter DATE_AND_TIME_FORMATTER = new DateTimeFormatterBuilder()
			.appendPattern("dd/MM/yyyy HH:mm")
			.toFormatter(Locale.getDefault());

	public static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
			.appendPattern("dd/MM/yyyy")
			.toFormatter(Locale.UK);
		
}
