package com.pizzabaker.utils;

public class Utils {

	public static String NormalizeText(String str) {
		if(str == null) return null;
		return str.trim().toLowerCase().replaceAll(" +", " ")
				.replaceAll("á", "a")
				.replaceAll("é", "e")
				.replaceAll("í", "i")
				.replaceAll("ó", "o")
				.replaceAll("ú", "u")
				.replaceAll("ü", "u");
	}
}
