/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTaskBeans;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author lesya
 */
@Singleton
public class DBManager implements DBManagerLocal {
    private final String DbPath = "FinalTask-ejbPU";

    @Override
    public String selectAll() {
        try {
            StringBuilder sb = new StringBuilder();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(DbPath);
            EntityManager em = emf.createEntityManager();
            List<String> ans = em.createNativeQuery("select param.\"name\" from param").getResultList();
            Iterator<String> iterator = ans.iterator();
            List<Integer> ans_int = em.createNativeQuery("select param. \"value\" from param").getResultList();
            Iterator<Integer> iterator_int = ans_int.iterator();

            while (iterator.hasNext())
            {
                String text = iterator.next();
                int i = iterator_int.next();
                sb.append(text).append(" ").append(i).append(", ");
            }
            
            return sb.toString();        
        } catch (Exception e) {
            return e.getMessage();
        }
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean checkUser(String user) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(DbPath);
            EntityManager em = emf.createEntityManager();
            List<String> ans = em.createNativeQuery("select users.\"user\" from users").getResultList();
            Iterator<String> iterator = ans.iterator();
            while (iterator.hasNext())
            {
                String text = iterator.next();
                if (text.equalsIgnoreCase(user)) {
                    return true;
                }
            }
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        return false;
}

    @Override
    public void addUser(String user) {
        try {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(DbPath);
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            int id = maxUserId();
            Users u = new Users(id);
            u.setUser(user);
            transaction.begin();
            em.persist(u);
            em.merge(u);
            em.flush();
            transaction.commit();
            em.close();
            System.out.println("----> " + "OK");
//            em.createNativeQuery("insert into users values (" + id + ", '" + user + "')").executeUpdate();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }

    @Override
    public int maxUserId() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DbPath);
        EntityManager em = emf.createEntityManager();
        int i = (int) em.createNativeQuery("select max(users.ID) from users").getSingleResult();
        return i + 1;
    }

    @Override
    public String selectByName(String name) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(DbPath);
            EntityManager em = emf.createEntityManager();
            int ans = (int) em.createNativeQuery("select param.\"value\" where param.\"name\" = '" + name + "'").getSingleResult();
            return String.valueOf(ans);        
        } catch (Exception e) {
            return e.getMessage();
        }
    }
        
}
