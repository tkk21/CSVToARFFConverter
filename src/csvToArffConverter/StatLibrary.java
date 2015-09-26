package csvToArffConverter;

import java.util.List;

public class StatLibrary {

	public double calculateMean (List<String> column){
		double sum = 0.0;
		for (String s : column){
			try {
				sum += Double.parseDouble(s);
			}
			catch(NumberFormatException e){
				System.out.println("bad double value in column");
			}
		}
		return sum/column.size();
	}
	
	public double calculateStandardDeviation(List<String> column){
		double mean = calculateMean(column);
		double variance = 0.0;
		for (String s : column){
			try {
				variance = (Double.parseDouble(s) - mean);
			}
			catch (NumberFormatException e){
				System.out.println("bad double value in column");
			}
		}
		variance /= column.size() - 1;
		return Math.sqrt(variance);
	}
}
