package utez.edu.mx.compraventaalmacenes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.compraventaalmacenes.model.Operacion;
import utez.edu.mx.compraventaalmacenes.service.OperacionService;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/operaciones")
@CrossOrigin(origins = "*")
public class OperacionController {

    @Autowired
    private OperacionService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Operacion operacion) {
        try {
            Operacion result = service.registrarOperacion(operacion);
            return ResponseEntity.ok(Map.of(
                    "message", "Operación registrada correctamente",
                    "data", result
            ));
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            Double expectedPrice = null;

            if (msg.contains("precio para")) {
                Pattern pattern = Pattern.compile("es (\\d+(\\.\\d{1,2})?)");
                Matcher matcher = pattern.matcher(msg);
                if (matcher.find()) {
                    expectedPrice = Double.valueOf(matcher.group(1));
                }
            }

            return ResponseEntity.badRequest().body(Map.of(
                    "message", msg,
                    "expectedPrice", expectedPrice
            ));
        }
    }


    @GetMapping
    public List<Operacion> obtenerTodas() {
        return service.obtenerTodas();
    }
}
