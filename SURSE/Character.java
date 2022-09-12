import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
@JsonRootName("characters")
public abstract class Character extends Entity {
    @JsonProperty("name")
    String nume_personaj;
    int ox_cr;
    int oy_cr;
    Inventory obiect;
    @JsonProperty("profession")
    String profession;
    @JsonProperty("experience")
    int exp_cr;//experienta_curenta
    @JsonProperty("level")
    int niv_cr;//nivel_curent
    int putere;
    int carisma;
    int dexteritate;

    public Character(List<Spell> abilitati, int viata_curenta, int viata_maxima, int mana_curenta,
                     int mana_maxima, boolean protectie_fire, boolean protecttie_ice, boolean protectie_earth,
                     String nume_personaj, int ox_cr, int oy_cr, Inventory obiect, int exp_cr,
                     int niv_cr, int putere, int carisma, int dexteritate, String profession) {
        super(abilitati, viata_curenta, viata_maxima, mana_curenta, mana_maxima,
                protectie_fire, protecttie_ice, protectie_earth);
        this.nume_personaj = nume_personaj;
        this.ox_cr = ox_cr;
        this.oy_cr = oy_cr;
        this.obiect = obiect;
        this.exp_cr = exp_cr;
        this.niv_cr = niv_cr;
        this.putere = putere;
        this.carisma = carisma;
        this.dexteritate = dexteritate;
        this.profession = profession;
    }

    public Character() {
        super();
    }

    boolean cumpararePotiune(Potion obj) {
        // verific daca am destui bani si spatiu ca sa cumpar potiunea obj
        if(obiect.monede >= obj.preluarePretPotiune() &&
                obiect.calculGreutateRamansa() >= obj.preluareGreutarePotiune()) {
            // adaug noua potiune in inventar si scad numarul de monede din inventar
            obiect.adaugarePotiune(obj);
            obiect.monede = obiect.monede - obj.preluarePretPotiune();
            return true;
        } else {
            System.out.println("The potion couldn't be bought");
        }
        return false;
    }
    int damageAtacNormal() {
        return 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Character{");
        sb.append("nume_personaj='").append(nume_personaj).append('\'');
        sb.append(", ox_cr=").append(ox_cr);
        sb.append(", oy_cr=").append(oy_cr);
        sb.append(", obiect=").append(obiect);
        sb.append(", exp_cr=").append(exp_cr);
        sb.append(", niv_cr=").append(niv_cr);
        sb.append(", putere=").append(putere);
        sb.append(", carisma=").append(carisma);
        sb.append(", dexteritate=").append(dexteritate);
        sb.append(", abilitati=").append(abilitati);
        sb.append(", viata_curenta=").append(viata_curenta);
        sb.append(", viata_maxima=").append(viata_maxima);
        sb.append(", mana_curenta=").append(mana_curenta);
        sb.append(", mana_maxima=").append(mana_maxima);
        sb.append(", protectie_fire=").append(protectie_fire);
        sb.append(", protecttie_ice=").append(protecttie_ice);
        sb.append(", protectie_earth=").append(protectie_earth);
        sb.append(", proffesion=").append(profession);
        sb.append('}');
        return sb.toString();
    }
}
