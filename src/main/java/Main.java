import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args){
        DataFile d = new DataFile("romain");
        SiteManager sm = new SiteManager();

        ZoneId zoneId = ZoneId.of( "Europe/Paris" );
        LocalDate today = LocalDate.now( zoneId );

        Constraint c = new Constraint(18, 1, 1, 1, 1, 1);
        Metadata m = new Metadata(today, 6, "pizza");
        Site s = new Site("twitch", "Pan0Axhq*a", "rom1", c, m);

        System.out.println(s.metaData.getDateCreation().toString());

        UserManager usm = new UserManager();
        Password thevertpassword = new Password("THHKTkTe");
        User tvm = new User("thévertman", thevertpassword, "thévert@gmail.com", usm.listSites(d));

        usm.deleteUser(tvm);

        //sm.addSite(d, s);
        //sm.deleteSite(d, s);
    }

    public void test(User romain){
        int i = 0;
        for(Site site : romain.ListeSites){
            System.out.println("");

            System.out.println(romain.ListeSites.get(i).getSiteName());
            System.out.println(romain.ListeSites.get(i).getPassword());
            System.out.println(romain.ListeSites.get(i).getIdUserInSite());

            System.out.println(romain.ListeSites.get(i).constraint.getTotalLength());
            System.out.println(romain.ListeSites.get(i).constraint.getUpperCase());
            System.out.println(romain.ListeSites.get(i).constraint.getLowerCase());
            System.out.println(romain.ListeSites.get(i).constraint.getSpecialChars());
            System.out.println(romain.ListeSites.get(i).constraint.getLetters());
            System.out.println(romain.ListeSites.get(i).constraint.getDigits());

            System.out.println(romain.ListeSites.get(i).metaData.getDateCreation().toString());
            System.out.println(romain.ListeSites.get(i).metaData.getDuration());
            System.out.println(romain.ListeSites.get(i).metaData.getComment());

            i++;
        }
    }
}
