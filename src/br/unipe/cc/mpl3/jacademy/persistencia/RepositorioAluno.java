/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unipe.cc.mpl3.jacademy.persistencia;

import br.unipe.cc.mpl3.jacademy.modelo.Aluno;
import br.unipe.cc.mpl3.jacademy.util.DriveException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo
 */
public class RepositorioAluno{
    
    private boolean execute(String  sql, Object... parametros) throws SQLException, DriveException{
        DataBase database = new DataBase();
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        for (int i = 0; i < parametros.length; i++) {
            preparedStatement.setObject(i + 1, parametros[i]);
        }

        boolean retorno = preparedStatement.execute();
        preparedStatement.close();
        database.close();
        return retorno;
    }
    
    public boolean insert(Aluno aluno){
        String query = "INSERT INTO aluno(nome, cpf, profissao, email, telefone, endereco, bairro, cidade, estado, cep, observacao) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            return execute(query, aluno.getNome(), aluno.getCpf(), aluno.getProfissao(), aluno.getEmail(), aluno.getTelefone(), aluno.getLogradouro(), aluno.getBairro(), aluno.getCidade(), aluno.getEstado(), aluno.getCep(), aluno.getObservacao());
        } catch (SQLException ex) {
            return false;
        } catch (DriveException ex) {
            return false;
        }
    }
    
    public boolean update(Aluno aluno){
        String query = "UPDATE aluno SET nome=?, cpf=?, profissao=?, email=?, telefone=?, endereco=?, bairro=?, cidade=?, estado=?, cep=?, observacao=? WHERE matricula=?";
        try {
            return execute(query,  aluno.getNome(), aluno.getCpf(), aluno.getProfissao(), aluno.getEmail(), aluno.getTelefone(), aluno.getLogradouro(), aluno.getBairro(), aluno.getCidade(), aluno.getEstado(), aluno.getCep(), aluno.getObservacao(), aluno.getMatricula());
        } catch (SQLException ex) {
            return false;
        } catch (DriveException ex) {
            return false;
        }
    }
    
    public boolean remove(String matricula){
        String query = "DELETE FROM aluno WHERE matricula = ?";
        try {
            return execute(query, matricula);
        } catch (SQLException ex) {
            return false;
        } catch (DriveException ex) {
            return false;
        }
    }
    
    public static List<Aluno> list(String busca){
        List<Aluno> lista = new ArrayList<>();
        try {
            DataBase database = new DataBase();
            ResultSet resultSet = database.getStatement().executeQuery("SELECT * FROM aluno WHERE nome LIKE \'" + busca + "%\'");

            while(resultSet.next()){
                Aluno aluno = new Aluno();
                
                aluno.setMatricula(resultSet.getInt("matricula"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setProfissao(resultSet.getString("profissao"));
                aluno.setEmail(resultSet.getString("email"));
                aluno.setTelefone(resultSet.getString("telefone"));
                aluno.setLogradouro(resultSet.getString("endereco"));
                aluno.setBairro(resultSet.getString("bairro"));
                aluno.setCidade(resultSet.getString("cidade"));
                aluno.setEstado(resultSet.getString("estado"));
                aluno.setCep(resultSet.getString("cep"));
                aluno.setObservacao(resultSet.getString("observacao"));
                
                lista.add(aluno);
            }

            resultSet.close();
            database.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;    
    }
    
    public Aluno get(String matricula){
        Aluno aluno = new Aluno();
        try {
            DataBase database = new DataBase();
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT * FROM aluno WHERE matricula = ?");
            preparedStatement.setObject(1, matricula);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                aluno.setMatricula(resultSet.getInt("matricula"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setProfissao(resultSet.getString("profissao"));
                aluno.setEmail(resultSet.getString("email"));
                aluno.setTelefone(resultSet.getString("telefone"));
                aluno.setLogradouro(resultSet.getString("endereco"));
                aluno.setBairro(resultSet.getString("bairro"));
                aluno.setCidade(resultSet.getString("cidade"));
                aluno.setEstado(resultSet.getString("estado"));
                aluno.setCep(resultSet.getString("cep"));
                aluno.setObservacao(resultSet.getString("observacao"));
            }

            resultSet.close();
            preparedStatement.close();
            database.close();
       
        } catch (SQLException ex) {
            return null;
        }
        return aluno;
    }
}
