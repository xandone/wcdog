package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.CommentBean;
import com.xandone.wcdog.pojo.JokeBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.service.UserService;
import com.xandone.wcdog.utils.IDUtils;
import com.xandone.wcdog.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public BaseResult addJoke(@RequestParam(value = "title") String title,
                              @RequestParam(value = "jokeUserId") String jokeUserId,
                              @RequestParam(value = "content") String content,
                              @RequestParam(value = "content") String contentHtml
    ) {
        BaseResult baseResult = new BaseResult();
        try {
            UserBean userBean = userService.getUserById(jokeUserId);
            if (userBean == null) {
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            }
            JokeBean jokeBean = jokeService.addJoke(title, jokeUserId, content, contentHtml);
            jokeBean.setJokeUserIcon(userBean.getUserIcon());
            jokeBean.setJokeUserNick(userBean.getNickname());
            List<JokeBean> list = new ArrayList<>();
            list.add(jokeBean);
            baseResult.setData(list);
            baseResult.setCode(Config.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/jokelist")
    @ResponseBody
    public BaseListResult getAllUser(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "row") Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getAllJoke(page, row);
            if (result != null) {
                result.setCode(Config.SUCCESS_CODE);
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
    public BaseResult deleteJokeById(@RequestParam(value = "jokeId") String jokeId,
                                     @RequestParam(value = "adminId") String adminId) {
        BaseListResult baseResult = new BaseListResult();
        System.out.println("delete:.." + jokeId);
        try {
            jokeService.deleteJokeById(jokeId);
            baseResult.setCode(Config.SUCCESS_CODE);
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
            baseResult.setCode(Config.SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

    @RequestMapping("/comment/add")
    @ResponseBody
    public BaseResult addComment(@RequestParam(value = "jokeId") String jokeId,
                                 @RequestParam(value = "userId") String userId,
                                 @RequestParam(value = "details") String details) {
        BaseResult baseResult = new BaseResult();
        List<CommentBean> dataList = new ArrayList<>();
        try {
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
            baseResult.setCode(Config.SUCCESS_CODE);
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
                                                String jokeId) throws Exception {
        System.out.println("jokeid.." + jokeId);
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = jokeService.getAllJokeCommentById(page, row, jokeId);
            if (result != null) {
                result.setCode(Config.SUCCESS_CODE);
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
    public BaseResult deleteCommentByList(@RequestParam(value = "userIds") String userIds) {
        BaseListResult baseResult = new BaseListResult();
        try {
            jokeService.deleteCommentByList(SimpleUtils.toList(userIds));
            baseResult.setCode(Config.SUCCESS_CODE);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

}
