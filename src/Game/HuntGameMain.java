package Game;

import GUI.MainFrame;

import javax.swing.*;

public class HuntGameMain {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            MainFrame gui = new MainFrame();
        });
    }
}
