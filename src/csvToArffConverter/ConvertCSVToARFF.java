package csvToArffConverter;

import java.io.File;
import java.io.FileNotFoundException;

public class ConvertCSVToARFF {

	public static void main(String[] args) {
		ARFFData arff = null;
		try {
			arff = new ARFFData(CSVParser.parseCSV(new File(args[0])));
		}
		catch(FileNotFoundException e){
			System.out.println("the csv file to read does not exist");
			System.exit(1);
		}
		
		arff.deleteAttribute("TimeStamp");
		arff.deleteAttribute("Counter");
		arff.deleteAttribute("mCurrentPattern");
		arff.insertAttribute("Label", "A");
		ARFFWriter.convertToARFF(new File(args[1]), arff);//train data
		System.out.println("done");
	}
}