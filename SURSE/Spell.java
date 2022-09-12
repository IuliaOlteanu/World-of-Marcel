public abstract class Spell implements Visitor {
    int val_damage;
    int cost_mana;

    public Spell (int val_damage, int cost_mana) {
        this.val_damage = val_damage;
        this.cost_mana = cost_mana;
    }
    public void visit(Entity obj) {
        obj.receiveDamage(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Spell{");
        sb.append("val_damage=").append(val_damage);
        sb.append(", cost_mana=").append(cost_mana);
        sb.append('}');
        return sb.toString();
    }

}
