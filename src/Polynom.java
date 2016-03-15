import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polynom {

	private List<Monom> terms;

	public Polynom() {
		terms = new ArrayList<Monom>();
	}

	public Polynom(Polynom p) {
		terms = new ArrayList<Monom>();
		for (Monom m : p.getTerms()) {
			addMonom(m);
		}
		Collections.sort(terms, new orderByDegree());
	}

	public void addMonom(Monom m) {
		terms.add(m);
	}

	public List<Monom> getTerms() {
		return terms;
	}

	public void setTerms(List<Monom> terms2) {
		this.terms = terms2;
	}

	public void value(String polinomText) {

		String degree[] = polinomText.split("(-|\\+)?\\d+[xX]\\^?");
		String coef[] = polinomText.split("[xX]\\^(-?\\d+\\b)");
		int[] c = new int[coef.length], d = new int[degree.length];
		String nr = "0123456789";
		int j = 0;

		for (int i = 0; i < coef.length; i++) {
			c[i] = Integer.parseInt(coef[i]);
		}

		for (int i = 0; i < degree.length; i++) {
			if (nr.indexOf(degree[i]) != 0) {
				d[j] = Integer.parseInt(degree[i]);
				j++;
			}
		}

		for (int i = 0; i < d.length && i < c.length; i++) {
			Monom a = new Monom();
			a.setCoef(c[i]);
			a.setPower(d[i]);
			terms.add(a);
		}
		Collections.sort(terms, new orderByDegree());
	}

}
