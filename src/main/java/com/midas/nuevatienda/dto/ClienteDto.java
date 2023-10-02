package com.midas.nuevatienda.dto;

import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@Data
public class ClienteDto {
    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private String name;

    @NotEmpty(message = "El nombre del correo es obligatorio")
    private String email;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 1, max = 5, message = "La contraseña debe ser mayor a cero y menor a 5 digitos")
    private String password;

    @NotEmpty(message = "Las contraseñas deben coincidir")
    private String password2;

//    private Rol rol;
//    private List<OrdenCompra> ordenCompra;

}
