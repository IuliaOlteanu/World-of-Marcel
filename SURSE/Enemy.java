import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity implements CellElement {

    public Enemy(List<Spell> abilitati, int viata_curenta,
                 int mana_curenta, boolean protectie_fire,
                 boolean protecttie_ice, boolean protectie_earth) {
        super(abilitati, viata_curenta, new GenerateRandom(10,0).generare(),
                mana_curenta,
                new GenerateRandom(50, 0).generare(),
                protectie_fire, protecttie_ice, protectie_earth);
    }
    public Enemy() {
        abilitati = new ArrayList<>();
        for (int i = 0 ; i < 3; i++) {
            if (new GenerateRandom(3, 0).generare() % 3 == 0) {
                Spell s = new Ice(new GenerateRandom(100, 0).generare(), new GenerateRandom(100, 0).generare());
                abilitati.add(s);
                continue;
            }
            else if (new GenerateRandom(3, 0).generare() % 3 == 1) {
                Spell s = new Fire(new GenerateRandom(100, 0).generare(), new GenerateRandom(100, 0).generare());
                abilitati.add(s);
                continue;
            }
            if (new GenerateRandom(3, 0).generare() % 3 == 2) {
                Spell s = new Earth(new GenerateRandom(100, 0).generare(), new GenerateRandom(100, 0).generare());
                abilitati.add(s);
            }
        }
        protectie_earth = new Random().nextBoolean();
        protecttie_ice = new Random().nextBoolean();
        protectie_fire = new Random().nextBoolean();
        viata_curenta = new GenerateRandom(10,5).generare();
        viata_maxima = new GenerateRandom(16,10).generare();
        mana_curenta = new GenerateRandom(50, 6).generare();
        mana_maxima = Math.max(new GenerateRandom(100,12).generare(), mana_curenta);
    }

    @Override
    public String toCharacter() {
        return "Caracter inamic";
    }

    @Override
    void receiveDamage(Spell val) {
        GenerateRandom obj = new GenerateRandom(100,0);
        if(obj.generare() >= 51) {
            System.out.println("Damge was avoided");
            return;
        }
        if(val instanceof Ice && !protecttie_ice)
            viata_curenta = Math.max(viata_curenta / 2, 0);
        else if(val instanceof Fire && !protectie_fire)
            viata_curenta = Math.max(viata_curenta - 2, 0);
        else if(val instanceof Earth && !protectie_earth)
            viata_curenta = Math.max(viata_curenta - 3, 0);
        else if(val instanceof Ice && protecttie_ice)
            viata_curenta = Math.max(viata_curenta - 2, 0);
        else if(val instanceof Fire && protectie_fire)
            viata_curenta = Math.max(viata_curenta - 2, 0);
        else if(val instanceof Earth && protectie_earth)
            viata_curenta = Math.max(viata_curenta - 4, 0);
    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    int getDamage() {
        GenerateRandom obj = new GenerateRandom(100,0);
        if(obj.generare() >= 51)
            return 2 * abilitati.get(0).val_damage;
        else
            return abilitati.get(0).val_damage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Enemy{");
        sb.append("abilitati=").append(abilitati);
        sb.append(", viata_curenta=").append(viata_curenta);
        sb.append(", viata_maxima=").append(viata_maxima);
        sb.append(", mana_curenta=").append(mana_curenta);
        sb.append(", mana_maxima=").append(mana_maxima);
        sb.append(", protectie_fire=").append(protectie_fire);
        sb.append(", protecttie_ice=").append(protecttie_ice);
        sb.append(", protectie_earth=").append(protectie_earth);
        sb.append('}');
        return sb.toString();
    }
}
class TestEnemy {
    public static void main(String args[]) {
        Enemy obj = new Enemy();
        System.out.println(obj);
    }
}