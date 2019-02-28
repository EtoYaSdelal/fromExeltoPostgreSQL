package service;

import bizlogic.SessionUtil;
import dao.DAO;
import entity.Address;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.List;


public class AddressService extends SessionUtil implements DAO<Address> {

    @Override
    public void add(Address address) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(address);
        closeTransactionSession();
    }

    @Override
    public List<Address> getAll() throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS";

        Session session = getSession();
        NativeQuery addressQuery = session.createNativeQuery(sql).addEntity(Address.class);
        List<Address> addressList = addressQuery.list();
        closeTransactionSession();
        return addressList;
    }

    @Override
    public Address getById(Long id) throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS WHERE ID = :id";

        Session session = getSession();
        NativeQuery<Address> query = session.createNativeQuery(sql).addEntity(Address.class);
        query.setParameter("id", id);

        closeTransactionSession();
        return query.getSingleResult();
    }

    @Override
    public void update(Address address, Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();

        Address oldAddress = session.get(Address.class, id);
        oldAddress.setCountry(address.getCountry());
        oldAddress.setCity(address.getCity());
        oldAddress.setStreet(address.getStreet());
        oldAddress.setPostCode(address.getPostCode());

        session.save(oldAddress);
        closeTransactionSession();
    }

    @Override
    public void delete(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(session.get(Address.class, id));
        closeTransactionSession();
    }
}
