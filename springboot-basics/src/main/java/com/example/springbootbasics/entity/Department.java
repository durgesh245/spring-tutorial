package com.example.springbootbasics.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Builder  //It will create a builder pattern for this class.
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long departmentId;
    @NotBlank(message = "Department Name Can't be blank.")
    private String name;
    @Column(columnDefinition = "VARCHAR(1000)")
    private String address;
    private String departmentCode;

 /*   //Other supported validations
    @Email  -> Can put some regex value as well
    @Future     -> For date
    @FutureOrPresent  -> For date
    @Past -> For date
    @PastOrPresent -> For date
    @Length(min = 0, max = 2141)
    @Size(min = 0, max = 979)
    @Positive  -> For numeric values
    @Negative -> For numeric values
    @PositiveOrZero -> For numeric values
    @NegativeOrZero -> For numeric values*/

    public Department() {
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", Address='" + address + '\'' +
                ", DepartmentCode='" + departmentCode + '\'' +
                '}';
    }
}
