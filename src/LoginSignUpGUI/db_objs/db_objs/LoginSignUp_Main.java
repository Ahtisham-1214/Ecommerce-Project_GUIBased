package LoginSignUpGUI.db_objs.db_objs;


import guis.LoginGui;

import javax.swing.*;

public class LoginSignUp_Main {
    public static void main(String[] args) {
        // use invokeLater to make updates to the GUI more thread-safe
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new LoginGui().setVisible(true);
            }
        });
    }
}
