import java.util.List;

public class Warrior extends Character {
    int greutate_maxima;

    public Warrior(List<Spell> abilitati, int viata_curenta, int viata_maxima, int mana_curenta,
                   int mana_maxima, boolean protecttie_ice, boolean protectie_earth,
                   String nume_personaj, int ox_cr, int oy_cr, Inventory obiect, int exp_cr,
                   int niv_cr, int carisma, int dexteritate, String profession) {
        super(abilitati, viata_curenta, viata_maxima, mana_curenta,
        mana_maxima, true, protecttie_ice, protectie_earth,
        nume_personaj, ox_cr, oy_cr, obiect, exp_cr,
        niv_cr, Math.max(carisma, dexteritate + 100), carisma, dexteritate, "Warrior");
        greutate_maxima = obiect.greutate_max;
    }

    @Override
    void receiveDamage(Spell val) {
        if(val instanceof Fire) {
            return;
        }
        if(val instanceof Ice && !protecttie_ice)
            if(new GenerateRandom(50,20).generare() > 25) {
                viata_curenta = Math.max(viata_curenta - (carisma + dexteritate) / 45, 0);
                mana_curenta = Math.max(mana_curenta - 5, 0);
                carisma = Math.max(carisma - 22, 0);
                dexteritate = Math.max(dexteritate - 14, 0);
            }

        if(val instanceof Earth && !protectie_earth)
            if(new GenerateRandom(80,30).generare() > 40) {
                viata_curenta = Math.max(viata_curenta - (carisma + dexteritate) / 50, 0);
                mana_curenta = Math.max(mana_curenta - 5, 0);
                carisma = Math.max(carisma - 24, 0);
                dexteritate = Math.max(dexteritate - 55, 0);
            }
    }

    @Override
    int damageAtacNormal() {
        return Math.max(putere - 30, 15);
    }

    @Override
    int getDamage() {
        if(new GenerateRandom(100, 20).generare() > 50)
            return Math.max(damageAtacNormal() + putere - 30, 40);
        return 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Warrior{");
        sb.append("nume_personaj='").append(nume_personaj).append('\'');
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
        sb.append(", greutate_maxima=").append(greutate_maxima);
        sb.append('}');
        return sb.toString();
    }
}
class TestWarrrior {
    public static void main(String args[]) {
        Inventory inventar = new Inventory(200);
        Potion potiune1 = new HealthPotion(100, 520, 90);
        Potion potiune2 = new HealthPotion(100, 580, 900);
        Potion potiune3 = new HealthPotion(1000, 550, 900);
        inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);
        inventar.adaugarePotiune(potiune3);
        //Warrior warrior = new Warrior(inventar);
        //System.out.println(warrior.greutate_maxima);
       // System.out.println(warrior);

    }
}