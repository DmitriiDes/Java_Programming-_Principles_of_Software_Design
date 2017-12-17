
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class tester {
    public void testGetFollows(){
        MarkovOne markovOne = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markovOne.setTraining(st);
        ArrayList<String> result = markovOne.getFollows("es");
        for (String i: result){
            System.out.print(i+", ");
        } 
        System.out.println();
        System.out.println(result.size());
    }
    
    public void testGetFollowsWithFile(){
       MarkovOne markovOne = new MarkovOne();
       FileResource fr = new FileResource();
       String st = fr.asString();
       markovOne.setTraining(st);
       ArrayList<String> result = markovOne.getFollows("th");
       for (String i: result){
           System.out.print(i+", ");
       } 
       System.out.println();
       System.out.println(result.size());
    }
    
    public void testStringCut(){
        
        String st = "0123456789";
        System.out.println(st.substring(st.indexOf("5", 3)+1, st.indexOf("5", 3)+2));
    }
}
