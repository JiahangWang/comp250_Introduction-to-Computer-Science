
import java.util.ArrayList;
import java.util.List;

public class TaxCollector {

	private List<FoodPlace> foodPlaces = new ArrayList<>();

	private double incomeTaxCollected;
	private double salesTaxCollected;

	public TaxCollector(List<FoodPlace> foodPlaces) {          /** TaxCollector构造器 */
		this.foodPlaces = foodPlaces;
	}

	public List<FoodPlace> getFoodPlaces() {
		return foodPlaces;
	}

	public double getIncomeTaxCollected() {
		return incomeTaxCollected;
	}

	public double getSalesTaxCollected() {
		return salesTaxCollected;
	}

	public void collectTax() {                             /** 收集所有的sale税和income税 */
		double sales = 0;
		double income = 0;
		for(FoodPlace foodPlace : foodPlaces){
			sales += foodPlace.getTotalSalesTax();
			for(IncomeTaxPayer incometaxpayer : foodPlace.getIncomeTaxPayers()){
				income += incometaxpayer.calculateIncomeTax();
			}
		}
		incomeTaxCollected += income;
		salesTaxCollected += sales;

	}
	
	public String toString() {
		return "TaxCollector: income tax collected: " + incomeTaxCollected + ", sales tax collected: " + salesTaxCollected;
	}
	
}
