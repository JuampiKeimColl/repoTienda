package com.midas.nuevatienda.dto;

import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
public class ClienteDto {
    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private String name;
    @NotEmpty(message = "El nombre del correo es obligatorio")
    private String email;
    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;
    @NotEmpty(message = "Las contraseñas deben coincidir")
    private String password2;
//    private Rol rol;
//    private List<OrdenCompra> ordenCompra;

}
