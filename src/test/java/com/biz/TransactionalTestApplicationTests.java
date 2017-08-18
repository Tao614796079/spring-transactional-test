package com.biz;

import com.biz.service.student.StudentService;
import com.biz.service.teacher.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTestApplicationTests {
    /**
     * 1、Propagation.REQUIRED
     * 方法被调用时自动开启事务，在事务范围内使用则使用同一个事务，否则开启新事务。
     * 2、Propagation.REQUIRES_NEW
     * 无论何时自身都会开启事务
     * 3、Propagation.SUPPORTS
     * 自身不会开启事务，在事务范围内则使用相同事务，否则不使用事务
     * 4、Propagation.NOT_SUPPORTED
     * 自身不会开启事务，在事务范围内使用挂起事务，运行完毕恢复事务
     * 5、Propagation.MANDATORY
     * 自身不开启事务，必须在事务环境使用否则报错
     * 6、Propagation.NEVER
     * 自身不会开启事务，在事务范围使用抛出异常
     * 7、Propagation.NESTED
     * 如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务,
     * 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。需要JDBC3.0以上支持。
     */
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    /**
     * 经测试无论在teacherService还是studentService如果不抛出异常，那么数据提交成功，如果抛出异常，数据提交失败。
     * 这说明teacherService和studentService使用的是同一个事务，并且只要方法被调用就开启事务。
     */
    @Test
    public void testRequired() {
        Data data = new Data();
        studentService.addStudent(data.getStudent(),data.getTeacher());
    }

    /**
     * 经测试如果在addStudent2中抛出异常，学生数据不能正确提交，教师信息被正确提交。
     *  Propagation.REQUIRES_NEW是在事务中新起一个事务
     *  如果在addTeacher2中抛出异常,异常会一直抛到最外面，学生和教师数据均回滚
     *
     *  注意：如果调用的是同一个类里面的方法，REQUIRES_NEW不会生效；
     */
    @Test
    public void testRequiresNew(){
        Data data = new Data();
        studentService.addStudent2(data.getStudent(),data.getTeacher());
    }
    @Test
    public void testRequiresNew2(){
        Data data = new Data();
        studentService.addStudent8(data.getStudent(),data.getTeacher());
    }

    /**
     * 经测试如果在addStudent3中抛出异常，学生数据和教师数据都被正确提交。
     * 说明teacherService和studentService没有被spring管理和开启事务，而是使用了本地事务，
     * 由于本地事务默认自动提交因此数据都提交成功，但它们使用的却不是同一个事务，一旦出现异常将导致数据的不一致。
     */
    @Test
    public void testSupport(){
        Data data = new Data();
        studentService.addStudent3(data.getStudent(),data.getTeacher());
    }
    //

    /**
     * Propagation.NOT_SUPPORTED:自身不会开启事务，在事务范围内使用挂起事务，运行完毕恢复事务
     * 经测试如果在addTeacher4中抛出异常，学生数据提交失败，教师数据提交成功。
     * 说明studentService开启了事务，teacherService没有开启事务，而是使用了本地事务。
     */
    @Test
    public void testNotSupport(){
        Data data = new Data();
        studentService.addStudent4(data.getStudent(),data.getTeacher());
    }

    /**
     * Propagation.MANDATORY:自身不开启事务，必须在事务环境使用否则报错
     * org.springframework.transaction.IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
     */
    @Test
    public void testMandatory(){
        Data data = new Data();
        studentService.addStudent5(data.getStudent());
    }

    /**
     * Propagation.NEVER:自身不会开启事务，在事务范围使用抛出异常
     */
    @Test
    public void testNever(){
        Data data = new Data();
        studentService.addStudent6(data.getStudent(),data.getTeacher());
    }
    /**
     * Propagation.NESTED:如果没有事务环境其特性同Propagation.REQUIRED,否则嵌套运行事务
     *
     * PROPAGATION_NESTED使用了一个单独的物理事务， 这个事务拥有多个可以回滚的保存点。
     * 这样部分回滚允许内部事务在它的作用域内触发一个回滚， 并且外部事务能够不受影响的继续。
     * 这通常是对应于JDBC的保存点，所以只会在 JDBC 资源事务管理上起效 (具体请参见 Spring的DataSourceTransactionManager).
     * 而DataSourceTransactionManager只会对Spring Jdbc（即JdbcTemplate）或者ibatis等jdbc操作有效，
     * 配置里使用了hibernate的sessionFacotry来访问，所以不会有效果.
     *
     * org.springframework.transaction.NestedTransactionNotSupportedException: JpaDialect does not support savepoints - check your JPA provider's capabilities
     *
     */
    @Test
    public void testNested(){
        Data data = new Data();
        studentService.addStudent7(data.getStudent(),data.getTeacher());
    }
}
