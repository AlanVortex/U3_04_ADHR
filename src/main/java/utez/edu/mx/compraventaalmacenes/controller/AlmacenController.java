package utez.edu.mx.compraventaalmacenes.controller;

import utez.edu.mx.compraventaalmacenes.model.Almacen;
import utez.edu.mx.compraventaalmacenes.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {

    @Autowired
    private AlmacenService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Almacen almacen) {
        try {
            Almacen result = service.createAlmacen(almacen);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Almacén registrado correctamente",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al registrar almacén: " + e.getMessage()
            ));
        }
    }


    @GetMapping
    public List<Almacen> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Almacen updated) {
        try {
            Almacen almacen = service.getById(id);
            almacen.setPrecioVenta(updated.getPrecioVenta());
            almacen.setTamano(updated.getTamano());
            almacen.setCede(updated.getCede());
            Almacen saved = service.updateAlmacen(almacen);
            return ResponseEntity.ok(Map.of(
                    "message", "Almacén actualizado correctamente",
                    "data", saved
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al actualizar almacén: " + e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(Map.of(
                    "message", "Almacén eliminado correctamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Error al eliminar almacén: " + e.getMessage()
            ));
        }
    }



}
