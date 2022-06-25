package br.com.willbigas.multidb.dao;

import br.com.willbigas.multidb.model.Usuario;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

@Transactional
@Repository
public class UsuarioDAO {

    private final JdbcTemplate jdbcDatabase1;

    private final JdbcTemplate jdbcDatabase2;

    public UsuarioDAO(@Qualifier("jdbcDatabase1") JdbcTemplate jdbcDatabase1, @Qualifier("jdbcDatabase2") JdbcTemplate jdbcDatabase2) {
        this.jdbcDatabase1 = jdbcDatabase1;
        this.jdbcDatabase2 = jdbcDatabase2;
    }

    public List<Usuario> getAllUser() {

        StringBuilder builder = new StringBuilder();

        String script = """
                select 
                id,
                nome 
                from Usuario
                """;

        builder.append("SELECT ")
                .append(" ID,")
                .append(" NOME,")
                .append(" FROM")
                .append(" Usuario");


        List<Usuario> list1 = jdbcDatabase1.query(script, new UserRowMapper());
        List<Usuario> list2 = jdbcDatabase2.query(script, new UserRowMapper());

       return Stream.concat(list1.stream(), list2.stream()).toList();
    }

    static class UserRowMapper implements RowMapper<Usuario> {

        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            return usuario;
        }

    }

}
