package polaczenieZbaz¹;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import customComponents.KlubComponent;
import klasyBazodanowe.*;



public final class DbProcessor {
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	private static String db;

	private DbProcessor() {

	}

	public static void main(String[] args) {
		System.out.println("Próba po³¹czenia z baz¹ danych");
		doConnection();
		List<Zespol> allBands= getAllBands();
		System.out.println("Wszystkie zespo³y to "+allBands);
		
		createMusicianList(1);
		createInstrumentList(2);
		
		/* Kod testowy dla metody getDataAboutMembership
		Timestamp[] tab=getDataAboutMembership(1, 1);
		if(tab==null)
			System.out.println("Metoda zwróci³a referancjê null, czyli wed³ug niej nie istniej cz³onkostwo o parametrach podanych w argumencie");
		else
			System.out.printf("Co¿ ta metoda zwróci³a: %tF\t%tF%n",tab[0],tab[1]);
		
		 */	
		JTextField pom =new JTextField("Skrzek");
		
		System.out.println(pom.getPreferredSize());
		System.out.println("Ma³a zmiana");
		

	}

	public static void doConnection() {
		try {
			String path = new java.io.File("Projekt1.accdb").getAbsolutePath();
			db = "JDBC:ODBC:Driver=Microsoft Access Driver (*.mdb, *.accdb); DBQ="
					+ path;

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			con = DriverManager.getConnection(db);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.toString());

		} finally {
			/*
			 * if(con!=null) try{ con.close(); }catch(SQLException e) {
			 * System.out.println(e.toString()); e.printStackTrace(); }
			 */
		}
		System.out.println(con);
	}
	
	public static Timestamp[] getDataAboutMembership(int nrZespo³u,int nrMuzyka)
	{
		Timestamp[] result=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT DataPrzyst,DataOpuszczenia FROM Cz³onkostwa WHERE NrMuzyka="+nrMuzyka+"AND NrZespo³u="+nrZespo³u);
			if(rs.next())
			{
				result=new Timestamp[2]; 
				result[0]=rs.getTimestamp("DataPrzyst");
				result[1]=rs.getTimestamp("DataOpuszczenia");
			}
		} catch (SQLException e) {
			result=null;
			e.printStackTrace();
		}
		return result;
	}
			
			
			
			
	public static String getBandName(int NumerZespo³u) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT NazwaZ FROM Zespo³y WHERE NrZespo³u="
					+ NumerZespo³u);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getClubName(int NumerKlubu) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT NazwaK FROM Kluby WHERE NrKlubu="
					+ NumerKlubu);
			if (rs.next())
				return rs.getString(1);
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean chekingPassword(String log, String pass) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM Pracownicy WHERE StrComp('"
					+ log + "',LoginP,0) = 0 AND StrComp('" + pass
					+ "',Has³oP,0)=0;");
			return rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con = null;
			return false;
		}

	}

	public static LinkedList<Zespol> getAllBands() {
		LinkedList<Zespol> wynik = new LinkedList<Zespol>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM ZESPO³Y");
			while (rs.next()) {
				int ID = rs.getInt("NrZespo³u");
				String nazwaZ = rs.getString("NazwaZ");
				Timestamp poczatekD = rs.getTimestamp("DataUformowania");
				Zespol nowyZespol = new Zespol(ID, nazwaZ, poczatekD);
				wynik.add(nowyZespol);
				//System.out.println(nowyZespol);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			con = null;
			return null;
		}

		return wynik;
	}
	/**
	 * Metoda tworzy listê muzyków, którzy s¹ czlonkami przez konretnego zespo³u
	 * @param nrZespo³u dla, ktorego mam byæ utworzona lista cz³onków
	 * @return Je¿eli s¹ w bazie tacy cz³onkowie w bazie, zostaje zwrócona lista z muzykami,
	 * je¿eli zespó³ nie ma cz³onków(czyli te¿ sytuacja kiedy zespó³ w ogóle nie istnieje)
	 * zwrócona zostaje lista pusta
	 * je¿eli coœ poszlo nie tak zwrócona zostanie referncja null
	 * Dla wszystkich metod create schemat, kiedy jaka wartoœæ jest zwracana jest taki sam
	 */
	public static List<Muzyk> createMusicianList(int nrZespo³u) {
		LinkedList<Muzyk> result = new LinkedList<Muzyk>();

		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT Muzycy.NrMuzyka,ImiêM,NazwiskoM,DataUrodzenia,DataŒmierci,ZdjêcieM FROM Muzycy INNER JOIN Cz³onkostwa ON Cz³onkostwa.NrMuzyka=Muzycy.NrMuzyka WHERE Cz³onkostwa.NrZespo³u="+nrZespo³u);

			while (rs.next()) {
				int ID_M = rs.getInt("NrMuzyka");
				String imie = rs.getString("ImiêM");
				String nazwisko = rs.getString("NazwiskoM");
				Timestamp birthD = rs.getTimestamp("DataUrodzenia");
				Timestamp deathD = rs.getTimestamp("DataŒmierci");
				// Pocz¹tek operacji zwi¹zanych z wczytywaniem zdjêcia
				BufferedImage zdjecie = null;
				byte[] imageByte = rs.getBytes("ZdjêcieM");

				imageByte = GetImageBytesFromOLEField(imageByte);
				if (imageByte != null) {
					InputStream in = new ByteArrayInputStream(imageByte);
					zdjecie = ImageIO.read(in);
				}
				Muzyk nowyMuzyk=new Muzyk(ID_M, imie, nazwisko, zdjecie, birthD, deathD);
				//System.out.println(nowyMuzyk);
				result.add(nowyMuzyk);

			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	
	
	
	public static List<Muzyk> createReverseMusicianList(int nrZespo³u) {
		LinkedList<Muzyk> result = new LinkedList<Muzyk>();

		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT Muzycy.NrMuzyka,ImiêM,NazwiskoM,DataUrodzenia,DataŒmierci,ZdjêcieM FROM Muzycy INNER JOIN Cz³onkostwa ON Cz³onkostwa.NrMuzyka=Muzycy.NrMuzyka WHERE Cz³onkostwa.NrZespo³u<>"+nrZespo³u);

			while (rs.next()) {
				int ID_M = rs.getInt("NrMuzyka");
				String imie = rs.getString("ImiêM");
				String nazwisko = rs.getString("NazwiskoM");
				Timestamp birthD = rs.getTimestamp("DataUrodzenia");
				Timestamp deathD = rs.getTimestamp("DataŒmierci");
				// Pocz¹tek operacji zwi¹zanych z wczytywaniem zdjêcia
				BufferedImage zdjecie = null;
				byte[] imageByte = rs.getBytes("ZdjêcieM");

				imageByte = GetImageBytesFromOLEField(imageByte);
				if (imageByte != null) {
					InputStream in = new ByteArrayInputStream(imageByte);
					zdjecie = ImageIO.read(in);
				}
				Muzyk nowyMuzyk=new Muzyk(ID_M, imie, nazwisko, zdjecie, birthD, deathD);
				//System.out.println(nowyMuzyk);
				result.add(nowyMuzyk);

			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}

	// Koniec operacji zwi¹zanych z wczytywaniem zdjêcia

	public static ArrayList<KlubComponent> createKlubComponents() {
		ArrayList<KlubComponent> result = new ArrayList<KlubComponent>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM Kluby");
			while (rs.next()) {
				String nazwa = rs.getString("NazwaK");
				String adres = rs.getString("AdresK");
				String w³aœciciel = rs.getString("W³aœciciel");
				int pojemnoœæ = rs.getInt("Pojemnoœæ");
				byte[] zdjêcie = rs.getBytes("Zdjêcie");
				System.out.println(zdjêcie.length);
				try {
					byte[] arr = GetImageBytesFromOLEField(zdjêcie);
					System.out.println(arr.length);
					result.add(new KlubComponent(nazwa, adres, w³aœciciel,
							pojemnoœæ, arr));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con = null;
			return null;
		}
	}

	public static ArrayList<Wystêp> createWystêpyList(int nrKoncertu) {
		ArrayList<Wystêp> result = new ArrayList<Wystêp>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM Wystêpy WHERE NrKoncertu="
					+ nrKoncertu);
			while (rs.next()) {
				int nrWystêpu = rs.getInt("NrWystêpu");
				String czasTrwaniaW = rs.getString("CzasTrwaniaW");
				Timestamp godzinaRozpoczêcia = rs
						.getTimestamp("GodzinaRozpoczêcia");
				int nrZespo³u = rs.getInt("NrZespo³u");
				result.add(new Wystêp(nrWystêpu, czasTrwaniaW,
						godzinaRozpoczêcia, nrKoncertu, nrZespo³u));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static List<Instrument> createInstrumentList(int nrMuzyka) {
		List<Instrument> instrumenty = new LinkedList<Instrument>();
		
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT Instrumenty.NrInstrumentu,NazwaIn,TypIn FROM Instrumenty INNER JOIN UmiejêtnoœciGry ON Instrumenty.NrInstrumentu=UmiejêtnoœciGry.NrInstrumentu WHERE NrMuzyka="+nrMuzyka);
			while (rs.next()) {
				int nrInstr = rs.getInt("NrInstrumentu");
				String nazwaIn = rs.getString("NazwaIn");
				String typIn = rs.getString("TypIn");
				Instrument nowyIn = new Instrument(nrInstr, typIn, nazwaIn);
				//System.out.println(nowyIn);
				instrumenty.add(nowyIn);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instrumenty;
	}

	public static ArrayList<Koncert> createKoncertyList() {
		ArrayList<Koncert> result = new ArrayList<Koncert>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM KONCERTY");
			while (rs.next()) {
				int ID = rs.getInt("NrKoncertu");
				String nawzaK = rs.getString("nazwaK");
				Timestamp data = rs.getTimestamp("DataKoncertu");
				int liczbaLudzi = rs.getInt("LiczbaLudzi");
				BigDecimal cenaBiletu = rs.getBigDecimal("CenaBiletu");
				Timestamp godzOtw = rs.getTimestamp("GodzOtw");
				int nrKlubu = rs.getInt("NrKlubu");
				result.add(new Koncert(ID, nawzaK, data, liczbaLudzi,
						cenaBiletu, godzOtw, nrKlubu));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(result.toArray()));
		return result;
	}

	public static void disconect() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("B³¹d przy zrywaniu po³¹czenia z baz¹ danych");
			e.printStackTrace();
		}
	}

	private static byte[] GetImageBytesFromOLEField(byte[] oleFieldBytes)
			throws Exception {
		final String BITMAP_ID_BLOCK = "BM";
		final String JPG_ID_BLOCK = "\u00FF\u00D8\u00FF";
		// final String JPG_ID_BLOCK = "JFIF";
		final String PNG_ID_BLOCK = "\u0089PNG\r\n\u001a\n";
		final String GIF_ID_BLOCK = "GIF8";
		final String TIFF_ID_BLOCK = "II*\u0000";

		byte[] imageBytes;
		if (oleFieldBytes == null) {
			imageBytes = null;
		} else {

			// Get a UTF7 Encoded string version
			// Encoding u8 = Encoding.UTF7;
			String strTemp;
			try {
				strTemp = new String(oleFieldBytes, "ISO-8859-1");

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			// Get the first 300 characters from the string
			String strVTemp = strTemp.substring(0, 300);
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
				throw new Exception(
						"Unable to determine header size for the OLE Object");

			// From the position above get the new image
			if (iPos == -1)
				throw new Exception(
						"Unable to determine header size for the OLE Object");

			imageBytes = new byte[oleFieldBytes.length - iPos];

			System.arraycopy(oleFieldBytes, iPos, imageBytes, 0,
					imageBytes.length);
		}
		return imageBytes;
	}

}
