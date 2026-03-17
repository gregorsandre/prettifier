import java.io.File;
import java.util.ArrayList;

public class prettifier {

    public static void main(String[] args){
        // input.txt output.txt airport-lookup.csv

        //converts arguments from string to file 
        String input = args[0];
        File inputTxt = new File(input);

        String output = args[1];
        File outputTxt = new File(output);
        
        String csv = args[2];
        File airportlookup = new File(csv);


        // calls input method which returns airport codes in an arraylist
        ArrayList<String> airportCode = inputFile.input(inputTxt);

        // calls csvread method which returns full airport names in an arraylist
        ArrayList<String> airportName = csvReader.csvRead(airportlookup, airportCode);

        //
        outputFile.output(outputTxt, airportName);

        /*
        
        inputFile.inpu(); to extract departure and arrival airports

        csvReader.csvRead(); to extract full airport name from the csv file

        outputFile.output(); to make itinerary user readable

        
        every method returns to prettifier, prettifier then calls the next method

        */


        
    }
    
}
