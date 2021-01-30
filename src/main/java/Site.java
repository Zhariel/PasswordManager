
public class Site {
    private String siteName;
    private String password;
    private String idUserInSite;
    public Constraint constraint;
    public Metadata metaData;

    public Site(String siteName, String password, String idUserInSite, Constraint constraint, Metadata metaData){
        this.siteName = siteName;
        this.password = password;
        this.idUserInSite = idUserInSite;
        this.constraint = constraint;
        this.metaData = metaData;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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
}
