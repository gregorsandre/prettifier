import java.io.*;
import java.util.ArrayList;

public class outputFile {

    public static void output(File outputFile ,ArrayList<String> airpotName){
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))){

            bw.append(airpotName.get(0));
            bw.append(" ");
            bw.append(airpotName.get(1)); 

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

}