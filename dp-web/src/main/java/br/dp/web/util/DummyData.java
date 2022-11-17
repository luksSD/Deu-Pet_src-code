package br.dp.web.util;

import br.dp.model.Usuario;

import java.sql.Timestamp;

public class DummyData {

    Timestamp date = new Timestamp(System.currentTimeMillis());

    public static Usuario generateAdmUser() {
        final Usuario u = new Usuario();

        u.setNome("Admin");
        u.setSenha("admin");
        u.setEmail("admin@deupet.com");
        u.setCelularTelefone("35998738536");
        u.setSituacao(true);
        u.setDataCadastro(new Timestamp(System.currentTimeMillis()));
        u.setAceite(true);
        u.setTipo("ADMIN");
        return u;
    }
}
