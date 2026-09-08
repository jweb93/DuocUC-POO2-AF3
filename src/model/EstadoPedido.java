package model;

public enum EstadoPedido {
    POR_RESERVAR("Por reservar"),
    EN_PREPARACION("En Preparación"),
    EN_DESPACHO("En despacho"),
    ENTREGADO("Entregado"),
    CANCELADO("Cancelado");

    private final String descripcion;

    EstadoPedido(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }
}
