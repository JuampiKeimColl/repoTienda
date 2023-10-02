package com.midas.nuevatienda.service;

import com.midas.nuevatienda.exceptions.MiExceptions;
import com.midas.nuevatienda.mapper.MapperDtoCliente;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import com.midas.nuevatienda.persistence.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MapperDtoCliente mapperDtoCliente;
    @Transactional
    public Cliente crearClienteAdmin(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                                @RequestParam String password2) throws MiExceptions {
        validarCliente(name, email, password, password2);
        Cliente cliente = new Cliente();
        cliente.setClienteName(name);
        cliente.setEmail(email);
        cliente.setPassword(password);
        cliente.setRol(Rol.ADMIN);

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente crearClienteUser(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                                     @RequestParam String password2) throws MiExceptions {
        validarCliente(name, email, password, password2);
        Cliente cliente = new Cliente();
        cliente.setClienteName(name);
        cliente.setEmail(email);
        cliente.setPassword(password);
        cliente.setRol(Rol.USER);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarUsuarios(){
        return clienteRepository.findAll();
    }

    public List<Cliente> findByRol(Rol rol){
        return clienteRepository.findAllByClienteRol(rol);
    }

    public void asignarRol(Rol rol){
        Cliente cliente = new Cliente();
        cliente.setRol(rol);

    }

    public void validarCliente(String name, String email, String password, String password2) throws MiExceptions {
        if(name.isEmpty()){
            throw new MiExceptions("El nombre es obligatorio.", HttpStatus.BAD_REQUEST);
        }
        if(email.isEmpty()){
            throw new MiExceptions("El email es obligatorio.", HttpStatus.BAD_REQUEST);
        }
        if(password.length() < 5){
            throw new MiExceptions("El password es obligatorio y debe tener más de 5 dígitos.", HttpStatus.BAD_REQUEST);
        }
        if(!password.equals(password2)){
            throw new MiExceptions("Los passwords deben ser iguales.", HttpStatus.BAD_REQUEST);
        }
    }

    public Cliente loginUsuario(String email, String password) {
        return clienteRepository.findUsuarioPassword(email, password);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Cliente cliente = clienteRepository.buscarPorEmail(email);
//
//        if(cliente != null){
//            List<GrantedAuthority> permisos = new ArrayList();
//            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ cliente.getRol().toString());
//            permisos.add(p);
//
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attributes.getRequest().getSession(true);
//            session.setAttribute("usersession", cliente);
//
//            return new User(cliente.getEmail(), cliente.getPassword(),permisos);
//        }else{
//            return null;
//        }
//    }
}
