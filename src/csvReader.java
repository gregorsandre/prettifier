import java.io.*;
import java.util.ArrayList;

public class csvReader{

    /*
    public static void main(String[] args){

        String lookup = args[0];
        File file = new File(lookup);
        String iata = "LAX";
        
        csvRead(file, iata);

    }
    */
    
    public static ArrayList<String> csvRead(File airportLookup, ArrayList<String> airportCode){
        
        ArrayList<String> airportName = new ArrayList<String>();
        
        
        try(BufferedReader bf = new BufferedReader(new FileReader(airportLookup))){

            String line;
            while((line = bf.readLine()) != null){

                String[] splittedLine = line.split(",");
                if(splittedLine[4].contentEquals(airportCode.get(0))){
                    
                    airportName.add(splittedLine[0]);

                }
                
            }

        }
        catch(IOException e){

            System.out.println(e);

        }

        try(BufferedReader bf = new BufferedReader(new FileReader(airportLookup))){

            String line;
            while((line = bf.readLine()) != null){

                String[] splittedLine = line.split(",");
                if(splittedLine[4].contentEquals(airportCode.get(1))){
                    
                    airportName.add(splittedLine[0]);

                }
                
            }

        }
        catch(IOException e){

            System.out.println(e);

        }


        return airportName;

    }

}