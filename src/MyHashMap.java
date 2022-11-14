package src;

import java.util.LinkedList;

public class MyHashMap {

//    final double DEFAULT_LOAD_FACTOR = 0.75;
//    final int DEFAULT_INITIAL_SIZE = 10;
//    final int DEFAULT_EXPANSION_RATE = 2;

    LinkedList<Entry>[] hashmap;
    private int size = 0;
    private double loadFactor = 0.75;
    private int initialSize = 10;
    private double expansionRate = 2;

    public MyHashMap() {
        this.hashmap = new LinkedList[initialSize];
    }

    public MyHashMap(double loadFactor) {
        this.loadFactor = loadFactor;
        this.hashmap = new LinkedList[initialSize];
    }

    public MyHashMap(int initialSize) {
        this.hashmap = new LinkedList[initialSize];
    }

    public MyHashMap(double loadFactor, int initialSize, double expansionRate) {
        this.loadFactor = loadFactor;
        this.hashmap = new LinkedList[initialSize];
        this.expansionRate = expansionRate;
    }

    // so far there is no option to add a filled array into a hashmap.
    // only create one by inserting each element one by one.
    // otherwise, we would've looped over every item of the array and put it.

    /**
     * this function puts the entry in the LinkedList array.
     * if the size has reached the length of the hashmap then, resize it.
     * check if array slot is empty then add the entry to the linkedlist,
     * otherwise check if there is a similar key, if yes, change value,
     * otherwise add a new node and insert the entry.
     *
     * @param k int, easier for the user instead of inserting a new Key object each time.
     * @param v int, easier for the user instead of inserting a new Value object each time.
     */
    public void put(int k, Object v) {
        if ((double) (size / hashmap.length) >= loadFactor) {
            resize();
        }

        Key key = new Key(k);
        int index = getIndex(key);

        // if array slot is empty
        // fill it with a new LinkedList and populate it with the Entry
        if (hashmap[index] == null) {
            hashmap[index] = new LinkedList<>();
        } else { // collision
            // check if key is already in the hashmap.
            for (Entry e : hashmap[index]) {
                if (e.key.equals(key)) {
                    e.value = v;
                    return;
                }
            }
        }
        hashmap[index].add(new Entry(key, v));
        size++;
    }

    /**
     * this method returns the value of the desired key.
     * **Note - the return type can be changed to int and return will become
     * return e.value.getValue();
     *
     * @param key int, easier for the user instead of inserting a new Key object each time.
     * @return value object
     */
    public Object get(int key) {
        Key key1 = new Key(key);
        int index = getIndex(key1);
        if (hashmap[index] != null) {
            for (Entry e : hashmap[index]) {
                if (e.key.equals(key1)) {
                    return e.value;
                }
            }
        }
        return null;
    }

    /**
     * this method checks if the hashmap contains the desired key.
     *
     * @param key int, easier for the user instead of inserting a new Key object each time.
     * @return true if key exists in hashmap, false if not.
     */
    public boolean containsKey(int key) {
        Key key1 = new Key(key);
        int index = getIndex(key1);
        if (hashmap[index] != null) {
            for (Entry e : hashmap[index]) {
                if (e.key.equals(key1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * this method removes the desired entry from the hashmap.
     *
     * @param key int, easier for the user instead of inserting a new Key object each time.
     */
    public void remove(int key) {
        Key k = new Key(key);
        int index = getIndex(k);
        if (hashmap[index] != null) {
            Entry entry = hashmap[index]
                    .stream()
                    .filter(e -> e.key.equals(k))
                    .findFirst()
                    .orElse(null);
            if (entry != null) {
                hashmap[index].remove(entry);
                size--;
            }
        }
    }

    public int size() {
        return size;
    }

    /**
     * copies hashmap into new array
     * by recalculating the hashcode of each element
     * and inserting them in their appropriate index.
     * the oldHashMap served us as a temp, to insert the old hashmap into the new one.
     * which overrides the old hashmap (inorder to use the put method).
     */
    private void resize() {
        LinkedList<Entry>[] oldHashMap = hashmap;
        hashmap = new LinkedList[calculateNewSize()];
        size = 0;
        for (LinkedList<Entry> entries : oldHashMap) {
            if (entries != null) {
                for (Entry e : entries) {
                    put(e.key.getKey(), e.getValue());
                }
            }
        }

    }

    private int calculateNewSize() {
        return (int) (size * expansionRate);
    }

    /**
     * this method prints all the keys and their values that are in the hashmap.
     */
    public void print() {
        for (LinkedList<Entry> entries : hashmap) {
            if (entries != null) {
                for (Entry e : entries) {
                    System.out.println("Key: " + e.key.getKey() + " Value: " + e.getValue());
                }
            }
        }
    }

    public void printDetails() {
        for (LinkedList<Entry> entries : hashmap) {
            if (entries != null) {
                for (Entry e : entries) {
                    System.out.println("Key: " + e.key.getKey() + " Value: " + e.getValue()
                            + " index: " + getIndex(e.key));
                }
            }
        }
    }

    // the following methods are to be changed into the implementation of the hashcode method in Key class.
    private int getHash(Key key) {
        return key.hashCode();
    }

    private int getIndex(Key key) {
        return getHash(key) % hashmap.length;
    }

    public double getLoadStatus() {
        return ((double) size / hashmap.length);
    }


}
