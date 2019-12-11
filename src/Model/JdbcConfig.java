package Model;

public class JdbcConfig {
    String driver;
    String url;
    String use;
    String password;

    @Override
    public String toString() {
        return "JdbcConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", use='" + use + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
