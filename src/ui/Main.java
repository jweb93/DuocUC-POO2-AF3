package ui;

import interfaces.*;
import model.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal para ejecutar el programa
 */

public class Main {

    public static void main(String[] args) {

        ArrayList<Pedido> pedidos1 = new ArrayList<>();
        ArrayList<Pedido> pedidos2 = new ArrayList<>();
        ArrayList<Pedido> pedidos3 = new ArrayList<>();


        // Creación de pedidos y cálculo de tiempo estimado mediante llamada interna del constructor
        pedidos1.add(new PedidoComida(
                1,
                new Direccion("Avenida Matta", 1042, "Santiago"),
                4
        ));

        pedidos1.add(new PedidoEncomienda(
                2,
                new Direccion("Avenida Central", 987, "Maipú"),
                6
        ));

        pedidos1.add(new PedidoExpress(
                3,
                new Direccion("Avenida Presidente Riesco", 777, "Las Condes"),
                7
        ));

        pedidos2.add(new PedidoComida(
                4,
                new Direccion("Avenida Irarrázaval", 2450, "Ñuñoa"),
                3
        ));

        pedidos2.add(new PedidoEncomienda(
                5,
                new Direccion("Gran Avenida", 5320, "San Miguel"),
                8
        ));

        pedidos2.add(new PedidoExpress(
                6,
                new Direccion("Avenida Providencia", 1850, "Providencia"),
                5
        ));

        pedidos3.add(new PedidoComida(
                7,
                new Direccion("Avenida Pajaritos", 3250, "Maipú"),
                9
        ));

        pedidos3.add(new PedidoEncomienda(
                8,
                new Direccion("Avenida Vicuña Mackenna", 4100, "Macul"),
                10
        ));

        pedidos3.add(new PedidoExpress(
                9,
                new Direccion("Avenida Apoquindo", 4500, "Las Condes"),
                2
        ));


        // Asignación de pedidos a repartidores
        try {
            System.out.println("----------- Buscando Repartidores disponibles 🔎");
            Thread.sleep(3000);
            System.out.println();
            System.out.println("----------- Se han encontrado repartidores. Los pedidos serán reservados 🔒");
            System.out.println();

            for(Pedido pedido: pedidos1){
                Thread.sleep(1000);
                ((Reservable) pedido).reservar();
            }
            for(Pedido pedido: pedidos2){
                Thread.sleep(1000);
                ((Reservable) pedido).reservar();
            }
            for(Pedido pedido: pedidos3){
                Thread.sleep(1000);
                ((Reservable) pedido).reservar();
            }

            System.out.println();
            System.out.println("----------- Asignando pedidos a repartidores 📝");
            Thread.sleep(1000);
            Repartidor repartidor1 = new Repartidor("🤖", "Arnold Schwarzenegger", pedidos1);
            System.out.println("Se asignaron pedidos a: " + repartidor1.getIcon() + " " +  repartidor1.getNombre());

            Thread.sleep(1000);
            Repartidor repartidor2 = new Repartidor("🥊", "Rocky Balboa", pedidos2);
            System.out.println("Se asignaron pedidos a: " + repartidor2.getIcon() + " " +  repartidor2.getNombre());


            Thread.sleep(1000);
            Repartidor repartidor3 = new Repartidor("🥋","Chuck Norris", pedidos3);
            System.out.println("Se asignaron pedidos a: " + repartidor3.getIcon() + " " + repartidor3.getNombre());
            System.out.println();

            Thread.sleep(1000);
            System.out.println("----------- Iniciando despacho concurrente 🚗🛵🚲");
            System.out.println();

            ExecutorService executor = Executors.newFixedThreadPool(3); // Objeto que administrará 3 hilos concurrentes
            executor.execute(repartidor1);
            executor.execute(repartidor2);
            executor.execute(repartidor3);

            executor.shutdown();

            try {
                boolean termino = executor.awaitTermination(1, TimeUnit.MINUTES); // Hace esperar a main a que terminen los hilos
                // de executor. Espera hasta 1 min, luego, retorna true si efectivamente terminaron o false si no.
                // se podría incluso almacenar el resultado para evaluar con if-else que hará main despúes
                // boolean termino = executor.await....

                if (termino){
                    System.out.println("----------- Todos los repartidores han finalizado 🎉🎉🎉🎉🎉");
                    System.out.println();
                    System.out.println("Verifiquemos 1 pedido");
                    System.out.println();
                    pedidos1.get(1).mostrarResumen();
                    System.out.println();
                    ((Rastreable) pedidos1.get(1)).verHistorial();
                }else{
                    System.out.println("----------- No todos los pedidos han sido repartidos aun ;(");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La espera de los repartidores fue interrumpida.");
        }

    }
}
