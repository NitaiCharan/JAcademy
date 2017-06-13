/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unipe.cc.mpl3.jacademy.persistencia;

import br.unipe.cc.mpl3.jacademy.modelo.Aluno;
import br.unipe.cc.mpl3.jacademy.util.DriveException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulo
 */
public class RepositorioAluno{
    /**
     * Pega atributos de Aluno salvos no BD.
     * Método servira apenas para transportar informações contidas no 
     * BD referente a Aluno.
     * Possibilita de query's  na tabela 'aluno', somente necessário passar por
     * parametro (SELECT * FROM aluno WHERE query).
     * 
     * 
     * @param query
     * @return Resultado da query realizado no BD em um conjunto Set de Aluno.
     */   
    
    public static Set<Aluno> getDadoAluno(String query) {
        Set<Aluno> alunos = new HashSet<>();

        try {
            DataBase database = new DataBase();
            ResultSet resultSet = database.getStatement().executeQuery(query);
            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setSexo(resultSet.getString("sexo"));
                aluno.setTelefone(resultSet.getString("telefone"));
                aluno.setProfissao(resultSet.getString("profissao"));
                aluno.setObservacao(resultSet.getString("observacao"));
                aluno.setEmail(resultSet.getString("email"));
                aluno.setRg(resultSet.getString("identidade"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setSituacao(resultSet.getString("situacao"));
                aluno.setEstadoCivil(resultSet.getString("estado_civil"));
                aluno.setMatricula(resultSet.getInt("Matricula"));
                alunos.add(aluno);
            }
            database.close();
            database.getStatement().close(); // Em avaliação
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        } catch (DriveException ex) {
        }
        return alunos;
    }
}
