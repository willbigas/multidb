package br.com.willbigas.multidb.service;

import br.com.willbigas.multidb.dao.UsuarioDAO;
import br.com.willbigas.multidb.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {


    private final UsuarioDAO userDao;

    public UsuarioService(UsuarioDAO userDao) {
        this.userDao = userDao;
    }

    public List<Usuario> getAllUser() {
        return userDao.getAllUser();
    }
}
