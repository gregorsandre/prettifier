import java.io.*;

public class outputFile {

    public void outputConversion(File outputFile){

        csvReader csvReader = new csvReader();
        inputFile inputFile = new inputFile();
        prettifier prettifier = new prettifier();

        try(BufferedReader bf = new BufferedReader(new FileReader(outputFile))){
            
            String line;
            while((line = bf.readLine()) != null){
                
                String[] parts = line.split(" ");
                int i = 0;

                for(String part : parts){

                    if(part.startsWith("#") || part.startsWith("##") || part.startsWith("*##") ){

                        part = csvReader.converted.get(i);


                        i++;

                    }
                    

                }


                // write parts to output file



            }

        }
        catch(IOException e){
            
            System.out.println("file not found");

        }




    }

}