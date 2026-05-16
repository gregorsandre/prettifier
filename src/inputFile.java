import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class inputFile {

    public static HashMap<Integer, String> input(File inputTxt) {

        HashMap<Integer, String> toConvert = new HashMap<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(inputTxt))) {

            String line;
            int i = 0;

            while ((line = bf.readLine()) != null) {

                String[] parts = line.trim().split("\\s+");

                for (String part : parts) {

                    if (part.startsWith("*##") || part.startsWith("*#") || part.startsWith("##") || part.startsWith("#")) {
                        toConvert.put(i, part);
                        i++;
                    }

                }

            }

        } catch (IOException e) {
            System.out.println("Input not found");
        }

        return toConvert;

    }

}
