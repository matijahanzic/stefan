/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefanpresentationlayer;

/**
 *
 * @author Robert
 */

import java.awt.*;
import javax.swing.*;
import stefan.business.DatabaseTester;

public class MySplashScreen extends JWindow {
  
    protected MySplashScreen() {
    }
    private static MySplashScreen instance = null;
    public static MySplashScreen getInstance(){
        if(instance == null){
            instance = new MySplashScreen();
        }
        return instance;
    }
    
    private void showSplash() {
        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.WHITE);
        
        // Set the window's bounds, centering the window
        int width = 450;
        int height =115;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        // Build the splash screen
        //JLabel label = new JLabel(new ImageIcon("java-tip.gif"));
        JLabel test = new JLabel
                ("Spajanje s bazom podataka", JLabel.CENTER);
        test.setFont(new Font("Arial", Font.BOLD, 18));
        //content.add(label, BorderLayout.CENTER);
        content.add(test, BorderLayout.CENTER);
        //Color oraRed = new Color(156, 20, 20,  255);
        //content.setBorder(BorderFactory.createLineBorder(oraRed, 10));
         content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
        
        // Display it
        setVisible(true);
        try {
            TestDatabase();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pogreška pri spajanju s bazom podataka: " + e.toString(),"Greška",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        setVisible(false);        
    }
    
    public void showSplashScreen() {     
        showSplash();
        return;
    }

    private void TestDatabase() throws Exception {
        DatabaseTester.getInstance().TestDatabase();
    }
}