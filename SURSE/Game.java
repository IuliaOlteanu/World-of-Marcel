import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    private static Game instance = null;
    List<Account> accounts;
    Map<celula, List<String>> stories;

    private Game() {

    }

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Character run(String mod) throws InterruptedException, IOException {
        CharacterFactory ch = new CharacterFactory();
        try {
            accounts = ObjectReaderWrapper.readAccounts();
            stories = ObjectReaderWrapper.readStories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Inventory inventory = new Inventory(new GenerateRandom(3000,200).generare());
        for (Account value : accounts) {
            for (int j = 0; j < value.personaje.size(); j++)
                value.personaje_cont.add(ch.getCharacter(null, new GenerateRandom(10, 2).generare(),
                        15,
                        new GenerateRandom(30, 2).generare(), 90,
                        new Random().nextBoolean(), new Random().nextBoolean(), new Random().nextBoolean(),
                        value.personaje.get(j).getName(), 0, 0, inventory,
                        value.personaje.get(j).getExperience(), value.personaje.get(j).getLevel(),
                        20, 40, 120,
                        value.personaje.get(j).getProfession()));

        }
        Account account = chooseAccount();
        if(mod.equals("CLI")) {
            PlayableCharacter playableCharacter = chooseCharacter(account);
            Character character = createCharacter(playableCharacter);
            System.out.println(character);
            return character;
        }
        else if(mod.equals("GUI")) {
            Logo frame = new Logo("Start page", accounts.get(index_cont));
        }
        return null;
    }

    Account chooseAccount() throws InterruptedException {
        Scanner in = new Scanner(System.in);
        do {
            int load = 0;
            while (load < 1 ) {
                System.out.println("Loading .");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Loading ..");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Loading ...");
                load ++;
            }
            System.out.println("\nChoose an account: ");
            afisareConturi();
            int accountNumber = in.nextInt();
            index_cont = accountNumber;
            if (accountNumber >= 0 && accountNumber <= accounts.size() - 1) {
                Account account = accounts.get(accountNumber);
                return account;
            } else {
                System.out.println("Please choose a correct account number between 0 and " + (accounts.size() - 1));
            }
        } while (true);
    }

    PlayableCharacter chooseCharacter(Account account) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        do {
            int load = 0;
            while (load < 1 ) {
                System.out.println("Loading .");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Loading ..");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Loading ...");
                load ++;
            }
            System.out.println("\nChoose a character: ");
            afisareCaractere();
            int characterNumber = in.nextInt();
            if (characterNumber >= 0 && characterNumber <= account.personaje_cont.size() - 1) {
                PlayableCharacter playableCharacter = account.personaje.get(characterNumber);
                System.out.println("Selected Character: " + playableCharacter);
                return playableCharacter;
            } else {
                System.out.println("Please choose a correct account number between 0 and " + (account.personaje.size() - 1));
            }
        } while (true);
    }

    Character createCharacter(PlayableCharacter playableCharacter) {
        Inventory inventar = new Inventory(new GenerateRandom(3000,700).generare());
        Potion potiune1 = new HealthPotion(new GenerateRandom(50,10).generare(),
                new GenerateRandom(200,90).generare(), new GenerateRandom(100,50).generare());
        Potion potiune2 = new ManaPotion(new GenerateRandom(50,10).generare(),
                new GenerateRandom(200,90).generare(), new GenerateRandom(100,50).generare());
        inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);

        Ice ice = new Ice(new GenerateRandom(100,30).generare(), new GenerateRandom(100,30).generare());
        Fire fire = new Fire(new GenerateRandom(100,10).generare(), new GenerateRandom(50,30).generare());
        Earth earth = new Earth(new GenerateRandom(100,10).generare(), new GenerateRandom(50,30).generare());
        List<Spell> abilitati = new ArrayList<>();
        abilitati.add(ice);
        abilitati.add(fire);
        abilitati.add(earth);

        return new CharacterFactory().getCharacter(abilitati, new GenerateRandom(50,30).generare(),
                new GenerateRandom(100,60).generare(),
                new GenerateRandom(100,20).generare(),
                new GenerateRandom(100,25).generare(),
                new Random().nextBoolean(), new Random().nextBoolean(), new Random().nextBoolean(),
                playableCharacter.getName(), 0,0, inventar, playableCharacter.getExperience(),
                playableCharacter.getLevel(), new GenerateRandom(100,20).generare() ,
                new GenerateRandom(200,12).generare(),
                new GenerateRandom(100,20).generare(),
                playableCharacter.getProfession());

    }

    public void showStory(Cell cell, int index) throws IOException {
        stories = ObjectReaderWrapper.readStories();

        if(cell.tip.equals(celula.EMPTY)) {
            for(int i = 0; i < stories.get(celula.EMPTY).size(); i++)
                if(i == index)
                    System.out.println(stories.get(celula.EMPTY).get(i));
        }

        if(cell.tip.equals(celula.SHOP)) {
            for(int i = 0; i < stories.get(celula.SHOP).size(); i++)
                if(i == index)
                    System.out.println(stories.get(celula.SHOP).get(i));
        }

        if(cell.tip.equals(celula.ENEMY)) {
            for(int i = 0; i < stories.get(celula.ENEMY).size(); i++)
                if(i == index)
                    System.out.println(stories.get(celula.ENEMY).get(i));
        }

        if(cell.tip.equals(celula.FINISH)) {
            for(int i = 0; i < stories.get(celula.FINISH).size(); i++)
                if(i == index)
                    System.out.println(stories.get(celula.FINISH).get(i));
        }
    }

    void afisareCaractere() {
        for(int i = 0; i < accounts.get(index_cont).personaje_cont.size(); i++) {
            System.out.println("Character " + i + " : " + accounts.get(index_cont).personaje_cont.get(i).nume_personaj + ", " +
                    accounts.get(index_cont).personaje_cont.get(i).profession);
        }
    }

    void afisareConturi() {
        for(int i = 0; i < accounts.size(); i++) {
            System.out.println("Account " + i + " : " + accounts.get(i).jucator.getNume());
        }
    }

    void afisareListaPotiuni(Cell cell, Shop shop, Inventory inventory,
                             Character character) {
        Scanner scanner = new Scanner(System.in);
        if(cell.tip.equals(celula.SHOP)) {
            System.out.println("Would you like to buy a potion ?(Y-yes, N-no)");
            String s = scanner.nextLine();
            if(s.equals("Y")) {
                System.out.println("This shop has the following current potions :");
                System.out.println(shop.potiuni);
                System.out.println("Choose the number of potions you want to buy");
                int nr_potiuni = scanner.nextInt();
                for (int i = 0; i < nr_potiuni; i++) {
                    System.out.println("Choose an index in order to buy a potion");
                    int index_potiune = scanner.nextInt();
                    if(character.cumpararePotiune(shop.potiuni.get(index_potiune)))
                        shop.eliminarePotiune(index_potiune);
                    System.out.println("This shop has the following current potions :");
                    System.out.println(shop.potiuni);
                }
            }
            else
                System.out.println("You didn't want to buy any potion!!!");
        }
    }

    void afisareOptiuniAtac(Cell cell, Character character, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        if(cell.tip.equals(celula.ENEMY)) {
            System.out.println("Show enemy -> life at start of the battle : " + enemy.viata_curenta + " & chackra at the start of the battle : " + enemy.mana_curenta);
            System.out.println("Show character -> life at start of the battle : " + character.viata_curenta + " & chackra at the start of the battle : " + character.mana_curenta);
            while (character.viata_curenta > 0 && enemy.viata_curenta > 0) {
                if (i % 2 == 0) {
                    System.out.println("It's your turn");
                    System.out.println("Methods of attack/regeneration : ");
                    System.out.println(" 0 - receiveDamage");
                    System.out.println(" 1 - useAbility");
                    System.out.println(" 2 - getDamage");
                    System.out.println(" 3 - regenerateLife");
                    System.out.println(" 4 - regenerateChackra");
                    int atac = scanner.nextInt();
                    if (atac == 0) {
                        enemy.receiveDamage(character.abilitati.get(new GenerateRandom(character.abilitati.size() - 1, 0).generare()));
                        System.out.println("Show enemy -> current life : " + enemy.viata_curenta + " & current chackra : " + enemy.mana_curenta);
                    } else if (atac == 1) {
                        character.folosireAbilitate(character.abilitati.get(new GenerateRandom(character.abilitati.size() - 1, 0).generare()), enemy);
                        System.out.println("Show enemy -> current life : " + enemy.viata_curenta + " & current chackra : " + enemy.mana_curenta);
                    } else if (atac == 2) {
                        enemy.abilitati.get(0).val_damage = enemy.getDamage();
                        System.out.println("Show enemy -> damage  : " + enemy.abilitati.get(0).val_damage);
                    } else if (atac == 3) {
                        character.regenerare_viata(new GenerateRandom(70, 35).generare());
                        System.out.println("Show character -> current life after regeneration : " + character.viata_curenta);
                    } else if (atac == 4) {
                        character.regenerare_mana(new GenerateRandom(70, 35).generare());
                        System.out.println("Show character -> current chackra : " + character.mana_curenta);
                    }
                } else if (i % 2 == 1) {
                    int atack = new GenerateRandom(4,0).generare();
                    if (atack == 0) {
                        System.out.println("You've just received damage");
                        character.receiveDamage(enemy.abilitati.get(new GenerateRandom(enemy.abilitati.size() - 1, 0).generare()));
                        System.out.println("Show character -> current life  : " + character.viata_curenta + " & current chackra : " + character.mana_curenta);
                    } else if (atack == 1) {
                        System.out.println("The enemy used an ability against you");
                        enemy.folosireAbilitate(enemy.abilitati.get(new GenerateRandom(enemy.abilitati.size() - 1, 0).generare()), character);
                        System.out.println("Show character -> current life  : " + character.viata_curenta + " & current chackra : " + character.mana_curenta);
                    } else if (atack == 2) {
                        System.out.println("The enemy damaged you ");
                        switch (character.profession) {
                            case "Rogue" -> {
                                character.dexteritate = Math.abs(character.dexteritate - character.getDamage());
                                System.out.println("Dexterity of character : " + character.dexteritate);
                            }
                            case "Warrior" -> {
                                character.putere = Math.abs(character.putere - character.getDamage());
                                System.out.println("Strenght of character : " + character.putere);
                            }
                            case "Mage" -> {
                                character.carisma = Math.abs(character.carisma - character.getDamage());
                                System.out.println("Charisma of character: " + character.carisma);
                            }
                        }
                    } else if (atack == 3) {
                        System.out.println("The enemy regenerated his life");
                        enemy.regenerare_viata(new GenerateRandom(40, 10).generare());
                        System.out.println("Show enemy -> current life after regeneration : " + enemy.viata_curenta);
                    } else if (atack == 4) {
                        System.out.println(" The enemy regenerated his chackra");
                        enemy.regenerare_mana(new GenerateRandom(40, 10).generare());
                        System.out.println("Show enemy -> current chackra : " + enemy.mana_curenta);
                    }
                }
                i++;
            }
        }
    }
    int index_cont;
}
