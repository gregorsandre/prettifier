import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class inputFile {

    public static void main(String[] args){

        File file = new File(args[0]);
        
        input(file);

    }
    
    public static ArrayList<String> input(File inputtxt){

        ArrayList<String> ar = new ArrayList<String>();
        
        try(BufferedReader bf = new BufferedReader(new FileReader(inputtxt))){
            
            String line = bf.readLine();
            String[] parts = line.split(" ");
            for(String part : parts){

                if(part.startsWith("#")){

                    part = part.substring(1, part.length() - 1);
                    ar.add(part);

                }

            }

        }
        catch(IOException e){
            
            System.out.println("file not found");

        }

        return ar;

    }
    
}