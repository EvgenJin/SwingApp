/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evg.swing_app;

import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new UserList();
        frame.setTitle("Swing App");
//        frame.setSize(600, 300);
        // location on desctop def center
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
