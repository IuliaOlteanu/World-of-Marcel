import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("credentials")
public class Credentials {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String parola;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Credentials : " + "email = '" + email + '\'' +
                ", parola = '" + parola + '\'' +
                "";
    }
}
