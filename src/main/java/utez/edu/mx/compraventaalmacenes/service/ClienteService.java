package utez.edu.mx.compraventaalmacenes.service;

import utez.edu.mx.compraventaalmacenes.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente createCliente(Cliente cliente);
    List<Cliente> getAllClientes();
    Cliente getClienteById(Long id);
    void deleteCliente(Long id);
}
