package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.JokeLikeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import com.xandone.wcdog.utils.SimpleUtils;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addJoke(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        try {
            JokeBean jokeBean = jokeService.addJoke(map);
            List<JokeBean> list = new ArrayList<>();
            list.add(jokeBean);
            baseResult.setData(list);
            baseResult.setCode(SUCCESS_CODE);
        } catch (Exception e) {
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteJokeById(@RequestBody Map<String, String> map) {
        BaseListResult baseResult = new BaseListResult();
        try {
            String jokeId = map.get("jokeId");
            String adminId = map.get("adminId");
            jokeService.deleteJokeById(jokeId);
            jokeService.deleteCommentByJokeId(jokeId);
            baseResult.setCode(SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

    @RequestMapping(value = "/deleteList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteJokeById(@RequestParam(value = "jokeIds") String jokeIds) {
        BaseListResult baseResult = new BaseListResult();
        System.out.println("joke:" + jokeIds);
        try {
            jokeService.deleteJokeByList(SimpleUtils.toList(jokeIds));
            baseResult.setCode(SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addComment(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        List<CommentBean> dataList = new ArrayList<>();
        try {
            String jokeId = map.get("jokeId");
            String userId = map.get("userId");
            String details = map.get("details");
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

            UserBean user = userService.getUserById(userId);
            if (user != null) {
                commentBean.setCommentNick(user.getNickname());
                commentBean.setCommentIcon(user.getUserIcon());
            }
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

    @RequestMapping(value = "/comment/deleteList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteCommentList(@RequestParam(value = "userIds") String userIds) {
        BaseListResult baseResult = new BaseListResult();
        try {
            jokeService.deleteCommentList(SimpleUtils.toList(userIds));
            baseResult.setCode(SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

    @RequestMapping(value = "/comment/deletebyjokeid", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteCommentByJokeId(@RequestParam(value = "jokeId") String jokeId,
                                            @RequestParam(value = "adminId") String adminId) {
        BaseListResult baseResult = new BaseListResult();
        try {
            jokeService.deleteCommentByJokeId(jokeId);
            baseResult.setCode(SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
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


}
