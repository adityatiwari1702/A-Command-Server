package commandclient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Aditya Tiwari
 */
public class CommandClient {

    static BufferedReader nis;
    static PrintWriter nos;

    public static void main(String[] args) throws Exception {
        String ip = JOptionPane.showInputDialog("Enter IP Address of Server:-");
        int portno = Integer.parseInt(JOptionPane.showInputDialog("Enter Port No of Server:-"));
        Socket soc = new Socket(ip, portno);
        nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        nos = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()), true);
        ClientUI ui=new ClientUI();
        String msg=nis.readLine();
        while(!msg.equalsIgnoreCase("end")){
            ui.appendTA(msg);
            msg=nis.readLine();
        }
        soc.close();
        ui.dispose();
    }

}

class ClientUI extends JFrame {

    JTextArea ta;
    JButton b1;
    JTextField tf;

    public ClientUI() {
        this.setTitle("Client");
        ta = new JTextArea(20, 20);
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        b1 = new JButton("Submit");
        tf = new JTextField(20);
        ta.setEditable(false);
        this.add(BorderLayout.CENTER, ta);
        p.add(tf);
        p.add(b1);
        this.add(BorderLayout.SOUTH, p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        b1.addActionListener((e) -> {
            String msg = tf.getText();
            CommandClient.nos.println(msg);
            tf.setText("");
        });
    }

    public void appendTA(String msg) {
        ta.append(msg + "\n");
    }

}
