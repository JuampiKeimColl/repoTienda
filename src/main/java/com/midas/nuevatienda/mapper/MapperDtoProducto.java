package com.midas.nuevatienda.mapper;

import com.midas.nuevatienda.dto.ProductoDto;
import com.midas.nuevatienda.persistence.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class MapperDtoProducto implements IMapper<ProductoDto, Producto> {
    @Override
    public Producto map(ProductoDto in) {
        return null;
    }
}
