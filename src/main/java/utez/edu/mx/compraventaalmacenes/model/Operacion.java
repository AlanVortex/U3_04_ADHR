package utez.edu.mx.compraventaalmacenes.model;

import jakarta.persistence.*;
import lombok.*;
import utez.edu.mx.compraventaalmacenes.model.Almacen;
import utez.edu.mx.compraventaalmacenes.model.Cliente;
import utez.edu.mx.compraventaalmacenes.model.TipoOperacion;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    private Almacen almacen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoOperacion tipo;

    @Column(nullable = false)
    private double monto;

    @Column(nullable = false)
    private LocalDate fecha;
}
