package utez.edu.mx.compraventaalmacenes.service;

import utez.edu.mx.compraventaalmacenes.model.Cede;

import java.util.List;

public interface CedeService {
    Cede createCede(Cede cede);
    Cede updateCede(Cede cede);
    List<Cede> getAllCedes();
    Cede getCedeById(Long id);
    void deleteCede(Long id);
}

