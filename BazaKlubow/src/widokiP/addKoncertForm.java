package widokiP;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addKoncertForm extends JPanel {
    private JLabel jcomp1;
    private JComboBox kluby;
    private JLabel jcomp3;
    private JTextField jcomp4;
    private JLabel jcomp5;
    private JTextField jcomp6;
    private JLabel jcomp7;
    private JTextField jcomp8;
    private JButton jcomp9;
    private JButton jcomp10;

    public addKoncertForm() {
        String[] klubyItems = {};

        jcomp1 = new JLabel ("Klub");
        kluby = new JComboBox (klubyItems);
        jcomp3 = new JLabel ("Data");
        jcomp4 = new JTextField (1);
        jcomp5 = new JLabel ("Cena biletu");
        jcomp6 = new JTextField (1);
        jcomp7 = new JLabel ("Godzina otwarcia");
        jcomp8 = new JTextField (1);
        jcomp9 = new JButton ("Wyczyœæ pola");
        jcomp10 = new JButton ("Dodaj koncert");

        //setPreferredSize (new Dimension (500, 250));
        BoxLayout layout = new BoxLayout (this, BoxLayout.Y_AXIS);
        setLayout (layout);

        add (jcomp1);
        add (kluby);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
    }
}
