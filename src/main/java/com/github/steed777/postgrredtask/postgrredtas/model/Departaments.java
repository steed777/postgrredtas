package com.github.steed777.postgrredtask.postgrredtas.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Departaments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depid;
    private String phone;
    private String chief;
    private String email;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name="depid",referencedColumnName="depid")
   private Set<Employee> employees = new HashSet<>();

    public Long getDepid() {
        return depid;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void setDepid(Long depid) {
        this.depid = depid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Departaments{" +
                "depid=" + depid +
                ", phone='" + phone + '\'' +
                ", chief='" + chief + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
