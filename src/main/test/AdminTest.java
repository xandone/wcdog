import com.xandone.wcdog.mapper.JokeMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.JokeBean;
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
                "2@qq.com",
                "2",
                "狗蛋2",
                "3",
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

    @Test
    public void addJoke() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JokeMapper mapper = context.getBean(JokeMapper.class);

        JokeBean jokeBean = new JokeBean();
        jokeBean.setJokeId("1");
        jokeBean.setJokeUserId("2");
        jokeBean.setTitle("FIFA探讨世界杯扩军：中国可能要进世界杯？");
        jokeBean.setContent("2018年4月，南美足协主席多明格斯公开发言，希望国际足联考虑在2022年实现世界杯扩军，因凡蒂诺顺水推舟，"
                + "开始寻求卡塔尔的合作。但卡塔尔不愿意与周边国家共同举办世界杯，而它自身的场馆数量，又无法满足48支球队比赛的需求，这一计划才最终作罢。");
        jokeBean.setPostTime(new Date());
        mapper.addJoke(jokeBean);

    }

}
