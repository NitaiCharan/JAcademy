/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unipe.cc.mpl3.jacademy.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dan
 */
public interface IDataBase {
    public Connection getConnection();
    public Statement getStatement();
    public void close() throws SQLException;
}
