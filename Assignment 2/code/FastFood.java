

import java.util.ArrayList;
import java.util.List;

public class FastFood extends FoodPlace {

    private List<Staff> staff = new ArrayList<>();

    public FastFood(String name, double fixedCosts, Owner owner, List<Staff> staff) {  /** FastFood构造器 */
        super(name,fixedCosts,owner);
        List<Staff> staffList = new ArrayList<Staff>();
        staffList.addAll(staff);
        this.staff = staffList;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name of FastFood: " + this.getName() +
                "\n" + "Owner: " + this.getOwner());
        int index = 1;
        for (Staff staff: staff) {
            builder.append("\n" + "Staff " + index++ + " : " + staff );
        }
        return builder.toString();
    }

    @Override
    public void workShift(int hours) {  /** Staff被付工资，Owner支出增加 */
        int expense = 0;
        for(Staff staff : staff){
            double s = staff.workHours(hours);
            staff.setIncome(s);
            expense += s;
        }
        getOwner().setSalaryExpenses(getOwner().getSalaryExpenses() + expense);
    }

    @Override
    public List<IncomeTaxPayer> getIncomeTaxPayers() {  /** 返回一个包含所有Staff和Owner的列表的浅拷贝 */
        ArrayList<IncomeTaxPayer> list = new ArrayList<>();
        list.addAll(staff);
        list.add(getOwner());
        ArrayList<IncomeTaxPayer> resultList = new ArrayList<>();
        resultList.addAll(list);
        return resultList;
    }

    @Override
    public void distributeIncomeAndSalesTax(Check check) {  /** 分配工资和税费 */
        getOwner().setIncome(getOwner().getIncome() + check.getMenuPrice());
        double tip = check.getTip() / staff.size();
        for(Staff s : staff){
            s.setIncome(s.getIncome() + tip);
        }
        setTotalSalesTax(getTotalSalesTax() + check.getSalesTax());
    }

    @Override
    public double getTipPercentage() {
        return 0;
    }
}
