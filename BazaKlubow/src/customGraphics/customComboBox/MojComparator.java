package customGraphics.customComboBox;

import java.util.Comparator;

public class MojComparator implements Comparator<String> {
	private String LAST_VALUE;
	public MojComparator(String lastVal) {
		LAST_VALUE=lastVal;
	}
	
	@Override
	public int compare(String w1, String w2) {
		if(w1.equals(LAST_VALUE)&&w2.equals(LAST_VALUE))
			return 0;
		else
			if(w2.equals(LAST_VALUE))
				return -1;
			else
				if(w1.equals(LAST_VALUE))
					return 1;
				else
					return w1.compareToIgnoreCase(w2);
		
	}

}
