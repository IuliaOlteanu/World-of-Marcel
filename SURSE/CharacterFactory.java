import java.util.ArrayList;
import java.util.List;

public class CharacterFactory {
    String nume_personaj;
    int ox_cr;
    int oy_cr;
    Inventory obiect;
    int exp_cr;
    int niv_cr;
    int putere;
    int carisma;
    int dexteritate;
    String profesion;
    public Character getCharacter(List<Spell> abilitati, int viata_curenta, int viata_maxima, int mana_curenta,
                                  int mana_maxima, boolean protectie_fire,boolean protecttie_ice, boolean protectie_earth,
                                  String nume_personaj, int ox_cr, int oy_cr, Inventory obiect, int exp_cr,
                                  int niv_cr, int putere, int carisma, int dexteritate, String profesion) {
        return switch (profesion) {
            case "Warrior" -> new Warrior(abilitati, viata_curenta, viata_maxima, mana_curenta,
                    mana_maxima, protecttie_ice, protectie_earth,
                    nume_personaj, ox_cr, oy_cr, obiect, exp_cr,
                    niv_cr, carisma, dexteritate, "Warrior");
            case "Mage" -> new Mage(abilitati, viata_curenta, viata_maxima, mana_curenta,
                    mana_maxima, protectie_fire, protectie_earth,
                    nume_personaj, ox_cr, oy_cr, obiect, exp_cr,
                    niv_cr, putere, dexteritate, "Mage");
            case "Rogue" -> new Rogue(abilitati, viata_curenta, viata_maxima, mana_curenta,
                    mana_maxima, protectie_fire, protecttie_ice,
                    nume_personaj, ox_cr, oy_cr, obiect, exp_cr,
                    niv_cr, putere, carisma, "Rogue");
            default -> null;
        };
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CharacterFactory{");
        sb.append("nume_personaj='").append(nume_personaj).append('\'');
        sb.append(", ox_cr=").append(ox_cr);
        sb.append(", oy_cr=").append(oy_cr);
        sb.append(", obiect=").append(obiect);
        sb.append(", exp_cr=").append(exp_cr);
        sb.append(", niv_cr=").append(niv_cr);
        sb.append(", putere=").append(putere);
        sb.append(", carisma=").append(carisma);
        sb.append(", dexteritate=").append(dexteritate);
        sb.append(", proffesion=").append(profesion);
        sb.append('}');
        return sb.toString();
    }

}
class TestCharacterFactory {
    public static void main(String[] args) {

        Inventory inventar = new Inventory(2000);
        Potion potiune1 = new HealthPotion(10, 100, 90);
        Potion potiune2 = new HealthPotion(100, 580, 900);
        Potion potiune3 = new HealthPotion(1000, 55, 900);
        //inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);
        //inventar.adaugarePotiune(potiune3);
        CharacterFactory character = new CharacterFactory();

        //inventar.monede = 200;
        Ice ice = new Ice(10,5);
        Fire fire = new Fire(20,12);
        List<Spell> abilitati = new ArrayList<>();
        abilitati.add(ice);
        abilitati.add(fire);
        System.out.println(abilitati);

        Character ch1 = character.getCharacter(abilitati,4,  10, 15,5,false,
                true,false,"marius", 2,3,inventar,123,55,33,158, 20, "Rogue");
        System.out.println("Rogue " + ch1);


    }
}