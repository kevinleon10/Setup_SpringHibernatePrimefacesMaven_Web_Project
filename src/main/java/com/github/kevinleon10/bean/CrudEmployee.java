package com.github.kevinleon10.bean;

import com.github.kevinleon10.model.Employee;
import com.github.kevinleon10.service.EmployeeService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class CrudEmployee implements Serializable {

    @ManagedProperty("#{employeeService}")
    private EmployeeService employeeService;

    private Employee employee = new Employee();

    private boolean isAlreadyCreated = false;

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String register() {
        // Calling Business Service
        if(isAlreadyCreated){
            employeeService.update(employee);
        } else {
            employeeService.create(employee);
        }
        // Add message
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The Employee " + this.employee.getEmployeeName() + " Is Registered Successfully"));
        return "";
    }

    public List<Employee> getEmployeeList(){
        return employeeService.list();
    }

    public void viewEmployee(Employee employee){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("create_update_employee.xhtml");
            if(employee!=null){
                isAlreadyCreated = true;
                setEmployee(employee);
            } else {
                this.employee = new Employee();
            }
        } catch (IOException ex) {
            Logger.getLogger(CrudEmployee.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Bad url"));
        }
    }

    public String deleteEmployee(Employee employee){
        // Calling Business Service
        employeeService.delete(employee);
        // Add message
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The Employee " + this.employee.getEmployeeName() + " Is Deleted Successfully"));
        return "";
    }

    public void returnHome(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CrudEmployee.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Bad url"));
        }
    }
}