public class Cell {
    int ox;
    int oy;
    celula tip;
    boolean vizitat;
    CellElement element;

    public Cell(int ox, int oy, celula tip, boolean vizitat, CellElement element) {
        this.ox = ox;
        this.oy = oy;
        this.tip = tip;
        this.vizitat = vizitat;
        this.element = element;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cell{");
        sb.append("ox=").append(ox);
        sb.append(", oy=").append(oy);
        sb.append(", tip=").append(tip);
        sb.append(", vizitat=").append(vizitat);
        sb.append(", element=").append(element);
        sb.append('}');
        return sb.toString();
    }
}
