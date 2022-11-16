package src;

import java.util.HashMap;

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

        hm.putIfAbsent(10,156486451);
        hm.putIfAbsent(1,1);


        System.out.println("Empty slots: ");
        System.out.println(hm.getEmptySlots());
        System.out.println(hm.getEmptySlotsPercentage());
        System.out.println();

        System.out.println("replace(key, oldValue, newValue): ");
        System.out.println("before: " + hm.get(7));
        hm.replace(7, 24, 10);
        System.out.println("after: " + hm.get(7));
        System.out.println("replace(key, oldValue): ");
        System.out.println("before: " + hm.get(7));
        hm.replace(7, 24);
        System.out.println("after: " + hm.get(7));
        System.out.println();

        System.out.println("Size: " + hm.size());
        System.out.println();
        hm.printDetails();
        System.out.println();

        hm.remove(7);

        System.out.println("Contains key: ");
        System.out.println(hm.containsKey(5));
        System.out.println(hm.containsKey(7));
        System.out.println();

        System.out.println("Normal print of keys and values: ");
        hm.print();
        System.out.println();
        System.out.println("Load factor status " + hm.getLoadStatus());

        hm.put(14, 7);
        hm.put(11, 2);
        hm.put(66, 24);
        hm.put(25, 2);
        hm.put(18, 24);

        System.out.println("after resizing/rehashing");
        System.out.println("Size: " + hm.size());
        System.out.println();
        hm.printDetails();

        System.out.println("Load factor status " + hm.getLoadStatus());

        System.out.println("hm isEmpty? " + hm.isEmpty());

        MyHashMap hm2 = new MyHashMap();
        System.out.println("hm2 isEmpty? " + hm2.isEmpty());


    }
}
