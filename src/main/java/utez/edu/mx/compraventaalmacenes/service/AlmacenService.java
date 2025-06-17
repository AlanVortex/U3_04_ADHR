package utez.edu.mx.compraventaalmacenes.service;

import utez.edu.mx.compraventaalmacenes.model.Almacen;

import java.util.List;

public interface AlmacenService {
    Almacen createAlmacen(Almacen almacen);
    Almacen updateAlmacen(Almacen almacen);
    List<Almacen> getAll();
    Almacen getById(Long id);
    void delete(Long id);
}
