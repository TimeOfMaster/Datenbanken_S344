import java.util.*;

public class Reinigungsmanager{

    private List<Zimmer> freieZimmer, reinigungsauftraege;

    private DatabaseConnector datenbank;

    public Reinigungsmanager(){
        datenbank = new DatabaseConnector("IP", 0, "S344.accdb", "abc","xyz");
        freieZimmer = new List<Zimmer>();
        reinigungsauftraege = new List<Zimmer>();

    }

    public void datenbankAusgeben(){
        datenbank.executeStatement("SELECT * FROM Gast");
        QueryResult tmp = datenbank.getCurrentQueryResult();   
        arrayAusgeben(tmp);
        datenbank.executeStatement("SELECT * FROM Zimmer");
        tmp = datenbank.getCurrentQueryResult();   
        arrayAusgeben(tmp);
    }

    private void arrayAusgeben(QueryResult sqlErgebnis){
        if(sqlErgebnis!=null){
            String[] attribut = sqlErgebnis.getColumnNames();
            String[] datentyp = sqlErgebnis.getColumnTypes();
            for(int j = 0; j<attribut.length; j++){
                System.out.print(attribut[j]+" ("+datentyp[j]+"), ");
            }
            System.out.println();

            for (int i = 0; i < sqlErgebnis.getRowCount(); i++) {
                String aktZeile[] = sqlErgebnis.getData()[i];
                for(int j = 0; j<aktZeile.length; j++){
                    System.out.print(attribut[j]+":"+aktZeile[j]+" , ");
                }
                System.out.println();
            }
            System.out.println();
        }
        else{
            System.out.println("Die SQL-Abfrage hat kein Ergebnis geliefert.");
        }
    }

    /**
     * Beispielmethode zum �ndern der Datens�tze.
     */
    public void abreisedatumHeute(){
        String s = "UPDATE Gast SET bis = CURDATE()-2 WHERE GastID = 1 ";
        datenbank.executeStatement(s);
        datenbank.executeStatement("SELECT * FROM Gast");
        QueryResult tmp = datenbank.getCurrentQueryResult();   
        arrayAusgeben(tmp);
    }
    
    /**
     * F�gt einen neuen Gast in die Tabelle Gast ein.
     * Die GastID wird automatisch gesetzt.
     * @param von gibt an, um wieviele Tage das Anreisedatum vom aktuellen Datum abweicht.
     * @param bis gibt an, um wieviele Tage das Abreisedatum vom aktuellen Datum abweicht.
     */
    public void neuerGast(String pVorname, String pName, char pGeschlecht, int pZimmernummer, int von, int bis){
        String s = "INSERT INTO Gast(von, bis, Vorname, Name, Geschlecht, ZimmerNr) VALUES(CURDATE()+"+von+",CURDATE()+"+bis+",'"+pVorname+"','"+pName+"','"+pGeschlecht+"','"+pZimmernummer+"')";
        datenbank.executeStatement(s);
        datenbank.executeStatement("SELECT * FROM Gast");
        QueryResult tmp = datenbank.getCurrentQueryResult();   
        arrayAusgeben(tmp);
    }    
    
    /**
     * Hilfsmethode für f ii)
     * @return true, wenn das erste als Parameter u?bergebene Datum zeitlich fru?her 
     * oder gleich dem zweiten als Paramter u?bergebenen Datum ist.
     */
    public boolean frueherAls(String datum1, String datum2){
        String tag1 = datum1.substring(0,2);
        String monat1 = datum1.substring(3,5);
        String jahr1 = datum1.substring(6,8);
        GregorianCalendar g1 = new GregorianCalendar(Integer.parseInt(jahr1), Integer.parseInt(monat1)-1, Integer.parseInt(tag1));
        String tag2 = datum2.substring(0,2);
        String monat2 = datum2.substring(3,5);
        String jahr2 = datum2.substring(6,8);
        GregorianCalendar g2 = new GregorianCalendar(Integer.parseInt(jahr2), Integer.parseInt(monat2)-1, Integer.parseInt(tag2));
        return g1.before(g2);
    }    
        
    /**
     * Teilaufgabe f iii)
     */
    public void methode1(){
        reinigungsauftraege.toFirst();
        while(reinigungsauftraege.hasAccess()){
            if(reinigungsauftraege.getContent().getGereinigt()){
                Zimmer z = reinigungsauftraege.getContent();
                String s = "UPDATE Zimmer SET bezugsfertig = true";
                datenbank.executeStatement(s);
            }
            reinigungsauftraege.next();
        }
    }

    public void executeQuery(String query) {
        datenbank.executeStatement(query);
        try {
            QueryResult result = datenbank.getCurrentQueryResult();
            arrayAusgeben(result);
        } catch (Exception e) {}
    }
    
}
