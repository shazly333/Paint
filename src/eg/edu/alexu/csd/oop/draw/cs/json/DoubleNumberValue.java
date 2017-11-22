package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class DoubleNumberValue implements Value {
    Double value = null;
    public DoubleNumberValue(Double value) {
        this.value = value;
    }
    @Override
    public void write(int tabNum, BufferedWriter outstream) {
        try {
        	outstream.write("+");
            outstream.write(String.valueOf(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Double getNumber() { return value; }
    public static Value read(List<String> valueString) {
        return new DoubleNumberValue(Double.parseDouble(valueString.get(0).substring(1)));
    }
}
