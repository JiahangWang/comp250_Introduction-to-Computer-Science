

public class Customer  {

	private String name;
	private int  targetTipPct;

	public Customer(String name, int targetTipPct) {          /** Customer构造器 */
		this.name = name;
		this.targetTipPct = targetTipPct;
	}

	public String getName() {
		return name;
	}

	public int getTargetTipPct() {
		return targetTipPct;
	}

	public String getDescriptiveMessage(FoodPlace foodPlace) {
		return this.name + " dined in " + foodPlace.getName();
	}

	public void dineAndPayCheck(FoodPlace foodPlace, double menuPrice ) {   /** 创建账单，设置Tip率，分配工资和税费 */
		Check check = new Check(menuPrice);
		check.setTipByPct((getTargetTipPct() + foodPlace.getTipPercentage()) / 2);
		foodPlace.distributeIncomeAndSalesTax(check);
	}
}
