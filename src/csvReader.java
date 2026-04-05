import java.io.*;
import java.util.HashMap;

public class csvReader{

    
    public static void main(String[] args){

        String lookup = args[0];
        File file = new File(lookup);
        String iata = "LAX";
        
        //csvRead(file);

    }
    

    
    public static HashMap<Integer, String> csvRead(File airportLookup, HashMap<Integer, String> toConvert){
        
        String line;
        HashMap<Integer, String> converted = new HashMap<>();
        
        try(BufferedReader bf = new BufferedReader(new FileReader(airportLookup))){

            
            String headerLine = bf.readLine();
            String[] headers = headerLine.split(",");
            HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
            for(int j = 0; j < headers.length; j++){
                columnMap.put(headers[j], j);
            }
            int nameIndex = columnMap.get("name");
           
            int i = 0;
            String inSequence = toConvert.get(i);
            //System.out.println(inSequence);
            
            
            while((line = bf.readLine()) != null){

                String[] splittedLine = line.split(",");

                        //iata
                    if(inSequence.startsWith("#[a-zA-z][a-zA-z]")){

                        inSequence = inSequence.substring(1, 4);
                        int IATAIndex = columnMap.get("iata_code");
                        if(inSequence.equals(splittedLine[IATAIndex])){
                            converted.put(i, splittedLine[nameIndex]);
                            i++;

                        }
                        else{
                            continue;
                        }

                    }   //icao
                    else if(inSequence.startsWith("##[a-zA-z]")){    
                    
                        inSequence = inSequence.substring(2, 6);
                        int ICAOIndex = columnMap.get("icao_code");
                        if(inSequence.equals(splittedLine[ICAOIndex])){
                            converted.put(i, splittedLine[nameIndex]);
                            i++;
                        }
                        else{
                            continue;
                        }

                    }   //city
                    else if(inSequence.startsWith("*##")){

                        inSequence = inSequence.substring(3);
                        int cityIndex = columnMap.get("municipality");
                        if(inSequence.equals(splittedLine[cityIndex])){
                            converted.put(i, splittedLine[cityIndex]);
                            i++;
                        }
                        else{
                            continue;
                        }
                        
                    }    
            
                    
            }
                
                
        }
        catch(IOException e){

            System.out.println("couldnt read");

        }

        return converted;
    
    }

}