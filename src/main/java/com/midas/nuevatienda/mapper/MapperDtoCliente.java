package com.midas.nuevatienda.mapper;

import com.midas.nuevatienda.dto.ClienteDto;
import com.midas.nuevatienda.persistence.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class MapperDtoCliente implements IMapper<ClienteDto, Cliente>{
    @Override
    public Cliente map(ClienteDto in) {
        //        cliente.setClienteName(in.getName());
//        cliente.setEmail(in.getEmail());
//        cliente.setPassword(in.getPassword());
//        cliente.setOrdenCompra(in.getOrdenCompra());
        return new Cliente();
    }
}
