import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TestareJoc {
    public static void main(String[] args) throws IOException, InvalidCommandException,
            InformationIncompleteException, InterruptedException {
        System.out.println("Before starting the game, you need to know its rules");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Your current position will be given by the character P on the map");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("At the start of the game, you will be placed on the upper north corner");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("This game will challenge you a lot.");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("If you're lucky enough, you will discover several shops if you get on a cell with character S.");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Shops give you the opportunity to buy essential potions.");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Using potions you increase the chances of winning and defeating the enemy");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Another part of this game involves fighting against enemies.");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("By arriving on a cell with character E, the fight between you and the enemy will start.");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Hopefully, you will use all the abilities and potions you have in order to defeat the enemy");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Last but not least, the finish line is given by stepping on a cell with F");
        TimeUnit.SECONDS.sleep(8);
        Game game = Game.getInstance();
        Scanner mod = new Scanner(System.in);
        System.out.println("Choose the way to start the game : CLI/GUI");
        String mode = mod.nextLine();

        Character ch = null;
        try {
            ch = game.run(mode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(mode.equals("CLI")) {
            Grid obj = Grid.getInstance();
            Grid harta = obj.generare2();

            harta.jucator = ch;
            harta.jucator.obiect.monede = 2000;
            harta.afisareHarta();
            Scanner scanner = new Scanner(System.in);
            while (!harta.celula_curenta.tip.equals(celula.FINISH)) {
                String move = scanner.nextLine();
                if(move.equals("P")) {
                    System.out.println("Options: ");
                    System.out.println("Go north : N");
                    System.out.println("Go south : S");
                    System.out.println("Go east : E");
                    System.out.println("Go west : V");
                    System.out.println("Stop : stop");
                    String optiune = scanner.nextLine();
                    try {
                        switch (optiune) {
                            case "N" -> harta.goNorth();
                            case "S" -> harta.goSouth();
                            case "E" -> harta.goEast();
                            case "V" -> harta.goWest();
                            case "stop" -> {
                                return;
                            }
                            default -> throw new InvalidCommandException("Error");
                        }
                    } catch (InvalidCommandException exception) {
                        exception.printStackTrace();
                    }
                    if (!harta.celula_curenta.vizitat) {
                        int random = new GenerateRandom(game.stories.size(), 0).generare();
                        game.showStory(harta.celula_curenta, random);
                    }
                    harta.celula_curenta.vizitat = true;
                    if (harta.celula_curenta.tip.equals(celula.EMPTY) ||
                            harta.celula_curenta.tip.equals(celula.FINISH))
                        harta.afisareHarta();
                    if (harta.celula_curenta.tip.equals(celula.SHOP)) {
                        System.out.println("Coins before buying a potion " + harta.jucator.obiect.monede);
                        System.out.println("Current weight of inventory " + harta.jucator.obiect.greutate_cr);
                        System.out.println("Maximum weight of inventory " + harta.jucator.obiect.greutate_max);
                        System.out.println("My potions before buying a new one " + harta.jucator.obiect.potiuni);
                        game.afisareListaPotiuni(harta.celula_curenta, new Shop(), harta.jucator.obiect, harta.jucator);
                        System.out.println("My potions after buying " + harta.jucator.obiect.potiuni);
                    }
                    if (harta.celula_curenta.tip.equals(celula.ENEMY)) {
                        Enemy enemy = new Enemy();
                        game.afisareOptiuniAtac(harta.celula_curenta, harta.jucator, enemy);
                        if (enemy.viata_curenta == 0) {
                            if (new GenerateRandom(100, 0).generare() > 20)
                                harta.jucator.obiect.monede += 1200;
                        }
                    }
                    if (harta.jucator.viata_curenta == 0) {
                        System.out.println("You lost the game!! Try again!!");
                        return;
                    }
                    if (harta.celula_curenta.tip.equals(celula.FINISH) && harta.jucator.viata_curenta > 0) {
                        System.out.println("You won !!! Congratulations!!");
                        System.out.println("You defeated the challenges, therefore you pass to a new level ");
                        harta.jucator.niv_cr++;
                        harta.jucator.exp_cr++;
                        harta.jucator.carisma += 20;
                        harta.jucator.putere += 25;
                        harta.jucator.dexteritate += 45;
                        int nr = game.accounts.get(game.index_cont).nr_jocuri_jucate + 1;
                        System.out.println("After winning this game you have current level " + harta.jucator.niv_cr + " ," +
                                " current experience " + harta.jucator.exp_cr + ", charisma " + harta.jucator.carisma + ", strength "
                                + harta.jucator.putere + ", dexterity " + harta.jucator.dexteritate + ", maps completed " +
                                nr);
                        PaginaFinala frame = new PaginaFinala("Pagina finala", harta.jucator);
                    }
                }
            }
        }else if(mode.equals("GUI")) {
            System.out.println("The game will start in GUI");
        }
    }
}

