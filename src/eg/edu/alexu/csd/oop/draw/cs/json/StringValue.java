package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class StringValue implements Value {
    String value = null;
    public StringValue(String value) {
        this.value = value;
    }
    @Override
    public void write(int tabNum, BufferedWriter outstream) {
        try {
            outstream.write("\""+value+"\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString() { return value; }

    public static Value read(List<String> valueString) {
        return new StringValue(valueString.get(0).substring(1,valueString.get(0).length()-1));
    }
}
