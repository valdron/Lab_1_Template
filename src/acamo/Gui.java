package acamo;

import javax.swing.*;

//import com.teamdev.jxbrowser.chromium.Browser;
import messer.*;
//import com.teamdev.jxbrowser.chromium.swing.BrowserView;
//import com.teamdev.jxbrowser.*;

import java.awt.*;

/**
 * Created by hagen on 29.11.2016.
 */
public class Gui extends JFrame {
    private JTabbedPane tp = new JTabbedPane();
    private JTextArea textAreaPositionMessage = new JTextArea(65,70);
    private JTextArea textAreaVelocityMessage = new JTextArea(65,70);
    private JTextArea textAreaIdentificationMessage = new JTextArea(65,70);
    private JTextArea textAreaOtherMessage = new JTextArea(65,70);
  //  private Browser browser = new Browser();
    public Gui(){
        super("ADSB-Control-Panel");
        setSize(1200,750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(tp);
    //    BrowserView view = new BrowserView(browser);
    //    tp.add(view, BorderLayout.CENTER);
        tp.add(new JScrollPane(textAreaPositionMessage), "POSITIONMESSAGE");
        tp.add(new JScrollPane(textAreaVelocityMessage), "VELOCITYMESSAGE");
        tp.add(new JScrollPane(textAreaIdentificationMessage), "ID-MESSAGE");
        tp.add(new JScrollPane(textAreaOtherMessage), "OTHER");
    //    browser.loadURL("https://www.google.de/maps/place/Esslingen+am+Neckar");
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
