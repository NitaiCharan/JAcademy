/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unipe.cc.mpl3.jacademy.persistencia;

import br.unipe.cc.mpl3.jacademy.modelo.Turma;
import br.unipe.cc.mpl3.jacademy.util.DriveException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nitai Charan
 */
public class RepositorioTurma {
    
    /**
     * Pega atributos de Tuma salvos no BD.
     * Este método servira apenas para transportar informações contidas
     * no BD referente a Turma.
     * Possibilita de query's  na tabela Turmas, somente necessário passar por
     * parametro (SELECT * FROM turma WHERE query).
     * 
     * 
     * @param query
     * @return Resultado da query realizado no BD em um conjunto Set de Turma
     */
    public static Set<Turma> getDadoTurmas(String query) {
        Set<Turma> dados = new HashSet<>();
        try {
            DataBase database = new DataBase();
            ResultSet resultSet = database.getStatement().executeQuery(query);
            while (resultSet.next()) {
                Turma turma = new Turma();
                turma.setId(resultSet.getInt("id"));
                turma.setNome(resultSet.getString("nome"));
                turma.setTurno(resultSet.getString("turno"));
                dados.add(turma);
            }
            database.close();
            database.getStatement().close(); // Em avaliação
            resultSet.close();
        } catch (SQLException ex) {
        }
        return dados;
    }
}
