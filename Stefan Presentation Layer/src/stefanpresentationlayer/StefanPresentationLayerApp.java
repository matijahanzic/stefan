/*
 * StefanPresentationLayerApp.java
 */

package stefanpresentationlayer;


import java.awt.Frame;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class StefanPresentationLayerApp extends SingleFrameApplication {

    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        
        StefanPresentationLayerView stefan=new StefanPresentationLayerView(this);
        show(stefan);
        stefan.getFrame().setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    /**
     * 
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
        
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of StefanPresentationLayerApp
     */
    public static StefanPresentationLayerApp getApplication() {
        return Application.getInstance(StefanPresentationLayerApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        MySplashScreen.getInstance().showSplashScreen();
        launch(StefanPresentationLayerApp.class, args);
    }
}
