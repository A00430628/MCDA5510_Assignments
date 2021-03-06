/* Output folder contains two files one with valid rows i.e. Output.csv and other
 * with invalid rows i.e. OutputError.csv
 * Logs folder contains log file i.e.OutputLogfile.log*/

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirWalker extends SimpleLogging {
	public static ArrayList<String> files = new ArrayList<String>();
	public static final String inputDirectory = "/home/kshitijnetwork/Sample Data/Sample Data";
    
	/*Walk through the directory structure for getting
	 * CSV files in a array*/
	public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();
        
        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
            	if(f.getAbsolutePath().endsWith(".csv")){
            	files.add(f.getAbsolutePath());
            	}
            }
        }
    }
    
    /* entry point of the assignment program */
    public static void main(String[] args) {
    	final long startTime = System.currentTimeMillis();
    	SimpleLogging logIT = new SimpleLogging();
    	logIT.logFortheProj(); 
    	DirWalker fw = new DirWalker();
    	SimpleCsvParser csvP = new SimpleCsvParser();
    	Logger logger = Logger.getLogger("Main");
    	logger.log(Level.INFO, "This is the start of the program");
    	fw.walk(inputDirectory);
    	csvP.readCsv(files);
    	final long endTime = System.currentTimeMillis();
    	logger.log(Level.INFO, "Total execution time: " + (endTime - startTime) + " ms");
    	
    }

}