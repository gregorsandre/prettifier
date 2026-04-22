import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;

public class inputFile {
    
    public static HashMap<Integer, String> input(File inputtxt){

        HashMap<Integer, String> toConvert = new HashMap<>();

        
        try(BufferedReader bf = new BufferedReader(new FileReader(inputtxt))){
            
            String line;
            int i = 0;

            while((line = bf.readLine()) != null){
                
                String[] parts = line.split(" ");

                for(String part : parts){

                    if(part.startsWith("#") || part.startsWith("##") || part.startsWith("*##") ){

                        toConvert.put(i, part);
                        //csvReader.csvRead(prettifier.airportLookup);

                        i++;

                    }
                    

                }

            }

        }
        catch(IOException e){
            
            System.out.println("file not found");

        }

        return toConvert;


    }
    
}