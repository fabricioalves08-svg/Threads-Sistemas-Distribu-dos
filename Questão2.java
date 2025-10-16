public class Principal {
    public static void main(String[] args) {
        Deposito dep = new Deposito();

        Produtor p = new Produtor(dep, 50);
        Consumidor c1 = new Consumidor(dep, 80);
        Consumidor c2 = new Consumidor(dep, 120);

        p.start();
        c1.start();
        c2.start();

        System.out.println("Execução no main da classe Principal terminada!");
    }
}

public class Deposito {
    private int itens = 0;
    private final int capacidade = 100;

    public synchronized boolean retirar() {
        if (itens > 0) {
            itens--;
            return true;
        }
        return false;
    }

    public synchronized boolean armazenar() {
        if (itens < capacidade) {
            itens++;
            return true;
        }
        return false;
    }

    public int getItens() {
        return itens;
    }
}

public class Produtor extends Thread {
    private Deposito dep;
    private int tempo;

    public Produtor(Deposito d, int t) {
        dep = d;
        tempo = t;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (dep.armazenar()) {
                    System.out.println("Produtor armazenou uma caixa. Total: " + dep.getItens());
                } else {
                    System.out.println("Depósito cheio. Produtor aguardando...");
                }
                Thread.sleep(tempo);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Consumidor extends Thread {
    private Deposito dep;
    private int tempo;

    public Consumidor(Deposito d, int t) {
        dep = d;
        tempo = t;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (dep.retirar()) {
                    System.out.println("Consumidor retirou uma caixa. Total: " + dep.getItens());
                } else {
                    System.out.println("Depósito vazio. Consumidor aguardando...");
                }
                Thread.sleep(tempo);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

