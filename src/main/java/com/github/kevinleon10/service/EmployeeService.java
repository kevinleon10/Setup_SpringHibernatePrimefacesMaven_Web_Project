package com.github.kevinleon10.service;

import com.github.kevinleon10.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Component
public class EmployeeService implements Serializable {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(Employee emp) {
        // Acquire session
        Session session = sessionFactory.getCurrentSession();
        // Save employee, saving behavior get done in a transactional manner
        session.save(emp);
    }

    @Transactional
    public void update(Employee emp) {
        // Acquire session
        Session session = sessionFactory.getCurrentSession();
        // Save employee, saving behavior get done in a transactional manner
        session.update(emp);
    }

    @Transactional
    public void delete(Employee emp) {
        // Acquire session
        Session session = sessionFactory.getCurrentSession();
        // Save employee, saving behavior get done in a transactional manner
        session.delete(emp);
    }

    @Transactional
    public List<Employee> list() {
        // Acquire session
        Session session = this.sessionFactory.getCurrentSession();
        List<Employee> employeeList = session.createQuery("from Employee").list();
        return employeeList;
    }
}
