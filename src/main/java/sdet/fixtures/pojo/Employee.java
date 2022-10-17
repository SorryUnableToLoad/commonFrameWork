package sdet.fixtures.pojo;

import java.util.List;

public class Employee {
    private int id;
    private String fname;
    private boolean isFTE;
    private Address address;
    private List<String> roles;

    public Employee(int id, String fname, boolean isFTE, Address address, List<String> roles) {
        this.id = id;
        this.fname = fname;
        this.isFTE = isFTE;
        this.address = address;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public boolean isFTE() {
        return isFTE;
    }

    public Address getAddress() {
        return address;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", isFTE=" + isFTE +
                ", address=" + address +
                ", roles=" + roles +
                '}';
    }
}
