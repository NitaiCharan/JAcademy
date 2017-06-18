/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unipe.cc.mpl3.jacademy.persistencia;

import br.unipe.cc.mpl3.jacademy.modelo.Leciona;
import br.unipe.cc.mpl3.jacademy.util.DriveException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nitai Charan
 */
public class RepositorioLeciona {
    
    public static Set<Leciona> getLecionas() {
         Set<Leciona> lecionas = new HashSet<>();
        try {
            DataBase database = new DataBase();
            ResultSet resultset = database.getStatement().executeQuery("SELECT * FROM leciona");
            while (resultset.next()) {
                Leciona leciona = new Leciona();
                leciona.setIdProfessor(resultset.getInt("idProfessor"));
                leciona.setIdTurma(resultset.getInt("idTurma"));
                lecionas.add(leciona);
            }
            database.close();
            database.getStatement().close(); // Em avaliação
            resultset.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
        return lecionas;
    }
}
