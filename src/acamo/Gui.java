package acamo;

import javax.swing.*;

import messer.*;



public class Gui extends JFrame {
    private JTabbedPane tp = new JTabbedPane();
    private JTextArea textAreaPositionMessage = new JTextArea(65,70);
    private JTextArea textAreaVelocityMessage = new JTextArea(65,70);
    private JTextArea textAreaIdentificationMessage = new JTextArea(65,70);
    private JTextArea textAreaOtherMessage = new JTextArea(65,70);
    public Gui(){
        super("ADSB-Control-Panel");
        setSize(1200,750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(tp);
        tp.add(new JScrollPane(textAreaPositionMessage), "POSITIONMESSAGE");
        tp.add(new JScrollPane(textAreaVelocityMessage), "VELOCITYMESSAGE");
        tp.add(new JScrollPane(textAreaIdentificationMessage), "ID-MESSAGE");
        tp.add(new JScrollPane(textAreaOtherMessage), "OTHER");
    }

    public void selectMessage(ADSBMessage message){
        if (message instanceof ADSBAirbonePositionMessage){
            textAreaPositionMessage.append(message.toString());
        }
        else if (message instanceof ADSBAirboneVelocityMessage){
            textAreaVelocityMessage.append(message.toString());
        }
        else if (message instanceof ADSBAircraftIdentificationMessage){
            textAreaIdentificationMessage.append(message.toString());
        }
        else {
            textAreaOtherMessage.append(message.toString());
        }


    }
}
