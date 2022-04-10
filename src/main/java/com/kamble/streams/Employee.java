package com.kamble.streams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private String name;
    private Double salary;
    private List<String> projects;
}
