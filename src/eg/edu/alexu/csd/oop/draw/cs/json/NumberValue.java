package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class NumberValue implements Value {
    Integer value = null;
    public NumberValue(Integer value) {
        this.value = value;
    }
    @Override
    public void write(int tabNum, BufferedWriter outstream) {
        try {
            outstream.write(String.valueOf(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Integer getNumber() { return value; }
    public static Value read(List<String> valueString) {
        return new NumberValue(Integer.parseInt(valueString.get(0)));
    }
}
