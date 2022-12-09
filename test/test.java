import javax.swing.*;
public class test {

    private static final JFrame frame = new JFrame();
    private static final JButton one = new JButton("one");


    public static void main(String[] args) {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(750, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        one.setBounds(0, 0, 250, 250);

        frame.add(one);

        frame.setVisible(true);
    }
}