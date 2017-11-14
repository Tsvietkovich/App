package com.tsvietkovich.TutorialsExamples.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

public class Service {
    public static void main( String[ ] args ) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPAHibernate" );

        EntityManager entitymanager = emfactory.createEntityManager( );


        entitymanager.getTransaction( ).begin( );
        Employee employee = new Employee("Gopal", 40000, "Technical Manager");
        entitymanager.merge( employee );
        entitymanager.getTransaction( ).commit( );
        System.out.println(employee);

        System.out.println("--------------------create---------------------------");
        entitymanager.getTransaction( ).begin( );

        List<Employee> em_list = new LinkedList<>();
        em_list.add(new Employee("Masha",45000,"Technical Writer"));
        em_list.add(new Employee("Krishna", 30000,"Technical Writer"));
        em_list.add(new Employee("Kiran", 35000,"Proof Reader"));

        for(Employee e:em_list){
            entitymanager.persist(e);
        }

        entitymanager.getTransaction( ).commit( );

        System.out.println("--------------------create---group--------------------");
        entitymanager.getTransaction( ).begin( );

        Employee employee2 = entitymanager.find( Employee.class, 1 );
        employee2.setSalary( 46000 );
        entitymanager.getTransaction( ).commit( );
        //after update
        System.out.println( employee2 );
        System.out.println("----------find---then----update----------------------");

        //entitymanager.getTransaction( ).begin( );

        //Employee employee3 = entitymanager.find( Employee.class, 1201 );
        //entitymanager.remove( employee3 );
        //entitymanager.getTransaction( ).commit( );

        System.out.println("----------find---then----remove----------------------");

        entitymanager.close( );
        emfactory.close( );
    }
}
