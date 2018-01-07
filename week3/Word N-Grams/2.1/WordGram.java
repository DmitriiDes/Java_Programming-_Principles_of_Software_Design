
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(String word:myWords){ret +=" "+word;}
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;        
        if (this.length() != other.length()){return false;}
        
        //if (!this.toString().equals(other.toString())){return false;}
        for(int k=0; k<myWords.length; k++){
            if(! myWords[k].equals(other.wordAt(k))){return false;}            
        }
        
        return true;
    }

    public WordGram shiftAdd(String word) { 
        String[] newMyWords = new String[myWords.length];
        for(int k=0; k<myWords.length-1; k++){
            newMyWords[k] = myWords[k+1];
        }
        newMyWords[myWords.length-1] = word;
        WordGram out = new WordGram(newMyWords, 0, myWords.length);
        
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        return out;
    }
    
    public int hashCode(){
        String content = this.toString();
        myHash = content.hashCode();
        return myHash;
    }

}