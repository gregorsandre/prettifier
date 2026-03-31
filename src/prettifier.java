import java.nio.file.*;
import java.io.File;
import java.util.HashMap;


public class prettifier {


    public static void main(String[] args){
        // input.txt output.txt airport-lookup.csv


        if(args.length == 1 && args[0].equals("-h")){

            System.out.println("itinerary usage:");
            System.out.println("$ java Prettifier.java ./input.txt ./output.txt ./airport-lookup.csv");

        }

        if(args.length != 3){
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
        Path source = inputTxt.toPath();
        Path target = outputTxt.toPath();
        try{
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(Exception e){
            System.out.println("couldnt copy!!!");
        }
        
        String csv = args[2];
        File airportlookup = new File(csv);
        if(!airportlookup.exists()){
            System.out.println("Airport lookup not found");
            System.exit(0);
        }

        HashMap<Integer, String> toConvert = inputFile.input(inputTxt);

        HashMap<Integer, String> converted = csvReader.csvRead(airportlookup, toConvert);

        System.out.println(converted);
/*
        outputFile outputFile = new outputFile();
        outputFile.outputConversion(outputTxt);

        // calls input method which returns airport codes in an arraylist
        //ArrayList<String> airportCode = inputFile.input(inputTxt);

        // calls csvread method which returns full airport names in an arraylist
        //ArrayList<String> airportName = csvReader.csvRead(airportlookup, airportCode);

        //to make output format right
        //outputFile.output(outputTxt, airportName);

        /*
        
        inputFile.inpu(); to extract departure and arrival airports

        csvReader.csvRead(); to extract full airport name from the csv file

        outputFile.output(); to make itinerary user readable

        
        every method returns to prettifier, prettifier then calls the next method

        */


        
    }
    
}
