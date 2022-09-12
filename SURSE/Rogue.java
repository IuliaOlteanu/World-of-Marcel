import java.util.ArrayList;
import java.util.List;

public class Rogue extends Character {
    int greutate_medie;
    public Rogue(List<Spell> abilitati, int viata_curenta, int viata_maxima, int mana_curenta,
                 int mana_maxima, boolean protectie_fire, boolean protecttie_ice,
                 String nume_personaj, int ox_cr, int oy_cr, Inventory obiect, int exp_cr,
                 int niv_cr, int putere, int carisma, String profession) {
        super(abilitati, viata_curenta, viata_maxima, mana_curenta,
                mana_maxima, protectie_fire, protecttie_ice, true,
                nume_personaj, ox_cr, oy_cr, obiect, exp_cr,
                niv_cr, putere, carisma, Math.max(putere, carisma + 100), "Rogue");
        this.obiect = obiect;
        greutate_medie = obiect.greutate_max / 2;
    }
    @Override
    void receiveDamage(Spell val) {
        if (val instanceof Earth) {
            return;
        }
        if (val instanceof Ice && !protecttie_ice)
            if (new GenerateRandom(50, 20).generare() > 15) {
                viata_curenta = Math.max(viata_curenta - (putere + carisma) / 25, 0);
                mana_curenta = Math.max(mana_curenta - 5, 0);
                putere = Math.max(putere - 12, 0);
                dexteritate = Math.max(dexteritate - 10, 0);
            }

        if (val instanceof Fire && !protectie_fire)
            if (new GenerateRandom(80, 30).generare() > 10) {
                viata_curenta = Math.max(viata_curenta - (putere + carisma) / 30, 0);
                mana_curenta = Math.max(mana_curenta - 5, 0);
                putere = Math.max(putere - 13, 0);
                dexteritate = Math.max(dexteritate - 20, 0) ;
            }
    }
    @Override
    int damageAtacNormal() {
        return Math.max(dexteritate - 5, 20);
    }
    @Override
    int getDamage() {
        if(new GenerateRandom(100, 10).generare() > 40)
            return Math.max(damageAtacNormal() + dexteritate - 30, 33);
        return 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Rogue{");
        sb.append("nume_personaj='").append(nume_personaj);
        sb.append(", ox_cr=").append(ox_cr);
        sb.append(", oy_cr=").append(oy_cr);
        sb.append(", obiect=").append(obiect);
        sb.append(", exp_cr=").append(exp_cr);
        sb.append(", niv_cr=").append(niv_cr);
        sb.append(", putere=").append(putere);
        sb.append(", carisma=").append(carisma);
        sb.append(", dexteritate=").append(dexteritate);
        sb.append(", abilitati=").append(abilitati);
        sb.append(", viata_curenta=").append(viata_curenta);
        sb.append(", viata_maxima=").append(viata_maxima);
        sb.append(", mana_curenta=").append(mana_curenta);
        sb.append(", mana_maxima=").append(mana_maxima);
        sb.append(", protectie_fire=").append(protectie_fire);
        sb.append(", protecttie_ice=").append(protecttie_ice);
        sb.append(", protectie_earth=").append(protectie_earth);
        sb.append(", greutate_medie=").append(greutate_medie);
        sb.append('}');
        return sb.toString();
    }
}
class TestRogue {
    public static void main(String args[]) {
        Inventory inventar = new Inventory(200);
        Potion potiune1 = new HealthPotion(100, 520, 90);
        Potion potiune2 = new HealthPotion(100, 780, 900);
        Potion potiune3 = new HealthPotion(1000, 550, 900);
        inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);
        inventar.adaugarePotiune(potiune3);
        Rogue rogue = new Rogue(null, 3, 7, 8,12,
                true, false, "RES", 2,5,inventar,5,1,100,200, "Rogue");

        System.out.println(rogue);
        List<Spell> abilitati = new ArrayList<>();
        abilitati.add(new Earth(30,40));
        abilitati.add(new Ice(40,3));
        rogue.receiveDamage(abilitati.get(1));
        System.out.println(rogue);
        rogue.receiveDamage(abilitati.get(0));
        System.out.println(rogue);

    }
}
