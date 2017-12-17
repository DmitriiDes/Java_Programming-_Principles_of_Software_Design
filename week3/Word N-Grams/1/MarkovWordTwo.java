
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println(key+":");
            //for (String word:follows){System.out.print(word+" ");}
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            //String next2 = follows.get(index+1);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
            //key2 = next2;
        }
        
        return sb.toString().trim();
    }
    
    public ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int k = indexOf(myText,key1, key2,0);
        while (k < myText.length-2){
            follows.add(myText[k+2]);
            k = indexOf(myText,key1, key2,k+2);
            if (k == -1){break;}
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target1, String target2, int start){
        for (int k=0; k < words.length-1; k++){
            if ( k>=start && words[k].equals(target1) && words[k+1].equals(target2)){return k;}
        }
        return -1;
    }
    
    public void testIndexOf(){
        String st = "this is just a test yes this is a simple test";
        setTraining(st);
        System.out.println(indexOf(myText,"this","is",0));
        System.out.println(getFollows("this","is"));
        System.out.println(indexOf(myText,"just","a",0));
        System.out.println(getFollows("just","a"));
    }
}
