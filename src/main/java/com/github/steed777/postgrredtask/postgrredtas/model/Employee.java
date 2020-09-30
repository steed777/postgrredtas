package com.github.steed777.postgrredtask.postgrredtas.model;


import javax.persistence.*;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastname;
    private String firstname;
    private String patronymic;
    private String departament;

    /*private String fio;
    private String pos;
    private String email;
    private String phone;
    private String salary;*/

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Departaments.class)
    @JoinColumn(name = "depid", insertable = false, updatable = false)
    private Departaments departaments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public Departaments getDepartaments() {
        return departaments;
    }

    public void setDepartaments(Departaments departaments) {
        this.departaments = departaments;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", departament='" + departament + '\'' +
                ", departaments=" + departaments +
                '}';
    }
}
