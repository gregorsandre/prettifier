import java.io.File;
import java.util.HashMap;


public class prettifier {


    public static void main(String[] args){
        // input.txt output.txt airport-lookup.csv


        if(args.length == 1 && args[0].equals("-h")){

            System.out.println("itinerary usage:");
            System.out.println("$ java Prettifier.java ./input.txt ./output.txt ./airport-lookup.csv");
            System.exit(0);

        }
        else if(args.length != 3){
            System.out.println("itinerary usage:");
            System.out.println("$ java Prettifier.java ./input.txt ./output.txt ./airport-lookup.csv");
            System.exit(0);
        }

        //converts arguments from string to file 
        String input = args[0];
        File inputTxt = new File(input);
        if(!inputTxt.exists()){
            System.out.println("Input not found");
            System.exit(0);
        }


        String output = args[1];
        File outputTxt = new File(output);
        
        String csv = args[2];
        File airportlookup = new File(csv);
        if(!airportlookup.exists()){
            System.out.println("Airport lookup not found");
            System.exit(0);
        }

        HashMap<Integer, String> toConvert = inputFile.input(inputTxt);

        HashMap<Integer, String> converted = csvReader.csvRead(airportlookup, toConvert);

        outputFile.outputConversion(inputTxt, outputTxt, converted);


        
    }
    
}
