/**
 * An array-based stack that doubles in size when capacity is reached.
 * 
 * @author Mordechai Schmutter 
 * @version 1.0
 */
public class ArrayStack<T>{
    private T[] stack;
    private int top;
    public ArrayStack(){
        this.stack = (T[]) new Object[9];
        this.top = -1;
    }

    public void push(T item){
        if (this.top == stack.length - 1) {
            expand(2 * this.top);
        }
        stack[++top] = item;
    }

    public T pop(){
        if (top == -1) {
            return null;
        }
        T item = stack[top];
        stack[top--] = null;
        return item;
    }

    public T peek(){
        return stack[top];
    }
    
    public int getTop(){
        return top;
    }

    private void expand(int size){
        T[] copy = (T[]) new Object[size];
        for (int i = 0; i < top + 1; i++){
            copy[i] = this.stack[i];
        }
        this.stack = copy;
    }

    public String toString(){
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < top + 1; i++){
            T item = stack[i];
            output.append(item.toString() + "\n");
        }
        return output.toString();
    }
}