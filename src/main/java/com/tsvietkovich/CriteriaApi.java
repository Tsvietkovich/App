package com.tsvietkovich;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CriteriaApi {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("JPAHibernate");
        EntityManager entityManager = managerFactory.createEntityManager();



        entityManager.getTransaction().begin();
        Developer devM = new Developer("Masha","Java",23);
        Developer devS = new Developer("Sasha","Python",25);
        devM.setTime(new Timestamp(System.currentTimeMillis()));
        devS.setTime(new Timestamp(System.currentTimeMillis()));
        List<Developer> devs = new LinkedList<>();
        devs.add(devM);
        devs.add(devS);

        for(Developer dev : devs){
            entityManager.persist(dev);
        }
        entityManager.getTransaction().commit();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object> criteriaQuery = builder.createQuery();
        Root<Developer> from = criteriaQuery.from(Developer.class);

        //select all records
        System.out.println("Select all records");

        CriteriaQuery<Object> select = criteriaQuery.select(from); //FROM
        TypedQuery<Object> typedQuery = entityManager.createQuery(select); //SELECT
        List<Object> resultlist = typedQuery.getResultList();

        System.out.println("TIME!!!!!!------------------------------");

        for(Object o:resultlist) {
            Developer e = (Developer)o;
            System.out.println("EID : " + e.getId() + " Ename : " + e.getName() + " Time : " + e.getTime());
            Timestamp time = e.getTime();

            System.out.println(time.toLocalDateTime().getMinute() + "<-MIN");
            System.out.println(time.toLocalDateTime().getHour() + "<-Hour");
            System.out.println(time.toLocalDateTime().getDayOfMonth() + "<-DayOfMonth");
            System.out.println(time.getTime() + "<----long");
            System.out.println(System.currentTimeMillis() + "<- currentmils");

        }

        //Ordering the records
        System.out.println("Select all records by follow ordering");
        CriteriaQuery<Object> select1 = criteriaQuery.select(from); //FROM
        select1.orderBy(builder.asc(from.get("name")));//GET ALL From root BY NAME ASC Order
        TypedQuery<Object> typedQuery1 = entityManager.createQuery(select);// SELECT
        List<Object> resultlist1 = typedQuery1.getResultList();

        for(Object o:resultlist1){
            Developer e=(Developer) o;
            System.out.println("EID : " + e.getId() + " Ename : " + e.getName() + " Language : " + e.getLanguage());
        }
        //ParamExpressions
        CriteriaQuery<Developer> queryParam = builder.createQuery(Developer.class);
        Root<Developer> developerRoot = queryParam.from(Developer.class);

        ParameterExpression<Integer> parameter = builder.parameter(Integer.class);//get DEV by age where age great then 23
        queryParam.select(developerRoot).where(builder.gt(developerRoot.<Number>get("age"), parameter));
        //Running
        TypedQuery<Developer> query = entityManager.createQuery(queryParam);
        query.setParameter(parameter, 23);
        List<Developer> results = query.getResultList();
        for(Developer d : results){
            System.out.println("where(builder.gtAGE)) ->"+ d);
        }

       /*CriteriaQuery<Developer> query1 = builder.createQuery(Developer.class);
        Root<Developer> root = query1.from(Developer.class);

        Expression<Integer> path = root.get("age");
        Expression<Integer> param = builder.parameter(Integer.class);
        Expression<Integer> sum1 = builder.sum(path, param);
        System.out.println("sum1 " + sum1);
        Expression<Integer> diff1 = builder.diff(path, param); // expression - expression
        System.out.println("diff1 " + diff1);
        Expression<Integer> prod1 = builder.prod(path, param); // expression * expression
        System.out.println("prod1" + prod1);
        Expression<Number> quot1 = builder.quot(path, param); // expression / expression
        System.out.println("quot1" + quot1);
        Expression<Integer> mod1 = builder.mod(path, param); // expression % expression
        System.out.println("mod1" + mod1);
        Expression<Integer> abs = builder.abs(param);
        System.out.println("abs" + abs);*/

        entityManager.close( );
        managerFactory.close( );
    }
}



