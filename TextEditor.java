import java.util.Scanner;
/**
 * A command line text editor.
 * 
 * @author Mordechai Schmutter 
 * @version 1.0
 */
public class TextEditor
{
    private static ArrayStack<String> text = new ArrayStack<String>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Begin typing:");
        readInput();
    }

    private static void readInput(){
        String input = scanner.nextLine();
        if(input.equals("#exit")){
            //exit the program
            return;
        }
        else if(input.equals("#delete")){
            //create empty stack
            ArrayStack<String> empty = new ArrayStack<String>();
            //set original stack equal to new stack
            text = empty;
        }
        else if(input.startsWith("#delete ") && Character.isDigit(input.charAt(8))){
            //parse number from text
            int line = Integer.parseInt(input.substring(8));
            //calculate how many lines to pop onto temporary stack
            int top = text.getTop();
            int j = top - line + 1;
            //create temporary stack
            ArrayStack<String> temp = new ArrayStack<String>();
            //pop lines above target line onto temporary stack
            for (int i = 0; i < j; i++){
                temp.push(text.pop());
            }
            //permanently pop line to delete
            text.pop();
            //push lines back to original stack
            while(temp.getTop() != -1){
                text.push(temp.pop());
            }
        }
        else if(input.equals("#print")){
            System.out.print(text);
        }
        else if(input.startsWith("#print ") && Character.isDigit(input.charAt(7))){
            //parse number from text
            int line = Integer.parseInt(input.substring(7));
            //calculate how many lines to pop onto temporary stack
            int top = text.getTop();
            int j = top - line + 1;
            //create temporary stack
            ArrayStack<String> temp = new ArrayStack<String>();
            //pop lines above target line onto temporary stack
            for (int i = 0; i < j; i++){
                temp.push(text.pop());
            }
            //print top line of original stack
            System.out.println(text.peek());
            //push lines back to original stack
            while(temp.getTop() != -1){
                text.push(temp.pop());
            }
        }
        else if(input.startsWith("#")){
            System.out.println("No such command exists.");
        }
        else{
            //push user input to stack
            text.push(input);
        }
        //use recursion to continue taking user input
        readInput();
    }
}
