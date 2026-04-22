import java.io.*;
import java.util.HashMap;

public class outputFile {

    public static void outputConversion(File inputTxt, File outputTxt,            
    HashMap<Integer, String> converted){

        try(BufferedReader bf = new BufferedReader(new FileReader(inputTxt));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputTxt))){
            
            String line;
            int i = 0;
            int blankCount = 0;

            while((line = bf.readLine()) != null){

                String[] subLines = line.split("[\u000B\u000C]", -1);    

                for(String subLine : subLines){                            
            
                    String[] parts = subLine.split(" ");

                    StringBuilder newLine = new StringBuilder();

                    for(int j = 0; j < parts.length; j++){
                        
                        if(parts[j].startsWith("*##") || parts[j].startsWith("##") || parts[j].startsWith("#")){

                            newLine.append(converted.get(i));
                            i++;

                        }
                        else if(parts[j].startsWith("D(") ||                         
                            parts[j].startsWith("T12(") || parts[j].startsWith("T24(")){
                            newLine.append(formatToken(parts[j]));                     
                                                                                        
                        }
                        else{
                            newLine.append(parts[j]);

                        }

                        if(j < parts.length - 1){
                            
                            newLine.append(" ");

                        }
                    
                    }

                    String result = newLine.toString();

                    if(result.trim().isEmpty()){                                   
                        blankCount++;                                              
                        if(blankCount > 1){                                        
                            continue;                                              
                        }
                    } else {
                        blankCount = 0;
                    }
                        
                    bw.write(result);
                    bw.newLine();

                }

            }

        }
        catch(IOException e){
            
            System.out.println("file not found");

        }




    }

    public static String formatToken(String token){
                                                                 
      int closeIndex = token.lastIndexOf(')');
      String suffix = token.substring(closeIndex + 1);           
                  
        String prefix;
        String inner;
                                                                    
        if(token.startsWith("T12(")){
            prefix = "T12";                                        
            inner = token.substring(4, closeIndex);
        } else if(token.startsWith("T24(")){
            prefix = "T24";                                        
            inner = token.substring(4, closeIndex);
        } else {                                                   
            prefix = "D";
            inner = token.substring(2, closeIndex);
        }

        String[] splitOnT = inner.split("T");                          
        String datePart = splitOnT[0];                                 
        String timeAndOffset = splitOnT[1];                            
        
        String[] dateParts = datePart.split("-");                      
        String year = dateParts[0];                                  
        String monthNum = dateParts[1]; 
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};                                  
        String monthName = months[Integer.parseInt(monthNum) - 1];                               
        String day = dateParts[2];
                                                                        
        String time = timeAndOffset.substring(0, 5);                   
        String offset = timeAndOffset.substring(5);

        if(!offset.equals("Z") && offset.length() != 6){
            return token;                                              
        }
                                                                        
        if(offset.equals("Z")){                                      
            offset = "+00:00";
        }
                                                                        
        if(prefix.equals("D")){                                        
            return day + " " + monthName + " " + year + suffix;
                                                                        
        } 
        else if(prefix.equals("T24")){
            return time + " (" + offset + ")" + suffix;                
                            
        } 
        else {
            int hours = Integer.parseInt(time.substring(0, 2));
            String minutes = time.substring(3, 5);                     
            String ampm;           
            int displayHours; 

            if(hours == 0){
                displayHours = 12; ampm = "AM";
            } 
            else if(hours < 12)
                { displayHours = hours; ampm = "AM";
            }
            else if(hours == 12){
                displayHours = 12; ampm = "PM";     
            } 
            else {
               displayHours = hours - 12; ampm = "PM";                                                          
            }  

            return String.format("%02d", displayHours) + ":" + minutes + ampm + " (" + offset + ")" + suffix;      
        
        }  
                                                 
    }

}