import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Logo extends JFrame implements ActionListener {

    JButton button;
    JLabel imageLabel;
    JSplitPane pane;
    Account cont;
    public Logo(String title, Account cont) throws IOException {
        super(title);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        getContentPane().setBackground(Color.pink);
        setLayout(new FlowLayout());
        this.cont = cont;

        imageLabel = new JLabel(new ImageIcon("D:\\Facultate\\ANUL 2\\SEM1-Iulia\\Programare Orientata pe Obiecte\\tema\\logo_poza.png"));

        button = new JButton(" Start ");
        button.setBounds(200,210,90,25);
        button.setBackground(Color.yellow);
        button.setForeground(Color.red);

        pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, imageLabel,button);

        add(pane);

        setSize(200,200);

        button.addActionListener(this);
        setVisible(true);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            Autentificare f = new Autentificare("Login");
            f.cont = cont;
            setVisible(false);
        }
    }
}
class TestLogo {
    public static void main(String args[]) {
        try {
            Logo f = new Logo("Pagina jocului", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}