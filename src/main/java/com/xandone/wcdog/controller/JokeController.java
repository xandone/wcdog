package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.*;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.service.FlowService;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import com.xandone.wcdog.utils.ResultHelper;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.xandone.wcdog.config.Config.SUCCESS_CODE;

/**
 * @author ：xandone
 * created on  ：2019/1/15 19:26
 * description：
 */
@Controller
@RequestMapping("/joke")
public class JokeController {
    @Autowired
    JokeService jokeService;
    @Autowired
    UserService userService;
    @Autowired
    FlowService flowService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addJoke(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        try {
            UserBean user = userService.getUserById(map.get("jokeUserId"));
            if (user == null) {
                return ResultHelper.getResult(Config.ERROR_CODE);
            }
            if (user.getBanned() == 1) {
                return ResultHelper.getResult(Config.ERROR_BANNED_CODE);
            }
            JokeBean jokeBean = jokeService.addJoke(map);
            List<JokeBean> list = new ArrayList<>();
            list.add(jokeBean);
            baseResult.setData(list);
            baseResult.setCode(SUCCESS_CODE);

            FlowBean flowBean = flowService.getFlowData(map.get(Config.ADMIN_ID));
            if (flowBean == null) {
                flowBean = new FlowBean();
                flowBean.setPostTime(new Date());
                flowService.addFlow(flowBean);
            }
            String tags = map.get("tags");
            if (TextUtils.isEmpty(tags)) {
                flowBean.setClassicCount(flowBean.getClassicCount() + 1);
            } else {
                if (tags.contains("0")) {
                    flowBean.setClassicCount(flowBean.getClassicCount() + 1);
                }
                if (tags.contains("1")) {
                    flowBean.setYellowCount(flowBean.getYellowCount() + 1);
                }
                if (tags.contains("2")) {
                    flowBean.setMindCount(flowBean.getMindCount() + 1);
                }
                if (tags.contains("3")) {
                    flowBean.setShiteCount(flowBean.getShiteCount() + 1);
                }
                if (tags.contains("4")) {
                    flowBean.setColdCount(flowBean.getColdCount() + 1);
                }
            }
            flowService.upDateFlow(flowBean);
        } catch (
                Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/jokelist")
    @ResponseBody
    public BaseListResult getAllJoke(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "row") Integer row,
                                     String tag) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getAllJoke(page, row, tag);
            if (result != null) {
                result.setCode(SUCCESS_CODE);
                result.setMsg(Config.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(Config.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addComment(@RequestParam(value = "jokeId") String jokeId,
                                 @RequestParam(value = "userId") String userId,
                                 @RequestParam(value = "details") String details) {
        BaseResult baseResult = new BaseResult();
        try {
            UserBean user = userService.getUserById(userId);
            if (user == null) {
                return ResultHelper.getResult(Config.ERROR_CODE);
            }
            if (user.getBanned() == 1) {
                return ResultHelper.getResult(Config.ERROR_BANNED_CODE);
            }
            List<CommentBean> dataList = new ArrayList<>();

            if (TextUtils.isEmpty(userId)) {
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            }
            CommentBean commentBean = new CommentBean();
            commentBean.setCommentId(IDUtils.RandomId());
            commentBean.setJokeId(jokeId);
            commentBean.setCommentUserId(userId);
            commentBean.setCommentDetails(details);
            commentBean.setCommentDate(new Date());

            commentBean.setCommentNick(user.getNickname());
            commentBean.setCommentIcon(user.getUserIcon());
            jokeService.addComment(commentBean);
            baseResult.setCode(SUCCESS_CODE);
            dataList.add(commentBean);
            baseResult.setData(dataList);
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }
    }

    @RequestMapping("/comment/list")
    @ResponseBody
    public BaseListResult getAllJokeCommentById(@RequestParam(value = "page") Integer page,
                                                @RequestParam(value = "row") Integer row,
                                                @RequestParam(value = "jokeId") String jokeId) throws Exception {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getAllJokeCommentById(page, row, jokeId);
            if (result != null) {
                result.setCode(SUCCESS_CODE);
                result.setMsg(Config.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(Config.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }


    @RequestMapping("joke/thumbs")
    @ResponseBody
    public BaseResult thumbsJoke(@RequestParam(value = "jokeId") String jokeId,
                                 @RequestParam(value = "jokeUserId") String jokeUserId) throws Exception {
        BaseResult baseResult = new BaseResult();
        boolean isThumbs = false;
        List<JokeLikeBean> likeBeans = jokeService.selectJokeLikeById(jokeId);
        for (int i = 0; i < likeBeans.size(); i++) {
            if (jokeUserId.equals(likeBeans.get(i).getJoke_user_id())) {
                // 已点赞
                isThumbs = true;
                break;
            }
        }
        if (isThumbs) {
            // 已点赞
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("你已经点过赞了");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("joke_id", jokeId);
            map.put("article_like_count", 1);
            jokeService.changeJokeLikeCount(map);
            jokeService.thumbsJoke(jokeId, jokeUserId);
            baseResult.setCode(SUCCESS_CODE);
        }

        return baseResult;
    }

    @RequestMapping(value = "/jokeDetails")
    @ResponseBody
    public BaseResult deleteJokeByList(@RequestParam(value = "jokeId") String jokeId) {
        BaseResult baseResult = new BaseResult();
        try {
            JokeBean jokeBean = jokeService.getJokeById(jokeId);
            List<JokeBean> list = new ArrayList<>();
            list.add(jokeBean);
            baseResult.setData(list);
            baseResult.setCode(Config.SUCCESS_CODE);
            baseResult.setMsg("获取数据成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("获取数据异常");
        }
        return baseResult;
    }

    @RequestMapping(value = "/jokelist/foggy")
    @ResponseBody
    public BaseListResult getJokeListFog(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "row") Integer row,
                                         String key) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getJokeListFog(page, row, key);
            if (result != null) {
                result.setCode(SUCCESS_CODE);
                result.setMsg(Config.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(Config.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping("joke/thumbs/self")
    @ResponseBody
    public BaseResult getThumbsJoke(String jokeId, String jokeUserId) throws Exception {
        BaseResult baseResult = new BaseResult();
        boolean isThumbs = false;

        JokeBean jokeBean = jokeService.getJokeById(jokeId);
        List<JokeBean> data = new ArrayList<>();
        data.add(jokeBean);

        List<JokeLikeBean> likeBeans = jokeService.selectJokeLikeById(jokeId);
        for (int i = 0; i < likeBeans.size(); i++) {
            if (jokeUserId.equals(likeBeans.get(i).getJoke_user_id())) {
                // 已点赞
                isThumbs = true;
                break;
            }
        }
        if (isThumbs) {
            // 已点赞
            baseResult.setCode(Config.ERROR_CODE);
        } else {
            baseResult.setCode(SUCCESS_CODE);
        }
        baseResult.setData(data);
        return baseResult;
    }

    @RequestMapping(value = "/jokelist/search")
    @ResponseBody
    public BaseListResult searchJokeList(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "row") Integer row,
                                         String key,
                                         String jokeId,
                                         String jokeUserId,
                                         String category,
                                         String tags) {
        BaseListResult baseResult = new BaseListResult();
        try {
            JokeBean jokeBean = new JokeBean(key, jokeId, jokeUserId, category, tags);
            BaseListResult result = jokeService.searchJokeList(page, row, jokeBean);
            if (result != null) {
                result.setCode(SUCCESS_CODE);
                result.setMsg(Config.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(Config.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg(Config.MES_SERVER_ERROR);
        }
        return baseResult;
    }

}
