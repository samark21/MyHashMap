package src;

public class Main {


    public static void main(String[] args) {
        System.out.println("hii");
        System.out.println("hii 2");

        MyHashMap hm = new MyHashMap();
        hm.put(10, 7);
        hm.put(5, 11);
        hm.put(3, 4);
        hm.put(4, 7);
        hm.put(10, 2);
        hm.put(7, 24);


        System.out.println(hm.get(10).getValue());
        System.out.println(hm.get(5).getValue());
        System.out.println(hm.get(3).getValue());
        System.out.println(hm.get(4).getValue());
        System.out.println(hm.get(7).getValue());

        System.out.println(hm.containsKey(5));
        System.out.println(hm.containsKey(6));

        System.out.println(hm.size());


    }
}
