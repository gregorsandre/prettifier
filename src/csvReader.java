import java.io.*;
import java.util.HashMap;

public class csvReader{

    public HashMap<Integer, String> converted = new HashMap<>();

    
    public static void main(String[] args){

        String lookup = args[0];
        File file = new File(lookup);
        String iata = "LAX";
        
        //csvRead(file);

    }
    

    
    public void csvRead(File airportLookup){
        
        prettifier prettifier = new prettifier();
        csvReader csvReader = new csvReader();
        inputFile inputFile = new inputFile();
        String line;
        System.out.println(inputFile.toConvert.get(0));


        
        try(BufferedReader bf = new BufferedReader(new FileReader(airportLookup))){

            
            String headerLine = bf.readLine();
            String[] headers = headerLine.split(",");
            HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
            for(int j = 0; j < headers.length; j++){
                columnMap.put(headers[j], j);
            }
            int nameIndex = columnMap.get("name");
            int i = 0;
            System.out.println(inputFile.toConvert.get(0));
            
            
            while((line = bf.readLine()) != null){

                String inSequence = inputFile.toConvert.get(i);

                String[] splittedLine = line.split(",");

                switch (inSequence) {
                    //iata
                    case "#[a-zA-z][a-zA-z]":

                        inSequence = inSequence.substring(1, 3);
                        int IATAIndex = columnMap.get("iata_code");
                        if(inSequence.equals(splittedLine[IATAIndex])){
                            //outputFile.outputConversion(prettifier.outputTxt, splittedLine[nameIndex]);
                            csvReader.converted.put(i, splittedLine[nameIndex]);
                            i++;

                        }
                        break;

                    //icao    
                    case "##[a-zA-z]":
    
                        inSequence = inSequence.substring(2, 5);
                        int ICAOIndex = columnMap.get("icao_code");
                        if(inSequence.equals(splittedLine[ICAOIndex])){
                            //outputFile.outputConversion(prettifier.outputTxt, splittedLine[nameIndex]);
                            csvReader.converted.put(i, splittedLine[nameIndex]);
                            i++;
                        }
                        break;

                    //city
                    case "*##":

                        inSequence = inSequence.substring(3);
                        int cityIndex = columnMap.get("municipality");
                        if(inSequence.equals(splittedLine[cityIndex])){
                            //outputFile.outputConversion(prettifier.outputTxt, splittedLine[cityIndex]);
                            csvReader.converted.put(i, splittedLine[cityIndex]);
                            i++;
                        }
                        break;
                        
                    default:
                        break;
                    
                }
                
                
            }

        }
        catch(IOException e){

            System.out.println(e);

        }


    }

}