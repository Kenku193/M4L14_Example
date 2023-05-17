package ru.javarush.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.javarush.entity.Account;
import ru.javarush.entity.Client;

import java.util.List;

public class EagerAndLazy {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client1 = new Client("Саша Пушкин");
        Client client2 = new Client("Миша Лермонтов");

        Account account1 = new Account(1000, client1);
        Account account2 = new Account(2000, client2);
        Account account3 = new Account(7000, client2);

        session.persist(account1);
        session.persist(account2);
        session.persist(account3);
        transaction.commit();

        // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //

        Session session2 = sessionFactory.openSession();
        Transaction transaction2 = session2.beginTransaction();
        Client client = session2.get(Client.class, 2L);

        List<Account> accounts = client.getAccounts();
        System.out.println(accounts);

        transaction2.commit();


    }

}
