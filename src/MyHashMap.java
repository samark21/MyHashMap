package src;

import java.util.LinkedList;

public class MyHashMap {

    final int DEFAULT_INITIAL_SIZE = 10;

    LinkedList<Entry>[] hashmap;
    private int size = 0;
    private double loadFactor = 0.75;
    private double expansionRate = 2;

    public MyHashMap() {
        this.hashmap = new LinkedList[DEFAULT_INITIAL_SIZE];
    }

    public MyHashMap(double loadFactor) {
        this.loadFactor = loadFactor;
        this.hashmap = new LinkedList[DEFAULT_INITIAL_SIZE];
    }

    public MyHashMap(int initialSize) {
        this.hashmap = new LinkedList[initialSize];
    }

    public MyHashMap(double loadFactor, int initialSize) {
        this.loadFactor = loadFactor;
        this.hashmap = new LinkedList[initialSize];
    }

    public MyHashMap(double loadFactor, int initialSize, double expansionRate) {
        this.loadFactor = loadFactor;
        this.hashmap = new LinkedList[initialSize];
        this.expansionRate = expansionRate;
    }

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
     * this method puts a new entry in the hashmap only if given key doesn't already exist in it.
     *
     * @param k int key.
     * @param v object value.
     */
    public void putIfAbsent(int k, Object v) {
        Entry entry = getEntry(k);
        if (entry == null) {
            put(k, v);
        }
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
        Key k = new Key(key);
        int index = getIndex(k);
        if (hashmap[index] != null) {
            for (Entry e : hashmap[index]) {
                if (e.key.equals(k)) {
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

    /**
     * this method replaces a key's value with newValue if it exists in the hashmap.
     * it checks if there is an entry that contains key and oldValue,
     * and replaces oldValue with newValue.
     *
     * @param key int, specified key.
     * @param oldValue current value.
     * @param newValue new value.
     */
    public void replace(int key, Object oldValue, Object newValue) {
        Entry entry = getEntry(key);
        if (entry != null) {
            if (oldValue.equals(entry.getValue())) {
                entry.value = newValue;
            }
        }
    }

    /**
     * this method replaces a key's value with newValue if it exists in the hashmap.
     *
     * @param key int, specified key.
     * @param newValue new value.
     */
    public void replace(int key, Object newValue) {
        Entry entry = getEntry(key);
        if (entry != null) {
            entry.value = newValue;
        }
    }

    public boolean isEmpty(){
        for(LinkedList<Entry> entry : hashmap){
            if(entry != null){
                return false;
            }
        }

        return true;
    }

    /**
     * this method returns the number of entries in the hashmap.
     *
     * @return number of entries.
     */
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

    /**
     * prints each entry of the hashmap including details of key, value and index in array.
     */
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

    private int getHash(Key key) {
        return key.hashCode();
    }

    private int getIndex(Key key) {
        return getHash(key) % hashmap.length;
    }

    /**
     * this method checks if there is suck entry with given key.
     * @param key
     * @return entry - if exists. null otherwise.
     */
    private Entry getEntry(int key) {
        Key k = new Key(key);
        int index = getIndex(k);
        if (hashmap[index] != null) {
            for (Entry e : hashmap[index]) {
                if (e.key.equals(k)) {
                    return e;
                }
            }
        }
        return null;
    }

    /* ------------------------------------------- USER FEEDBACK METHODS ------------------------------------------- */

    /**
     * @return load status at time of call.
     */
    public double getLoadStatus() {
        return ((double) size / hashmap.length);
    }

    /**
     * empty slots are the slots in the array that have yet been filled with entries.
     * in other words, their linked-list is still empty.
     *
     * @return number of empty slots remaining in the hashmap.
     */
    public int getEmptySlots() {
        int counter = 0;
        for (LinkedList<Entry> entry : hashmap) {
            if (entry == null) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return percentage of empty slots out of total slots (size of hashmap).
     */
    public double getEmptySlotsPercentage() {
        return 1 - ((double) getEmptySlots() / hashmap.length);
    }

}
