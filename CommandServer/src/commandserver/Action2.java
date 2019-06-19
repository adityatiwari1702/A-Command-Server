package commandserver;

import java.io.PrintWriter;

/**
 *
 * @author Aditya Tiwari
 */
public class Action2 implements Action{

    @Override
    public void execute(PrintWriter out) {
        out.println("This is is Action2");
       for(int i=0;i<5;i++){
           out.println("Action2 "+i);
       }
       out.println("Action2 Ends");
    }
    
}
