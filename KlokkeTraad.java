public class KlokkeTraad implements Runnable{
    Modell modell;

    public KlokkeTraad(Modell modell) {
        this.modell = modell;
    }

    public void run() {
        try {
            while(!Kontroller.erFerdig) {
                modell.flyttRetning();
                Thread.sleep(8000);
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
