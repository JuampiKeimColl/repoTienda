package com.midas.nuevatienda.service;

import com.midas.nuevatienda.exceptions.MiExceptions;
import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.persistence.repository.CarritoComprasRepository;
import com.midas.nuevatienda.persistence.repository.ClienteRepository;
import com.midas.nuevatienda.persistence.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraService {
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @Autowired
    CarritoComprasRepository carritoComprasRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public OrdenCompra crearOrden(Long carritoId, String address) throws MiExceptions {
        Optional<CarritoCompras> rtaCarrito = carritoComprasRepository.findById(carritoId);
        if (rtaCarrito.isEmpty()){
            throw new MiExceptions("El Carrito de Compras no existe o fue dado de baja.", HttpStatus.NOT_FOUND);
        }
        OrdenCompra ordenCompra = new OrdenCompra();
        CarritoCompras carrito = rtaCarrito.get();
        ordenCompra.setCarritoCompras(carrito);
        ordenCompra.setAddress(address);

        Optional<Cliente> rtaCliente = clienteRepository.findById(Long.valueOf(carrito.getCliente().getClienteId()));
        if (rtaCliente.isEmpty()){
            throw new MiExceptions("El Carrito de Compras no existe o fue dado de baja.", HttpStatus.NOT_FOUND);
        }

        Cliente cliente = rtaCliente.get();
        ordenCompra.setCliente(cliente);

        return ordenCompraRepository.save(ordenCompra);
    }

    public List<OrdenCompra> listarPedidos() {
        return ordenCompraRepository.findAll();
    }
}
