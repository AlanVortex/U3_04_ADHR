package utez.edu.mx.compraventaalmacenes.service;

import utez.edu.mx.compraventaalmacenes.model.Operacion;

import java.util.List;

public interface OperacionService {
    Operacion registrarOperacion(Operacion operacion);
    List<Operacion> obtenerTodas();
}
