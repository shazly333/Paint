package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class BooleanValue implements Value {
	Boolean value = null;

	public BooleanValue(Boolean value) {
		this.value = value;
	}

	@Override
	public void write(int tabNum, BufferedWriter outstream) {
		try {
			if(value == null)
				outstream.write("null");
			else if(value)
				outstream.write("true");
			else
				outstream.write("false");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean getBoolean() {
		return value;
	}

	public static Value read(List<String> valueString) {
		Boolean value = null;
		if(valueString.get(0).equals("true"))
			value = new Boolean(true);
		else if(valueString.get(0).equals("false"))
			value = new Boolean(false);
		return new BooleanValue(value);
	}
}
