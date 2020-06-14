package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.PSource;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User getUserByCar(Car car) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where car.name= :carName and car.series=:carSeries");
        query.setParameter("carName", car.getName());
        query.setParameter("carSeries", car.getSeries());
        List<User> users = query.getResultList();
        if (!users.isEmpty()) {
            return users.get(0);
        }
        else
            return null;
    }
}
