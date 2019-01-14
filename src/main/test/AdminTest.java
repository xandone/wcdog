import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.UserBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class AdminTest {
    @Test
    public void addUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        UserMapper mapper = context.getBean(UserMapper.class);

        UserBean user = new UserBean(
                "3@qq.com",
                "2",
                "狗蛋2",
                "2",
                new Date());

        mapper.addUser(user);
    }

    @Test
    public void getUserByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        UserMapper mapper = context.getBean(UserMapper.class);
        UserBean user = mapper.getUserByName("2@qq.com");
        System.out.println(user.toString());
    }
}
