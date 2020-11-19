package Kasiski;

abstract public class KasiskiTest {
    protected GcdsTable gcdsTable;
    protected String text;
    protected int lgrammsLength;

    public KasiskiTest(String text, int lgrammsLength) {
        this.text = text;
        this.lgrammsLength = lgrammsLength;
        gcdsTable = new GcdsTable();
    }

    public abstract int test();
}