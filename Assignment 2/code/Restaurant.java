
import java.util.ArrayList;
import java.util.List;


public class Restaurant extends FoodPlace {

	private Staff cook;
	private Server server;

	public Restaurant(String name, double fixedCosts, Owner owner, Staff cook, Server server) {  /** Restaurant构造器 */
		super(name,fixedCosts,owner);
		this.cook = cook;
		this.server = server;
	}

	public Staff getCook() {
		return cook;
	}

	public Server getServer() {
		return server;
	}

	@Override
	public String toString() {
		return "Name of restaurant: " + this.getName() +
				"\n" + "Owner: " + this.getOwner() +
				"\n" + "Cook: " + cook +
				"\n" + "Server: " + server;
	}

	@Override
	public void workShift(int hours) {        /** cook和server被付工资，Owner总支出增加 */
		getOwner().setSalaryExpenses(getOwner().getSalaryExpenses() + cook.workHours(hours) + server.workHours(hours));
	}

	@Override
	public List<IncomeTaxPayer> getIncomeTaxPayers() {       /** 返回一个纳税人的列表 */
		ArrayList<IncomeTaxPayer> list = new ArrayList<>();
		list.add(cook);
		list.add(server);
		list.add(getOwner());
		return list;
	}

	@Override
	public void distributeIncomeAndSalesTax(Check check) {      /** 分配工资和税费 */
		getOwner().setIncome(getOwner().getIncome() + check.getMenuPrice());
		cook.setIncome(cook.getIncome() + 0.2 * check.getTip());
		server.setIncome(server.getIncome() + 0.8 * check.getTip());
		setTotalSalesTax(getTotalSalesTax() + check.getSalesTax());
	}

	@Override
	public double getTipPercentage() {     /** 返回Server的Tip率 */
		return server.getTargetTipPct();
	}

}
