package utez.edu.mx.compraventaalmacenes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String clave; // Formato: [clave de la cede]-A[id]

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false)
    private double precioVenta;

    @Column(nullable = false)
    private String tamano; // G, M, P

    @ManyToOne(optional = false)
    @JoinColumn(name = "cede_id", nullable = false)
    private Cede cede;
}
