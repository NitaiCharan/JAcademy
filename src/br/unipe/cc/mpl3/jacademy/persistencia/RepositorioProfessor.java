package br.unipe.cc.mpl3.jacademy.persistencia;

import br.unipe.cc.mpl3.jacademy.modelo.Professor;
import br.unipe.cc.mpl3.jacademy.util.DriveException;
import br.unipe.cc.mpl3.jacademy.util.QueryNullException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class RepositorioProfessor extends Repositorio implements IRepositorioProfessor {

    private DataBase database;

    @Override
    public boolean inserir(Professor professor) {

        boolean resultado = false;
        String sql = "INSERT INTO professor(situacao,profissao,titularidade,nome,cpf,sexo,email,telefone,endereco,bairro,cidade,estado,cep,observacao) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            save(sql, professor.getSituacao(),
                    professor.getProfissao(),
                    professor.getTitularidade(),
                    professor.getNome(),
                    professor.getCpf(),
                    professor.getSexo(),
                    professor.getEmail(),
                    professor.getTelefone(),
                    professor.getLogradouro(),
                    professor.getBairro(),
                    professor.getCidade(),
                    professor.getEstadoCivil(),
                    professor.getCep(),
                    professor.getObs());
            resultado = true;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return resultado;
    }

    @Override
    public boolean deletar(String id) {

        boolean resultado = false;
        String sql = "delete from professor where matricula = ?";

        try {
            delete(sql, id);
            resultado = true;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return resultado;
    }

    @Override
    public List<Professor> getProfessores() {
        List<Professor> professores = new ArrayList<Professor>();
        try {
            database = new DataBase();
            String sql = "SELECT * FROM professor order by matricula";
            ResultSet rs = database.getStatement().executeQuery(sql);
            try {
                throw new QueryNullException(rs);
            } catch (QueryNullException ex) {
            }
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setMatricula(rs.getInt("matricula"));
                professor.setSituacao(rs.getString("situacao"));
                professor.setProfissao(rs.getString("profissao"));
                professor.setTitularidade(rs.getString("titularidade"));
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setSexo(rs.getString("sexo"));
                professor.setEmail(rs.getString("email"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setLogradouro(rs.getString("endereco"));
                professor.setBairro(rs.getString("bairro"));
                professor.setCidade(rs.getString("cidade"));
                professor.setEstado(rs.getString("estado"));
                professor.setCep(rs.getString("cep"));
                professor.setObservacao(rs.getString("observacao"));
                professores.add(professor);
            }
            database.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return professores;
    }

    @Override
    public boolean atualizar(Professor professor) {

        boolean resultado = false;  
        String sql = "update professor set (situacao = ?,profissao = ?,titularidade = ?,nome = ?,cpf = ?,sexo = ?,email = ?,telefone = ?,endereco = ?,bairro = ?,cidade = ?,estado = ?,cep = ?,observacao = ?) "
                + " where matricula = \'" + professor.getMatricula() + "\'";

        try {
            update(sql, professor.getSituacao(),
                    professor.getProfissao(),
                    professor.getTitularidade(),
                    professor.getNome(),
                    professor.getCpf(),
                    professor.getSexo(),
                    professor.getEmail(),
                    professor.getTelefone(),
                    professor.getLogradouro(),
                    professor.getBairro(),
                    professor.getCidade(),
                    professor.getEstado(),
                    professor.getCep(),
                    professor.getObs());

            resultado = true;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return resultado;
    }

    @Override
    public Professor getProfessor(String id) {

        Professor professor = new Professor();

        try {
            database = new DataBase();
            String sql = "SELECT * FROM professor where matricula = \'" + id + "\' order by matricula";
            ResultSet rs = database.getStatement().executeQuery(sql);
            try {
                throw new QueryNullException(rs);
            } catch (QueryNullException ex) {
            }

            if (rs != null && rs.next()) {

                professor.setMatricula(rs.getInt("matricula"));
                professor.setSituacao(rs.getString("situacao"));
                professor.setProfissao(rs.getString("profissao"));
                professor.setTitularidade(rs.getString("titularidade"));
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setSexo(rs.getString("sexo"));
                professor.setEmail(rs.getString("email"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setLogradouro(rs.getString("endereco"));
                professor.setBairro(rs.getString("bairro"));
                professor.setCidade(rs.getString("cidade"));
                professor.setEstado(rs.getString("estado"));
                professor.setCep(rs.getString("cep"));
                professor.setObservacao(rs.getString("observacao"));
            }

            database.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return professor;
    }

    @Override
    public List<Professor> busca(String desc) {

        List<Professor> professores = new ArrayList<Professor>();

        try {
            database = new DataBase();
            String sql = "SELECT nome, cpf, matricula FROM professor WHERE nome LIKE '%" + desc + "%' order by matricula";

            ResultSet rs = database.getStatement().executeQuery(sql);
            try {
                throw new QueryNullException(rs);
            } catch (QueryNullException ex) {
            }

            while (rs.next()) {
                Professor professor = new Professor();

                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setMatricula(rs.getInt("matricula"));

                professores.add(professor);
            }

            database.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
        return professores;
    }
}
