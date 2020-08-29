package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            
            Query query = em.createQuery("Select e from Employee e where e.salary > 100000");
            List<Employee> result = query.getResultList();

            for (Employee employee : result) {
                System.out.println(employee.getFirstName() + " : " + employee.getSalary());
            }
            
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            
            Query query2 = em.createQuery("Select e from Employee e where e.id = 'klo999'");
            List<Employee> result2 = query2.getResultList();

            for (Employee employee : result2) {

                System.out.println(employee.getFirstName());

            }
            
            //3) Create a query to fetch the highest salary and print the value
            
            Query query3 = em.createQuery("Select MAX(e.salary)from Employee e");
            
            BigDecimal result3 = (BigDecimal)query3.getSingleResult();
            
            System.out.println(result3);

            

            //4) Create a query to fetch the firstName of all Employees and print the names
            
            Query query4 = em.createQuery("Select e.firstName from Employee e");
            List<String> result4 = query4.getResultList();

            for (String employee : result4) {

                System.out.println(employee);

            }
            
           
            //5 Create a query to calculate the number of employees and print the number
            
            Query query5 = em.createQuery("SELECT COUNT(e) FROM Employee e");
            Object result5 = query5.getSingleResult();

            System.out.println(result5);
            
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            
            Query query6 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
             Employee employee2 = (Employee) query6.getSingleResult();
            
            System.out.println(employee2.getId());
            System.out.println(employee2.getFirstName());
            System.out.println(employee2.getLastName());
            System.out.println(employee2.getSalary());

            
            
        } finally {
            em.close();
            emf.close();
        }
    }

}
