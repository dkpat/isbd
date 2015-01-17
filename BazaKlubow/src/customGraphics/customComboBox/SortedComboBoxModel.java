

//Wyspecjalizowany do konkretnych potrzeb model danych dla comboBox!!!!!!!!!!!!!!!!!!!!!
package customGraphics.customComboBox;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/*
 *  Custom model to make sure the items are stored in a sorted order.
 *  The default is to sort in the natural order of the item, but a
 *  Comparator can be used to customize the sort order.
 */
//class SortedComboBoxModel extends DefaultComboBoxModel
public class SortedComboBoxModel<E> extends DefaultComboBoxModel<E>
{
	private boolean fire=false;
	private Comparator<E> comparator;

	/*
	 *  Create an empty model that will use the natural sort order of the item
	 */
	public SortedComboBoxModel()
	{
		super();
	}

	/*
	 *  Create an empty model that will use the specified Comparator
	 */
	public SortedComboBoxModel(Comparator<E> comparator)
	{
		super();
		this.comparator = comparator;
	}

	/*
	 *	Create a model with data and use the nature sort order of the items
	 */
	public SortedComboBoxModel(E items[])
	{
		this( items, null );
	}

	/*
	 *  Create a model with data and use the specified Comparator
	 */
	public SortedComboBoxModel(E items[], Comparator<E> comparator)
	{
		this.comparator = comparator;

		for (E item : items)
		{
            addElement( item );
        }
	}

	/*
	 *	Create a model with data and use the nature sort order of the items
	 */
	public SortedComboBoxModel(Vector<E> items)
	{
		this( items, null );
	}

	/*
	 *  Create a model with data and use the specified Comparator
	 */

	public SortedComboBoxModel(Vector<E> items, Comparator<E> comparator)
	{
		this.comparator = comparator;

		for (E item : items)
		{
            addElement( item );
        }
	}

	@Override
	public void addElement(E element)
	{
		insertElementAt(element, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insertElementAt(E element, int index)


	
	
	
	
	{
		
		
		int size = getSize();

		//  Determine where to insert element to keep model in sorted order

		for (index = 0; index < size; index++)
		{
			if (comparator != null)
			{
				E o = getElementAt( index );
				//System.out.printf("Por�wnuje %s z %s, a wynik to %d%n", o,element,comparator.compare(o, element));
				if (comparator.compare(o, element) > 0)
					break;
			}
			else
			{
				Comparable<E> c = (Comparable<E>)getElementAt( index );
			
				if (c.compareTo(element) > 0)
					break;
			}
		}
		
		super.insertElementAt(element, index);

		//  Select an element when it is added to the beginning of the model

		if (index == 0 && element != null)
		{
			setSelectedItem( element );
		}
	}


	public void setFire(boolean fire)
	{
		this.fire=fire;
	}
	
	  @Override
      protected void fireContentsChanged(Object obj, int i, int j) {
          if (!fire) {
              super.fireContentsChanged(obj, i, j);
          }
      }




}