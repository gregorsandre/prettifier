import java.io.*;

public class csvReader{

    public static void main(String[] args){

        String lookup = args[0];
        File file = new File(lookup);
        String iata = "LAX";
        
        csvRead(file, iata);

    }
    
    public static void csvRead(File airportLookup, String iata){
        
        try(BufferedReader bf = new BufferedReader(new FileReader(airportLookup))){

            String line;
            while((line = bf.readLine()) != null){

                String[] iataStrings = line.split(",");
                if(iataStrings[4].contentEquals(iata)){
                    System.out.println(iataStrings[0]);
                }
                
            }

        }
        catch(IOException e){

            System.out.println(e);

        }

        


    }

}