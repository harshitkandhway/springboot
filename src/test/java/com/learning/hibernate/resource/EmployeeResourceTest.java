package com.learning.hibernate.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.hibernate.domain.Employee;
import com.learning.hibernate.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void should_add_employee_using_Requestbody() throws Exception {
        Employee employee = new Employee("Apoorva", "Consultant", 20.00);
        String json = objectMapper.writeValueAsString(employee);
        mockMvc.perform(post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated()).andDo(print())
                .andExpect(jsonPath("$.name").value("Apoorva"));
    }

//    @Test
//    void should_add_employee_using_RequestParam() throws Exception {
//        mockMvc.perform(post("/api/employee?name=Apoorva")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated()).andDo(print())
//                .andExpect(jsonPath("$.name").value("Apoorva"));
//    }
//
//    @Test
//    void should_add_employee_using_PathVariable() throws Exception {
//        mockMvc.perform(post("/api/employee/Apoorva")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated()).andDo(print())
//                .andExpect(jsonPath("$.name").value("Apoorva"));
//

//    }



    @Test
    void get_All_Employee() throws Exception {
        List<Employee> employees = createAndSaveEmployeeObjects();
        String json = objectMapper.writeValueAsString(employees);
        mockMvc.perform(get("/api/employee")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isFound()).andDo(print());
    }

    @Test
    void get_employee_by_id() throws Exception {
        List<Employee> employees = createAndSaveEmployeeObjects();
        String json = objectMapper.writeValueAsString(employees);
        int id = 1;
        mockMvc.perform(get("/api/employee/{id}" ,id).contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isFound()).andDo(print())
                .andExpect(jsonPath("$.name").value("Harshit"));
    }

    @Test
    void should_update_by_id() throws Exception {
        List<Employee> employees = createAndSaveEmployeeObjects();
        int id = 2;
        mockMvc.perform(patch("/api/employee/{id}?salary=6000000.0",id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
        .andExpect(jsonPath("$.salary").value("6000000.0"));
    }

    List<Employee> createAndSaveEmployeeObjects() {
        Employee employee1 = new Employee("Harshit", "Consultant", 20.00);
        Employee employee2 = new Employee("Apoorva", "Consultant", 20.00);
        return employeeRepository.saveAll(Arrays.asList(employee1, employee2));
    }

}
