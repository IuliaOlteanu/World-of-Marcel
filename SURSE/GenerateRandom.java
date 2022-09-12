public class GenerateRandom {
    int limita_superioara;
    int limita_inferioara;

    public GenerateRandom(int limita_superioara, int limita_inferioara) {
        this.limita_superioara = limita_superioara;
        this.limita_inferioara = limita_inferioara;
    }
    // generez un numar intreg intre limita_inferioara si limita_superioara
    // daca numarul este negativ, se returneaza 0
    int generare() {
        return Math.max((int) Math.floor(Math.random() * (limita_superioara -
                limita_inferioara + 1) + limita_inferioara), 0);
    }
}
class TestGenerate {
    public static void main(String args[]) {
        for(int i = 0; i < 10; i++)
            System.out.print(new GenerateRandom(3,1).generare() + " ");

        GenerateRandom obj = new GenerateRandom(10,0);
        System.out.println(obj.generare());
    }
}
