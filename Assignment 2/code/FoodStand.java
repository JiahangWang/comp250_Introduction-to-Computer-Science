
import java.util.ArrayList;
import java.util.List;

public class FoodStand extends FoodPlace {

    public FoodStand(String name, double fixedCosts, WorkingOwner owner) {       /** FoodStand构造器 */
        super(name,fixedCosts,owner);
    }

    @Override
    public String toString() {
        return "Name of FoodStand: " + this.getName() +
                "\n" + "Owner: " + this.getOwner();
    }

    @Override
    public void workShift(int hours) {
        // no salaried workers so do nothing
    }

    @Override
    public List<IncomeTaxPayer> getIncomeTaxPayers() {       /** 返回一个只含有Owner的列表 */
        ArrayList<IncomeTaxPayer> list = new ArrayList<>();
        list.add(getOwner());
        return list;
    }

    @Override
    public void distributeIncomeAndSalesTax(Check check) {   /** 分配工资和税费 */
        getOwner().setIncome(getOwner().getIncome() + check.getMenuPrice() + check.getTip());
        setTotalSalesTax(getTotalSalesTax() + check.getSalesTax());
    }

    @Override
    public double getTipPercentage() {                    /** 返回这个WorkingOwner的Tip率 */
        return ((WorkingOwner)this.getOwner()).getTargetTipPct();
    }
}
