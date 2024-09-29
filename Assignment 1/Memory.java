import java.util.LinkedList;
public class Memory {

    public class StringInterval{
        int id ;
        int start;
        int length;

        public StringInterval(int id, int start, int length) {
            this.id = id;
            this.start = start;
            this.length = length;
        }

        public String toString() {
            return "[ start: " + start + " length: " + length + " id: " + id + " ]";
        }
    }

    LinkedList<StringInterval> intervalList ;
    char[] memoryArray ;
    static int idCount;

    public Memory(int length) {
        memoryArray = new char[length];
        intervalList = new LinkedList<>();
        idCount = 0;
    }

    public int showidCount(){
        return idCount;
    }

    public String get(int id){
        String result = "";
        for (int i = 0; i < intervalList.size(); i++) {
            if(intervalList.get(i).id == id){
                for (int j = intervalList.get(i).start; j < intervalList.get(i).start + intervalList.get(i).length; j++) {
                    result += memoryArray[j];
                }
                return result;
            }
        }
        return null;
    }

    public int get(String s){
        int result = 0;
        for (int i = 0; i < intervalList.size(); i++) {
            String str  = "";
            for (int j = intervalList.get(i).start; j < intervalList.get(i).start + intervalList.get(i).length; j++) {
                str += memoryArray[j];
            }
            if(s.equals(str)){
                result = intervalList.get(i).id;
                return result;
            }
        }
        return -1;
    }

    public String remove(int id){
        String result = "";
        if(get(id) != null){
            result += get(id);
            for (int i = 0; i < intervalList.size(); i++) {
                if(intervalList.get(i).id == id){
                    intervalList.remove(i);
                }
            }
            return result;
        }
        return null;
    }

    public int remove(String s){
        int result = 0;
        if(get(s) != -1){
            result += get(s);
            for (int i = 0; i < intervalList.size(); i++) {
                String str = "";
                for (int j = intervalList.get(i).start; j < intervalList.get(i).start + intervalList.get(i).length; j++) {
                    str += memoryArray[j];
                }
                if(s.equals(str)){
                    intervalList.remove(i);
                }
            }
            return result;
        }
        return -1;
    }

    public int put(String s){
         int strLength = s.length();

         if(memoryArray.length < strLength){
             return -1;
         }
         else if(intervalList.size() == 0 && idCount == 0){
             intervalList.add(0,new StringInterval(idCount,0,strLength));
             for (int i = 0; i < strLength; i++) {
                 memoryArray[i] = s.charAt(i);
             }
             idCount ++;
             return 0;
         }
         else if(intervalList.get(0).start >= strLength){
             intervalList.add(0,new StringInterval(idCount,0,strLength));
             for (int i = 0; i < strLength; i++) {
                 memoryArray[i] = s.charAt(i);
             }
             idCount ++;
             return idCount - 1;
         }
         else if(intervalList.size() >=2 && findGapLo(strLength) != -1){
             for (int i = findGapLo(strLength); i < findGapLo(strLength) + strLength; i++) {
                 memoryArray[i] = s.charAt(i - findGapLo(strLength));
             }
             intervalList.add(findGapNum(strLength) + 1,new StringInterval(idCount,findGapLo(strLength),strLength));
             idCount ++;
             return idCount - 1;
         }
         else if(((memoryArray.length) - (intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length)) >= strLength && intervalList.size() >= 1){
             for (int i = intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length; i < intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length + strLength; i++) {
                 memoryArray[i] = s.charAt(i - (intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length));
             }
             intervalList.add(new StringInterval(idCount,intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length,strLength));
             idCount ++;
             return idCount - 1;
         }
         else {
             defragment();
             if(((memoryArray.length) - (intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length)) >= strLength){
                 for (int i = intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length; i < intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length + strLength; i++) {
                     memoryArray[i] = s.charAt(i - (intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length));
                 }
                 intervalList.add(new StringInterval(idCount,intervalList.get(intervalList.size() - 1).start + intervalList.get(intervalList.size() - 1).length,strLength));
                 idCount ++;
                 return idCount - 1;
             }
             else {
                 return -1;
             }
         }

    }

    public void defragment(){
        if(intervalList.get(0).start > 0){
            for (int i = intervalList.get(0).start; i < intervalList.get(0).start + intervalList.get(0).length; i++) {
                memoryArray[i - intervalList.get(0).start] = memoryArray[i];
            }
            intervalList.get(0).start = 0;
        }
        for (int i = 1; i < intervalList.size(); i++) {
            if(intervalList.get(i).start > (intervalList.get(i - 1).start + intervalList.get(i - 1).length)){
                for (int j = intervalList.get(i).start;  j < intervalList.get(i).start + intervalList.get(i).length; j++) {
                    memoryArray[j - (j - (intervalList.get(i - 1).start + intervalList.get(i - 1).length)) + (j - intervalList.get(i).start)] = memoryArray[j];
                }
                intervalList.get(i).start -= (intervalList.get(i).start - (intervalList.get(i - 1).start + intervalList.get(i - 1).length));
            }
        }
    }

    public int findGapLo(int length) {
        int result = 0 ;
        for (int i = 0; i < intervalList.size() - 1; i++) {
            if ((intervalList.get(i + 1).start - (intervalList.get(i).start + intervalList.get(i).length)) >= length) {
                result += intervalList.get(i).start + intervalList.get(i).length;
                return result;
            }
        }
        return -1;
    }

    public int findGapNum(int length) {
        for (int i = 0; i < intervalList.size() - 1; i++) {
            if ((intervalList.get(i + 1).start - (intervalList.get(i).start + intervalList.get(i).length)) >= length) {
                return i;
            }
        }
        return -1;
    }



}
