package hw_8;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class MyArrayList<E> {
    private static final float EULER_NUMBER = 2.71828182845904523536028747135266249775724709369995957f;
    private static final float SCALING_FACTOR = 0.015f;
    private static final float LIMIT_IN_INFINITY_FACTOR = 1 / EULER_NUMBER;
    private static final int INITIAL_SIZE = 8;
    private int size;
    private Object[] data;
    private void resize(){
        int size = data.length;;
        int newSize = (int)( size * (LIMIT_IN_INFINITY_FACTOR + 1 + (EULER_NUMBER / (1 + SCALING_FACTOR * size))));
        data = Arrays.copyOf(data, newSize);
    }
    public MyArrayList(){//Чи правильно ініціювати data інстансами типу  Object чи краще кидати в конструктор назву класу яким ініціювати?
        size = 0;
        data = new Object[INITIAL_SIZE];
    }
    private void excludeSpecificDataElement(int index){
        Object tmp;
        int indexOfLastElement = size() - 1;
        data[index] = data[index + 1];
        for(int i = index + 1; i < indexOfLastElement; i ++){
            tmp = data[i + 1];
            data[i + 1] = data[i];
            data[i] = tmp;
            index ++;
        }
        data[indexOfLastElement] = null;
        size --;
    }
    private int getSize(){return size;}

    public int size(){
        int sizeCounter = 0;
        for (Object datum : data) {
            if(datum == null) {
                if(this.size != sizeCounter)
                    System.out.println("Internal size of " + this.getClass() + " is wrong");
                return sizeCounter;
            }
            sizeCounter ++;
        }
        if(this.size != sizeCounter)
            System.out.println("Internal size of " + this.getClass() + " is wrong");
        return sizeCounter;
    }
    public boolean isEmpty(){
        return data[0] == null? true:false;
    }
    public int debugGetAllocatedMemoryElements(){
        return data.length;
    }

    public boolean contains(Object obj) {
        for (Object datum : data) {
            if (datum.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(E newElement){
        if(newElement == null)
            throw new NullPointerException("New element is \"null\"");
        int tail = size();
        if(data.length > tail) {
            data[tail] = newElement;
            size ++;
        }
        else{
            try {
                resize();
            }
            catch (OutOfMemoryError e){
                System.out.println(e.getStackTrace() + " current internal array size is " + data.length);
                return false;
            }
            data[tail] = newElement;
            size ++;
        }
        return true;
    }

    public boolean remove(Object value){
        if(value == null)
            return false;
        boolean didValuePresent = false;
        int countOfValueInstances = 0;
        /*for (Object datum : data) {
            if(datum.equals(value))
                countOfValueInstances ++;
        }*/ // Тут проблема в тім, що деякі елементи data мають значення null, null не має метода .equals(). Цикл потрібно переписати так щоб не переходити на null елементи
        for(int i = 0; i < size; i ++){
            if(data[i].equals(value))
                countOfValueInstances++;
        }
        if(countOfValueInstances > 0)
            didValuePresent = true;
        else
            return didValuePresent;
        int[] indexesOfValue = new int[countOfValueInstances];
        int indexOfIndexOfValues = 0;
        for(int i = 0; i < size; i ++){
            if(data[i].equals(value)){
                indexesOfValue[indexOfIndexOfValues] = i;
                indexOfIndexOfValues ++;
            }
        }
        for (int index : indexesOfValue) {
            excludeSpecificDataElement(index);
        }
        return didValuePresent;
    }

    public void clear() {
        for(int i = 0; i < size; i ++){
            data[i] = null;
        }
        size = 0;
    }
    public void releaseMemory(){
        this.data = new Object[INITIAL_SIZE];
    }

    public E get(int index) {
        return (E)data[index];
    }

    public E remove(int index) {
        Object obj = data[index];
        excludeSpecificDataElement(index);
        return (E)obj;
    }

    public E[] toArray(){
        return (E[])Arrays.copyOfRange(data, 0, size()-1);
    }

}
