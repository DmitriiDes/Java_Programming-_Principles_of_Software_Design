
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int param;
    private HashMap<String, ArrayList<String>> MyMap = new HashMap<String, ArrayList<String>>();
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        param = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-param);
        String key = myText.substring(index, index+param);
        buildMap();
        sb.append(key);        
        for(int k=0; k < numChars-param; k++){
            
            if (MyMap.get(key).isEmpty()){break;}
            
            index = myRandom.nextInt(MyMap.get(key).size());
            String next = MyMap.get(key).get(index);
                
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
       return ("Efficient Markov model of order"+" "+param);
       }
       
    //public void buildMap (String key){
    //   if (!MyMap.containsKey(key)){
    //       ArrayList<String> follows = getFollows(key);
    //       MyMap.put(key, follows);
    //   }
    //}
    
    public void buildMap (){
       for(int k=0; k <= myText.length()-param; k++){
           String key = myText.substring(k, k+param);
           if (!MyMap.containsKey(key)){
               ArrayList<String> follows = getFollows(key);
               MyMap.put(key, follows);
           }
           
       }
    }
    
    public ArrayList<String> getFollowsEf(String key){
        return MyMap.get(key);
    }
       
    public void printMyMapInfo(){
       if (MyMap.size()<15){
           System.out.println("Elements:");
           for (String i: MyMap.keySet()){
            System.out.println(i+": "+MyMap.get(i));
           }
       }
       System.out.println("Size:");
       System.out.println(MyMap.size());
       int max = maxSize();
       System.out.println("Max Size of element:");
       System.out.println(max);
       System.out.println("Keys with Max Size of element:");
       for (String i: MyMap.keySet()){
           if(max == MyMap.get(i).size()){
           System.out.print(i+", ");}
           }
    }
    
    private int maxSize(){
        int maxS = 0;
        for (ArrayList<String> i: MyMap.values()){
            if(maxS<i.size()){maxS = i.size();};
        }
        return maxS;
    }
    
    
}
