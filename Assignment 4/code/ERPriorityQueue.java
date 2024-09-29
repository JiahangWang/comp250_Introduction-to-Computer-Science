import java.util.ArrayList;
import java.util.HashMap;

public class ERPriorityQueue{

	public ArrayList<Patient>  patients;
	public HashMap<String,Integer>  nameToIndex;


	public ERPriorityQueue(){

		//  use a dummy node so that indexing starts at 1, not 0 （在列表开头创建了一个哑节点）

		patients = new ArrayList<Patient>();
		patients.add( new Patient("dummy", 0.0) );

		nameToIndex  = new HashMap<String,Integer>();
	}

	private int parent(int i){
		return i/2;
	}

	private int leftChild(int i){
	    return 2*i;
	}

	private int rightChild(int i){
	    return 2*i+1;
	}

    public boolean isEmpty(){
		return patients.size() == 1;
	}

	public boolean isLeaf(int i){
		return (2 * i) > (patients.size() - 1);
	}

	public void swap(int i, int j){
		Patient temp1 = patients.get(i);
		Patient temp2 = patients.get(j);
		nameToIndex.put(temp1.name,j);
		nameToIndex.put(temp2.name,i);
		patients.remove(i);
		patients.add(i,temp2);
		patients.remove(j);
		patients.add(j,temp1);
	}


	/**
	 * 上堆方法
	 * @param i
	 */
	public void upHeap(int i){
		while ((i > 1) && (patients.get(i).priority < patients.get(i / 2).priority)){
			swap(i,i / 2);
			i = i / 2;
		}
	}

	/**
	 * 下堆方法
	 * @param i
	 */
	public void downHeap(int i){
		while ((2 * i) <= (patients.size() - 1)){
			int child = 2 * i;
			if(child < (patients.size() - 1)){
				if(patients.get(child + 1).priority < patients.get(child).priority){
					child = child + 1;
				}
			}
			if(patients.get(child).priority < patients.get(i).priority){
				swap(i,child);
				i = child;
			}
			else return;
		}
	}

	/**
	 * 包含名字方法
	 * @param name
	 * @return 堆中是否包含传入的病人名字
	 */
	public boolean contains(String name){
		return nameToIndex.containsKey(name);
	}

	/**
	 * 获取病人优先度方法
	 * @param name
	 * @return 传入病人名字对应的优先度
	 */
	public double getPriority(String name){
        if(contains(name)){
        	return patients.get(nameToIndex.get(name)).priority;
		}
        return -1;
	}

	/**
	 * 找堆中最小优先级的方法
	 * @return 最小的优先级
	 */
	public double getMinPriority(){
        if(!isEmpty()){
        	return patients.get(1).priority;
		}
        return -1;
	}

	/**
	 * 删除并返回最小优先度病人的名字
	 * @return 删除前最小优先度病人的名字
	 */
	public String removeMin(){
		if(isEmpty()){
			return null;
		}
        String temp = patients.get(1).name;
        remove(temp);
        return temp;
	}

	/**
	 * 找最小优先级对应病人名字的方法
	 * @return 最小优先级对应病人名字
	 */
	public String peekMin(){
        if(!isEmpty()){
        	return patients.get(1).name;
		}
        return null;
	}

	/*
	 * There are two add methods.  The first assumes a specific priority.
	 * The second gives a default priority of Double.POSITIVE_INFINITY
	 *
	 * If the name is already there, then throw an IllegalArgumentException exception.
	 */

	/**
	 * 添加一个新的病人的方法
	 * @param name
	 * @param priority
	 * @return 是否添加成功
	 */
	public boolean add(String name, double priority){
		if(contains(name)) { return false; }
        Patient patient = new Patient(name,priority);
		patients.add(patient);
		upHeap(patients.size() - 1);
		nameToIndex.put(name,patients.indexOf(patient));
		return true;
	}

	/**
	 * 添加一个新的病人的方法，优先度设为默认值
	 * @param name
	 * @return 是否添加成功
	 */
	public boolean  add(String name){
		if(contains(name)) { return false; }
		add(name,Double.POSITIVE_INFINITY);
		return true;
	}

	/**
	 * 从堆中删除一个指定的病人
	 * @param name
	 * @return 有没有这个传入的病人
	 */
	public boolean remove(String name){
		if(!contains(name)) { return false; }
		int i = nameToIndex.get(name);
		swap(i,patients.size() - 1);
		patients.remove(patients.size() - 1);
		nameToIndex.remove(name);
		downHeap(i);
		return true;
	}

	/*
	 *   If new priority is different from the current priority then change the priority
	 *   (and possibly modify the heap).
	 *   If the name is not there, return false
	 */

	/**
	 * 改变一个病人的优先度，并且修改堆
	 * @param name
	 * @param priority
	 * @return 有没有这个传入的病人
	 */
	public boolean changePriority(String name, double priority){
        if(!contains(name)) { return false; }
        int i = nameToIndex.get(name);
        patients.get(i).setPriority(priority);
        if(patients.get(i).priority < patients.get(parent(i)).priority){
        	upHeap(i);
        	return true;
		}
        else if((2 * i) <= (patients.size() - 1)){
			downHeap(i);
			return true;
		}
        return true;
	}

	/**
	 * 返回所有优先度小于等于这个阈值的病人的一个列表并修改堆的方法
	 * @param threshold
	 * @return 一个列表包含了所有优先度小于等于这个阈值的病人
	 */
	public ArrayList<Patient> removeUrgentPatients(double threshold){
		ArrayList<Patient> list = new ArrayList<>();
		for (int i = 1; i < patients.size(); i++) {
			if(patients.get(i).priority <= threshold){
				list.add(patients.get(i));
			}
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			remove(list.get(i).name);
		}
		return list;
	}

	/**
	 * 返回所有优先度大于等于这个阈值的病人的一个列表并修改堆的方法
	 * @param threshold
	 * @return 一个列表包含了所有优先度大于等于这个阈值的病人
	 */
	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
		ArrayList<Patient> list = new ArrayList<>();
		for (int i = 1; i < patients.size(); i++) {
			if(patients.get(i).priority >= threshold){
				list.add(patients.get(i));
			}
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			remove(list.get(i).name);
		}
		return list;
	}


	/**
	 * 病人内部类
	 */
	static class Patient{
		private String name;
		private double priority;

		Patient(String name,  double priority){
			this.name = name;
			this.priority = priority;
		}

		Patient(Patient otherPatient){
			this.name = otherPatient.name;
			this.priority = otherPatient.priority;
		}

		double getPriority() {
			return this.priority;
		}

		void setPriority(double priority) {
			this.priority = priority;
		}

		String getName() {
			return this.name;
		}

		@Override
		public String toString(){
			return this.name + " - " + this.priority;
		}

		public boolean equals(Object obj){
			if (!(obj instanceof  ERPriorityQueue.Patient)) return false;
			Patient otherPatient = (Patient) obj;
			return this.name.equals(otherPatient.name) && this.priority == otherPatient.priority;
		}

	}
}
