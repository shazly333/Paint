package eg.edu.alexu.csd.oop.draw.cs.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectValue implements Value {
    private Map<String, Value> prop = null;
    private int tabNum = 0;
    private BufferedWriter outstream = null;

    public ObjectValue(HashMap<String, Value> prop) {
        this.prop = prop;
    }

    @Override
    public void write(int tabNum, BufferedWriter outstream) {
        this.tabNum = tabNum;
        this.outstream = outstream;
        try {
            outstream.write("{\n");
            boolean firstProp = true;
            for (Map.Entry<String, Value> entry : prop.entrySet()) {
                if(!firstProp)
                    outstream.write(",\n");
                else {
                    firstProp = false;
                    outstream.write("\n");
                }
                printTabs();
                outstream.write("\t\""+(String) entry.getKey()+"\": ");
                Value value = (Value)entry.getValue();
                value.write(tabNum+1,outstream);
            }
            printTabs();
            outstream.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Value> getProp() { return prop; }
    private void printTabs() {
        try {
            for (int i = 0; i < tabNum; i++) {
                outstream.write("\t");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Value read(List<String> valueString) {
        HashMap<String, Value> prop = new HashMap<>();
        int ind = 1;
        while (!valueString.get(ind).equals("}")) {
            if(valueString.get(ind).equals(","))
                ind++;
            String key = valueString.get(ind).substring(1,valueString.get(ind).length()-1);
            ind+=2; // For :
            int endInd = getEndInd(valueString,ind);
            Value value = ValueReader.read(valueString.subList(ind,endInd));
            prop.put(key,value);
            ind = endInd;
        }
        return new ObjectValue(prop);
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
}
