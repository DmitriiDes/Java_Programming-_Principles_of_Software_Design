
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;


public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<String, ArrayList<String>>();
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
        buildMapFull();
        //buildMap(key);
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
            //buildMap(key);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollowsMap(WordGram kGram) {
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
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        String sGramKey = kGram.toString();
        if (!myMap.containsKey(sGramKey)){return follows;}
        follows = myMap.get(kGram.toString());
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
    
    private void buildMap(WordGram kGram){
        String sGramKey = kGram.toString();
        if (!myMap.containsKey(sGramKey)){
            myMap.put(sGramKey, this.getFollowsMap(kGram));
        }
    
    }
    
    private void buildMapFull(){
        int index = 0;
        WordGram key = new WordGram(myText, index, myOrder);
        buildMap(key);
        for(int k=0; k < myText.length-myOrder; k++){
            String next = myText[k+myOrder];
            key = key.shiftAdd(next);
            buildMap(key);
        }
    }
    
    private int LargestValueInHM(){
        int max = 0;
        for(String k: myMap.keySet()){
            if (max<myMap.get(k).size()){max = myMap.get(k).size();}
        };
            return max;
    }
    
    private void printKeyWithValue(int val){
        for(String k: myMap.keySet()){
            if (val == myMap.get(k).size()){System.out.print(k+" : "+myMap.get(k)+" ; ");}
        };
    }
    
    public void printHashMapInfo(){
        int myMapSize = myMap.size();
        int max = this.LargestValueInHM();
        if (myMapSize < 5){
            for(String k: myMap.keySet()){
                System.out.println(k+": " + myMap.get(k));
            };
        }
        System.out.println();
        System.out.println("HM Info");
        System.out.println("Map size: " + myMapSize);
        System.out.println("The size of Largest HM value: " + max);
        System.out.println("The keys with Largest HM value: ");
        //this.printKeyWithValue(max);
    
    }
}
