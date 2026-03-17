import java.io.File;

public class prettifier {

    public static void main(String[] args){

        //call checkArgs
        //usage.checkArgs(args);

        String csv = args[0];
        File csvFile = new File(csv);

        String airport = args[1];

        csvReader.csvRead(csvFile, airport);


        
    }
    
}
