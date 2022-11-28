package by.piskunou.university.ds.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {
	private static String construcAnswer(int removable, String raw, Pattern pattern) {
		Matcher matcher = pattern.matcher(raw);
		while(matcher.find()) {
			String oldSeq = matcher.group(removable);
			String newSeq = oldSeq.replaceAll("\\d*", "");
			String oldIdentity = matcher.group();
			String newIdentity = oldIdentity.replace(oldSeq, newSeq);
			raw = raw.replace(oldIdentity, newIdentity);
		}
		
		return raw;
	}
	
	public static String translate(String raw) {
		Pattern classSimpleNamePattern = Pattern.compile("([A-Z][\\w$]*\\s*\\.\\s*)*[A-Z][\\w$]*");
		Pattern primitivesPattern = Pattern.compile("(char)|(byte)|(short)|(int)|(long)|(float)|(double)");
		Pattern typesPattern = Pattern.compile("(" + primitivesPattern + "|(" + classSimpleNamePattern + "))");
		
		Pattern makeArrayPattern = Pattern.compile("\\[\\s*\\]");
		
		Pattern collectionTypesPattern = Pattern.compile("((" + classSimpleNamePattern + "\\s*(" + makeArrayPattern + ")?)|((" +
														 primitivesPattern + ")\\s*" + makeArrayPattern + "))");
		
		Pattern colllectionPattern = Pattern.compile(classSimpleNamePattern + "\\s*<\\s*(" + collectionTypesPattern + "\\s*,?\\s*)+>");
		Pattern allTypesPattern = Pattern.compile("(" + typesPattern + "|(" + colllectionPattern + "))");
		
		Pattern identifierPattern = Pattern.compile("[a-zA-Z_$][\\w$]*");
		
		Pattern pattern1 = Pattern.compile(typesPattern + "\\s+(" + identifierPattern + ")\\s*(" + makeArrayPattern + ")?");
		Pattern pattern2 = Pattern.compile(colllectionPattern + "\\s*(" + identifierPattern + ")\\s*(" + makeArrayPattern + ")?");
		Pattern pattern3 = Pattern.compile(allTypesPattern + "\\s*" + makeArrayPattern + "\\s*(" + identifierPattern + ")");
		
		raw = construcAnswer(11, raw, pattern1);
		raw = construcAnswer(16, raw, pattern2);
		raw = construcAnswer(28, raw, pattern3);
		
		return raw;
	}
}
