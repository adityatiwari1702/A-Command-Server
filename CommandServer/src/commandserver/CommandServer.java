package commandserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Tiwari
 */
public class CommandServer {

    public static void main(String[] args) throws Exception {
        int portno = Integer.parseInt(JOptionPane.showInputDialog("Enter Port No for Server:-"));
        try {
            ServerSocket ss = new ServerSocket(portno);
            Socket soc=null;
            try {
                soc = ss.accept();
                BufferedReader nis=new BufferedReader(new InputStreamReader(soc.getInputStream()));
                PrintWriter nos=new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
                String command=nis.readLine();
                while(!command.equalsIgnoreCase("end")){
                    try{
                        Class t=Class.forName("commandserver."+command);
                        Action a=(Action)t.newInstance();
                        a.execute(nos);
                    }catch(ClassNotFoundException |InstantiationException e){
                        DefaultAction a=new DefaultAction();
                        a.execute(nos);
                    }
                    command=nis.readLine();
                }
                nos.println("end");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Socket Not Created");
            }
            if(soc!=null)
                soc.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server Socket Not Created");
        }
    }

}
