package hw_8;

import java.util.Objects;

public class MyHashMap<K,V> {

//    +*put(Object key, Object value) добавляет пару ключ + значение
//    *remove(Object key) удаляет пару по ключу
//    *clear() очищает коллекцию
//    +*size() возвращает размер коллекции
//    +*get(Object key) возвращает значение(Object value) по ключу
    private ForwardList<K,V>[] entryListArr;
    MyHashMap(){
        entryListArr = new ForwardList[16];
        for(int i = 0; i < 16; i ++){
            entryListArr[i] = new ForwardList<K,V>();
        }
    }
    private boolean containsKey(K key){
        int index = Math.abs(key.hashCode()) % 16;
        boolean contains = false;
            if(entryListArr[index].get(key) == key)
                contains = true;
        return contains;
    }
    public void put(K key, V value){
        if(!containsKey(key)) {
            Entry<K, V> newEntry = new Entry<K, V>(key, value);
            int index = Math.abs(key.hashCode()) % 16;
            entryListArr[index].add(newEntry);
        }
        else
            throw new IllegalArgumentException("This key already presents");
    }
    public void remove(K key){
        int index = Math.abs(key.hashCode()) % 16;

        entryListArr[index].remove(key);
    }
    public void clear(){
        for(int i = 0; i < 16; i ++){
            entryListArr[i].clear();
        }
    }
    public int size(){
        int size = 0;
        for(int i = 0; i < 16; i ++){
            size += entryListArr[i].size();
        }
        return size;
    }
    public V get(K key){
        int index = Math.abs(key.hashCode()) % 16;
        return entryListArr[index].get(key);
    }
}
class ForwardList<K,V>{
    protected Entry<K,V> head;
    public int size() {
        if(head == null)
            return 0;
        int size = 0;
        Entry<K,V> wayToTail = head;
        if(wayToTail.getKey() == null)
            return 0;
        size ++;
        while (wayToTail.hasNext()){
            wayToTail = wayToTail.getNextEntry();
            if(wayToTail.getKey() != null)
                size ++;
        }
        return size;
    }

    public void add(K key, V value) {
        if(head == null){
            head = new Entry<K,V>(key,value);
            return;
        }
        Entry<K,V> newEntry;
        newEntry = new Entry<K,V>();
        Entry<K,V> tail = head;
        while(tail.hasNext()){
            tail = tail.getNexEntry();
        }
        tail.setNextEntry(newEntry);
    }
    public void add(Entry<K,V> newEntry){
        if(head == null){
            head = newEntry;
            return;
        }
        Entry<K,V> tail = head;
        while(tail.hasNext()){
            tail = tail.getNexEntry();
        }
        tail.setNextEntry(newEntry);
    }

    public void clear() {
        Entry<K,V> entry = head;
        while(entry.hasNext()){
            head.setNextEntry(null);
            head = entry;
            entry = entry.getNextEntry();
        }
        head.setNextEntry(null);
        head = null;
    }

    public V get(K key) {
       if(head == null)
           return null;
       Entry<K,V> searchingEntry = head;
       while (searchingEntry.getKey() != key && searchingEntry != null){
           searchingEntry = searchingEntry.getNextEntry();
       }
       if(searchingEntry.getKey() == key)
           return searchingEntry.getValue();
       else
           return null;
    }

    public void remove(K key) {
        Entry<K,V> currentEntry, nextEntry, previousEntry = null;
        if(head == null)
            return;
        currentEntry = head;
        while (currentEntry.hasNext())
        {
            previousEntry = currentEntry;
            currentEntry = currentEntry.getNextEntry();
        }
        if(currentEntry.hasNext()){
            nextEntry = currentEntry.getNextEntry();
            currentEntry.setKey(null);
            currentEntry.setValue(null);
            if(previousEntry == null)
                return;
            previousEntry.setNextEntry(nextEntry);
        }
    }
}
class Entry<K,V>{
    private Object key;
    private Object value;
    private Entry nextEntry;
    public Entry(){
        this(null,null,null);
    }
    public Entry(K key, V value){
        this(key, value, null);
    }
    public Entry(K key, V value, Entry nextEntry){
        this.key = key;
        this.value = value;
        this.nextEntry = nextEntry;
    }
    public boolean hasNext(){
        if(nextEntry != null)
            return true;
        return false;
    }
    public void strip(){
        nextEntry = null;
    }
    public Entry<K,V> getNexEntry() {
        return nextEntry;
    }

    public K getKey() {
        return (K)key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return (V)value;
    }
    public void setValue(V value) {
        this.value = value;
    }

    public Entry getNextEntry() {
        return nextEntry;
    }

    public void setNextEntry(Entry nextEntry) {
        this.nextEntry = nextEntry;
    }
    public int hashCode(){
        return Objects.hash(key);
    }

}