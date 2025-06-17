package utez.edu.mx.compraventaalmacenes.service.impl;

import utez.edu.mx.compraventaalmacenes.model.Almacen;
import utez.edu.mx.compraventaalmacenes.model.Cede;
import utez.edu.mx.compraventaalmacenes.repository.AlmacenRepository;
import utez.edu.mx.compraventaalmacenes.repository.CedeRepository;
import utez.edu.mx.compraventaalmacenes.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlmacenServiceImpl implements AlmacenService {

    @Autowired
    private AlmacenRepository repository;

    @Autowired
    private CedeRepository cedeRepository;

    @Override
    public Almacen createAlmacen(Almacen almacen) {
        almacen.setFechaRegistro(LocalDate.now());

        // Obtener la cede completa desde la BD
        Cede cede = cedeRepository.findById(almacen.getCede().getId())
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));

        almacen.setCede(cede);

        String clave = cede.getClave() + "-A" + System.currentTimeMillis();
        almacen.setClave(clave);

        return repository.save(almacen);
    }

    @Override
    public List<Almacen> getAll() {
        return repository.findAll();
    }

    @Override
    public Almacen getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
    }

    @Override
    public Almacen updateAlmacen(Almacen almacen) {
        // Solo actualizar precio, tamaño y cede
        Cede cede = cedeRepository.findById(almacen.getCede().getId())
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
        almacen.setCede(cede); // actualiza la relación si cambió
        return repository.save(almacen); // no se toca clave ni fecha
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
