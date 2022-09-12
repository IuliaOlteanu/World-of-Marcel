import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Potion> potiuni = new ArrayList<>();
    int greutate_max; //greutate maxima inventar
    int monede;
    int greutate_cr; //greutate curenta inventar
    public Inventory(int greutate_max) {
        this.greutate_max = greutate_max;
        monede = 0;
        greutate_cr = 0;
    }
    void adaugarePotiune(Potion obj) {
        if(!potiuni.contains(obj)) {
            //System.out.println("obj nu exista inca");
            potiuni.add(obj);
            greutate_cr = greutate_cr + obj.preluareGreutarePotiune();
        }
    }

    void eliminarePotiune(Potion obj) {
        if(potiuni.contains(obj)) {
            System.out.println("obj eliminat");
            potiuni.remove(obj);
            greutate_cr = greutate_cr - obj.preluareGreutarePotiune();
        }
    }

    int calculGreutateRamansa() {
        return greutate_max - greutate_cr;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Inventory{");
        sb.append("potiuni=").append(potiuni);
        sb.append(", greutate_max=").append(greutate_max);
        sb.append(", monede=").append(monede);
        sb.append(", greutate_cr=").append(greutate_cr);
        sb.append('}');
        return sb.toString();
    }
}
class TestInventar {
    public static void main(String args[]) {
        Inventory inventar = new Inventory(200);
        Potion potiune1 = new HealthPotion(100, 50, 90);
        Potion potiune2 = new HealthPotion(100, 50, 900);
        Potion potiune3 = new HealthPotion(1000, 50, 900);
        inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);
        //System.out.println(inventar);
        //inventar.eliminarePotiune(potiune1);
        //inventar.eliminarePotiune(potiune3);
        //System.out.println(inventar);
        //System.out.println(inventar.calculGreutateRamansa());
        Potion potiune4 = new ManaPotion(20,10,504);
        inventar.adaugarePotiune(potiune4);
        System.out.println(inventar);
        System.out.println(inventar.calculGreutateRamansa());
        Ice abilitate1 = new Ice(100, 200);
        Fire abilitate2 = new Fire(20,88);
        List<Spell> spells = new ArrayList<>();
        spells.add(abilitate1);
        spells.add(abilitate2);
        System.out.println(spells);

    }
}
