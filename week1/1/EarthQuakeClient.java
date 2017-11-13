import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData){
            if (quake.getMagnitude()>magMin){ 
                answer.add(quake);
            }        
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData){
            
            if (quake.getLocation().distanceTo(from)<(distMax*1000)){
                answer.add(quake);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double depthMin,
    double depthMax) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry quake : quakeData){
            double qdepth = quake.getDepth();
            if (qdepth>depthMin && qdepth<depthMax){
                answer.add(quake);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where,
    String Phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if (where.contains("start")){
            for (QuakeEntry quake : quakeData){
                if (quake.getInfo().startsWith(Phrase)){
                    answer.add(quake);
                }
            }        
        }
        if (where.contains("end")){
            for (QuakeEntry quake : quakeData){
                if (quake.getInfo().endsWith(Phrase)){
                    answer.add(quake);
                }
            }   
        }
        if (where.contains("any")){
            for (QuakeEntry quake : quakeData){
                double qdepth = quake.getDepth();
                if (quake.getInfo().contains(Phrase)){
                    answer.add(quake);
                }
            }
        
        }
        return answer;
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    
    public void printQuakes(ArrayList<QuakeEntry> list){
        System.out.println("# of Quakes: "+list.size());
        for(QuakeEntry qe : list){
            System.out.printf("(%4.2f,%4.2f),mag = %4.2f, depth = %4.2f, title = %s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),                
                qe.getMagnitude(),
                qe.getDepth(),
                qe.getInfo());
        }
    
    }
    
    public void printQuakesWithDist(ArrayList<QuakeEntry> list, Location from){
        System.out.println("# of Quakes: "+list.size());
        for(QuakeEntry qe : list){
            System.out.printf(
                "(%4.2f,%4.2f), dist = %4.2f, mag = %4.2f, depth = %4.2f, title = %s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getLocation().distanceTo(from)/1000,
                qe.getMagnitude(),
                qe.getDepth(),
                qe.getInfo());
        }
    
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, 5.0);
        printQuakes(bigQuakes);
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        //ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list,1000,city);
        //printQuakes(closeQuakes);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list,1000,city);
        printQuakesWithDist(closeQuakes,city);
        // TODO
    }
    
    public void QuakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> depthQuakes = filterByDepth(list,-10000,-5000);
        printQuakes(depthQuakes);
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> depthQuakes = filterByPhrase(list,"start","Explosion");
        printQuakes(depthQuakes);
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
