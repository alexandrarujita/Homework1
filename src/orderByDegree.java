import java.util.Comparator;

public class orderByDegree implements Comparator<Monom> {

	@Override
	public int compare(Monom m1, Monom m2) {
		if (m1.getPower()>m2.getPower())
		  return -1;
		if(m1.getPower()<m2.getPower())
			return 1;
		return 0;
	}
}
