public class KlokkeTraad implements Runnable{
    Modell modell;

    public KlokkeTraad(Modell modell) {
        this.modell = modell;
    }

    public void run() {
        try {
            while(!Kontroller.erFerdig) {
                int tid = (int) Kontroller.hentHastighet();
                modell.flyttRetning();
                Thread.sleep(tid);
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
