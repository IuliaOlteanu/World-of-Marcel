import java.util.ArrayList;
import java.util.List;

public class Shop implements CellElement {
    List<Potion> potiuni = new ArrayList<>();

    Potion eliminarePotiune(int index) {
        Potion potiune = potiuni.get(0);
        for(int i = 0; i < potiuni.size(); i++) {
            if(i == index) {
                potiune = potiuni.get(index);
                potiuni.remove(potiuni.get(index));
                return potiune;
            }
        }
        return null;
    }
    @Override
    public String toCharacter() {
        return "Lista";
    }
    public Shop() {
        for (int i = 0 ; i < 4; i++) {
            int index = new GenerateRandom(4, 0).generare();
            if (index % 4 == 0) {
                Potion s = new HealthPotion(new GenerateRandom(100, 0).generare(),
                            new GenerateRandom(100, 0).generare(),
                            new GenerateRandom(100,0).generare());
                potiuni.add(s);
                continue;
            }
            if (index % 4 == 1) {
                Potion s = new ManaPotion(new GenerateRandom(100, 0).generare(),
                        new GenerateRandom(100, 0).generare(),
                        new GenerateRandom(100,0).generare());
                potiuni.add(s);
                continue;
            }
            if (index % 4 == 2) {
                Potion s = new HealthPotion(new GenerateRandom(100, 0).generare(),
                        new GenerateRandom(100, 0).generare(),
                        new GenerateRandom(100,0).generare());
                potiuni.add(s);
                continue;
            }
            if (index % 4 == 3) {
                Potion s = new ManaPotion(new GenerateRandom(100, 0).generare(),
                        new GenerateRandom(100, 0).generare(),
                        new GenerateRandom(100,0).generare());
                potiuni.add(s);
            }
        }
    }
}
class TestShop {
    public static void main(String args[]) {
        Shop obj = new Shop();
        //obj.potiuni.add(new HealthPotion(100, 50, 90));
        //obj.potiuni.add(new HealthPotion(100, 60, 80));

       // System.out.println("Potiuni " + obj.potiuni);
       // System.out.println(" Dim lista de potiuni : " + obj.potiuni.size());
        //System.out.println("Potiune eliminata : " + obj.eliminarePotiune(1));
       // System.out.println("Potiuni dupa eliminare " + obj.potiuni);
        //System.out.println(obj.eliminarePotiune(0));
        System.out.println(obj.potiuni);
    }
}
