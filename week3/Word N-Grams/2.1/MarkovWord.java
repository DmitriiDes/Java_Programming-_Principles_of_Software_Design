
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;


public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int k = indexOf(myText,kGram,0);
        if (k == -1){return follows;}
        
        while (k < myText.length-kGram.length()){
            follows.add(myText[k+kGram.length()]);
            k = indexOf(myText,kGram,k+1);
            if (k == -1){break;}
        }
        return follows;
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        
        for (int k = start; k < words.length-target.length(); k++){
            for(int i = 0; i < target.length(); i++){
                if (!words[k+i].equals(target.wordAt(i))){break;};
                if (i == target.length()-1){return k;};
            }
        }
        
        return -1;
    }
    
    
}
