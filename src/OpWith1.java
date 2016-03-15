
import java.util.Collections;

public class OpWith1 implements OnePolynomOp {

	@Override
	public Polynom diff(Polynom p) {
		Polynom rez = new Polynom(p);

		for (Monom m1 : rez.getTerms()) {
			m1.setCoef(m1.getPower() * m1.getCoef());
			m1.setPower(m1.getPower() - 1);
		}
		return rez;
	}

	@Override
	public Polynom integrate(Polynom p) {
		Polynom rez = new Polynom(p);
		double c;

		for (Monom m1 : rez.getTerms()) {
			if (m1.getPower() != 0)
				c = 1 / m1.getPower();
			else
				c = 1;
			m1.setCoef(m1.getCoef() * c);
			m1.setPower(m1.getPower() + 1);
		}
		return rez;
	}

	public int degMax(Polynom polinom) {

		Collections.sort(polinom.getTerms(), new orderByDegree());
		if (polinom.getTerms().size() > 0)
			return polinom.getTerms().get(0).getPower();
		return 0;
	}

	public Polynom addAfterModif(Polynom p1) {

		Polynom rez = new Polynom();
		p1.addMonom(new Monom(0, 0));
		Collections.sort(p1.getTerms(), new orderByDegree());

		Monom m3;
		int j = p1.getTerms().size();
		int k = 0;
		double c = 0;
		boolean nuZero = false;

		for (Monom m : p1.getTerms()) {
			if (m.getCoef() != 0)
				nuZero = true;
		}
		if (!nuZero) {
			return rez;
		} else {
			while (k < j - 1) {
				c = 0;
				m3 = new Monom();
				if (p1.getTerms().get(k).getCoef() == 0)
					k++;
				else {
					if (p1.getTerms().get(k).getPower() == p1.getTerms().get(k + 1).getPower())
						while ((k < j - 1)
								&& (p1.getTerms().get(k).getPower() == p1.getTerms().get(k + 1).getPower())) {
							c += p1.getTerms().get(k).getCoef() + p1.getTerms().get(k + 1).getCoef();
							k++;

						}
					else
						c = p1.getTerms().get(k).getCoef();

					m3.setCoef(c);
					m3.setPower(p1.getTerms().get(k).getPower());
					rez.addMonom(m3);
					k++;
				}
			}
		}

		return rez;
	}

	public boolean exDegree(Polynom p1, Monom m) {

		for (Monom m1 : p1.getTerms()) {
			if (m.getPower() == m1.getPower())
				return true;
		}
		return false;
	}

}
