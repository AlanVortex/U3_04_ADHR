package utez.edu.mx.compraventaalmacenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.compraventaalmacenes.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
