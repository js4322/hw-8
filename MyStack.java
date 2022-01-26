package hw_8;

public class MyStack<T> extends MyLinkedList<T>{
    public void push(Object value){
        Node<T> newNode = new Node<T>((T)value);
        if(super.head != null)
        super.connect(newNode,super.head);
        super.head = newNode;
    }
    public T peek(){
        return (T)super.head.getData();
    }
    public T pop(){
        T result = (T)super.head.getData();
        super.remove(0);
        return result;
    }

}
