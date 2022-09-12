public class Story {
    private celula type;
    private String value;
    public Story() {

    }
    public Story(celula type, String value) {
        this.type = type;
        this.value = value;
    }

    public celula getType() {
        return type;
    }

    public void setType(celula type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Story{");
        sb.append("type=").append(type);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
