public class usage{
    
    public static void checkArgs(String[] args){

        if(args.length != 3 || args[0] == "-h"){
            System.out.println("itinerary usage:\r\n" + //
                                "$ java Prettifier.java ./input.txt ./output.txt ./airport-lookup.csv");
        }
        
        

    }
}