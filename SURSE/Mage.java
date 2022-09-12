import java.util.List;

public class Mage extends Character {
    int greutate_minima;

    public Mage(List<Spell> abilitati, int viata_curenta, int viata_maxima, int mana_curenta,
                int mana_maxima, boolean protectie_fire, boolean protectie_earth,
                String nume_personaj, int ox_cr, int oy_cr, Inventory obiect, int exp_cr,
                int niv_cr, int putere, int dexteritate, String profession) {
        super(abilitati, viata_curenta, viata_maxima, mana_curenta,
                mana_maxima, protectie_fire, true, protectie_earth,
                nume_personaj, ox_cr, oy_cr, obiect, exp_cr,
                niv_cr, putere, Math.max(putere + 100, dexteritate), dexteritate, "Mage");
        this.obiect = obiect;
        greutate_minima = obiect.greutate_max / 5;
    }
    @Override
    void receiveDamage(Spell val) {
        if(val instanceof Ice) {
            return;
        }
        if(val instanceof Fire && !protectie_fire)
            if(new GenerateRandom(80,20).generare() > 35) {
                viata_curenta = Math.max(viata_curenta - (putere + dexteritate) / 30, 0);
                mana_curenta = Math.max(mana_curenta - 5, 0);
                putere = Math.max(putere - 10, 0);
                dexteritate = Math.max(dexteritate - 2, 0);
            }
        if(val instanceof Earth && !protectie_earth) {
            if(new GenerateRandom(90,30).generare() > 45)
                viata_curenta = Math.max(viata_curenta - (putere + dexteritate) / 35, 0);
                mana_curenta = Math.max(mana_curenta - 6, 0);
                putere = Math.max(putere - 2, 0);
                dexteritate = Math.max(dexteritate - 22, 0);
        }
    }
    @Override
    int damageAtacNormal() {
        return Math.max(carisma - 12, 17);
    }
    @Override
    int getDamage() {
        if(new GenerateRandom(100, 15).generare() > 30)
            return Math.max(damageAtacNormal() + carisma - 23, 18);
        return 0;
    }
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Mage{");
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
        sb.append(", greutate_minima=").append(greutate_minima);
        sb.append('}');
        return sb.toString();
    }
}
class TestMage {
    public static void main(String args[]) {
        Inventory inventar = new Inventory(200);
        Potion potiune1 = new HealthPotion(100, 620, 90);
        Potion potiune2 = new HealthPotion(100, 780, 900);
        Potion potiune3 = new HealthPotion(1000, 550, 900);
        inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);
        inventar.adaugarePotiune(potiune3);

        CharacterFactory character1 = new CharacterFactory();

        Character ch = character1.getCharacter(null, 10,15, 6, 10, true,false,true,
                "marius",0,1, inventar,3,123,55,33,158, "Mage");
        System.out.println(ch);
    }
}
