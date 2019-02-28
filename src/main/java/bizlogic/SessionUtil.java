package bizlogic;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {
    Session session;
    Transaction transaction;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    private Session openSession() {
        return HibernateUtil.getSession().openSession();
    }

    public Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    private void closeSession() {
        session.close();
    }

    public void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
