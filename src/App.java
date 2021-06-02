/*
* @author  Ömer Can Baltacı
*/
import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        String input = readFromInput();
        System.out.println(calculate(input));
    }

    private static int calculate(String input) {
        int row = input.split("\n").length;
        int col = input.split("\n")[row - 1].split(" ").length;
        int [][] arr = new int[row][col];

        // Creating a 2D array from the input
        for (int i = 0; i < row; i++) {
            int flag = i + 1;
            for (int j = 0; j < col; j++) {
                if (flag > 0) {
                    arr[i][j] = Integer.parseInt(input.split("\n")[i].split(" ")[j]);
                    flag--;
                }
                else arr[i][j] = 0;
            }
        }

        // Starting from the bottom and going upward, if a number is not a prime number,
        // then the maximum of its adjacent numbers will be added onto it. Then the top of
        // the pyramid will contain the result. This way, a path doesn't have to be drawn
        // everytime we have to compare 2 nonprime equal numbers by going downward.
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < col - 1; j++) {
                if (isPrime(arr[i][j]))
                    arr[i][j] = Integer.MIN_VALUE;
                else if (i + 1 < arr.length)
                    arr[i][j] += Math.max(arr[i + 1][j], arr[i + 1][j + 1]);
            }
        }
        
        return arr[0][0];
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        
        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false; 
        }

        return true;
    }

    private static String readFromInput() {
        try {
            String data = "";
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
              data += myReader.nextLine() + "\n";
            }

            myReader.close();
            
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            
            return null;
        }
    }
}
