package Modelo;

public class Ruleta {
    private int saldo;

    public Ruleta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    public void depositar(int monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }

    public boolean descontarApuesta(int monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    public boolean evaluarResultado(int numeroGanador, TipoApuesta apuesta) {
        if (numeroGanador == 0) return false;
        switch (apuesta) {
            case ROJO: return esRojo(numeroGanador);
            case NEGRO: return !esRojo(numeroGanador);
            case PAR: return numeroGanador % 2 == 0;
            case IMPAR: return numeroGanador % 2 != 0;
            default: return false;
        }
    }

    private boolean esRojo(int n) {
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int r : rojos) {
            if (r == n) return true;
        }
        return false;
    }
}