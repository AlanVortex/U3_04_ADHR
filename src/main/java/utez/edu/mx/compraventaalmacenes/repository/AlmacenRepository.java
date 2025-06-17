package utez.edu.mx.compraventaalmacenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.compraventaalmacenes.model.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
