import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PaginaFinala extends JFrame implements ActionListener {
    JLabel labelNume;
    JLabel exp;
    JLabel niv;
    JLabel putere, carisma, dexteritate;

    JButton b1, b2, b3, b4, b5, b6;

    JPanel panel;

    JPanel panel1;

    Character character;

    public PaginaFinala(String title, Character character) {
        super(title);
        this.character = character;
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        getContentPane().setBackground(Color.pink);
        setLayout(new FlowLayout());

        labelNume = new JLabel("Character name");
        labelNume.setBounds(150,8,100,20);

        exp = new JLabel("Experience");
        exp.setBounds(100,8,70,20);

        niv = new JLabel("Level");
        niv.setBounds(100,8,70,20);

        putere = new JLabel("Strength");
        putere.setBounds(100,8,70,20);

        carisma = new JLabel("Charisma");
        carisma.setBounds(100,8,70,20);

        dexteritate = new JLabel("Dexterity");
        dexteritate.setBounds(100,8,70,20);

        panel = new JPanel(new GridLayout(6,1));
        panel.add(labelNume);
        panel.add(exp);
        panel.add(niv);
        panel.add(putere);
        panel.add(carisma);
        panel.add(dexteritate);

        b1 = new JButton("Character name");
        b1.setBounds(100,8,70,20);
        b1.setBackground(Color.white);
        b1.setForeground(Color.blue);
        b1.setEnabled(true);
        b1.addActionListener(this);

        b2 = new JButton("Experience");
        b2.setBounds(100,8,70,20);
        b2.setBackground(Color.white);
        b2.setForeground(Color.blue);
        b2.setEnabled(true);
        b2.addActionListener(this);

        b3 = new JButton("Level");
        b3.setBounds(100,8,70,20);
        b3.setBackground(Color.white);
        b3.setForeground(Color.blue);
        b3.setEnabled(true);
        b3.addActionListener(this);

        b4 = new JButton("Strength");
        b4.setBounds(100,8,70,20);
        b4.setBackground(Color.white);
        b4.setForeground(Color.blue);
        b4.setEnabled(true);
        b4.addActionListener(this);

        b5 = new JButton("Charisma");
        b5.setBounds(100,8,70,20);
        b5.setBackground(Color.white);
        b5.setForeground(Color.blue);
        b5.setEnabled(true);
        b5.addActionListener(this);

        b6 = new JButton("Dexterity");
        b6.setBounds(100,8,70,20);
        b6.setBackground(Color.white);
        b6.setForeground(Color.blue);
        b6.setEnabled(true);
        b6.addActionListener(this);

        panel1 = new JPanel(new GridLayout(6,1));
        panel1.add(b1);
        panel1.add(b2);
        panel1.add(b3);
        panel1.add(b4);
        panel1.add(b5);
        panel1.add(b6);

        add(panel, BorderLayout.SOUTH);
        add(panel1, BorderLayout.NORTH);
        setVisible(true);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if(b.getText().equals("Character name")) {
            labelNume.setText(character.nume_personaj);
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "You played with the character " + character.nume_personaj);
        }
        else if(b.getText().equals("Experience")) {
            exp.setText(Integer.toString(character.exp_cr));
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Current experience of the character " + character.exp_cr);
        }
        else if(b.getText().equals("Level")) {
            niv.setText(Integer.toString(character.niv_cr));
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Current level of the character " + character.niv_cr);
        }
        else if(b.getText().equals("Strength")) {
            putere.setText(Integer.toString(character.putere));
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Current strength of the character " + character.putere);
        }
        else if(b.getText().equals("Charisma")) {
            carisma.setText(Integer.toString(character.carisma));
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Current charisma of the character " + character.carisma);
        }
        else if(b.getText().equals("Dexterity")) {
            dexteritate.setText(Integer.toString(character.dexteritate));
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Current dexterity of the character " + character.dexteritate);
        }

    }
}
class TestFinal {
    public static void main(String args[]) {


        Ice ice = new Ice(10,5);
        Fire fire = new Fire(20,12);
        List<Spell> abilitati = new ArrayList<>();
        abilitati.add(ice);
        abilitati.add(fire);

        Inventory inventar = new Inventory(2000);
        Potion potiune1 = new HealthPotion(10, 100, 90);
        Potion potiune2 = new HealthPotion(100, 580, 900);
        Potion potiune3 = new HealthPotion(1000, 55, 900);
        inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);
        inventar.adaugarePotiune(potiune3);

        CharacterFactory character = new CharacterFactory();
        Character ch1 = new CharacterFactory().getCharacter(abilitati, 5,12,45,70, true,false,
                true,"Chlarimonde Markert",
                0,0 , inventar,1, 4, 15,1,20, "Rogue");
        PaginaFinala frame = new PaginaFinala("Pagina de sfarsit", ch1);

    }
}
