For the following assignments, you will start with the files provided, using most of the classes, and modifying only a few of them, and create a new class. The classes provided are:
<ul><li>
The class Location, from the Android platform and revised for this course, a data class representing a geographic location. One of the constructors has parameters latitude and longitude, and one of the public methods is distanceTo.
</li><li>The class QuakeEntry, from the lesson, which has a constructor that requires latitude, longitude, magnitude, title, and depth. It has several get methods and a toString method.
</li><li>The class EarthQuakeParser, from the lesson, which has a read method with one String parameter that represents an XML earthquake data file and returns an ArrayList of QuakeEntry objects.
</li><li>The class EarthQuakeClient, which has been started for you and creates an EarthQuakeParser to read in an earthquake data file, creating an ArrayList of QuakeEntrys. You can test the program with the method createCSV to store an ArrayList of the earthquake data and print a CSV file. You will complete the methods that filter magnitude and distance in this class and add additional methods to it.
</li><li>The class ClosestQuakes, which has been started for you to find the ten closest quakes to a particular location. You will complete this method.
</li></ul>
<h3>Assignment 1: Filtering by Magnitude and Distance</h3>
<p>
In this assignment you will complete the program to filter earthquake data by magnitude and distance, which was described in this lesson in the videos “Coding a Magnitude Filter” and “Coding a Distance Filter.”
</p><p>
Specifically, for this assignment, you will only modify one class, the EarthQuakeClient class:
</p><ul><li>
Write the method filterByMagnitude that has already been started for you. This method has two parameters, an ArrayList of type QuakeEntry named quakeData, and a double named magMin. This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that have a magnitude larger than magMin. Notice that we have already created an ArrayList named answer for you to store those earthquakes that satisfy this requirement.
</li><li>Modify the method bigQuakes that has no parameters to use filterByMagnitude and print earthquakes above a certain magnitude, and also print the number of such earthquakes. Currently this method reads data on earthquakes from a file, stores a QuakeEntry for each earthquake read in the ArrayList named list, and prints out the number of earthquakes read in. 
</li><li>Write the method filterByDistanceFrom that has already been started for you. This method has three parameters, an ArrayList of type QuakeEntry named quakeData, a double named distMax, and a Location named from. This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that are less than distMax from the location from. Notice that we have already created an ArrayList named answer for you to store those earthquakes that satisfy this requirement.
</li><li>Modify the method closeToMe that has no parameters to call filterByDistance to print out the earthquakes within 1000 Kilometers to a specified city (such as Durham, NC). For each earthquake found, print the distance from the earthquake to the specified city, followed by the information about the city (use getInfo()). Currently this method reads data on earthquakes from a URL, stores a QuakeEntry for each earthquake read in the ArrayList named list, and prints out the number of earthquakes read in. It also gives the location for two cities, Durham, NC (35.988, -78.907) and Bridgeport, CA (38.17, -118.82). After making modifications, when you run your program on the file nov20quakedatasmall.atom for the city location Durham, NC, no earthquakes are found.
</li></ul>

<h3>Assignment 2: Filtering by Depth</h3>
<p>
In this assignment you will filter earthquakes by their depth, finding those earthquakes whose depth is between a minimum and maximum value. For more information on what the "depth" of an earthquake means, see the information here: http://earthquake.usgs.gov/learn/topics/seismology/determining_depth.php
</p><p>
Specifically, for this assignment, you will add new methods to one class, the EarthQuakeClient class:
</p><ul><li>
Write the method filterByDepth that has three parameters, an ArrayList of type QuakeEntry named quakeData, a double named minDepth and a double named maxDepth. This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose depth is between minDepth and maxDepth, exclusive. (Do not include quakes with depth exactly minDepth or maxDepth.)
</li><li>Write the void method quakesOfDepth that has no parameters to use filterByDepth and print all the earthquakes from a data source whose depth is between a given minimum and maximum value. You should also print out the number of earthquakes found. 
</li></ul>
<h3>Assignment 3: Filtering by Phrase in Title</h3>
<p>
In this assignment you will filter earthquakes by a phrase in the title given for the earthquake in three ways, finding those earthquakes whose title starts with a phrase, ends with a phrase, or just has a phrase somewhere in the title.
</p><p>
Specifically, for this assignment, you will add new methods to one class, the EarthQuakeClient class:
</p><ul><li>
Write the method filterByPhrase that has three parameters, an ArrayList of type QuakeEntry named quakeData, a String named where that indicates where to search in the title and has one of three values: (“start”, ”end”, or “any”), and a String named phrase, indicating the phrase to search for in the title of the earthquake. The title of the earthquake can be obtained through the getInfo() method. The filterByPhrase method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose titles have the given phrase found at location where (“start” means the phrase must start the title, “end” means the phrase must end the title and “any” means the phrase is a substring anywhere in the title.)
</li><li>Write the void method quakesByPhrase to use filterByPhrase and print all the earthquakes from a data source that have phrase in their title in a given position in the title. You should also print out the number of earthquakes found. 
</li></ul>
<h3>Assignment 4: Finding the Closest Earthquakes to a Location</h3>
<p>
In this assignment you will complete the program to determine the N closests earthquakes to a specified location that was described in this lesson in the video.
</p><p>
Specifically, for this assignment, you will only modify one class, the ClosestQuakes class:
</p><ul><li>
The first method you need has already been written for you. The method findClosestQuakes reads in data on earthquakes storing them in the ArrayList list and prints how many quakes there are. It sets a location variable named jakarta to the location of the city Jakarta. It then calls the method getClosest to determine the ten closest earthquakes in list and prints information about those quakes and how close they are to Jakarta. This method has already been written for you, but doesn’t work yet since the method getClosest is not complete.
</li><li>Complete the method getClosest that has already been started for you. This method has three parameters, an ArrayList of type QuakeEntry named quakeData, a Location named current, and an int named howMany. This method should find the closest number of howMany earthquakes to the current Location and return them in an ArrayList of type QuakeEntry. The earthquakes should be in the ArrayList in order with the closest earthquake in index position 0. If there are fewer then howMany earthquakes in quakeData, then the ArrayList returned would be the same size as quakeData.
</li><li>Now run the method findClosestQuakes by calling getClosest with the location current set to Jakarta (-6.211,106.845) and howMany set to 3.
</li></ul>
<h3>Assignment 5: Finding the Largest Magnitude Earthquakes
</h3><p>
In this assignment you will write a new class and methods to determine the N biggest earthquakes, those with largest magnitude.
</p><p>
Specifically, for this assignment, you will:
</p><ul><li>
Write a new class named LargestQuakes. Be sure to import java.util.*;
</li><li>Write a void method named findLargestQuakes that reads in earthquake data from a source and storing them into an ArrayList of type QuakeEntry. Then it prints all the earthquakes and how many earthquakes that were from the source. You should read in earthquakes from the small file nov20quakedatasmall.atom, print all the earthquakes and also print how many there are. After this works you should comment out the printing of all the earthquakes, but continue to print out the total number of earthquakes read in.
</li><li>Write a method named indexOfLargest that has one parameter, an ArrayList of type QuakeEntry named data. This method returns an integer representing the index location in data of the earthquake with the largest magnitude. You should test out this method by adding code to the method findLargestQuakes to print the index location of the largest magnitude earthquake in the file nov20quakedatasmall.atom and the earthquake at that location. You will see that the largest such earthquake is at location 3 and has magnitude 5.50. Once this works you may want to comment this out.
</li><li>Write a method named getLargest that has two parameters, an ArrayList of type QuakeEntry named quakeData and an integer named howMany. This method returns an ArrayList of type QuakeEntry of the top howMany largest magnitude earthquakes from quakeData. The quakes returned should be in the ArrayList in order by their magnitude, with the largest magnitude earthquake in index position 0. If quakeData has fewer than howMany earthquakes, then the number of earthquakes returned in the ArrayList is equal to the number of earthquakes in quakeData. This method should call the method indexOfLargest.
</li><li>Modify the method findLargestQuakes to call getLargest to print the five earthquakes of largest magnitude from the file nov20quakedatasmall.atom. 
</li></ul>
