package customStructure;

public class ZmiennaWartosc<T> {
		private T oldValue;
		private T newValue;
	public ZmiennaWartosc(T oldValue) 
	{
		this.oldValue=oldValue;
		newValue=null;
	}
	public void setNewValue(T newValue)
	{
		
		if(oldValue.equals(newValue))
			this.newValue=null;
		else
			this.newValue=newValue;
		System.out.println("Nowa wartoœæ "+newValue +"     jjjeeeeeeeeeeeee" );
		System.out.println("Stara wartoœæ "+oldValue +"     jjjeeeeeeeeeeeee" );
	}
	public void setOldValue(T oldValue) {
		
		this.oldValue=oldValue;
	}
	public T getNewValue() {
		return newValue;
	}
	public T getOldValue() {
		return oldValue;
	}
	public boolean czyRzczywiscieMamNowaWartosc()
	{	
		return newValue!=null;
	}
	

}
