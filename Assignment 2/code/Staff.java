
public class Staff extends IncomeTaxPayer {

	private int salaryPerHour;
	final private int incomeTaxPercentage = 25;

	public Staff(String name, boolean isCook) {  /** Staff 构造器 */
		super(name);
		if(isCook){
			salaryPerHour = 20;
		}
		else {
			salaryPerHour = 10;
		}
	}

	public int getSalaryPerHour() {
		return salaryPerHour;
	}

	public int getIncomeTaxPercentage() {
		return incomeTaxPercentage;
	}

	public double workHours(int numHours) {  /**返回挣到的钱，并修改他的工资 */
		setIncome(getIncome() + numHours * salaryPerHour);
		return numHours * salaryPerHour;
	}

	@Override
	public double calculateIncomeTax() {  /** 计算工资交的税费 */
		return this.getIncome() * 0.25;
	}

}
