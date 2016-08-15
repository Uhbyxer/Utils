package com.epam.spring.movie;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration("file:src/main/webapp/WEB-INF/conf/spring.xml")
@ContextConfiguration("classpath:spring.xml")
public abstract class AbstractTestCase {

}
