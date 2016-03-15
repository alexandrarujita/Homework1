
public class Monom {
	
	private double coef;
	private int power;
	
	Monom(double coef2,int p){
		setCoef(coef2);
		setPower(p);
	}
	
	Monom(){}
	
	public double getCoef() {
		return coef;
	}
	public void setCoef(double d) {
		this.coef = d;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}

}
