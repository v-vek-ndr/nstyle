package com.nstyleintl.nstyle.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utility {

	public static LocalDate casteToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
	}
}
