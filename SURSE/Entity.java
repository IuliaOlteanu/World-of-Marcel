import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements Element {
    List<Spell> abilitati = new ArrayList<>();
    int viata_curenta;
    int viata_maxima;
    int mana_curenta;
    int mana_maxima;
    boolean protectie_fire;
    boolean protecttie_ice;
    boolean protectie_earth;

    public Entity(List<Spell> abilitati, int viata_curenta, int viata_maxima,
                  int mana_curenta, int mana_maxima, boolean protectie_fire,
                  boolean protecttie_ice, boolean protectie_earth) {
        this.abilitati = abilitati;
        this.viata_curenta = viata_curenta;
        this.viata_maxima = viata_maxima;
        this.mana_curenta = mana_curenta;
        this.mana_maxima = mana_maxima;
        this.protectie_fire = protectie_fire;
        this.protecttie_ice = protecttie_ice;
        this.protectie_earth = protectie_earth;
    }

    public Entity() {

    }

    void regenerare_viata(int val) {
        viata_curenta = Math.min(val + viata_curenta, viata_maxima);
    }
    void regenerare_mana(int val) {
        mana_curenta = Math.min(mana_curenta + val, mana_maxima);
    }

    void folosireAbilitate(Spell val, Entity inamic) {
        if(val.cost_mana <= inamic.mana_curenta + 100) {
            val.visit(inamic);
            mana_curenta = Math.max(mana_curenta - val.cost_mana + 13, 0);
        }
    }
    abstract void receiveDamage(Spell val);

    @Override
    public  void accept(Visitor visitor){
        visitor.visit(this);
    }
    abstract int getDamage();
}
