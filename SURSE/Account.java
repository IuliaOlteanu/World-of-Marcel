import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.*;

public class Account {
    @JsonUnwrapped
    Information jucator;
    ArrayList<Character> personaje_cont = new ArrayList<>(); //characters
    @JsonProperty("characters")
    ArrayList<PlayableCharacter> personaje = new ArrayList<>();
    @JsonProperty("maps_completed")
    int nr_jocuri_jucate; //mapsCompleted

    public Account() {

    }
    public Account(Information jucator, ArrayList<PlayableCharacter> personaje,
                   int nr_jocuri_jucate) {
        this.jucator = jucator;
        this.personaje = personaje;
        this.nr_jocuri_jucate = nr_jocuri_jucate;
    }

    public ArrayList<PlayableCharacter> getPersonaje() {
        return personaje;
    }

    public void setPersonaje(ArrayList<PlayableCharacter> personaje) {
        this.personaje = personaje;
    }

    public Information getJucator() {
        return jucator;
    }

    public void setJucator(Information jucator) {
        this.jucator = jucator;
    }

    public void afisare() {
        System.out.print("Afisare personaje asociate contului:");
        for (Character character : personaje_cont) {
            System.out.print(character + ",");
        }
    }

    public void setPersonaje_cont(ArrayList<Character> personaje_cont) {
        this.personaje_cont = personaje_cont;
    }

    public int getNr_jocuri_jucate() {
        return nr_jocuri_jucate;
    }

    public void setNr_jocuri_jucate(int nr_jocuri_jucate) {
        this.nr_jocuri_jucate = nr_jocuri_jucate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Account{");
        sb.append("jucator=").append(jucator);
        sb.append(", personaje_cont=").append(personaje_cont);
        sb.append(", personaje=").append(personaje);
        sb.append(", nr_jocuri_jucate=").append(nr_jocuri_jucate);
        sb.append("}\n");
        return sb.toString();
    }

    @JsonDeserialize(builder = Information.InformationBuilder.class)

        static class Information {
        @JsonProperty("credentials")
        Credentials jucator;
        @JsonProperty("favorite_games")
        SortedSet<String> jocuri_preferate;
        @JsonProperty("name")
        String nume;
        @JsonProperty("country")
        String tara;

        public Information(InformationBuilder informationBuilder) {
            this.jucator = informationBuilder.jucator;
            this.jocuri_preferate = informationBuilder.jocuri_preferate;
            this.nume = informationBuilder.nume;
            this.tara = informationBuilder.tara;
        }

        public Credentials getJucator() {
            return jucator;
        }

        public void setJucator(Credentials jucator) {
            this.jucator = jucator;
        }

        public SortedSet<String> getJocuri_preferate() {
            return jocuri_preferate;
        }

        public void setJocuri_preferate(SortedSet<String> jocuri_preferate) {
            this.jocuri_preferate = jocuri_preferate;
        }

        public String getNume() {
            return nume;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public String getTara() {
            return tara;
        }

        public void setTara(String tara) {
            this.tara = tara;
        }
        public void add(String joc) {
            if(!jocuri_preferate.contains(joc))
                jocuri_preferate.add(joc);
        }
        public void remove(String joc) {
            jocuri_preferate.remove(joc);
        }
        @JsonPOJOBuilder(withPrefix = "")
        public static class InformationBuilder {
            @JsonProperty("credentials")
            Credentials jucator;
            @JsonProperty("favorite_games")
            SortedSet<String> jocuri_preferate;
            @JsonProperty("name")
            String nume;
            @JsonProperty("country")
            String tara;
            public InformationBuilder() {
                this.jocuri_preferate = new TreeSet<>();
            }
            public InformationBuilder jucator(Credentials jucator) {
                this.jucator = jucator;
                return this;
            }
            public InformationBuilder jocuri_preferate(SortedSet<String> jocuri_preferate) {
                this.jocuri_preferate = jocuri_preferate;
                return this;
            }
            public InformationBuilder nume(String nume) {
                this.nume = nume;
                return this;
            }
            public InformationBuilder tara(String tara) {
                this.tara = tara;
                return this;
            }
            public Information build() throws InformationIncompleteException {
                if(nume == null || jucator == null)
                    throw new InformationIncompleteException("date incomplete");
                return new Information(this);
            }
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Information{");
            sb.append("jucator=").append(jucator);
            sb.append(", jocuri_preferate=").append(jocuri_preferate);
            sb.append(", nume='").append(nume).append('\'');
            sb.append(", tara='").append(tara).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
class Test {
    public static void main(String args[]) throws InformationIncompleteException {
        Inventory inventar = new Inventory(2000);
        Potion potiune1 = new HealthPotion(10, 100, 90);
        Potion potiune2 = new HealthPotion(100, 580, 900);
        Potion potiune3 = new HealthPotion(1000, 55, 900);
        inventar.adaugarePotiune(potiune1);
        inventar.adaugarePotiune(potiune2);
        inventar.adaugarePotiune(potiune3);

        inventar.monede = 200;
        Ice ice = new Ice(10,5);
        Fire fire = new Fire(20,12);
        List<Spell> abilitati = new ArrayList<>();
        abilitati.add(ice);
        abilitati.add(fire);
        Credentials c = new Credentials();
        c.setEmail("ion@gmail.com");
        c.setParola("abf");

        Account.Information inf1 =  new Account.Information.InformationBuilder().nume("Ion").tara("Romania").jucator(c).build();
        inf1.jocuri_preferate.add("cs");
        inf1.jocuri_preferate.add("cs");
        inf1.jocuri_preferate.add("lool");
        inf1.jocuri_preferate.remove("cs");
        System.out.println(inf1);

    }
}
