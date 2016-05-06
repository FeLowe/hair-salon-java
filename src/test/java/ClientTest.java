import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStylists = "DELETE FROM stylists *;";
      String deleteClients = "DELETE FROM clients *;";
      con.createQuery(deleteStylists).executeUpdate();
      con.createQuery(deleteClients).executeUpdate();
    }
  }

  @Test
  public void Client_initiatesCorrectly_true() {
    Client newClient = new Client ("Lataevia", 1);
    assertEquals(true, newClient instanceof Client);
  }

  @Test
  public void Client_getName_InstantiatesWithName_String() {
    Client newClient = new Client ("Lataevia", 1);
    assertEquals("Lataevia", newClient.getName());
  }

  @Test
  public void Client_all_emptyAtFirst_List() {
    assertEquals(Client.all().size(), 0);
  }
  @Test
  public void Client_equals_testIfClientsAretheSame_true() {
    Client firstClient = new Client ("Lataevia", 1);
    Client secondClient = new Client ("Lataevia", 1);
    assertTrue(firstClient.equals(secondClient));
  }
  @Test
  public void Client_save_testIfClientIsSavedIntoDatabase_true() {
    Client newClient = new Client ("Lataevia", 1);
    newClient.save();
    assertTrue(Client.all().get(0).equals(newClient));
  }
  @Test
  public void Client_save_assignsIdToClient_true() {
    Client newClient = new Client ("Lataevia", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }
  @Test
  public void Client_find_findsClientInDatabase_true() {
    Client newClient = new Client ("Lataevia", 1);
    newClient.save();
    Client savedClient = Client.find(newClient.getId());
    assertTrue(newClient.equals(savedClient));
  }
  @Test
  public void Client_save_savesStylistIdIntoDB_true() {
    Stylist newStylist = new Stylist("Sabrina");
    newStylist.save();
    Client newClient = new Client("Lataevia", newStylist.getId());
    newClient.save();
    Client savedClient = Client.find(newClient.getId());
    assertEquals(savedClient.getStylistId(), newStylist.getId());
  }
  @Test
  public void Client_update_updatesClientName_true() {
    Client newClient = new Client ("Lataevia", 1);
    newClient.save();
    newClient.update("Nadyia");
    assertEquals("Nadyia", Client.find(newClient.getId()).getName());
  }

  @Test
  public void Clien_delete_deletesClient_true() {
    Client newClient = new Client ("Lataevia", 1);
    newClient.save();;
    int newClientId = newClient.getId();
    newClient.delete();
    assertEquals(null, Client.find(newClientId));
  }

}
