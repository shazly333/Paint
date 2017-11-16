package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ArrayValue implements Value {
    private Value[] elements = null;
    private int tabNum = 0;
    private BufferedWriter outstream = null;

    public ArrayValue(Value[] elements) {
        this.elements = elements;
    }
    public Value[] getArray() { return elements; }
    @Override
    public void write(int tabNum, BufferedWriter outstream) {
        this.tabNum = tabNum;
        this.outstream = outstream;
        try {
            outstream.write("[\n");
            for (Value element : elements) {
                printTabs();
                element.write(tabNum+1,outstream);
                if(element != elements[elements.length-1])
                    outstream.write(",");
                outstream.write("\n");
            }
            printTabs();
            outstream.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Value read(List<String> valueString) {
        LinkedList<Value> values = new LinkedList<>();
        int ind = 1;
        while (!valueString.get(ind).equals("]")) {
            if(valueString.get(ind).equals(","))
                ind++;
            int endInd = getEndInd(valueString,ind);
            values.add(ValueReader.read(valueString.subList(ind,endInd)));
            ind = endInd;
        }
        return new ArrayValue(values.toArray(new Value[values.size()]));
    }

    private static int getEndInd(List<String> valueString, int startInd){
        Character firstChar = valueString.get(startInd).charAt(0);
        if(firstChar == '"' || Character.isDigit(firstChar) || firstChar == '-' || Character.isAlphabetic(firstChar) || firstChar == '+')
            return startInd + 1;
        int containersCont = 0;
        String startContainer = valueString.get(startInd);
        String closingContainer = getClosingContaier(startContainer);
        int curInd = startInd;
        do {
            if(valueString.get(curInd).equals(startContainer))
                containersCont--;
            else if(valueString.get(curInd).equals(closingContainer))
                containersCont++;
            curInd++;
        } while (containersCont != 0);
        return curInd;
    }
    private static String getClosingContaier(String str) {
        if (str.equals("["))
            return "]";
        else
            return "}";
    }
    private void printTabs() {
        try {
            for (int i = 0; i < tabNum; i++) {
                outstream.write("\t");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
