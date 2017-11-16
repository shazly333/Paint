package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ValueReader {
	public static Value read(List<String> valueString) {
		String firstString = valueString.get(0);
		Character firstChar = firstString.charAt(0);
        if(firstString.equals("{")) {
            return ObjectValue.read(valueString);
        }
        else if(firstString.equals("[")) {
            return ArrayValue.read(valueString);
        }
        else if(firstChar == '"') {
            return StringValue.read(valueString);
        }
        else if(firstString.equals("true") || firstString.equals("false") || firstString.equals("null"))
        	return BooleanValue.read(valueString);
        else if(firstChar == '+'){
            return DoubleNumberValue.read(valueString);
        }
        else {
        	return NumberValue.read(valueString);
        }
    }
    public static Value read(BufferedReader inputstream) {
        StringBuilder streamString = new StringBuilder();
        try {
            String line;
            while ((line =inputstream.readLine()) != null)
                streamString.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<String> valueString = new LinkedList<>();
        for (int i = 0;i < streamString.length();i++) {
            Character curChar = streamString.charAt(i);
            if(curChar == '[' || curChar == ']' || curChar == '{' || curChar == '}' || curChar == ',' || curChar == ':')
                valueString.add(curChar.toString());
            else if(curChar == '"')
            {
                StringBuilder newString = new StringBuilder();
                newString.append(curChar);
                i++;
                while (!(streamString.charAt(i) == '"' && streamString.charAt(i-1) != '\\')) {
                    newString.append(streamString.charAt(i));
                    i++;
                }
                newString.append(streamString.charAt(i));
                valueString.add(newString.toString());

            }
            else if(curChar == '-' || Character.isDigit(curChar) || curChar == '+') {
               StringBuilder newString = new StringBuilder();
               do {
                    newString.append(curChar);
                    i++;
                    curChar= streamString.charAt(i);
                }while (Character.isDigit(curChar) || curChar == '-');
               	i--;
                valueString.add(newString.toString());
            }
            else if(Character.isAlphabetic(curChar))
            {
            	StringBuilder newString = new StringBuilder();
            	do {
                    newString.append(curChar);
                    i++;
                    curChar= streamString.charAt(i);
                }while (Character.isAlphabetic(curChar));
            	i--;
            	valueString.add(newString.toString());
            }
        }
        return read(valueString);
    }
}
