package com.learning.hibernate.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.hibernate.domain.Employee;
import com.learning.hibernate.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeResourceTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void should_add_employee() throws Exception {
        Employee employee = new Employee("Apoorva", "Consultant", 20.00);
        String json = objectMapper.writeValueAsString(employee);
        mockMvc.perform(post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated()).andDo(print())
                .andExpect(jsonPath("$.name").value("Apoorva"));

    }

    @Test
    void get_All_Employee() throws Exception {
        Employee employee1 = new Employee("Harshit", "Consultant", 20.00);
        Employee employee2 = new Employee("Apoorva", "Consultant", 20.00);
        List<Employee> employees = employeeRepository.saveAll(Arrays.asList(employee1, employee2));
        String json = objectMapper.writeValueAsString(employees);
        mockMvc.perform(get("/api/employee")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isFound()).andDo(print());


    }
}
