
public class WorkingOwner extends Owner {

    private int targetTipPct;

    public WorkingOwner(String name, int targetTipPct) {  /** WorkingOwner构造器 */
        super(name);
        this.targetTipPct = targetTipPct;
    }

    public int getTargetTipPct() {
        return targetTipPct;
    }
}
