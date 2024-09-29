import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Test {
    public static void main(String[] args) {


        ERPriorityQueue queue = new ERPriorityQueue();
        ArrayList<ERPriorityQueue.Patient> list = queue.patients;
        HashMap<String,Integer> map = queue.nameToIndex;

        queue.add("wjh",12);
        queue.add("hjt",8);
        queue.add("xjp",67);
        queue.add("jzm",22);
        queue.add("cgx",9);
        queue.add("gjm",13);

//        ArrayList<ERPriorityQueue.Patient> list2 = queue.removeUrgentPatients(13);
        ArrayList<ERPriorityQueue.Patient> list3 = queue.removeNonUrgentPatients(13);


        for(ERPriorityQueue.Patient patient : list3){
            System.out.println(patient);
        }

        System.out.println("\n");


        for(ERPriorityQueue.Patient patient : list){
            System.out.println(patient);
        }

        System.out.println("\n");

        Set<String> keys = map.keySet();
        for (String key : keys){
            System.out.println(key + "  -  " + map.get(key));
        }


    }
}
