package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;

public class FalseValue implements Value {
    @Override
    public void write(int tabNum, BufferedWriter outstream) {
        try {
            outstream.write("false");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
