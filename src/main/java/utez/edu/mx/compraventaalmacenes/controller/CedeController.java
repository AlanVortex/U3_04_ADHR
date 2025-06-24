package utez.edu.mx.compraventaalmacenes.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.compraventaalmacenes.model.Cede;
import utez.edu.mx.compraventaalmacenes.service.CedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cedes")
@CrossOrigin(origins = "*")
@Validated
public class CedeController {

    @Autowired
    private CedeService service;

//    @PostMapping
//    public ResponseEntity<?> create(@Valid @RequestBody Cede cede, BindingResult result) {
//        if (result.hasErrors()) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "message", "Error de validación",
//                    "errors", result.getFieldErrors().stream().map(error -> Map.of(
//                            "field", error.getField(),
//                            "error", error.getDefaultMessage()
//                    ))
//            ));
//        }
//
//        try {
//            Cede resultCede = service.createCede(cede);
//            return ResponseEntity.ok(Map.of(
//                    "message", "Cede registrada correctamente",
//                    "data", resultCede
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "message", "Error al registrar cede: " + e.getMessage()
//            ));
//        }
//    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cede cede, BindingResult result) {
        String regex = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,50}$";

        // Validación manual de formato
        if (cede.getEstado() == null || !cede.getEstado().matches(regex)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "El estado debe contener solo letras y tener entre 2 y 50 caracteres"
            ));
        }

        if (cede.getMunicipio() == null || !cede.getMunicipio().matches(regex)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "El municipio debe contener solo letras y tener entre 2 y 50 caracteres"
            ));
        }

        try {
            // 🔒 Sanitización contra <script> u otros intentos
            cede.setEstado(cede.getEstado().replaceAll("[<>]", ""));
            cede.setMunicipio(cede.getMunicipio().replaceAll("[<>]", ""));

            Cede resultCede = service.createCede(cede);
            return ResponseEntity.ok(Map.of(
                    "message", "Cede registrada correctamente",
                    "data", resultCede
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al registrar cede: " + e.getMessage()
            ));
        }
    }

    @GetMapping
    public List<Cede> getAll() {
        return service.getAllCedes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cede> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCedeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cede updated, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error de validación",
                    "details", errorMsg
            ));
        }

        try {
            Cede cede = service.getCedeById(id);
            cede.setEstado(updated.getEstado());
            cede.setMunicipio(updated.getMunicipio());
            Cede saved = service.updateCede(cede);
            return ResponseEntity.ok(Map.of(
                    "message", "Cede actualizada correctamente",
                    "data", saved
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al actualizar cede: " + e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteCede(id);
            return ResponseEntity.ok(Map.of(
                    "message", "Cede eliminada correctamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al eliminar cede: " + e.getMessage()
            ));
        }
    }
}
