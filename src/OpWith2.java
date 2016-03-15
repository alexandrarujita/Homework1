
import java.util.Collections;

public class OpWith2 implements TwoPolynomOp {

	private Polynom ZERO = new Polynom();
	private OpWith1 o = new OpWith1();
	private Polynom rez1 = new Polynom();

	@Override
	public Polynom add(Polynom p1, Polynom p2) {
		Polynom rez2 = new Polynom(), rez = new Polynom();

		for (Monom m1 : p1.getTerms()) {
			if (!o.exDegree(p2, m1))
				rez2.addMonom(m1);
		}
		for (Monom m2 : p2.getTerms()) {
			if (!o.exDegree(p1, m2))
				rez2.addMonom(m2);
		}

		for (Monom m1 : p1.getTerms()) {
			for (Monom m2 : p2.getTerms()) {
				if (m1.getPower() == m2.getPower()) {
					m1.setCoef(m1.getCoef() + m2.getCoef());
					rez.addMonom(m1);
				}

			}

		}

		for (Monom m1 : rez.getTerms()) {
			rez2.addMonom(m1);
		}
		Collections.sort(rez2.getTerms(), new orderByDegree());

		return rez2;
	}

	@Override
	public Polynom sub(Polynom p1, Polynom p2) {
		Polynom rez2 = new Polynom(), rez = new Polynom();

		for (Monom m1 : p1.getTerms()) {
			if (!o.exDegree(p2, m1))
				rez2.addMonom(m1);
		}
		for (Monom m1 : p2.getTerms()) {
			if (!o.exDegree(p1, m1))
				rez2.addMonom(m1);
		}

		for (Monom m1 : p1.getTerms()) {
			for (Monom m2 : p2.getTerms()) {
				if (m1.getPower() == m2.getPower()) {
					m1.setCoef(m1.getCoef() - m2.getCoef());
					rez.addMonom(m1);
				}

			}

		}

		for (Monom m1 : rez.getTerms()) {
			rez2.addMonom(m1);
		}
		Collections.sort(rez2.getTerms(), new orderByDegree());
		return o.addAfterModif(rez2);
	}

	@Override
	public Polynom mul(Polynom p1, Polynom p2) {
		Polynom rez = new Polynom();
		Monom m3;

		for (Monom m1 : p1.getTerms()) {
			for (Monom m2 : p2.getTerms()) {
				m3 = new Monom();
				m3.setCoef(m1.getCoef() * m2.getCoef());
				m3.setPower(m1.getPower() + m2.getPower());
				rez.addMonom(m3);
			}
		}
		return o.addAfterModif(rez);
	}

	@Override
	public Polynom div(Polynom p11, Polynom p22) {

		ZERO.addMonom(new Monom(0, 0));

		if ((o.degMax(p22) == 0) && (p22.getTerms().get(0).getCoef() == 0))
			throw new RuntimeException("Divide by zero polynomial");

		if (o.degMax(p11) < o.degMax(p22))
			return ZERO;

		if (o.degMax(p11) >= o.degMax(p22)) {
			Polynom aux = new Polynom();

			int exponent = o.degMax(p11) - o.degMax(p22);
			double coef = p11.getTerms().get(0).getCoef() / p22.getTerms().get(0).getCoef();
			Monom c = new Monom(coef, exponent);

			aux.addMonom(c);
			rez1.addMonom(c);

			p11 = new Polynom(sub(p11, mul(p22, aux)));
			p11 = new Polynom(div(p11, p22));

		}
		return rez1;
	}
}
