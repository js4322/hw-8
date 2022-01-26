package hw_8;

public class MyQueue<T> extends MyLinkedList<T>{

//    add(Object value) добавляет элемент в конец
//    remove(int index) удаляет элемент под индексом
//    clear() очищает коллекцию
//    size() возвращает размер коллекции
//    peek() возвращает первый элемент в очереди (FIFO)
//    poll() возвращает первый элемент в очереди и удаляет его из коллекции

    public void add( T value) {
        super.add(super.size(), value);
    }
    public T peak(){
        return super.get(super.size()-1);
    }
    public T poll(){
        T result =  super.get(super.size()-1);
        super.remove(size()-1);
        return result;
    }
}
