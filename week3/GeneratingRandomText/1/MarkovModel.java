
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;


public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int param;
    
    public MarkovModel(int n) {
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
        sb.append(key);        
        for(int k=0; k < numChars-param; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
        int pos = 0;
        ArrayList<String> follows = new ArrayList<String>();
        while (pos < myText.length()- key.length()){
            int index = myText.indexOf(key, pos);
            if (index == -1){break;}
            if (index > myText.length()- key.length()-1){break;}
            follows.add(myText.substring(index+key.length(),index+key.length()+1));
            pos = index + 1;
        }
    
        return follows;
       }
}
