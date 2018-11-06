package com.model;

public class User {
    private Integer ID;
    private String name;
    private String department;
    private String sdt;


    public User(String name, String department, String sdt) {
        this.name = name;
        this.department = department;
        this.sdt = sdt;
    }

    public User(Integer ID, String name, String department, String sdt) {
        this.ID = ID;
        this.name = name;
        this.department = department;
        this.sdt = sdt;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setCompany(String department) {
        this.department = department;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

	@Override
	public String toString() {
		return "User [ID=" + ID + ", name=" + name + ", department="
				+ department + ", sdt=" + sdt + "]";
	}
    
}
