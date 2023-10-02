package com.midas.nuevatienda.controller;

import com.midas.nuevatienda.dto.ClienteDto;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import com.midas.nuevatienda.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "ClienteApi", description = "Api para crear tipo de usuario.")
@Slf4j
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping("/registroUser")
    @Operation(summary = "Método para crear un USER.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public Cliente crearClienteUser(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                                    @RequestParam String password2) throws Exception {
        log.info("Creando USER... " + name + " " + email);
        log.error("Error al crear el USER.");
        return clienteService.crearClienteUser(name, email, password, password2);
    }

    @PostMapping("/registroAdmin")
    @Operation(summary = "Método para crear un ADMIN.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public Cliente crearClienteAdmin(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                                     @RequestParam String password2) throws Exception {
        log.info("Creando ADMIN... " + name + " " + email);
        log.error("Error al crear el ADMIN.");
        return clienteService.crearClienteAdmin(name, email, password, password2);
    }

    @GetMapping("/login")
    @Operation(summary = "Inicio de sesión.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public Cliente login(@RequestParam(required = false) String email, String password){
        if (email!=null){

            log.error("Usuario o contraseña incorrectos: " + email + " " + password);

        }
        return clienteService.loginUsuario(email, password);

    }

    @GetMapping("/listarUsuarios")
    @Operation(summary = "Método para listar todos los usuarios.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public List<Cliente> listarUsuarios(){
        return clienteService.listarUsuarios();
    }

    @GetMapping("/roles{rol}")
    @Operation(summary = "Método para listar según rol.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public List<Cliente> findByRol(@PathVariable("rol") Rol rol){
        return clienteService.findByRol(rol);
    }


}
