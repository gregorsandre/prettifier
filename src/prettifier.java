import java.io.File;
import java.util.HashMap;

public class prettifier {

    public static void main(String[] args) {

        if (args.length == 1 && args[0].equals("-h") || args.length != 3) {
            printUsage();
            System.exit(0);
        }

        File inputTxt = new File(args[0]);
        if (!inputTxt.exists()) {
            System.out.println("Input not found");
            System.exit(0);
        }

        File outputTxt = new File(args[1]);

        File airportLookup = new File(args[2]);
        if (!airportLookup.exists()) {
            System.out.println("Airport lookup not found");
            System.exit(0);
        }

        HashMap<Integer, String> toConvert = inputFile.input(inputTxt);
        HashMap<Integer, String> converted = csvReader.csvRead(airportLookup, toConvert);
        outputFile.outputConversion(inputTxt, outputTxt, converted);
    }

    private static void printUsage() {
        System.out.println("itinerary usage:");
        System.out.println("$java -cp build prettifier ./input.txt ./output.txt ./airport-lookup.csv");
    }

}
