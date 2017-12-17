
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(key+":");
            //for (String word:follows){System.out.print(word+" ");}
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int k = indexOf(myText,key,0);
        while (k < myText.length-1){
            follows.add(myText[k+1]);
            k = indexOf(myText,key,k+1);
            if (k == -1){break;}
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target, int start){
        int k = 0;
        for (String word:words){
            if (k>=start && word.equals(target)){return k;}
            k++;
        }
        return -1;
    }
    
    public void testIndexOf(){
        String st = "this is just a test yes this is a simple test";
        setTraining(st);
        System.out.println(indexOf(myText,"this",0));
        System.out.println(indexOf(myText,"this",3));
        System.out.println(indexOf(myText,"frog",0));
        System.out.println(indexOf(myText,"frog",3));
    }
    
}
