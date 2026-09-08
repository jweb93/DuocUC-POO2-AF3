package model;

import interfaces.*;

/**
 * Representa un pedido de Express de algún comercio
 */

public class PedidoExpress extends Pedido implements Despachable, Cancelable, Rastreable, Entregable, Reservable {

    // Constructor
    public PedidoExpress(int idPedido, Direccion direccionEntrega, double distanciaKM) {
        super(idPedido, direccionEntrega, distanciaKM);
    }

    // Implementación del método abstracto
    @Override
    public int calcularTiempoEntrega(){
        if (getDistanciaKM() > 5){
            return 15;
        }
        else{
            return 10;
        }
    }

    // Sobreescritura
    @Override
    public void mostrarResumen(){
        System.out.println("Pedido Express N°: " + getIdPedido());
        System.out.println("Destino: " + getDireccionEntrega());
        System.out.println("Distancia (km): " + getDistanciaKM());
        System.out.println("Tiempo (min): " + calcularTiempoEntrega());
    }

    @Override
    public void asignarRepartidor(){
        if(getEstado() == EstadoPedido.EN_PREPARACION){
            //System.out.println("Asignando un/a repartidor/a para entregar su pedido express N° " + getIdPedido());
            agregaHistorial("Se asignó un/a repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }

    }

    @Override
    public void asignarRepartidor(Repartidor repartidor){
        if(getEstado() == EstadoPedido.EN_PREPARACION){
            //System.out.println("Asignando a " + repartidor.getNombre() + " para entregar su pedido express N° " + getIdPedido());
            agregaHistorial("Se asignó a " + repartidor.getNombre() + " como repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }
    }

    // Implementación de interfaces
    @Override
    public void reservar() {
        if(getEstado() == EstadoPedido.POR_RESERVAR){
            System.out.println("Se ha reservado el pedido express N°: " + getIdPedido());
            setEstado(EstadoPedido.EN_PREPARACION);
        }else{
            System.out.println("No fue posible reservar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void cancelar() {
        if(getEstado() == EstadoPedido.POR_RESERVAR){
            System.out.println("Se ha cancelado el pedido express N°: " + getIdPedido());
            setEstado(EstadoPedido.CANCELADO);
        }else{
            System.out.println("No fue posible cancelar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void despachar() {
        if(getEstado() == EstadoPedido.EN_PREPARACION){
            //System.out.println("Se ha despachado el pedido express N°: " + getIdPedido());
            setEstado(EstadoPedido.EN_DESPACHO);
        }else{
            System.out.println("No fue posible despachar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void entregar() {
        if(getEstado() == EstadoPedido.EN_DESPACHO){
            //System.out.println("Se ha entregado el pedido express N°: " + getIdPedido());
            setEstado(EstadoPedido.ENTREGADO);
        }else{
            System.out.println("No fue posible entregar el pedido express N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }
    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido express N°: " + getIdPedido());
        int i = 1;
        for(String evento: getHistorial()){
            System.out.println(i + ". " + evento);
            i += 1;
        }
    }
}
