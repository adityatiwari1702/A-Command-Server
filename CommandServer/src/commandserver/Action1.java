package commandserver;

import java.io.PrintWriter;

/**
 *
 * @author Aditya Tiwari
 */
public class Action1 implements Action {

    @Override
    public void execute(PrintWriter out) {
        out.println("This is is Action1");
        for (int i = 0; i < 5; i++) {
            out.println("Action1 " + i);
        }
        out.println("Action1 Ends");

    }

}
