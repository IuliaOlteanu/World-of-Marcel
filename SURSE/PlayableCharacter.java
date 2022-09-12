public class PlayableCharacter {

    private String name;
    private String profession;
    private int level;
    private int experience;

    public PlayableCharacter() {
    }

    public PlayableCharacter(String name, String profession, int level, int experience) {
        this.name = name;
        this.profession = profession;
        this.level = level;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "PlayableCharacter{" +
                "name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                '}';
    }
}
