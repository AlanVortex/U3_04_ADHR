package utez.edu.mx.compraventaalmacenes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String clave; // C[id]-[ddMMyyyy]-[aleatorio]

    @Column(nullable = false)
    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El estado solo puede contener letras y espacios")
    private String estado;

    @Column(nullable = false)
    @NotBlank(message = "El municipio no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El municipio solo puede contener letras y espacios")
    private String municipio;
}
