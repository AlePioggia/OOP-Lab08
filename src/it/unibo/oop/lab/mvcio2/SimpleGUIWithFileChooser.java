package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;
import it.unibo.oop.lab.mvcio.SimpleGUI;

/*
     * TODO: Starting from the application in mvcioo:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();
    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUIWithFileChooser(Controller controller) {
        final JPanel panel = new JPanel(new BorderLayout());
        final JTextField txt = new JTextField();
        panel.add(txt);
        final JButton button = new JButton("Save");
        panel.add(button,BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.writeOnCurrentFile(txt.getText());
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        /*1) Add a JTextField and a button "Browse..." on the upper part of the
         * graphical interface.
         * Suggestion: use a second JPanel with a second BorderLayout, put the panel
         * in the North of the main panel, put the text field in the center of the
         * new panel and put the button in the line_end of the new panel.
         */
        final BorderLayout bLayout = new BorderLayout();
        final JPanel bPanel = new JPanel(bLayout);
        final JTextField browseText = new JTextField();
        /*2) The JTextField should be non modifiable. And, should display the
        * current selected file.
        * */
        browseText.setEditable(false);
        bPanel.add(browseText,BorderLayout.CENTER);
        final JButton browseButton = new JButton("browse");
        browseButton.addActionListener(new ActionListener() {
        /*3) On press, the button should open a JFileChooser. The program should
        * use the method showSaveDialog() to display the file chooser, and if the
        * result is equal to JFileChooser.APPROVE_OPTION the program should set as
        * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
        * then the program should do nothing. Otherwise, a message dialog should be
        * shown telling the user that an error has occurred (use
        * JOptionPane.showMessageDialog()).
        */
            public void actionPerformed(ActionEvent e) {
                try {
                    final Controller c = new Controller();
                    final JFileChooser choice = new JFileChooser();
                    if(choice.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        c.setFile(choice.getSelectedFile());
                        /*4) When in the controller a new File is set, also the graphical interface
                        * must reflect such change. Suggestion: do not force the controller to
                        * update the UI: in this example the UI knows when should be updated, so
                        * try to keep things separated.
                        */
                        browseText.setText(choice.getSelectedFile().getPath());
                        //Checks if the change happened printing on console
                        System.out.println(c.getFile());
                    }
                    else if(choice.showSaveDialog(frame) == JFileChooser.CANCEL_OPTION){
                        System.out.println("Nothing happening");
                    }
                    else {
                        JOptionPane.showMessageDialog(frame,JOptionPane.ERROR_MESSAGE);
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        });
        bPanel.add(browseButton,BorderLayout.LINE_END);
        panel.add(bPanel,BorderLayout.NORTH);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         /*
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(String[] s) {
        new SimpleGUIWithFileChooser(new Controller());
     }

}