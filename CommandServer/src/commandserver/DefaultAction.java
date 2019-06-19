package commandserver;
import java.io.PrintWriter;

/**
 *
 * @author Aditya Tiwari
 */
public class DefaultAction implements Action {

    @Override
    public void execute(PrintWriter out) {
        out.println("This is is DefaultAction");
        for (int i = 0; i < 5; i++) {
            out.println("DefaultAction " + i);
        }
        out.println("DefaultAction Ends");

    }

}
