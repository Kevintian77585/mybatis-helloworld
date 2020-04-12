package com.kevin.test;

import com.kevin.dao.EmployeeMapper;
import com.kevin.mybatis.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Employee employee = null;
        try {
            employee = sqlSession.selectOne("com.kevin.mybatis.EmployeeMapper.selectEmp", 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        System.out.println(employee);
    }

    @Test
    public void test1() throws IOException {
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3获取接口的实现类对象
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp = mapper.getEmpById(1);
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }

    }

    /**
     * 测试增删改
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取SqlSession对象（不会自动提交数据）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3获取接口的实现类对象
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //进行测试
            Employee emp = new Employee(null, "test1","test1@123.com" ,"1");
            mapper.addEmp(emp);
            //手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }

}
