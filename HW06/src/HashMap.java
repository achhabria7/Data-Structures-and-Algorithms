import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 * 
 * @author Anmol Chhabria
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {

        this(INITIAL_CAPACITY);

    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {

        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {

        if (value == null || key == null) {
            throw new IllegalArgumentException(
                    "Neither Value nor Key can be null.");
        }

        if ((size + 1) > table.length * MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }

        V ret = putHelper(key, value, table);

        if (ret == null) {
            size++;
            return null;
        }

        return ret;
    }

    /**
     *
     * @param key the MapEntry key to be added
     * @param value the value for the entry being added
     * @param aTable the table that the entry is being added to
     * @return return the old value if it existed, otherwise
     * return null
     */

    private V putHelper(K key, V value, MapEntry<K, V>[] aTable) {

        int i = hashValue(key, aTable);

        if (aTable[i] == null) {
            aTable[i] = new MapEntry<>(key, value, null);
            return null;
        } else {

            MapEntry<K, V> current = aTable[i];
            while (current != null) {

                if (current.getKey().equals(key)) {
                    V ret = current.getValue();
                    current.setValue(value);
                    return ret;
                } else if (current.getNext() == null) {
                    MapEntry<K, V> newest =
                            new MapEntry<K, V>(key, value, aTable[i]);
                    aTable[i] = newest;
                    return null;
                }

                current = current.getNext();
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {

        if (key == null) {

            throw new IllegalArgumentException("Key cannot be null.");

        } else if (table[hashValue(key, table)] == null) {

            throw new NoSuchElementException("This key is not in the HashMap.");

        } else if (table[hashValue(key, table)].getKey().equals(key)) {
            V ret = table[hashValue(key, table)].getValue();
            table[hashValue(key, table)] =
                    table[hashValue(key, table)].getNext();
            size--;
            return ret;
        } else {

            MapEntry<K, V> current = table[hashValue(key, table)];
            MapEntry<K, V> currentNext = current.getNext();

            while (currentNext != null) {
                if (currentNext.getKey().equals(key)) {
                    V ret = currentNext.getValue();
                    current.setNext(currentNext.getNext());
                    size--;
                    return ret;
                }
                current = current.getNext();
                currentNext = current.getNext();
            }

            throw new NoSuchElementException("This key is not in the HashMap.");
        }
    }

    @Override
    public V get(K key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        } else if (table[hashValue(key, table)] == null) {

            throw new NoSuchElementException("This key is not in the HashMap.");

        } else {

            MapEntry<K, V> current = table[hashValue(key, table)];

            while (current != null) {
                if (current.getKey().equals(key)) {
                    return current.getValue();
                }
                current = current.getNext();
            }
        }
        throw new NoSuchElementException("This key is not in the HashMap.");
    }

    @Override
    public boolean containsKey(K key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if (table[hashValue(key, table)] != null) {

            MapEntry<K, V> current = table[hashValue(key, table)];

            while (current != null) {
                if (current.getKey().equals(key)) {
                    return true;
                }

                current = current.getNext();
            }
        }

        return false;
    }

    @Override
    public void clear() {

        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public Set<K> keySet() {

        HashSet<K> ret = new HashSet<K>(size);

        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> entry = table[i];
            while (entry != null) {
                ret.add(entry.getKey());
                entry = entry.getNext();
            }
        }

        return ret;
    }

    @Override
    public List<V> values() {

        List<V> ret = new ArrayList<V>(size);

        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> entry = table[i];
            while (entry != null) {
                ret.add(entry.getValue());
                entry = entry.getNext();
            }
        }

        return ret;
    }

    @Override
    public void resizeBackingTable(int length) {

        if (length < (size) || length <= 0) {
            throw new IllegalArgumentException("Invalid length parameter.");
        }

        MapEntry<K, V>[] tempTable = new MapEntry[length];
        MapEntry<K, V> temp;

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                temp = table[i];
                while (temp != null) {
                    MapEntry<K, V> store = temp.getNext();
                    temp.setNext(null);
                    putHelper(temp.getKey(), temp.getValue(), tempTable);
                    temp = store;
                }
            }
        }

        table = tempTable;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

    /**
     *
     * @param key the key you need the HashValue for
     * @param aTable the table that the key is being added to
     * @return return the hashvalue for the key,
     * which is the mod of the hashcode
     * of the key with the aTable length
     */

    private int hashValue(K key, MapEntry<K, V>[] aTable) {
        return (Math.abs(key.hashCode())) % aTable.length;
    }

}
