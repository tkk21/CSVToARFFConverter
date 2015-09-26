package csvToArffConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ConvertCSVToARFF {

	public static List<ARFFData> convert(File fileA, File fileB){
		List<ARFFData> trainTestPair = new ArrayList<ARFFData>();
		final int ROWS_FOR_TRAIN = 35;
		ARFFData arffA = null;
		ARFFData arffB = null;
		try {
			arffA = new ARFFData(CSVParser.parseCSV(fileA));
			arffB = new ARFFData(CSVParser.parseCSV(fileB));
		}
		catch(FileNotFoundException e){
			System.out.println("the csv file to read does not exist");
			System.exit(1);
		}
		
		arffA.deleteAttribute("TimeStamp");
		arffA.deleteAttribute("mCurrentPattern");
		arffA.insertAttribute("Label", "A");
		
		arffB.deleteAttribute("TimeStamp");
		arffB.deleteAttribute("mCurrentPattern");
		arffB.insertAttribute("Label", "B");
		
		ARFFData arffATest = arffA.splitData(ROWS_FOR_TRAIN);
		ARFFData arffBTest = arffB.splitData(ROWS_FOR_TRAIN);
		
		//combine arffA = arffA + arffB
		arffA.combineData(arffB);
		arffATest.combineData(arffBTest);
		
		arffA.deleteAttribute("Counter");
		arffATest.deleteAttribute("Counter");
		trainTestPair.add(arffA);
		trainTestPair.add(arffATest);
		
		return trainTestPair;
	}
	
	public static void main(String[] args) {
		List<ARFFData> list = convert(new File(args[0]), new File(args[1]));
		ARFFWriter.convertToARFF(new File("train.arff"), list.get(0));
		ARFFWriter.convertToARFF(new File("test.arff"), list.get(1));
		
		System.out.println("done");
	}
}