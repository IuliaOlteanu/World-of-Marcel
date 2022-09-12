public class Fire extends Spell {
    public Fire(int val_damage, int cost_mana) {
        super(val_damage, cost_mana);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Fire{");
        sb.append("val_damage=").append(val_damage);
        sb.append(", cost_mana=").append(cost_mana);
        sb.append('}');
        return sb.toString();
    }
}
