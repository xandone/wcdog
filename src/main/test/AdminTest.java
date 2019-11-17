import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.mapper.*;
import com.xandone.wcdog.pojo.*;
import com.xandone.wcdog.utils.IDUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class AdminTest {
    @Test
    public void addUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        UserMapper mapper = context.getBean(UserMapper.class);

        UserBean user = new UserBean(
                "admin",
                "123",
                "Admin",
                "250",
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
        jokeBean.setJokeId("1231231");
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
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
            BannerMapper mapper = context.getBean(BannerMapper.class);
            BannerBean bannerBean = new BannerBean();
            bannerBean.setUserId("1");
            bannerBean.setArticelId(IDUtils.RandomId());
            bannerBean.setImgUrl("https://upload-images.jianshu.io/upload_images/2518499-3d5a6ec6bc7f7efd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
            bannerBean.setTitle("长河落日圆");
            bannerBean.setPageViews(0);
            bannerBean.setUpTime(new Date());
            mapper.addBanner(bannerBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addAdmin() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        AdminMapper mapper = context.getBean(AdminMapper.class);

        AdminBean user = new AdminBean(
                "admin",
                "123",
                "Admin",
                "250",
                new Date());
        user.setAdminIcon("http://p1.pstatp.com/large/pgc-image/8f5a9eaea7cb426c895a67e6557eec32");

        mapper.addAdmin(user);
    }


    @Test
    public void addPlank() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        PlankMapper mapper = context.getBean(PlankMapper.class);

        PlankTalkBean plankTalkBean = new PlankTalkBean();
        plankTalkBean.setSendTime(new Date());
        plankTalkBean.setContent("今天吃白菜炖粉条~~~~~");
        mapper.addPlankTalk(plankTalkBean);

    }

    @Test
    public void addApk() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        ApkMapper mapper = context.getBean(ApkMapper.class);

        ApkBean apkBean = new ApkBean();
        apkBean.setApkCode(2);
        apkBean.setApkId("1");
        apkBean.setApkVersion("1.1.2");
        apkBean.setApkUrl(Config.APK_LOAD_URL);
        apkBean.setContent("解决部分已知bug,优化网络请求");
        apkBean.setSendTime(new Date());
        mapper.addLastApk(apkBean);

    }


    @Test
    public void adda() {
        Connection conn = null;
        try {
            String userName = "root";
            String password = "123123";
            String jdbcurl = "jdbc:mysql://localhost:3306/wcdog?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(jdbcurl, userName, password);
            String sql = "select * from y_user";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            String result = "";
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("nickname");
                result += id + "\t" + name + "\t" + status + "\n";
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
