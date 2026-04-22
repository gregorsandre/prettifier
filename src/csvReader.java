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
            if(headers.length < 6) {
                System.out.println("Airport lookup malformed");
                System.exit(0);
            }    
            HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
            
            for(int j = 0; j < headers.length; j++){
                if(headers[j].isEmpty()){
                    System.out.println("Airport lookup malformed");
                    System.exit(0);
                }
                columnMap.put(headers[j], j);
            }

            if(!columnMap.containsKey("name") || !columnMap.containsKey("icao_code") || !columnMap.containsKey("iata_code") || !columnMap.containsKey("municipality")){                       
                System.out.println("Airport lookup malformed");
                System.exit(0);
            } 

            int nameIndex = columnMap.get("name");
            int ICAOIndex = columnMap.get("icao_code");
            int IATAIndex = columnMap.get("iata_code");
            int cityIndex = columnMap.get("municipality");

            HashMap<String, String> lookup = new HashMap<>();
            
            while((line = bf.readLine()) != null){

                String[] splittedLine = line.split(",");
                String iataCode = splittedLine[IATAIndex];                                
                String icaoCode = splittedLine[ICAOIndex];
                String city = splittedLine[cityIndex];                                    
                String name = splittedLine[nameIndex];

                if(name.isEmpty() || iataCode.isEmpty() || icaoCode.isEmpty() || city.isEmpty()){                                            
                    
                    System.out.println("Airport lookup malformed");
                    System.exit(0); 

                }  

                lookup.put(iataCode, name);
                lookup.put(icaoCode, name);
                lookup.put(city, name);                                                       
   
                    
            }

            for(int i = 0; i < toConvert.size(); i++){                                    
                String inSequence = toConvert.get(i);
                                                                                            
                if(inSequence.startsWith("*##")){
                    String code = inSequence.substring(3); 
                    String suffix = "";
                    while(code.length() > 0 &&
                        !Character.isLetter(code.charAt(code.length() - 1))){          
                        suffix = code.charAt(code.length() - 1) + suffix;
                        code = code.substring(0, code.length() - 1);           
                    }    
                    String name = lookup.get(code);                       
                    converted.put(i, (name != null ? name : inSequence.substring(0, inSequence.length() - suffix.length())) + suffix);
                                                                                            
                } else if(inSequence.startsWith("##")){
                    String code = inSequence.substring(2, 6);
                    String name = lookup.get(code);                             
                    converted.put(i, name != null ? name : inSequence);                                   
            
                } else if(inSequence.startsWith("#")){                                    
                    String code = inSequence.substring(1, 4);
                    String name = lookup.get(code);
                    converted.put(i, name != null ? name : inSequence);                                   
                }
                else{
                    continue;
                }

            } 
                
                
        }
        catch(IOException e){

            System.out.println("couldnt read");

        }

        return converted;
    
    }

}