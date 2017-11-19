
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
        
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
        String title2 = q2. getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);
        int res = title1.compareTo(title2);
        if (res == 0){return Double.compare(q1.getMagnitude(), q2.getMagnitude());};
        return res;
    }
    
    
}
