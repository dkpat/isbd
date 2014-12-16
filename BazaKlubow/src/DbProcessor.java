import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;


 public final class DbProcessor {
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static String db;
	
	
	private DbProcessor()
	{
		
	}
	public static void main(String[] args)
	{
		System.out.println("Próba po³¹czenia z baz¹ danych");
		DbProcessor take1=new DbProcessor();
		take1.createKoncertyList();
		System.out.println("Ma³a zmiana");
		
	}
	
	
	public static void doConnection(){
		 try{
	            String path = new java.io.File("Projekt1.accdb").getAbsolutePath();
	        db ="JDBC:ODBC:Driver=Microsoft Access Driver (*.mdb, *.accdb); DBQ="+path;
	         
	       
        
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            con = DriverManager.getConnection(db);
        }catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex.toString());

        }
        finally
        {
        	/*
        	if(con!=null)
        	try{
        		con.close();
        	}catch(SQLException e)
        	{
        		System.out.println(e.toString());
        		e.printStackTrace();
        	}
        	*/
        }
		 System.out.println(con);
    }
	
	public static boolean chekingPassword(String log,String pass)
	{
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM Pracownicy WHERE StrComp('"+log+"',LoginP,0) = 0 AND StrComp('"+pass+"',Has³oP,0)=0;");
			return rs.next();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con=null;
			return false;
		}
		
	}
	
	public static ArrayList<KlubComponent> createKlubComponents()
	{
		ArrayList<KlubComponent> result= new ArrayList<KlubComponent>();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM Kluby");
			while(rs.next())
			{
				String nazwa=rs.getString("NazwaK");
				String adres=rs.getString("AdresK");
				String w³aœciciel=rs.getString("W³aœciciel");
				int pojemnoœæ=rs.getInt("Pojemnoœæ");
				byte[] zdjêcie=rs.getBytes("Zdjêcie");
				System.out.println(zdjêcie.length);
				try {
					byte[] arr= GetImageBytesFromOLEField(zdjêcie);
					System.out.println(arr.length);
					result.add(new KlubComponent(nazwa, adres, w³aœciciel, pojemnoœæ, arr));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con=null;
			return null;
		}
	}
	
	public static ArrayList<Koncerty> createKoncertyList()
	{
		ArrayList<Koncerty> result=new ArrayList<Koncerty>();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM KONCERTY");
			while(rs.next())
			{
				int ID=rs.getInt("NrKoncertu");
				Timestamp data=rs.getTimestamp("DataKoncertu");
				int liczbaLudzi=rs.getInt("LiczbaLudzi");
				BigDecimal cenaBiletu=rs.getBigDecimal("CenaBiletu");
				int nrKlubu=rs.getInt("NrKlubu");
				result.add(new Koncerty(ID, data, liczbaLudzi, cenaBiletu, nrKlubu));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(result.toArray()));
		return result;
	}
	
	public static void disconect()
	{
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("B³¹d przy zrywaniu po³¹czenia z baz¹ danych");
			e.printStackTrace();
		}
	}
	
	private static byte[] GetImageBytesFromOLEField(byte[] oleFieldBytes) throws Exception
    {
        final String BITMAP_ID_BLOCK = "BM";
        final String JPG_ID_BLOCK = "\u00FF\u00D8\u00FF";
        //final String JPG_ID_BLOCK = "JFIF";
        final String PNG_ID_BLOCK = "\u0089PNG\r\n\u001a\n";
        final String GIF_ID_BLOCK = "GIF8";
        final String TIFF_ID_BLOCK = "II*\u0000";
 
        byte[] imageBytes;
 
        // Get a UTF7 Encoded string version
       // Encoding u8 = Encoding.UTF7;
        String strTemp;
		try {
			strTemp = new String(oleFieldBytes,"ISO-8859-1");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
 
        // Get the first 300 characters from the string
        String strVTemp = strTemp.substring(0,300);
        // Search for the block
        int iPos = -1;
        if (strVTemp.indexOf(BITMAP_ID_BLOCK) != -1)
            iPos = strVTemp.indexOf(BITMAP_ID_BLOCK);
        else if (strVTemp.indexOf(JPG_ID_BLOCK) != -1)
            iPos = strVTemp.indexOf(JPG_ID_BLOCK);
        else if (strVTemp.indexOf(PNG_ID_BLOCK) != -1)
            iPos = strVTemp.indexOf(PNG_ID_BLOCK);
        else if (strVTemp.indexOf(GIF_ID_BLOCK) != -1)
            iPos = strVTemp.indexOf(GIF_ID_BLOCK);
        else if (strVTemp.indexOf(TIFF_ID_BLOCK) != -1)
            iPos = strVTemp.indexOf(TIFF_ID_BLOCK);
        else
            throw new Exception("Unable to determine header size for the OLE Object");
 
        // From the position above get the new image
        if (iPos == -1)
            throw new Exception("Unable to determine header size for the OLE Object");
 
       
        imageBytes = new byte[oleFieldBytes.length - iPos];
    
        
        System.arraycopy(oleFieldBytes,iPos, imageBytes, 0, imageBytes.length);
        
        return imageBytes;
    }
	

}
