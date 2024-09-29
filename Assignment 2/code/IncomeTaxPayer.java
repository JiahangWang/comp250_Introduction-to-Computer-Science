
public abstract class IncomeTaxPayer {

	private static int currentMaxTaxID;
	private int  taxID;
	private String  name;
	private double  income;

	public IncomeTaxPayer(String name){  /** IncomeTaxPayer构造器 */
		this.name = name;
		currentMaxTaxID ++;
		this.taxID = currentMaxTaxID;
	}

	public static int getCurrentMaxTaxID() {
		return currentMaxTaxID;
	}

	public int getTaxID() {
		return taxID;
	}

	public String getName() {
		return name;
	}

	public double getIncome() {
		return this.income;
	}

	public void setIncome( double income) {
		this.income = income;
	}

	public String toString() {
		return "  " + taxID + " " + name + " income " + income ;
	}

	public boolean equals(Object obj) {  /** 判断相等 */
		if(obj instanceof IncomeTaxPayer && ((IncomeTaxPayer) obj).taxID == this.taxID){
			return true;
		}
		return false;
	}

	public abstract double calculateIncomeTax();
}
