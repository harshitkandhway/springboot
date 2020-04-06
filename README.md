# springboot
Learning spring boot

# Exception Type = org.springframework.web.bind.MethodArgumentNotValidException
This exception is thrown when request has some argument that is not present in the domain object in database ,and we try to save the request through POST(create) APIin controller. 

# Ambiguous mapping. Cannot map 'employeeResource' method com.learning.hibernate.resource.EmployeeResource#addEmployee(Employee)
# to {POST /api/employee}: There is already 'employeeResource' bean method com.learning.hibernate.resource.EmployeeResource#addEmployee(String) mapped.
This exception will be thrown when you try to map same URI to same methods in your controller.
