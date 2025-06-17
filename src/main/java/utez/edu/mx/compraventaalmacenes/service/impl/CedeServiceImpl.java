package utez.edu.mx.compraventaalmacenes.service.impl;

import utez.edu.mx.compraventaalmacenes.model.Cede;
import utez.edu.mx.compraventaalmacenes.repository.CedeRepository;
import utez.edu.mx.compraventaalmacenes.service.CedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CedeServiceImpl implements CedeService {

    @Autowired
    private CedeRepository repository;

    @Override
    public Cede createCede(Cede cede) {
        String clave = generarClaveCede();
        cede.setClave(clave);
        return repository.save(cede);
    }

    @Override
    public Cede updateCede(Cede cede) {
        return repository.save(cede); // mantiene clave original
    }

    private String generarClaveCede() {
        String fecha = new SimpleDateFormat("ddMMyyyy").format(new Date());
        int aleatorio = new Random().nextInt(9000) + 1000;
        return "C" + System.currentTimeMillis() + "-" + fecha + "-" + aleatorio;
    }

    @Override
    public List<Cede> getAllCedes() {
        return repository.findAll();
    }

    @Override
    public Cede getCedeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
    }

    @Override
    public void deleteCede(Long id) {
        repository.deleteById(id);
    }
}

