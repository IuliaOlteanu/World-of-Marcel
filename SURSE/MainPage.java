import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends JFrame implements ActionListener {
    int lungime;
    int latime;
    JButton nord, sud, est, vest;
    JPanel panel;
    JButton mana, viata, experienta, nivel;
    JTextField Mana, Viata, Exp, niv;
    JPanel panel2;
    JPanel panel3;
    Character character;
    JSplitPane pane, pane2, pane3;

    public MainPage(String title, Grid map) {
        super(title);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(100, 100));
        getContentPane().setBackground(Color.pink);


        lungime = map.lungime_tabla;
        latime = map.latime_tabla;
        setLayout(new GridLayout(lungime, 5));


        nord = new JButton(" NORTH ");
        nord.setBounds(100,110,90,25);
        nord.setBackground(Color.yellow);
        nord.setForeground(Color.red);
        nord.setEnabled(true);

        sud = new JButton(" SOUTH ");
        sud.setBounds(100,110,90,25);
        sud.setBackground(Color.yellow);
        sud.setForeground(Color.red);
        sud.setEnabled(true);

        est = new JButton(" EAST ");
        est.setBounds(100,110,90,25);
        est.setBackground(Color.yellow);
        est.setForeground(Color.red);
        est.setEnabled(true);

        vest = new JButton(" WEST ");
        vest.setBounds(100,110,90,25);
        vest.setBackground(Color.yellow);
        vest.setForeground(Color.red);
        vest.setEnabled(true);

        panel = new JPanel(new GridLayout(4,1));
        panel.setBackground(Color.pink);
        panel.add(nord);
        panel.add(sud);
        panel.add(est);
        panel.add(vest);
        add(panel, BorderLayout.CENTER);

        mana = new JButton(" Chackra ");
        mana.setBounds(100,110,90,25);
        mana.setBackground(Color.yellow);
        mana.setForeground(Color.red);
        mana.setEnabled(true);

        viata = new JButton(" Life ");
        viata.setBounds(100,110,90,25);
        viata.setBackground(Color.yellow);
        viata.setForeground(Color.red);
        viata.setEnabled(true);

        experienta = new JButton(" Experience ");
        experienta.setBounds(100,110,90,25);
        experienta.setBackground(Color.yellow);
        experienta.setForeground(Color.red);
        experienta.setEnabled(true);

        nivel = new JButton(" Level ");
        nivel.setBounds(100,110,90,25);
        nivel.setBackground(Color.yellow);
        nivel.setForeground(Color.red);
        nivel.setEnabled(true);

        panel2 = new JPanel(new GridLayout(4,1));
        panel2.setBackground(Color.pink);
        panel2.add(viata);
        panel2.add(mana);
        panel2.add(experienta);
        panel2.add(nivel);

        Mana = new JTextField(4);
        Mana.setBounds(10,27,19,20);
        Mana.setEnabled(true);

        Viata = new JTextField(4);
        Viata.setBounds(10,27,19,20);
        Viata.setEnabled(true);

        Exp = new JTextField(4);
        Exp.setBounds(10,27,19,20);
        Exp.setEnabled(true);

        niv = new JTextField(4);
        niv.setBounds(10,27,19,20);
        niv.setEnabled(true);

        panel3 = new JPanel(new GridLayout(4,1));
        panel3.setBackground(Color.pink);
        panel3.add(Viata);
        panel3.add(Mana);
        panel3.add(Exp);
        panel3.add(niv);

        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, panel2);
        pane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, panel3);

        mana.addActionListener(this);
        viata.addActionListener(this);
        experienta.addActionListener(this);
        nivel.addActionListener(this);

        Color color1 = Color.BLACK;
        Color color2 = Color.WHITE;

        JPanel tabla = new JPanel(new GridLayout(lungime, latime));
        tabla.setVisible(true);
        Insets buttonMargin = new Insets(0,0,0,0);
        for(int i = 0; i < lungime; i++) {
            for(int j = 0; j < latime; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(lungime * latime,
                        lungime * latime, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((i + j) % 2 == 1) {
                    b.setBackground(color1);
                    tabla.add(b);
                } else {
                    b.setBackground(color2);
                    tabla.add(b);
                }
            }
        }
        pane3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane2, tabla);
        add(pane3);
        setVisible(true);
        setSize(200,200);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if(b.getText().equals(" Chackra ")) {
            Mana.setText(String.valueOf(character.mana_curenta));
            Mana.setBackground(Color.yellow);
            Mana.setForeground(Color.red);
        }
        if(b.getText().equals(" Life ")) {
            Viata.setText(String.valueOf(character.viata_curenta));
            Viata.setBackground(Color.yellow);
            Viata.setForeground(Color.red);
        }
        if(b.getText().equals(" Experience ")) {
            Exp.setText(String.valueOf(character.exp_cr));
            Exp.setBackground(Color.yellow);
            Exp.setForeground(Color.red);
        }
        if(b.getText().equals(" Level ")) {
            niv.setText(String.valueOf(character.niv_cr));
            niv.setBackground(Color.yellow);
            niv.setForeground(Color.red);
        }
    }
}

class TestMain {
    public static void main(String args[]) {
        Grid obj = Grid.getInstance();
        Grid harta = obj.generare2();
        MainPage mainPage = new MainPage("Pagina jocului ", harta);
        Inventory inventar = new Inventory(1000);
        Potion potiune1 = new HealthPotion(10, 100, 90);
        inventar.adaugarePotiune(potiune1);
        CharacterFactory character = new CharacterFactory();

        Ice ice = new Ice(10,5);
        Fire fire = new Fire(20,12);
        List<Spell> abilitati = new ArrayList<>();
        abilitati.add(ice);
        abilitati.add(fire);
        System.out.println(abilitati);

        Character ch1 = character.getCharacter(abilitati,4,  10, 15,5,false,
                true,false,"marius", 2,3,inventar,123,55,33,158, 20, "Rogue");
        System.out.println("Rogue " + ch1);
        mainPage.character = ch1;

    }
}