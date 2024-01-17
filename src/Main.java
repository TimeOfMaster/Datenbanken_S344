public class Main {
    public static void main(String[] args){
        Reinigungsmanager reinigungsmanager = new Reinigungsmanager();
        reinigungsmanager.executeQuery("SELECT Vorname, Name, ZimmerNr FROM Gast WHERE Geschlecht = 'w'");
        reinigungsmanager.freieZimmer();
    }
}
