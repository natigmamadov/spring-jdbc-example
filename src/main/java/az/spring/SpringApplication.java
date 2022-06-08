package az.spring;


import az.spring.config.SpringJdbcConfig;
import az.spring.dao.EmployeeDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringApplication {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        EmployeeDao employeeDao = applicationContext.getBean(EmployeeDao.class);

    }
}
