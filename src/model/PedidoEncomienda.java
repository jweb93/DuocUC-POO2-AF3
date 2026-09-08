package model;

import interfaces.*;

/**
 * Representa un pedido de Encomienda que puede ser fragil o no
 */

public class PedidoEncomienda extends Pedido implements Despachable, Cancelable, Rastreable, Entregable, Reservable {

    // Constructor
    public PedidoEncomienda(int idPedido, Direccion direccionEntrega, double distanciaKM) {
        super(idPedido, direccionEntrega, distanciaKM);
    }

    // Implementación del método abstracto
    @Override
    public int calcularTiempoEntrega(){
        return (int) Math.round(20 + (1.5 * getDistanciaKM()));
    }

    // Sobreescritura
    @Override
    public void mostrarResumen(){
        System.out.println("Pedido Encomienda N°: " + getIdPedido());
        System.out.println("Destino: " + getDireccionEntrega());
        System.out.println("Distancia (km): " + getDistanciaKM());
        System.out.println("Tiempo (min): " + calcularTiempoEntrega());
    }

    @Override
    public void asignarRepartidor(){
        if(getEstado() == EstadoPedido.EN_PREPARACION){
            //System.out.println("Asignando un/a repartidor/a para entregar su encomienda N° " + getIdPedido());
            agregaHistorial("Se asignó un/a repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }

    }

    @Override
    public void asignarRepartidor(Repartidor repartidor){
        if(getEstado() == EstadoPedido.EN_PREPARACION){
            //System.out.println("Asignando a " + repartidor.getNombre() + " para entregar su encomienda N° " + getIdPedido());
            agregaHistorial("Se asignó a " + repartidor.getNombre() + " como repartidor/a");
        }else{
            System.out.println("Sólo se puede asignar un/a repartidor/a cuando el pedido está En preparación");
        }
    }

    // Implementación de interfaces

    @Override
    public void reservar() {
        if(getEstado() == EstadoPedido.POR_RESERVAR){
            System.out.println("Se ha reservado la encomienda N°: " + getIdPedido());
            setEstado(EstadoPedido.EN_PREPARACION);
        }else{
            System.out.println("No fue posible reservar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void cancelar() {
        if(getEstado() == EstadoPedido.POR_RESERVAR){
            System.out.println("Se ha cancelado la encomienda N°: " + getIdPedido());
            setEstado(EstadoPedido.CANCELADO);
        }else{
            System.out.println("No fue posible cancelar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void despachar() {
        if(getEstado() == EstadoPedido.EN_PREPARACION){
            //System.out.println("Se ha despachado la encomienda N°: " + getIdPedido());
            setEstado(EstadoPedido.EN_DESPACHO);
        }else{
            System.out.println("No fue posible despachar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }

    @Override
    public void entregar() {
        if(getEstado() == EstadoPedido.EN_DESPACHO){
            //System.out.println("Se ha entregado la encomienda N°: " + getIdPedido());
            setEstado(EstadoPedido.ENTREGADO);
        }else{
            System.out.println("No fue posible entregar la encomienda N° " + getIdPedido() + " ya que se encuentra " + getEstado());
        }
    }
    @Override
    public void verHistorial() {
        System.out.println("Historial de la encomienda N°: " + getIdPedido());
        int i = 1;
        for(String evento: getHistorial()){
            System.out.println(i + ". " + evento);
            i += 1;
        }
    }
}
