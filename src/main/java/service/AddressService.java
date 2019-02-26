package service;

import bizlogic.Util;
import dao.DAO;
import entity.Address;
import entity.EmplProj;
import entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AddressService extends Util implements DAO<Address> {
    private Connection connection = getConnection();

    @Override
    public void add(Address address) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("add");
        }
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO address (id, country, city, street, post_code)" +
                            "VALUES (?,?,?,?,?)");
            ps.setLong(1, address.getId());
            ps.setString(2, address.getCountry());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getStreet());
            ps.setString(5, address.getPostCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Address> getAll() throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get all");
        }
        List<Address> addresses = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = null;
            try {
                rs = st.executeQuery("SELECT * FROM ADDRESS");
                while (rs.next()) {
                    addresses.add(createAddress(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                } else {
                    System.err.println("Result set error");
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return addresses;
    }

    @Override
    public Address getById(Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get by id");
        }
        Address address = new Address();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM ADDRESS WHERE ID=?")) {
            ps.setLong(1, id);
            ResultSet rs = null;
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    address = createAddress(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                } else {
                    System.err.println("Result set Error");
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return address;
    }

    @Override
    public void update(Address address, Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("update");
        }
        try (PreparedStatement pr = connection.prepareStatement(
                "UPDATE ADDRESS SET country=?,city=?, street=?,post_code=? WHERE ID= ?")) {
            pr.setString(1, address.getCountry());
            pr.setString(2, address.getCity());
            pr.setString(3, address.getStreet());
            pr.setString(4, address.getPostCode());
            pr.setLong(5, id);
            pr.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Address address) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("delete");
        }
        try (PreparedStatement pr = connection.prepareStatement("DELETE FROM ADDRESS WHERE id=?")) {
            pr.setLong(1, address.getId());
            pr.executeUpdate();
        }

    }

    private Address createAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getLong(1));
        address.setCountry(rs.getString(2));
        address.setCity(rs.getString(3));
        address.setStreet(rs.getString(4));
        address.setPostCode(rs.getString(5));
        return address;
    }

}
