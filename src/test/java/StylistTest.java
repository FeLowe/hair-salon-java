import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

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
  public void Stylist_instantiatesCorrectly_true() {
    Stylist newStylist = new Stylist("Sabrina");
    assertEquals(true, newStylist instanceof Stylist);
  }

  @Test
  public void Stylist_getName_InstantiatesWithName_String() {
    Stylist newStylist = new Stylist("Sabrina");
    assertEquals("Sabrina", newStylist.getName());
  }

  @Test
  public void Stylist_all_emptyAtFirst_List() {
    assertEquals(Stylist.all().size(), 0);
  }
  @Test
  public void Stylist_equals_testIfStylistsAretheSame_true() {
    Stylist firstStylist = new Stylist("Sabrina");
    Stylist secondStylist = new Stylist("Sabrina");
    assertTrue(firstStylist.equals(secondStylist));
  }
  @Test
  public void Stylist_save_testIfStylistIsSavedIntoDatabase_true() {
    Stylist newStylist = new Stylist("Sabrina");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }
  @Test
  public void Stylist_save_assignsIdToStylist_true() {
    Stylist newStylist = new Stylist("Sabrina");
    newStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(newStylist.getId(), savedStylist.getId());
  }
  @Test
  public void Stylist_find_findsStylistInDatabase_true() {
    Stylist newStylist = new Stylist("Sabrina");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getId());
    assertTrue(newStylist.equals(savedStylist));
  }
}
