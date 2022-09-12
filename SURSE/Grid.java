import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {
    static Grid instance = null;
    int lungime_tabla;
    int latime_tabla;
    Character jucator;

    Cell celula_curenta;

    private Grid(int lungime_tabla, int latime_tabla,
                Character jucator, Cell celula_curenta) {
        this.lungime_tabla = lungime_tabla;
        this.latime_tabla = latime_tabla;
        this.jucator = jucator;
        this.celula_curenta = celula_curenta;
    }

    private Grid() {

    }
    public static Grid getInstance() {
        if(instance == null) {
            instance = new Grid();
        }
        return instance;
    }
    // metoda de generare harta, cu inamici si magazine pozitionate aleator
    static Grid generare(int lungime_tabla, int latime_tabla,
                         int nrInamici, int nrMagzine) {
        Grid harta = new Grid();
        harta.latime_tabla = latime_tabla;
        harta.lungime_tabla = lungime_tabla;
        for (int i = 0; i < lungime_tabla; i++) {
            ArrayList<Cell> linie = new ArrayList<>();
            for (int j = 0; j < latime_tabla; j++) {
                linie.add(new Cell(i, j, celula.EMPTY, false, null));
            }
            harta.add(linie);
        }

        harta.get(0).get(0).tip = celula.START;
        harta.get(0).get(0).vizitat = true;
        harta.celula_curenta = harta.get(0).get(0);

        harta.get(lungime_tabla - 1).get(latime_tabla - 1).tip = celula.FINISH;

        harta.celula_curenta.ox = 0;
        harta.celula_curenta.oy = 0;

        for(int i = 0; i < nrInamici; i++) {
            int lungime = new GenerateRandom(lungime_tabla - 1, 0).generare();
            int latime = new GenerateRandom(latime_tabla - 1, 0).generare();
            Cell cell = new Cell(lungime, latime, celula.ENEMY, false, new Enemy());
            if (harta.get(lungime).get(latime).tip.equals(celula.EMPTY)) {
                harta.get(lungime).remove(latime);
                harta.get(lungime).add(latime, cell);
            }else {
                i--;
            }
        }
        for(int i = 0; i < nrMagzine; i++) {
            int lungime = new GenerateRandom(lungime_tabla - 1, 0).generare();
            int latime = new GenerateRandom(latime_tabla - 1, 0).generare();
            Cell cell = new Cell(lungime, latime, celula.SHOP, false, new Shop());
            if (harta.get(lungime).get(latime).tip.equals(celula.EMPTY)) {
                harta.get(lungime).remove(latime);
                harta.get(lungime).add(latime, cell);
            }else {
                i--;
            }
        }
        return harta;
    }

    //generare harta, respectand modelul din cerinta
    public Grid generare2() {
        Grid obj = new Grid();
        obj.latime_tabla = 5;
        obj.lungime_tabla = 5;
        for (int i = 0; i < 5; i++) {
            ArrayList<Cell> linie = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                linie.add(new Cell(i, j, celula.EMPTY, false, null));
            }
            obj.add(linie);
        }
        obj.get(0).get(0).tip = celula.START;
        obj.get(0).get(0).vizitat = true;
        obj.celula_curenta = obj.get(0).get(0);

        obj.get(0).get(1).tip = celula.EMPTY;
        obj.get(0).get(2).tip = celula.EMPTY;
        obj.get(0).get(3).tip = celula.SHOP;
        obj.get(0).get(4).tip = celula.EMPTY;

        obj.get(1).get(0).tip = celula.EMPTY;
        obj.get(1).get(1).tip = celula.EMPTY;
        obj.get(1).get(2).tip = celula.EMPTY;
        obj.get(1).get(3).tip = celula.SHOP;
        obj.get(1).get(4).tip = celula.EMPTY;


        obj.get(2).get(0).tip = celula.SHOP;
        obj.get(2).get(1).tip = celula.EMPTY;
        obj.get(2).get(2).tip = celula.EMPTY;
        obj.get(2).get(3).tip = celula.EMPTY;
        obj.get(2).get(4).tip = celula.EMPTY;

        obj.get(3).get(0).tip = celula.EMPTY;
        obj.get(3).get(1).tip = celula.EMPTY;
        obj.get(3).get(2).tip = celula.EMPTY;
        obj.get(3).get(3).tip = celula.EMPTY;
        obj.get(3).get(4).tip = celula.ENEMY;

        obj.get(4).get(0).tip = celula.SHOP;
        obj.get(4).get(1).tip = celula.EMPTY;
        obj.get(4).get(2).tip = celula.EMPTY;
        obj.get(4).get(3).tip = celula.EMPTY;
        obj.get(4).get(4).tip = celula.FINISH;

        return obj;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Grid{");
        sb.append("lungime_tabla=").append(lungime_tabla);
        sb.append(", latime_tabla=").append(latime_tabla);
        sb.append(", celula_curenta=").append(celula_curenta);
        sb.append('}');
        return sb.toString();
    }

    // afisare harta
    void afisare() {
        for(int i = 0; i < lungime_tabla; i++) {
            for(int j = 0; j < latime_tabla; j++) {
                if(this.get(i).get(j).tip.equals(celula.START)) {
                    System.out.print(" P ");
                }
                else if(this.get(i).get(j).tip.equals(celula.EMPTY)) {
                    System.out.print(" ? ");
                }
                else if(this.get(i).get(j).tip.equals(celula.ENEMY)) {
                    System.out.print(" E ");
                }
                else if(this.get(i).get(j).tip.equals(celula.SHOP)) {
                    System.out.print(" S ");
                }
                else
                    System.out.print(" F ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //afisare harta "ascunsa"
    void afisareHarta() {
        for (int i = 0; i < lungime_tabla; i++) {
            for (int j = 0; j < latime_tabla; j++) {
                if(jucator.ox_cr == i && jucator.oy_cr == j)
                    System.out.print("P");
                if (!this.get(i).get(j).vizitat) {
                    System.out.print("? ");
                }
                else if(this.get(i).get(j).tip.equals(celula.EMPTY))
                    System.out.print("N ");
                else if(this.get(i).get(j).tip.equals(celula.SHOP))
                    System.out.print("S ");
                else if(this.get(i).get(j).tip.equals(celula.ENEMY))
                    System.out.print("E ");
                else if(this.get(i).get(j).tip.equals(celula.FINISH))
                    System.out.println("F ");
            }
            System.out.println();
        }
    }

    //deplasare in nord
    void goNorth() {
        if(jucator.ox_cr == 0) {
            System.out.println("Jucatorul nu se poate deplasa in nord");
            return;
        }
        this.celula_curenta.tip = celula.EMPTY;
        jucator.ox_cr --;
        celula_curenta = this.get(jucator.ox_cr).get(jucator.oy_cr);
        if(!celula_curenta.vizitat) {
            //celula_curenta.vizitat = true;
            if(new GenerateRandom(100, 0).generare() < 20
                    && celula_curenta.tip.equals(celula.EMPTY))
                jucator.obiect.monede = jucator.obiect.monede + 1000;
        }
    }

    //deplasare in sud
    void goSouth() {
        if(jucator.ox_cr == lungime_tabla - 1) {
            System.out.println("Jucatorul nu se poate deplasa in sud");
            return;
        }
        this.celula_curenta.tip = celula.EMPTY;
        jucator.ox_cr++;
        celula_curenta = this.get(jucator.ox_cr).get(jucator.oy_cr);
        if(!celula_curenta.vizitat) {
            //celula_curenta.vizitat = true;
            if(new GenerateRandom(100, 0).generare() < 20
                && celula_curenta.tip.equals(celula.EMPTY))
                jucator.obiect.monede = jucator.obiect.monede + 1000;
        }
    }

    //deplasare in vest
    void goWest() {
        if(jucator.oy_cr == 0) {
            System.out.println("Jucatorul nu se poate deplasa in vest");
            return;
        }
        this.celula_curenta.tip = celula.EMPTY;
        jucator.oy_cr--;
        celula_curenta = this.get(jucator.ox_cr).get(jucator.oy_cr);
        if(!celula_curenta.vizitat) {
            //celula_curenta.vizitat = true;
            if(new GenerateRandom(100, 0).generare() < 20
                && celula_curenta.tip.equals(celula.EMPTY))
                jucator.obiect.monede = jucator.obiect.monede + 1000;
        }
    }

    //deplasare in est
    void goEast() {
        if(jucator.oy_cr == latime_tabla - 1) {
            System.out.println("Jucatorul nu se poate deplasa in est");
            return;
        }
        this.celula_curenta.tip = celula.EMPTY;
        jucator.oy_cr++;
        celula_curenta = this.get(jucator.ox_cr).get(jucator.oy_cr);
        if(!celula_curenta.vizitat) {
            //celula_curenta.vizitat = true;
            if(new GenerateRandom(100, 0).generare() < 20
                    && celula_curenta.tip.equals(celula.EMPTY))
                jucator.obiect.monede = jucator.obiect.monede + 1000;
        }
    }
}

class TestGrid {
    public static void main(String args[]) {
        Grid obj = Grid.getInstance();
        Grid harta = obj.generare(5,5,2,2);
        harta.afisare();
    }
}