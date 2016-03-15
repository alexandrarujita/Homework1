import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {

	private static final long serialVersionUID = 8226553768379549121L;

	private JButton operations[];// submit, add, sub, div, integrate, diff;
	private JTextField polinom1, polinom2, rez;
	private JLabel e, r, pol1, pol2, ex;
	private JPanel sections[];
	private Polynom prez, p1 = new Polynom(), p2 = new Polynom();
	private final int nrOfOp = 10;

	public GUI() {

		sections = new JPanel[2];
		operations = new JButton[nrOfOp];
		String op[] = { "Submit", "Add", "Substract", "Multiply", "Divide P1 to P2", "Divide P2 to P1", "Integrate P1",
				"Differentiate P1", "Integrate P2", "Differentiate P2" };

		createButtons(operations, op);
		addComponents(2);

		actions();

		setLayout(new GridLayout(2, 1));
		setSize(550, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createButtons(JButton[] operations2, String[] op) {
		for (int i = 0; i < op.length; i++)
			operations2[i] = new JButton(op[i]);

		e = new JLabel("    Example");
		pol1 = new JLabel("Polynomial 1:");
		pol2 = new JLabel("Polynomial 2:");
		r = new JLabel("Result :");

		polinom1 = new JTextField(15);
		polinom2 = new JTextField(15);
		rez = new JTextField(15);
		ex = new JLabel("       aX^n+bX^n-1+....+aX^1+bX^0            ");

	}

	private void addComponents(int nrSections) {

		for (int i = 0; i < nrSections; i++) {
			sections[i] = new JPanel();
			add(sections[i]);
		}
		sections[0].setLayout(new FlowLayout());
		sections[1].setLayout(new GridLayout(6, 1));

		Dimension prefSize = new Dimension(400, 10);
		ex.setPreferredSize(prefSize);
		sections[0].add(e);
		sections[0].add(ex);

		sections[0].add(pol1);
		sections[0].add(polinom1);

		sections[0].add(pol2);
		sections[0].add(polinom2);

		sections[0].add(r);
		sections[0].add(rez);

		sections[0].add(operations[0]);

		for (int i = 1; i < nrOfOp; i++)
			sections[1].add(operations[i]);
	}

	private void transfText() {
		p1 = new Polynom();
		p2 = new Polynom();
		String s1 = polinom1.getText();
		String s2 = polinom2.getText();
		if (s1.equals(""))
			p1.value("0x^0");
		else
			p1.value(s1);

		if (s2.equals(""))
			p2.value("0x^0");
		else
			p2.value(s2);
	}

	private void actions() {
		OpWith1 o1 = new OpWith1();
		OpWith2 o2 = new OpWith2();

		operations[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				prez = new Polynom(o2.add(p1, p2));
				rez.setText(afisare(prez));
			}
		});

		operations[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				Polynom prez = new Polynom(o2.sub(p1, p2));
				rez.setText(afisare(prez));

			}
		});

		operations[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				Polynom prez = new Polynom(o2.mul(p1, p2));
				rez.setText(afisare(prez));

			}
		});
		operations[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				Polynom prez = new Polynom(o2.div(p1, p2));
				rez.setText(afisare(prez));

			}
		});

		operations[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				Polynom prez = new Polynom(o2.div(p2, p1));
				rez.setText(afisare(prez));

			}
		});

		operations[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				prez = new Polynom(o1.integrate(p1));
				rez.setText(afisare(prez));
			}
		});
		operations[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				Polynom prez = new Polynom(o1.diff(p1));
				rez.setText(afisare(prez));
			}
		});

		operations[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				prez = new Polynom(o1.integrate(p2));
				rez.setText(afisare(prez));
			}
		});

		operations[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transfText();
				Polynom prez = new Polynom(o1.diff(p2));
				rez.setText(afisare(prez));
			}
		});

	}

	public String afisare(Polynom p) {
		StringBuilder pol = new StringBuilder();

		boolean nuZero = false;
		for (Monom m : p.getTerms()) {
			if (m.getCoef() != 0)
				nuZero = true;
		}
		if (!nuZero) {
			pol.append(0);
			return pol.toString();
		}

		for (Monom m : p.getTerms()) {
			if (m.getCoef() != 0) {
				if (m.getPower() == 0) {
					if (m.getCoef() > 0)
						pol.append("+" + m.getCoef());
					else
						pol.append(m.getCoef());
				} else {
					if (m.getPower() > 0 && m.getPower() != 1) {
						if (m.getCoef() == 1) {
							pol.append("+ x^" + m.getPower());
						} else {
							if (m.getCoef() > 1)
								pol.append("+" + m.getCoef() + "x^" + m.getPower());
							else
								pol.append(m.getCoef() + "x^" + m.getPower());
						}
					} else if (m.getPower() < 0) {
						if (m.getCoef() == 1) {
							pol.append("+x^(" + m.getPower() + ")");
						} else {
							if (m.getCoef() > 1)
								pol.append("+" + m.getCoef() + "x^(" + m.getPower() + ")");
							else
								pol.append(m.getCoef() + "x^(" + m.getPower() + ")");
						}
					} else {
						if (m.getPower() == 1) {
							if (m.getCoef() == 1) {
								pol.append("+x ");
							} else {
								if (m.getCoef() > 1)
									pol.append("+" + m.getCoef() + "x ");
								else
									pol.append(m.getCoef() + "x ");
							}
						}
					}
				}
			}
		}

		if (pol.charAt(0) == '+')
			pol.replace(0, 1, " ");

		return pol.toString();
	}
}