package customGraphics;


import java.awt.*;
/**
 * Niniejsza klasa upraszcza korzystanie z klasy GridBagContraints
 * @author Core Java
 *
 */
public class GBC extends GridBagConstraints 
{ 
	/**
	 * Tworzy obekt typu GBC z podanymi wartoœciami gridx i gridy oraz wszystkimi posta³ymi
	 * parametrami ustawionymi na wartoœci domyœlne.
	 * @param gridx wspó³rzêdna gridx
	 * @param gridy wspó³rzêdna gridy
	 */
	public GBC(int gridx,int gridy)
	{
		this.gridx=gridx;
		this.gridy=gridy;
	}
	/**
	 * Tworzy obiekt typu GBC z podanymi wartoœciami gridx, gridy, gridwidth, gridheight oraz 
	 * wszystkimi pozosta³ymi parametrami ustawionymi na wartoœci domyœlne
	 * @param gridx wspó³rzêdna gridx
	 * @param gridy wspó³rzêdna gridy
	 * @param gridwidth liczba zajmowanych komórek w poziomie
	 * @param gridheight liczba zajmowanych komorek w pionie
	 */
	public GBC(int gridx,int gridy,int gridwidth,int gridheight)
	{
		this.gridx=gridx;
		this.gridy=gridy;
		this.gridwidth=gridwidth;
		this.gridheight=gridheight;
	}
	/**
	 * Ustawia parametr anchor.
	 * @param anchor wartoœc parametru anchor
	 * @return this obiekt do dalszej modyfikacji
	 */
	public GBC setAnchor(int anchor)
	{
		this.anchor=anchor;
		return this;
	}
	/**
	 * Ustawia kierunek zape³nienia.
	 * @pram fill kierunek zape³nienia
	 * @return this obiekt do dalszej modyfikacji
	 */
	public GBC setFill(int fill)
	{
		this.fill=fill;
		return this;
	}
	/**
	 * Ustawia parametr weight komórek.
	 * @param weightx parametr weight w poziomie
	 * @param weighty parametr weight w pionie
	 * @return this obiekt do dalszej modyfikacji
	 */
	public GBC setWeight(int weightx, int weighty)
	{
		this.weightx=weightx;
		this.weighty=weighty;
		return this;
	}
	/**
	 * Ustawia dodatkow¹ pust¹ przestrzeñ w komórce.
	 * @param distance dope³nienie we wszystkich kierunkach
	 * @return this obiekt do dalszej modyfikacji  
	 */
	public GBC setInsets(int distance)
	{
		this.insets=new Insets(distance,distance,distance,distance);
		return this;
	}
	/**
	 * Ustawia dope³nienia w komórce.
	 * @param top odstêp od górnej krawêdzi
	 * @param left odstêp od lewej krawêdzi
	 * @param bottom odstêp od prawej krawêdzi
	 * @param right odstêp od prawej krawêdzi
	 * @return obiekt do dalszej modyfikacji
	 */
	public GBC setInsets(int top,int left,int bottom,int right)
	{
		this.insets=new Insets(top,left,bottom,right);
		return this;
	}
	/**
	 * Ustawia dope³nienie wewnêtrzne.
	 * @param ipadx dope³nienie wewnêtrzne poziome
	 * @param ipady dopelnienie zewnêtrzne pionowe
	 * @return obiekt do dalszej modyfikacji
	 */
	public GBC setIpad(int ipadx,int ipady)
	{
		this.ipadx=ipadx;
		this.ipady=ipady;
		return this;
	}
}
