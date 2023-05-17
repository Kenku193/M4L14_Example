package ru.javarush.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.javarush.entity.Account;
import ru.javarush.entity.Client;

public class NPlus1 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client1 = new Client("Саша Пушкин");
        Client client2 = new Client("Миша Лермонтов");
        Client client3 = new Client("Боря Пастернак");

        Account account1 = new Account(1000, client1);
        Account account2 = new Account(2000, client2);
        Account account3 = new Account(7000, client2);
        Account account4 = new Account(80000, client2);
        Account account5 = new Account(987000, client3);

        session.persist(account1);
        session.persist(account2);
        session.persist(account3);
        session.persist(account4);
        session.persist(account5);
        transaction.commit();

        // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //

        Session session2 = sessionFactory.openSession();
        Transaction transaction2 = session2.beginTransaction();

      Query from_client = session2.createQuery("SELECT c FROM Client c")
              .setCacheable(true)
                      .setCacheRegion("my_clients");
//      Query from_client = session2.createQuery("SELECT c FROM Client c JOIN FETCH c.accounts as acc ");

//        from_client.setFirstResult(0);
//        from_client.setMaxResults(1);

        System.out.println(from_client.list());
        transaction2.commit();


    }
}
