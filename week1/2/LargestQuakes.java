
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int maxIndex = 0;
        double maxMag = 0;
            for(int k=0; k < quakeData.size(); k++){
                QuakeEntry quake = quakeData.get(k);
                double mag = quake.getMagnitude();
                if (mag > maxMag){
                    maxIndex=k;
                    maxMag = mag;
                }
            }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int j=0; j < howMany; j++){
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
       }
        return ret;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        //Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> large = getLargest(list,5);
        EarthQuakeClient client = new EarthQuakeClient();
        client.printQuakes(large);
        System.out.println("number found: "+large.size());
    }
}
