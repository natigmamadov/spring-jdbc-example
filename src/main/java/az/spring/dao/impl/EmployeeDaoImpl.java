package az.spring.dao.impl;

import az.spring.dao.EmployeeDao;
import az.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insert(Employee employee) {
        String query = "insert into employee(name,surname,age,salary) values(?,?,?,?)";
        jdbcTemplate.update(query, employee.getName(), employee.getSurname(), employee.getAge(), employee.getSalary());
    }

    @Override
    public void update(Employee employee) {
        String query = "update employee set name=?,surname=?,age=?,salary=? where id=?";
        jdbcTemplate.update(query, employee.getName(), employee.getSurname(), employee.getAge(), employee.getSalary(), employee.getId());
    }

    @Override
    public void delete(int id) {
        String query = "delete from employee where id=?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Employee getEmployeeById(int id) {
        String query = "select * from employee where id=?";
        Employee emp = jdbcTemplate.queryForObject(query, new EmployeeRowMapper(), id);
        return emp;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select * from employee";
        List<Employee> employees = jdbcTemplate.query(query, new EmployeeRowMapper());
        return employees;
    }

    @Override
    public long count() {
        String query = "select count(*) from employee";
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    public static class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("age"),
                    rs.getDouble("salary")
            );
        }
    }
}
