package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {
    //HELPER
    public Department setUpDepartment(){
        return new Department("Finance", "Money", 2);

    }

    @Test
    public void Department_instanciatedCorrectly() {
        Department department = setUpDepartment();
        assertTrue(department instanceof Department);
    }

    @Test
    public void Department_insatanciatesWithName() {
        Department department = setUpDepartment();
        assertEquals("Finance", department.getName());
    }

    @Test
    public void Department_insatanciatesWithDescription() {
        Department department = setUpDepartment();
        assertEquals("Money", department.getDescription());
    }

    @Test
    public void Department_insatanciatesWithEmployeeCount() {
        Department department = setUpDepartment();
        assertEquals(2, department.getNumberOfEmployees());
    }
}