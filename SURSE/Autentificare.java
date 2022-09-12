import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.SortedSet;

public class Autentificare extends JFrame implements ActionListener {
    JLabel labelParola, labelEmail;
    JTextField email;
    JButton button1;
    JButton button2;

    JPanel newPanel;
    JPasswordField parola;
    Account cont;

    public Autentificare(String title) {
        super(title);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        getContentPane().setBackground(Color.pink);
        setLayout(new FlowLayout());

        email = new JTextField(20);
        email.setBounds(100,27,190,20);
        email.setEnabled(true);

        labelEmail = new JLabel("EMAIL");
        labelEmail.setBounds(100,8,70,20);

        labelParola = new JLabel("PASSWORD");
        labelParola.setBounds(100,55,70,20);

        parola = new JPasswordField(20);
        parola.setBounds(100,75,190,30);

        button1 = new JButton("LOGIN");
        button1.setBounds(100,110,90,25);
        button1.setBackground(Color.yellow);
        button1.setForeground(Color.red);

        button2 = new JButton("REMOVE");
        button2.setBounds(100,110,90,25);
        button2.setBackground(Color.yellow);
        button2.setForeground(Color.red);


        newPanel = new JPanel(new GridLayout(4,1));
        newPanel.setBackground(Color.pink);
        newPanel.add(labelEmail);
        newPanel.add(email);

        newPanel.add(labelParola);
        newPanel.add(parola);
        newPanel.add(button1);
        newPanel.add(button2);
        add(newPanel, BorderLayout.CENTER);

        button1.addActionListener(this);
        button2.addActionListener(this);

        setVisible(true);
        pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String Email = email.getText();
        String Parola1 = parola.getText();

        if(e.getSource() == button1) {
            if(Email.equals(cont.jucator.jucator.getEmail()) && Parola1.equals(cont.jucator.jucator.getParola()))
                JOptionPane.showMessageDialog(null, "Login successful");
            else {
                JOptionPane.showMessageDialog(null, "Login unsuccessful");
            }
        }
        if(e.getSource() == button2) {
            email.setText("");
            parola.setText("");
        }
        if(Email.equals(cont.jucator.jucator.getEmail()) && Parola1.equals(cont.jucator.jucator.getParola())) {
            NewPage page = new NewPage();
            page.setVisible(true);
            JLabel l = new JLabel("Welcome " + Email);
            l.setBounds(100,7,70,20);
            JLabel l2 = new JLabel("Info about current player");
            l2.setBounds(100,8,70,20);

            String nume = cont.jucator.nume;
            String tara = cont.jucator.tara;
            SortedSet<String> jocuri_preferate = cont.jucator.jocuri_preferate;

            JLabel numeJuctor = new JLabel("Name: " + nume);
            numeJuctor.setBounds(100,8,70,20);

            JLabel numeTara = new JLabel("Country: " + tara);
            numeTara.setBounds(100,8,70,20);

            JLabel jocuri = new JLabel("Favorite Games: " + jocuri_preferate);


            JPanel nou = new JPanel(new GridLayout(3,1));
            nou.add(numeJuctor, nume);
            nou.add(numeTara, tara);
            nou.add(jocuri, jocuri_preferate);

            page.getContentPane().add(l);
            page.getContentPane().add(l2);
            page.getContentPane().add(nou, BorderLayout.SOUTH);

            if(e.getSource() == button1)
                setVisible(false);

        }
    }
    class NewPage extends JFrame implements ActionListener {
        JButton button2;
        JButton button3;

        public NewPage() {

            setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            setMinimumSize(new Dimension(500, 500));
            setResizable(false);
            getContentPane().setBackground(Color.pink);
            setLayout(new FlowLayout());

            button2 = new JButton("Choose character");
            button2.setBounds(100,110,90,25);
            button2.setBackground(Color.yellow);
            button2.setForeground(Color.red);
            button2.setEnabled(true);

            button3 = new JButton("Start game");
            button3.setBounds(100,110,90,25);
            button3.setBackground(Color.yellow);
            button3.setForeground(Color.red);
            button3.setEnabled(true);

            button2.addActionListener(this);
            button3.addActionListener(this);
            add(button2);
            add(button3);

            setVisible(true);
            pack();

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b1 = (JButton) e.getSource();
            if(b1.getText().equals("Choose character")) {
                Selectie frame = new Selectie(cont.personaje_cont);
                frame.setVisible(true);

                setVisible(true);
                pack();

            }else if (b1.getText().equals("Start game")){
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f, "You didn't select a character");
                setVisible(true);
            }
            if(b1.getText().equals("Choose character"))
                setVisible(false);
        }
    }
    class Selectie extends JFrame implements ActionListener {
        int i;
        JPanel panel1;
        JButton b;
        Selectie(ArrayList<Character> characters) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setMinimumSize(new Dimension(300, 200));
            setLayout(new FlowLayout());
            getContentPane().setBackground(Color.pink);

            i = characters.size();
            panel1 = new JPanel(new GridLayout(i,1));
            for(int j = 0; j < characters.size(); j++) {
                b = new JButton(characters.get(j).nume_personaj);
                b.setBounds(j + 100,2 * j + 110,90,25);
                b.setBackground(Color.yellow);
                b.setForeground(Color.red);
                b.setEnabled(true);
                b.addActionListener(this);
                panel1.add(b);
            }
            add(panel1);
            setVisible(true);
            pack();

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if(button != null) {
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f, "The game is about to start");
            }
        }
    }
}
