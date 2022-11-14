package src;

public class Main {


    public static void main(String[] args) {
        MyHashMap hm = new MyHashMap();
        hm.put(10, 7);
        hm.put(5, 11);
        hm.put(3, 4);
        hm.put(4, 7);
        hm.put(10, 2);
        hm.put(7, 24);
        hm.put(15, 2);
        hm.put(8, 24);


//        System.out.println(hm.get(10).getValue());
//        System.out.println(hm.get(5).getValue());
//        System.out.println(hm.get(3).getValue());
//        System.out.println(hm.get(4).getValue());
//        System.out.println(hm.get(7).getValue());
//
        System.out.println("Size: " + hm.size());
        System.out.println();
        hm.printDetails();
        System.out.println();

        hm.remove(7);

        System.out.println(hm.containsKey(5));
        System.out.println(hm.containsKey(23));

        System.out.println("Normal print of keys and values: ");
        hm.print();
        System.out.println();

        hm.put(14, 7);
        hm.put(11, 2);
        hm.put(66, 24);
        hm.put(25, 2);
        hm.put(18, 24);

        System.out.println("after resizing/rehashing");
        System.out.println("Size: " + hm.size());
        System.out.println();
        hm.printDetails();

    }
}
