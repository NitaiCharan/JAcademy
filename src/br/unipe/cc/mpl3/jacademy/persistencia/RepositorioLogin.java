package br.unipe.cc.mpl3.jacademy.persistencia;

import br.unipe.cc.mpl3.jacademy.modelo.Login;
import br.unipe.cc.mpl3.jacademy.util.Criptografia;
import br.unipe.cc.mpl3.jacademy.util.DriveException;
import br.unipe.cc.mpl3.jacademy.util.QueryNullException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class RepositorioLogin extends Repositorio {

    private DataBase database;

    public boolean validarLogin(String id, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean retorno = false;

        try {
            database = new DataBase();
            String sql = "SELECT id,senha FROM login WHERE id = \'" + id + "\' ";
            ResultSet resultSet = database.getStatement().executeQuery(sql);
            try {
                throw new QueryNullException(resultSet);
            } catch (QueryNullException ex) {
            }

            Criptografia criptografia = new Criptografia();
            String senhaHex = criptografia.criptografar(senha);

            if (resultSet.next()) {
                String senhaBanco = resultSet.getString(2);

                if (senhaBanco.equals(senhaHex)) {
                    retorno = true;
                }
            }

            database.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
        return retorno;
    }

    public boolean inserir(Login login) {

        boolean resultado = false;
        String sql = "INSERT INTO login(id,usuario,senha) VALUES(?,?,?)";

        try {
            Criptografia criptografia = new Criptografia();

            save(sql, login.getId(), login.getUsuario(), criptografia.criptografar(login.getSenha()));
            resultado = true;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Erro:" + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return resultado;
    }

    public boolean deletar(String id) {

        boolean resultado = false;
        String sql = "delete from login where id = ?";

        try {
            delete(sql, id);
            resultado = true;
        } catch (SQLException ex) {
            //Logger.getLogger(RepositorioLeciona.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro:" + ex.getMessage());
        }

        return resultado;
    }

    public List<Login> getUsuarios() {

        List<Login> professores = new ArrayList<Login>();

        try {
            database = new DataBase();
            String sql = "SELECT id,usuario FROM login order by id";
            ResultSet rs = database.getStatement().executeQuery(sql);
            try {
                throw new QueryNullException(rs);
            } catch (QueryNullException ex) {
            }

            while (rs.next()) {
                Login login = new Login();

                login.setId(rs.getString("id"));
                login.setUsuario(rs.getString("usuario"));

                professores.add(login);
            }

            database.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return professores;
    }

}
