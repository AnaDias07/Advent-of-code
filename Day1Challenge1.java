import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Day1AdventCallendar{ 
    
    public static List<String> ReadFile(){
        List<String> allLines = new ArrayList<String>();
        try {
			allLines = Files.readAllLines(Paths.get("day1input.txt"));
		} catch (IOException e) {
            System.out.println("Error reading the file");
			e.printStackTrace();
		}
        return allLines;
    }
    
    public static int Computation(List<String> allLines) {
        List<String> numbersFound = new ArrayList<String>();
        int finalValue = 0;
        String temporaryString = "";

        for (String line : allLines) {
            int lineLegth = line.length();
            for (int i = 0; i < lineLegth; i++) {
                if (Character.isDigit(line.charAt(i))){
                    numbersFound.add(Character.toString(line.charAt(i)));
                } else if (i+3 <= line.length() && line.substring(i,i+3).equals("one")) {
                    numbersFound.add("1");
                } else if (i+3 <= line.length() && line.substring(i,i+3).equals("two")) {
                    numbersFound.add("2");
                } else if (i+5 <= line.length() && line.substring(i,i+5).equals("three")) {
                    numbersFound.add("3");
                } else if (i+4 <= line.length() && line.substring(i,i+4).equals("four")) {
                    numbersFound.add("4");
                } else if (i+4 <= line.length() && line.substring(i,i+4).equals("five")) {
                    numbersFound.add("5");
                } else if (i+3 <= line.length() && line.substring(i,i+3).equals("six")) {
                    numbersFound.add("6");
                } else if (i+5 <= line.length() && line.substring(i,i+5).equals("seven")) {
                    numbersFound.add("7");
                } else if (i+5 <= line.length() && line.substring(i,i+5).equals("eight")) {
                    numbersFound.add("8");
                } else if (i+4 <= line.length() && line.substring(i,i+4).equals("nine")) {
                    numbersFound.add("9");
                }
            }
            if (numbersFound.size() == 1){
                temporaryString = numbersFound.get(0) + numbersFound.get(0);
            } else if (numbersFound.size() > 1) {
                temporaryString = numbersFound.get(0) + numbersFound.get(numbersFound.size()-1);
            } else {
                System.out.println("Empty list found?");
            }
            finalValue = Integer.parseInt(temporaryString)+finalValue;  
            temporaryString = "";
            numbersFound.clear();
        }
        
        return finalValue;

    }
    
    
    public static void main(String args[]){  
        List<String> allLines = ReadFile();
        int finalValue = Computation(allLines);
        System.out.println("Final value: " + finalValue);
    }  
}  