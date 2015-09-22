package csvToArffConverter;

import java.util.ArrayList;
import java.util.List;

public class ARFFData {

	private String[] attributes;
	private List<String[]> data;
	
	public ARFFData(List<String[]> csv){
		this.attributes = csv.get(0);
		data = new ArrayList<String[]>(csv.size());
		for (int i = 1; i<csv.size(); i++){
			data.add(csv.get(i));
		}
	}
	
	public void deleteAttribute (String attribute){
		int attributeIndex = -1;
		for (int i = 0; i<attributes.length; i++){
			if (attribute.equals(attributes[i])){
				attributeIndex = i;
				break;
			}
		}
		if (attributeIndex == -1){
			//attribute not found
			return;
		}
		this.attributes = removeColumn(attributes, attributeIndex);
		for (int i = 0; i<data.size(); i++){
			data.set(i, removeColumn(data.get(i), attributeIndex));
		}
	}
	
	public List<String> getARFF(){
		return null;
	}
	/**
	 * @return the attributes
	 */
	public String[] getAttributes() {
		return attributes;
	}

	/**
	 * @return the data
	 */
	public List<String[]> getData() {
		return data;
	}

	private String[] removeColumn (String[] row, int column){
		String[] newRow = new String[row.length-1];
		for (int i = 0; i<row.length; i++){
			if (i!=column){
				newRow[i] = row[i];
			}
		}
		return newRow;
	}
}
