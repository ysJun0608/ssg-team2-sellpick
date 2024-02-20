package config.printEndImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ImageDisplay {

    // 이미지를 출력하는 메소드
    // 10초간 이미지를 출력하고 종료합니다.
    public void printImage() {
        SwingUtilities.invokeLater(() -> {
            // Replace "path/to/your/image.gif" with the actual path or URL to your GIF file
            URL imagePath = getClass().getResource("/config/printEndImage/ThankYou.gif");
            if (imagePath == null) {
                throw new RuntimeException("Image file not found!");
            }
            ImageIcon icon = new ImageIcon(imagePath);

            // Create a JFrame to display the image
            JFrame frame = new JFrame("이상 봐주셔서 감사합니다.");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JLabel to hold the image
            JLabel label = new JLabel(icon);

            // Set the content pane of the JFrame
            frame.getContentPane().add(label, BorderLayout.CENTER);

            // Set the size of the JFrame
            frame.setSize(500, 500);

            // Center the JFrame on the screen
            frame.setLocationRelativeTo(null);

            // Create a Timer to automatically close the program after 10 seconds
            Timer timer = new Timer(7200, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            timer.start();

            // Make the JFrame visible
            frame.setVisible(true);
        });
    }
}