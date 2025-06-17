package utez.edu.mx.compraventaalmacenes.controller;

import utez.edu.mx.compraventaalmacenes.model.Cliente;
import utez.edu.mx.compraventaalmacenes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        try {
            Cliente saved = service.createCliente(cliente);
            return ResponseEntity.ok(Map.of("message", "Cliente registrado correctamente", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error al registrar cliente: " + e.getMessage()));
        }
    }


    @GetMapping
    public List<Cliente> getAll() {
        return service.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClienteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente updated) {
        try {
            Cliente cliente = service.getClienteById(id);
            cliente.setNombreCompleto(updated.getNombreCompleto());
            cliente.setTelefono(updated.getTelefono());
            cliente.setCorreo(updated.getCorreo());
            Cliente saved = service.createCliente(cliente);
            return ResponseEntity.ok(Map.of("message", "Cliente actualizado correctamente", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error al actualizar cliente: " + e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteCliente(id);
            return ResponseEntity.ok(Map.of("message", "Cliente eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error al eliminar cliente: " + e.getMessage()));
        }
    }


}
