package customStructure;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

public class SortingOrder implements Comparator<SortingImporatnce> {

	@Override
	public int compare(SortingImporatnce kolor1, SortingImporatnce kolor2) {
		SortingImporatnce[] tab = { kolor1, kolor2 };
		Integer[] value = new Integer[2];

		for (int i = 0; i < tab.length; i++)
			switch (tab[i]) {
			case CZERWONY:
				value[i] = 0;
				break;
			case ¯Ó£TY:
				value[i] = 1;
				break;
			case NIEBIESKI:
				value[i] = 2;
				break;
			case ZIELONY:
				value[i] = 3;
				break;
			case BIA£Y:
				value[i] = 4;
				break;
				
			default:
				value[i]=Integer.MAX_VALUE;
				break;
			}
		

		return value[0].compareTo(value[1]);
	}
	
	public static void main(String[] args)
	{
		Comparator<SortingImporatnce> komparator=new SortingOrder();
		TreeMap<SortingImporatnce, String> testMap=new TreeMap<SortingImporatnce, String>();
		/*
		testMap.put(SortingImporatnce.¯Ó£TY, "karma");
		testMap.put(SortingImporatnce.NIEBIESKI, "karma");
		testMap.put(SortingImporatnce.CZERWONY, "karma");
		testMap.put(SortingImporatnce.ZIELNOY, "karma");
		testMap.put(SortingImporatnce.BIA£Y, "karma");
		
		for(SortingImporatnce k: testMap.keySet())
			System.out.println(k);
	*/
		LinkedList<SortingImporatnce> sortList=new LinkedList<SortingImporatnce>();
		sortList.add(SortingImporatnce.BIA£Y);
		sortList.add(SortingImporatnce.ZIELONY);
		sortList.add(SortingImporatnce.NIEBIESKI);
		sortList.add(SortingImporatnce.¯Ó£TY);
		sortList.add(SortingImporatnce.CZERWONY);
		
		Collections.sort(sortList,komparator);
		System.out.println(sortList.get(1));
		
	}

}
