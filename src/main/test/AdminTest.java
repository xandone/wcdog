import com.xandone.wcdog.mapper.BannerMapper;
import com.xandone.wcdog.mapper.JokeMapper;
import com.xandone.wcdog.mapper.UserMapper;
import com.xandone.wcdog.pojo.BannerBean;
import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.utils.IDUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class AdminTest {
    @Test
    public void addUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        UserMapper mapper = context.getBean(UserMapper.class);

        UserBean user = new UserBean(
                "6@qq.com",
                "2",
                "狗蛋2",
                "6",
                new Date());
        user.setUserIcon("http://img5.imgtn.bdimg.com/it/u=2230167403,4188800858&fm=26&gp=0.jpg");

        mapper.addUser(user);
    }

    @Test
    public void getUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        UserMapper mapper = context.getBean(UserMapper.class);
        UserBean bean = mapper.getUserById("1");
        System.out.println(bean.toString());
    }

    @Test
    public void getUserByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        UserMapper mapper = context.getBean(UserMapper.class);
        UserBean user = mapper.getUserByName("6@qq.com");
        System.out.println(user);
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

    @Test
    public void addComment() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JokeMapper mapper = context.getBean(JokeMapper.class);

        CommentBean commentBean = new CommentBean();
        commentBean.setCommentId(IDUtils.RandomId());
        commentBean.setJokeId("152112021530410");
        commentBean.setCommentUserId("152112021530406");
        commentBean.setCommentDetails("写得好");
        commentBean.setCommentDate(new Date());

        mapper.addComment(commentBean);
    }

    @Test
    public void addBanner() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        BannerMapper mapper = context.getBean(BannerMapper.class);
        BannerBean bannerBean = new BannerBean();
        bannerBean.setUserId("1");
        bannerBean.setArticelId(IDUtils.RandomId());
        bannerBean.setImgUrl("http://img5.imgtn.bdimg.com/it/u=2230167403,4188800858&fm=26&gp=0.jpg");
        bannerBean.setTitle("长河落日圆");
        bannerBean.setPageViews(0);
        bannerBean.setUpTime(new Date());
        mapper.addBanner(bannerBean);

    }

}
