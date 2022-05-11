public class KlokkeTraad implements Runnable{
    Modell modell;

    public KlokkeTraad(Modell modell) {
        this.modell = modell;
    }

    public void run() {
        try {
            while(!Kontroller.erFerdig) {
                modell.flyttRetning();
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
