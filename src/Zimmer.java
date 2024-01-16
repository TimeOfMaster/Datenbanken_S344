public class Zimmer{
    private int zimmerNr;
    private String freiBis;
    private char geschlecht;
    private boolean gereinigt;

    public Zimmer(int pZimmerNr){
        zimmerNr = pZimmerNr;
    }

    public int getZimmerNr(){
        return zimmerNr;
    }

    public String getFreiBis(){
        return freiBis;
    }

    public void setFreiBis(String pFreiBis){
        freiBis = pFreiBis;
    }
    
    public char getGeschlecht(){
        return geschlecht;
    }

    public void setGeschlecht(char pGeschlecht){
        geschlecht = pGeschlecht;
    }
    
    public boolean getGereinigt(){
        return gereinigt;
    }

    public void setGereinigt(boolean pGereinigt){
        gereinigt = pGereinigt;
    }
    
}