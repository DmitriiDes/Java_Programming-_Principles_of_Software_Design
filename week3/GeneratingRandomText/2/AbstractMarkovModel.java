
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel{
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key){
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
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    abstract public String toString();
    
    abstract public String getRandomText(int numChars);

}
