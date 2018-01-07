
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        String TestSt = "this is a test this is a test this is a test of words";
        int seed = 844;
        int textLen = 100;
        MarkovWord markovWordThree = new MarkovWord(5);
        runModel(markovWordThree, st, textLen, seed);
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void testHashMap(){
        String TestSt = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        int seed = 65;
        int textLen = 100;
        EfficientMarkovWord EfficientMarkovWordTwo = new EfficientMarkovWord(2);
        runModel(EfficientMarkovWordTwo, st, textLen, seed);
        EfficientMarkovWordTwo.printHashMapInfo();
    }
    
    public void compareMethods(){
        int order = 2;
        int seed = 42;
        int textLen = 100;
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        long start = System.nanoTime(); 
        MarkovWord markovWordTwo = new MarkovWord(order);
        runModel(markovWordTwo, st, textLen, seed);
        long finish = System.nanoTime();
        long m2Time = finish - start;
        start = System.nanoTime();
        EfficientMarkovWord EfficientMarkovWordTwo = new EfficientMarkovWord(order);
        runModel(EfficientMarkovWordTwo, st, textLen, seed);
        finish = System.nanoTime();
        long Em2Time = finish - start;
        System.out.println(Em2Time-m2Time);
    }

}
