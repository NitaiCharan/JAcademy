/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unipe.cc.mpl3.jacademy.persistencia;

import br.unipe.cc.mpl3.jacademy.modelo.Disciplina;
import br.unipe.cc.mpl3.jacademy.util.DriveException;
import br.unipe.cc.mpl3.jacademy.util.QueryNullException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Pega atributos de Disciplina salvos no BD. Este método servira apenas para
 * transportar informações contidas no BD referente a Disciplina. Possibilita de
 * query's na tabela Disciplinas, somente necessário passar por parametro
 * (SELECT * FROM disciplina WHERE query).
 *
 *@author Nitai Charan
 * @param query
 * @return Resultado da query realizado no BD em um conjunto Set de Disciplina
 */
public class RepositorioDisciplina implements IRepositorioDisciplina{

    public Set<Disciplina> getDadosDisciplina() {
        Set<Disciplina> dados = new HashSet<>();
        try {
            DataBase database = new DataBase();
            ResultSet resultSet = database.getStatement().executeQuery("SELECT * FROM disciplina");
            try {
                throw new QueryNullException(resultSet);
            } catch (QueryNullException ex) {
            }
            while (resultSet.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(resultSet.getInt("id"));
                disciplina.setNome(resultSet.getString("nome"));
                disciplina.setDescricao(resultSet.getString("descricao"));
                dados.add(disciplina);
            }
            database.close();
            database.getStatement().close(); // Em avaliação
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Exception RepositorioDisciplina:  " + ex);
        }
        return dados;
    }

}
