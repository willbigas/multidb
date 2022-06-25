package br.com.willbigas.multidb.controller;

import br.com.willbigas.multidb.model.Usuario;
import br.com.willbigas.multidb.dao.UsuarioDAO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioDAO usuarioDao;

    @GetMapping()
    public List<Usuario> findAllFromDatabase1() {
     return usuarioDao.getAllUser();
    }
}
