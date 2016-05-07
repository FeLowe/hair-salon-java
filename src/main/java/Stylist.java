import java.util.List;
import org.sql2o.*;

public class Stylist{
  private String name;
  private int id;


  public Stylist (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Stylist> all(){
    String stylistInfoRow = "SELECT id, name FROM stylists";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(stylistInfoRow).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
      this.getId() == newStylist.getId();
    }
  }
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String stylistInfoRow = "INSERT INTO stylists (name) VALUES (:name)";
      this.id = (int) con.createQuery(stylistInfoRow, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }
  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String stylistInfoRow = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(stylistInfoRow)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String clientInfoRow = "SELECT * FROM clients WHERE stylist_id=:id";
      return con.createQuery(clientInfoRow)
      .addParameter("id", this.id)
      .executeAndFetch(Client.class);
    }
  }

}
