
public class Server extends Staff {
	
	private int targetTipPct;

	public Server(String name, int targetTipPct){    /** Server 构造器 */
		super(name, false);
		this.targetTipPct = targetTipPct;
	}

	public int getTargetTipPct() {
		return targetTipPct;
	}
}
