package customGraphics;
import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;


public class UIManagerColorKeys {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    List<String> colorKeys = new ArrayList<String>();
	    Set<Entry<Object, Object>> entries = UIManager.getLookAndFeelDefaults().entrySet();
	    for (Entry entry : entries)
	    {
	      if (entry.getValue() instanceof JFrame)
	      {
	        colorKeys.add((String) entry.getKey());
	      }
	    }
	 
	    // sort the color keys
	    Collections.sort(colorKeys);
	     
	    // print the color keys
	    for (String colorKey : colorKeys)
	    {
	      System.out.println(colorKey);
	    }
	 
	  }

}
