package dao;

import entity.Address;
import entity.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAO <T extends Entity> {

    //create
    void add(T t) throws SQLException;

    //read
    List<T> getAll() throws SQLException;

    T getById(Long id) throws SQLException;

    //update
    void update(T t, Long id) throws SQLException;

    //delete
    void delete(T t) throws SQLException;


}
