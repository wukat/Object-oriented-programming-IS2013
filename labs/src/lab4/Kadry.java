/**
 * Kadry.java
 * @author - wukat
 * @data - 5 lis 2013
 */
package lab4;

import java.util.Iterator;
import java.util.LinkedList;

import lab1.Pesel;

/**
 * @author wukat
 *
 */
public class Kadry {
	LinkedList<Pracownik> pracownicy = new LinkedList<Pracownik>();
	
	/**
	 * 
	 * @param silaRobocza
	 */
	public void dodajPracownika(Pracownik silaRobocza) {
		pracownicy.add(silaRobocza);
	}
	
	/**
	 * 
	 * @param pesel
	 * @return pracownik or null
	 */
	public Pracownik znajdz(Pesel pesel) {
		Iterator<Pracownik> iter = pracownicy.iterator();
		while (iter.hasNext()) {
			Pracownik temp = iter.next();
			if (temp.getNumPESEL().equals(pesel))
				return temp;
		}
		return null;
	}
	/**
	 * 
	 * @param pesel
	 */
	public void usunPracownika(Pesel pesel) {
		pracownicy.remove(znajdz(pesel));
	}
	
	/**
	 * get
	 * @param pesel
	 * @return
	 */
	public double getWynagrodzenieBrutto(Pesel pesel) {
		return znajdz(pesel).getWynagrodzenieBrutto();
	}
	
	/**
	 * set
	 * @param pesel
	 * @param kwota
	 */
	public void setWynagrodzenieBrutto(Pesel pesel, double kwota) {
		znajdz(pesel).setWynagrodzenieBrutto(kwota);
	}
	

	/**
	 * 
	 * @param pesel
	 * @return
	 */
	public double getWynagrodzenieNetto(Pesel pesel) {
		return znajdz(pesel).liczNetto();
	}
	
	/**
	 * 
	 */
	public void wyswietlWysztkich() {
		Iterator<Pracownik> iter = pracownicy.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
}
