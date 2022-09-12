public class Earth extends Spell {
    public Earth(int val_damage, int cost_mana) {
        super(val_damage, cost_mana);
    }

    @Override
    public String toString() {
        String sb = "Earth{" + "val_damage=" + val_damage +
                ", cost_mana=" + cost_mana +
                '}';
        return sb;
    }
}
