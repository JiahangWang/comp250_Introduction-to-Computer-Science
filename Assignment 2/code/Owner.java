
public class Owner extends IncomeTaxPayer {

	final private int incomeTaxPct = 10;
	private double salaryExpenses;

	private FoodPlace foodPlace;

	public Owner(String name) {     /** Owner构造器 */
		super(name);
	}

	public int getIncomeTaxPct() {
		return incomeTaxPct;
	}

	public double getSalaryExpenses() {
		return salaryExpenses;
	}

	public void setSalaryExpenses(double salaryExpenses) {
		this.salaryExpenses = salaryExpenses;
	}

	public void setFoodPlace(FoodPlace foodPlace) {
		this.foodPlace = foodPlace;
	}

	public FoodPlace getFoodPlace() {
		return foodPlace;
	}

	@Override
	public double calculateIncomeTax() {   /** 返回Owner需要缴纳的税费 */
		if((getIncome() - salaryExpenses - foodPlace.getFixedCosts())> 0){
			return ((getIncome() - salaryExpenses - foodPlace.getFixedCosts()) * 0.1);
		}
		return 0;
	}
}
