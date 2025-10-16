public class Race {
    public static void main(String[] args) {
        Thread[] racers = new Thread[10];

        // Inicia os racers ímpares (1, 3, 5, 7, 9)
        for (int i = 1; i <= 10; i += 2) {
            racers[i - 1] = new Racer(i);
            racers[i - 1].setPriority(Thread.NORM_PRIORITY); // prioridade padrão
            racers[i - 1].start();
        }

        // Espera todos os ímpares terminarem antes de começar os pares
        for (int i = 1; i <= 10; i += 2) {
            try {
                racers[i - 1].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Inicia os racers pares (2, 4, 6, 8, 10)
        for (int i = 2; i <= 10; i += 2) {
            racers[i - 1] = new Racer(i);
            racers[i - 1].setPriority(Thread.NORM_PRIORITY + 1); // prioridade levemente maior
            racers[i - 1].start();
        }
    }
}

public class Racer extends Thread {
    private int id;

    public Racer(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // Cada Racer imprime 1000 vezes e depois termina
        for (int i = 1; i <= 1000; i++) {
            System.out.println("Racer " + id + " – imprimindo " + i);
            try {
                // Pausa breve para permitir alternância de execução
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
