public class Test02 {
    public static void main(String[] args) {
        Owner me = new Owner("jerry");
        Staff s1 = new Staff("bob",true);
        Server s2 = new Server("bill",30);
        Restaurant res = new Restaurant("ham",12,me,s1,s2);
        System.out.println(me.getFoodPlace());
    }
}
