package hw_8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
public class MyLinkedList<E> {
    protected Node<E> head;
    protected void connect(Node<E>previous, Node<E>next){
        previous.setNextNode(next);
        next.setPreviousNode(previous);
    }
    public int size() {
        if(head == null)
            return 0;
        int size = 0;
        Node<E> wayToTail = head;
        if(wayToTail.getData() == null)
            return 0;
        size ++;
        while (wayToTail.hasNext()){
            wayToTail = wayToTail.getNextNode();
            if(wayToTail.getData() != null)
                size ++;
        }
        return size;
    }

    public void add(E e) {
        if(head == null){
                head = new Node<E>(e);
                return;/*не перевірено чи викликає цей ретарн проблеми*/
            }
        Node<E> newNode;
            newNode = new Node<E>(e);
        Node<E> tail = head;
        while(tail.hasNext()){
            tail = tail.getNextNode();
        }
        connect(tail, newNode);
    }

    public void clear() {
        Node node = head;
        while(node.hasNext()){
            node = node.getNextNode();
        }
        while(node.hasPrevious()) {
            node = node.getPreviousNode();
            node.setNextNode(null);
        }
        head = null;
    }

    public E get(int index) {
        Node<E> result = head;
        for(int i = 0; i < index ;i++){
            if(result.hasNext())
            result = result.getNextNode();
            else throw new IndexOutOfBoundsException();
        }
        return (E)(result.getData());
    }

    public void add(int index, E element) {
        if(head == null) {
            head = new Node<E>(element);
            return;
        }
        Node<E> moveToIndex = head;
        int i = 0;
        for(; i < index - 1; i ++){
            if(moveToIndex.hasNext())
                moveToIndex = moveToIndex.getNextNode();
            else
                throw new IndexOutOfBoundsException("Last index is " + i);
        }
        Node<E> previous = moveToIndex;
        if(!previous.hasNext()){
            Node<E> currentElement = new Node<E>(element);
            connect(previous,currentElement);
            return;
        }
        Node<E> next = moveToIndex.getNextNode();
        Node<E> currentElement = new Node<E>(element);
        connect(previous,currentElement);
        connect(currentElement,next);
    }

    public E remove(int index) {
        Node<E> currentNode, nextNode, previousNode;
        currentNode = head;
        for (int i = 0; i < index; i ++)
        {
            if(currentNode.hasNext())
                currentNode = currentNode.getNextNode();
            else throw new IndexOutOfBoundsException();
        }
        if(!currentNode.hasPrevious() && !currentNode.hasNext()){
            Object data = currentNode.getData();
            currentNode.setData(null);
            return (E)data;
        }
        if(!currentNode.hasNext()){
            previousNode = currentNode.getPreviousNode();
            previousNode.setNextNode(null);
            return (E)currentNode.getData();
        }
        if(!currentNode.hasPrevious()){
            head = currentNode.getNextNode();
            head.setPreviousNode(null);
            return (E)currentNode.getData();
        }
        previousNode = currentNode.getPreviousNode();
        nextNode = currentNode.getNextNode();
        connect(previousNode,nextNode);
        return (E)currentNode.getData();
    }

}
class Node<E>{
    private Object data;
    private Node nextNode;
    private Node previousNode;
    public Node(){
        this(null);
        nextNode = null;
        previousNode = null;
    }
    public Node(E data){
        this(data, null, null);
    }
    public Node(E data,Node<E> next, Node<E> previous){
        this.data = data;
        nextNode = next;
        previousNode = previous;
    }
    public boolean hasNext(){
        if(nextNode != null)
            return true;
        return false;
    }
    public boolean hasPrevious(){
        if(previousNode != null)
            return true;
        return false;
    }
    public void strip(){
        nextNode = null;
        previousNode = null;
    }
    public Node getNextNode() {
        return nextNode;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public void setPreviousNode(Node previousNode) {this.previousNode = previousNode;}
}
