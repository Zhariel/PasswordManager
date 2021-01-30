
public class Site {
    private String name;
    private String password;
    private String idUserInSite;
    public Constraint constraint;
    public Metadata metaData;

    public Site(String name, String password, String idUserInSite, Constraint constraint, Metadata metaData){
        this.name = name;
        this.password = password;
        this.idUserInSite = idUserInSite;
        this.constraint = constraint;
        this.metaData = metaData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdUserInSite() {
        return idUserInSite;
    }

    public void setIdUserInSite(String idUserInSite) {
        this.idUserInSite = idUserInSite;
    }

    @Override
    public String toString() {
        return "Site{" +
                "site='" + name + '\'' +
                ", password='" + password + '\'' +
                ", idUserInSite='" + idUserInSite + '\'' +
                ", constraint=" + constraint.toString() +
                ", metaData=" + metaData.toString() +
                '}';
    }
}
