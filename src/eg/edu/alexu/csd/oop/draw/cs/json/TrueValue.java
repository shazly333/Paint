package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;

public class TrueValue implements Value {
    @Override
    public void write(int tabNum, BufferedWriter outstream) {
        try {
            outstream.write("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
