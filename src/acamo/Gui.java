package acamo;

import javax.swing.*;
import java.awt.*;
import messer.*;

/**
 * Created by hagen on 29.11.2016.
 */
public class Gui extends JFrame {
    private JTabbedPane tp = new JTabbedPane();
    private JTextArea positionMessage = new JTextArea(65,70);
    private JTextArea velocityMessage = new JTextArea(65,70);
    private JTextArea identificationMessage = new JTextArea(65,70);
    private JTextArea otherMessage = new JTextArea(65,70);
    public Gui(){
        super("ADSB-Control-Panel");
        setSize(1200,750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(tp);
        tp.add(new JScrollPane(positionMessage), "POSITIONMESSAGE");
        tp.add(new JScrollPane(velocityMessage), "VELOCITYMESSAGE");
        tp.add(new JScrollPane(identificationMessage), "ID-MESSAGE");
        tp.add(new JScrollPane(otherMessage), "OTHER");
    }

    public void selectMessage(ADSBMessage message){
        if (message instanceof ADSBAirbonePositionMessage){
            positionMessage.append(message.toString());
        }
        else if (message instanceof ADSBAirboneVelocityMessage){
            velocityMessage.append(message.toString());
        }
        else if (message instanceof ADSBAircraftIdentificationMessage){
            identificationMessage.append(message.toString());
        }
        else {
            otherMessage.append(message.toString());
        }


    }
}
