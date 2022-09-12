public class ManaPotion implements Potion {
    private int pret_potiune;
    private int greutate_potiune;
    private int val_regenerare;

    public ManaPotion() {
        this(0, 0, 0);
    }

    public ManaPotion(int pret_potiune, int greutate_potiune,
                      int val_regenerare) {
        this.pret_potiune = pret_potiune;
        this.greutate_potiune = greutate_potiune;
        this.val_regenerare = val_regenerare;
    }

    @Override
    public void regenerare() {

    }

    @Override
    public int preluarePretPotiune() {
        return pret_potiune;
    }

    @Override
    public int preluareValRegenerare() {
        return val_regenerare;
    }

    @Override
    public int preluareGreutarePotiune() {
        return greutate_potiune;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ManaPotion{");
        sb.append("pret_potiune=").append(pret_potiune);
        sb.append(", greutate_potiune=").append(greutate_potiune);
        sb.append(", val_regenerare=").append(val_regenerare);
        sb.append('}');
        return sb.toString();
    }
}
