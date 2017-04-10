import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Uses words contained in "document.txt" to add and manipulate using
 * a HashMap. HashMap entries are sorted to display the top 20
 * most frequent words, and top 20 least frequent words.   
 * 
 * Assumptions / Restrictions: 
 * Used built-in Java sorting on collections.
 * 
 * Noteworthy features:
 * Dynamically receive "document.txt"
 * 
 * @author Greg Miller
 *
 */

public class HashMapFrequency {

	/**
	 * Declare variables
	 */
    private String line;
	private String result = "";
    private String splitResult[];
    private static final int NUM_WORDS_SORTED = 20;
    private ArrayList<Entry<String, Integer>> sortedWordFreqs;
    private HashMap<String,Integer> map;
    
	public static void main(String[] args) throws IOException {
		
		/**
		 * Instantiate HashMap, sort, print to .csv file
		 */
		HashMapFrequency hmf = new HashMapFrequency();
		hmf.SaveWordFreqsToFile();
	
	}
    
    /**
     * Empty constructor.
     * This function instantiates the HashMap, reads in a file to
     * parse into the HashMap, sorts this HashMap in ascending
     * and descending order, and prints those values
     * @throws IOException
     */
	public HashMapFrequency() throws IOException{
    	map = new HashMap<String, Integer>();

		try{
		
			Path currentRelativePath = Paths.get("");
			String fileName = currentRelativePath.toAbsolutePath().toString();
			fileName += "/resources/document.txt";
			System.out.println("Current relative path is: " + fileName);
			   
			//Create object of FileReader
			FileReader inputFile = new FileReader(fileName);
			
			//Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);
			
			// Read file line by line and print on the console
			while ((line = bufferReader.readLine()) != null)   {
				//convert to lower case and replace all non-alphabetic with an empty string
			line = line.replaceAll("(\\/|\\\\|-|\\+)+", " ");
			result += " " + line.replaceAll("[^a-zA-Z ]", "").toLowerCase();
			}
			          
			bufferReader.close();
		   }catch(Exception e){
			   System.out.println("Error while reading file: " + e.getMessage());                      
		   }
		   
	   splitResult = result.split("\\s+");
	   
	   //add words to hashmap
	   for(int i = 0;i<splitResult.length;i++){
		   //word is key, frequency is value
	   	   //if it contains the word already, then get the value, increase it, and put it
		   if(map.containsKey(splitResult[i])){
			   int val = map.get(splitResult[i]);
			   val = val + 1;
			   map.put(splitResult[i], val);
		   }else{
			   map.put(splitResult[i], 1);
		   }
	   }
	   
       map.remove("");


	    // the descending sorted word frequencies
	    sortedWordFreqs = sortFrequentWords(map, false);
	    
	    /**
	     * Print 20 most frequent words
	     */
	    System.out.println(NUM_WORDS_SORTED + " most frequent words: ");
	    for(int i = 0; i < NUM_WORDS_SORTED; i++){
	        System.out.println(sortedWordFreqs.get(i).getKey() + ": " + 
	                sortedWordFreqs.get(i).getValue().toString());
	    }
	    
	    /**
	     * Descending 20 most frequent words
	     * 
	        the: 6149
			and: 6000
			of: 4848
			to: 4337
			in: 3078
			a: 2812
			that: 1874
			for: 1560
			or: 1264
			with: 1090
			is: 1086
			are: 1016
			health: 911
			on: 910
			as: 856
			students: 845
			soy: 806
			be: 798
			their: 775
			protein: 734
		 *
	     */
	    
        // the ascending sorted word frequencies
        sortedWordFreqs = sortFrequentWords(map, true);
        
        /**
         * Print 20 most infrequent words
         */
        System.out.println("\n" + NUM_WORDS_SORTED + " least frequent words: ");
        for(int i = 0; i < NUM_WORDS_SORTED; i++){
            System.out.println(sortedWordFreqs.get(i).getKey() + ": " + 
                    sortedWordFreqs.get(i).getValue().toString());
        }
        
	    /**
	     * Ascending 20 most frequent words
	     * 
			nicely: 1
			shooting: 1
			hall: 1
			naturalized: 1
			pretend: 1
			wreck: 1
			morton: 1
			investment: 1
			vegetarianism: 1
			cds: 1
			rounded: 1
			hang: 1
			fontana: 1
			transitional: 1
			dieticians: 1
			resuscitation: 1
			gripping: 1
			q: 1
			readers: 1
			obvious: 1
		 *
	     */
	}
    
    /**
     * Saves the sorted word frequencies to a text file that will be used
     * to generate the graph.
     * @param fileName The name of the file to save to.
     * @param sortedWordFreqs The words and frequencies.
     * @throws IOException 
     */
    public void SaveWordFreqsToFile() throws IOException{
    	
    	//declare writers
    	BufferedWriter bw = null;
    	FileWriter fw = null;
    	
        try{
        	//instantiate writers
        	fw = new FileWriter("resources/WordFrequencies.csv");
        	bw = new BufferedWriter(fw);
            
        	//ensure that sortedWordFreqs are sorted in descending order!
    	    sortedWordFreqs = sortFrequentWords(map, false);
    	    
        	/**
        	 * Excel will be reading entries by comma,
        	 * so loop through the first row which are ranks,
        	 * loop through the second row which are frequencies
        	 * for those numbers. The end output will 
        	 * display the frequency below it's number in the 
        	 */
        	// first row are ranks
            for(int i = 0; i < sortedWordFreqs.size(); i++){
                bw.write(((Integer)(i+1)).toString());
                if(i < sortedWordFreqs.size() - 1)
                    bw.write(",");
                else
                    bw.write("\n");
            }
            
            // second row are frequencies
            for(int i = 0; i < sortedWordFreqs.size(); i++){
                bw.write(sortedWordFreqs.get(i).getValue().toString());
                if(i < sortedWordFreqs.size() - 1)
                    bw.write(",");
            }
        }finally{
        	//close writers
            if(bw != null)
                bw.close();
            if(fw != null)
            	fw.close();
        }
    }
    
    /**
     * Sorts the <String, Integer> HashMap entries in ascending or 
     * descending order.
     * We are assuming the HashMap to sort is in the form <String, Integer>.
     * @param map The HashMap to sort
     * @param ascending Sorting in ascending vs descending
     * @return ArrayList of the sorted entries from the HashMap.
     */
    public ArrayList<Entry<String, Integer>> sortFrequentWords(
            HashMap<String, Integer> map, boolean ascending){
    	
        // store entries as list of entries
        ArrayList<Entry<String, Integer>> sortedWords = 
                new ArrayList<Entry<String, Integer>>(map.entrySet());

        // sort, keeping key and value matched
        Collections.sort(sortedWords, new Comparator<Entry<String, Integer>>(){
            @Override
            public int compare(Entry<String, Integer> num1, Entry<String, Integer> num2) {
                if(ascending)
                    return num1.getValue().compareTo(num2.getValue());
                else
                    return num2.getValue().compareTo(num1.getValue());
            }
            
        });
        
        return sortedWords;
        
    }

}
