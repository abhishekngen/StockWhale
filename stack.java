
import java.util.ArrayList;

public class stack {

    public stack(){
        System.out.println("Stack Instantiated"); //Debug message to console
    }

    private ArrayList<String> StackString = new ArrayList<String>(); //List used to produce stack containing string values
    private ArrayList<String[][]> StackStringArray = new ArrayList<String[][]>(); //List used to produce stack containing string array values

    public void push(String s){ //Push method for pushing string values onto stack when string parameter is used
        try {
            StackString.add(s);
        }
        catch(ArrayStoreException e){ //If string array value is attempted to be inserted into string stack
            System.out.println("Error: " + e + " - wrong data type inserted into stack");
        }
    }

    public void push(String[][] s){ //Push method for pushing string array values onto stack when string array parameter is used
        try {
            StackStringArray.add(s);
        }
        catch(ArrayStoreException e){ //If string value is attempted to be inserted into string array stack
            System.out.println("Error: " + e + " - wrong data type inserted into stack");
        }
    }

    public String popString(){ //Pop function for string stacks
        try {
            String s = StackString.get(StackString.size()-1);
            StackString.remove(StackString.size()-1); //Takes top value, copies, removes from stack, then returns copied value
            return s;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error: " + e);
            return null;
        }
    }

    public String[][] popStringArray(){ //Pop function for string array stacks
        try {
            String[][] s = StackStringArray.get(StackStringArray.size()-1);
            StackStringArray.remove(StackStringArray.size()-1); //Takes top value, copies, removes from stack, then returns copied value
            return s;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error: " + e);
            return null;
        }
    }

    public int sizeString(){
        return StackString.size(); //To return size of string stack
    }

    public int sizeStringArray(){
        return StackStringArray.size(); //To return size of string array stack
    }
}
