package com.tsvietkovich.TutorialsExamples.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

public class Selects {
    public static void main(String[] args) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPAHibernate");
        EntityManager entitymanager = emfactory.createEntityManager();

        entitymanager.getTransaction( ).begin( );

        List<Employee> em_list = new LinkedList<>();
        em_list.add(new Employee("Masha",45000,"Technical Writer"));
        em_list.add(new Employee("Krishna", 30000,"Technical Writer"));
        em_list.add(new Employee("Kiran", 35000,"Proof Reader"));

        for(Employee e:em_list){
            entitymanager.persist(e);
        }

        entitymanager.getTransaction( ).commit( );

        //Scalar function
        Query query = entitymanager.
                createQuery("Select UPPER(e.ename) from Employee e");
        List<String> list = (List<String>)query.getResultList();

        for (String e : list) {
            System.out.println("Employee NAME :" + e);
        }

        //Aggregate function
        Query query1 = entitymanager.createQuery("Select MAX(e.salary) from Employee e");
        Double result = (Double) query1.getSingleResult();
        System.out.println("Max Employee Salary :" + result);

        //Between
        Query query3 = entitymanager.createQuery("Select e " + "from Employee e " + "where e.salary " + "Between 30000 and 40000");

        List<Employee> list3 = (List<Employee>) query3.getResultList();

        for (Employee e : list3) {
            System.out.print("Employee ID :" + e.getEid());
            System.out.println("\t  Between - Employee salary :" + e.getSalary());
        }

        //Like
        Query query4 = entitymanager.createQuery("Select e " + "from Employee e " + "where e.ename LIKE 'M%'");

        List<Employee> list1 = (List<Employee>) query4.getResultList();

        for (Employee e : list1) {
            System.out.print("Employee ID :" + e.getEid());
            System.out.println("\t With Like - Employee name :" + e.getEname());
        }

        Query query5 = entitymanager.createNamedQuery("find employee by id",Employee.class);

        query5.setParameter("id", 2);
        List<Employee> list5 = (List<Employee>)query5.getResultList( );

        for( Employee e:list5 ){
            System.out.print("Employee ID :" + e.getEid( ));
            System.out.println("\t NameQuery - Employee Name :" + e.getEname( ));
        }
    }
}
