package model;
/**
 * Representa un repartidor que recibirá pedido y los despachará en tiempos aleatorios.
 */

import interfaces.Despachable;
import interfaces.Entregable;

import java.util.ArrayList;
import java.util.Random;

public class Repartidor implements Runnable{
    private final Random random = new Random(); //Objeto que permitirá generar numeros aleatorios

    private String icon;
    private String nombre;
    private ArrayList<Pedido> pedidosAsignados;

    public Repartidor() {
        this.nombre = "Anónimo";
        this.pedidosAsignados = new ArrayList<>();
    }

    public Repartidor(String icon, String nombre, ArrayList<Pedido> pedidosAsignados) {
        this.icon = icon;
        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Pedido> getPedidosAsignados() {
        return pedidosAsignados;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public void run(){
        try {
            for(Pedido pedido : pedidosAsignados){
                pedido.asignarRepartidor(this);

                Thread.sleep(3000 + random.nextInt(1000)); // Tiempo que tarda en llegar a retirar el pedido
                System.out.println(icon + " Repartidor " + nombre + " recibió y despachará el pedido N° " + pedido.getIdPedido());
                ((Despachable) pedido).despachar();

                System.out.println();
                Thread.sleep(6000 + random.nextInt(1000)); // Tiempo que tarda en llegar a destino
                ((Entregable) pedido).entregar();
                System.out.println(icon + " Repartidor " + nombre + " entregó pedido N° " + pedido.getIdPedido());
                System.out.println();

            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
