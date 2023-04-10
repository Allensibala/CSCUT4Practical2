import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class FilesInOut {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input filepath: ");
        String inputPath = scanner.nextLine().trim();
        if (inputPath.isEmpty()) {
            inputPath = "../Checkpoint4/input.txt";
        }

        System.out.print("Enter output filepath: ");
        String outputPath = scanner.nextLine().trim();
        if (outputPath.isEmpty()) {
            outputPath = inputPath.replaceAll("\\.\\w+$", "") + "output.txt";
        }

        try (Scanner inputScanner = new Scanner(new File(inputPath));
             PrintWriter outputWriter = new PrintWriter(new FileWriter(outputPath))) {

            while (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                String[] words = line.split(" ");

                StringBuilder formattedName = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    String formattedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase().replaceAll("\\d", "");
                    formattedName.append(i == 0 ? formattedWord : " " + formattedWord);
                }

                String dateString = line.substring(line.lastIndexOf(" ") + 1);
                String formattedDate = String.format("%s/%s/%s", dateString.substring(0, 2), dateString.substring(2, 4), dateString.substring(4));

                outputWriter.println(formattedName + " " + formattedDate);
            }

            System.out.println("Output file created: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
}
