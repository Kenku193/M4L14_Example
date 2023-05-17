package ru.javarush.util;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.javarush.entity.Account;
import ru.javarush.entity.Client;

public class Cache1 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session1 = sessionFactory.openSession();
        session1.setCacheMode(CacheMode.IGNORE);
        Transaction transaction = session1.beginTransaction();

        Client client1 = new Client("Саша Пушкин");
        Client client2 = new Client("Миша Лермонтов");

        Account account1 = new Account(1000, client1);
        Account account2 = new Account(2000, client2);
        Account account3 = new Account(7000, client2);

        session1.persist(account1);
        session1.persist(account2);
        session1.persist(account3);

        // РАБОТАЕМ С КЭШЕМ
        Client client = session1.get(Client.class, 1L);

        transaction.commit();

        System.out.println(client);



    }
}
