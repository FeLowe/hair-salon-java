
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int stylist_id;


  public Client(String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public String getName(){
    return name;
  }

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public static List<Client> all() {
    String clientInfoRow = "SELECT id, name, stylist_id FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(clientInfoRow).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getId() == newClient.getId() &&
             this.getStylistId() == newClient.getStylistId();

    }
  }

  public void save() {
     try(Connection con = DB.sql2o.open()) {
       String clientInfoRow = "INSERT INTO clients(name, stylist_id) VALUES (:name, :stylist_id)";
       this.id = (int) con.createQuery(clientInfoRow, true)
         .addParameter("name", this.name)
         .addParameter("stylist_id", this.stylist_id)
         .executeUpdate()
         .getKey();
     }
}

public static Client find(int id) {
   try(Connection con = DB.sql2o.open()) {
     String clientInfoRow = "SELECT * FROM clients where id=:id";
     Client client = con.createQuery(clientInfoRow)
       .addParameter("id", id)
       .executeAndFetchFirst(Client.class);
     return client;
   }
 }

 public void update(String name) {
  try(Connection con = DB.sql2o.open()) {
    String clientInfoRow = "UPDATE clients SET name = :name WHERE id = :id";
    con.createQuery(clientInfoRow)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
  }
}
}
