import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Day2Challenge {

    public static List<String> ReadFile(String document_location) {

        List<String> allLines = new ArrayList<String>();
        try {
            allLines = Files.readAllLines(Paths.get(document_location));
        } catch (IOException e) {
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
        return allLines;
    }

    public static int ColorCheck(int target_red_value, int target_green_value, int target_blue_value,
            List<String> allLines) {
        int current_index = 1;
        Boolean possible_blue = false;
        Boolean possible_red = false;
        Boolean possible_green = false;
        int total_game_index = 0;

        for (String line : allLines) {
            Pattern pattern_blue = Pattern.compile("blue", Pattern.CASE_INSENSITIVE);
            Matcher matcher_blue = pattern_blue.matcher(line);
            Pattern pattern_red = Pattern.compile("red", Pattern.CASE_INSENSITIVE);
            Matcher matcher_red = pattern_red.matcher(line);
            Pattern pattern_green = Pattern.compile("green", Pattern.CASE_INSENSITIVE);
            Matcher matcher_green = pattern_green.matcher(line);

            while (matcher_blue.find()) {
                int i = matcher_blue.start();
                int k = i - 3;

                while (!(line.substring(k, k + 1)).equals(" ")) {
                    k = k - 1;
                }

                int tmp_blue_amount = Integer.parseInt(line.substring(k + 1, i - 1));

                if (target_blue_value < tmp_blue_amount) {
                    possible_blue = false;
                    break;
                } else {
                    possible_blue = true;
                }
            }

            while (matcher_red.find()) {
                int i = matcher_red.start();
                int k = i - 3;

                while (!(line.substring(k, k + 1)).equals(" ")) {
                    k = k - 1;
                }

                int tmp_red_amount = Integer.parseInt(line.substring(k + 1, i - 1));

                if (target_red_value < tmp_red_amount) {
                    possible_red = false;
                    break;
                } else {
                    possible_red = true;
                }
            }

            while (matcher_green.find()) {
                int i = matcher_green.start();
                int k = i - 3;

                while (!(line.substring(k, k + 1)).equals(" ")) {
                    k = k - 1;
                }

                int tmp_green_amount = Integer.parseInt(line.substring(k + 1, i - 1));

                if (target_green_value < tmp_green_amount) {
                    possible_green = false;
                    break;
                } else {
                    possible_green = true;
                }
            }

            if (possible_red == true && possible_green == true && possible_blue == true) {
                total_game_index = total_game_index + current_index;
            }
            current_index = current_index + 1;
        }

        return total_game_index;
    }

    public static int MinValueCubes(List<String> allLines){
        
        int[] powerPerGame = new int[allLines.size()];
        int sumOfPowers = 0;
        
        int gameIndex = 1;

        for (String line : allLines) {
            Pattern pattern_blue = Pattern.compile("blue", Pattern.CASE_INSENSITIVE);
            Matcher matcher_blue = pattern_blue.matcher(line);
            Pattern pattern_red = Pattern.compile("red", Pattern.CASE_INSENSITIVE);
            Matcher matcher_red = pattern_red.matcher(line);
            Pattern pattern_green = Pattern.compile("green", Pattern.CASE_INSENSITIVE);
            Matcher matcher_green = pattern_green.matcher(line);
            int max_blue = 0;
            int max_red =0;
            int max_green = 0;

            while (matcher_blue.find()) {
                int i = matcher_blue.start();
                int k = i-3;
                
                while (!(line.substring(k, k+1)).equals(" ")){
                    k = k-1;
                }

                int blue_amount = Integer.parseInt(line.substring(k+1,i-1));

                if (blue_amount > max_blue) {
                    max_blue = blue_amount;
                }       
            }

            while (matcher_red.find()) {
                int i = matcher_red.start();
                int k = i-3;
                
                while (!(line.substring(k, k+1)).equals(" ")){
                    k = k-1;
                }

                int red_amount = Integer.parseInt(line.substring(k+1,i-1));

                if (red_amount > max_red) {
                    max_red = red_amount;
                }
            }

            while (matcher_green.find()) {
                int i = matcher_green.start();
                int k = i-3;
                
                while (!(line.substring(k, k+1)).equals(" ")){
                    k = k-1;
                }

                int green_amount = Integer.parseInt(line.substring(k+1,i-1));

                if (green_amount > max_green) {
                    max_green = green_amount;
                }
                
            }

            int powerOfGame = max_blue*max_green*max_red;
            powerPerGame[gameIndex-1] = powerOfGame;
            gameIndex = gameIndex+1;
        }

        for (int i = 0; i< powerPerGame.length; i++ ){
            sumOfPowers = sumOfPowers + powerPerGame[i];
        }

        return sumOfPowers;
    }

    public static void main(String args[]) {
        int target_red_value = 12;
        int target_green_value = 13;
        int target_blue_value = 14;
        String document_location = "day2input.txt";
        List<String> allLines = ReadFile(document_location);
        int total_game_index = ColorCheck(target_red_value, target_green_value, target_blue_value, allLines);
        System.out.println("Sum of possible solvable games: " + total_game_index);
        int sumOfPowers = MinValueCubes(allLines);
        System.out.println("Sum of powers: "+ sumOfPowers);
    }
}
