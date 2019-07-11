package com.xandone.wcdog.controller;

import com.xandone.wcdog.config.Config;
import com.xandone.wcdog.pojo.AdminBean;
import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.Base.BaseResult;
import com.xandone.wcdog.pojo.PlankTalkBean;
import com.xandone.wcdog.pojo.UserBean;
import com.xandone.wcdog.service.AdminService;
import com.xandone.wcdog.service.JokeService;
import com.xandone.wcdog.service.PlankService;
import com.xandone.wcdog.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.xandone.wcdog.config.Config.SUCCESS_CODE;


/**
 * @author ：xandone
 * created on  ：2019/1/14 16:52
 * description：
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    JokeService jokeService;
    @Autowired
    PlankService plankService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        List<AdminBean> list = new ArrayList<>();
        AdminBean adminBean = null;
        String name = map.get("name");
        String psw = map.get("psw");
        try {
            adminBean = adminService.getAdminByName(name);
            if (adminBean == null) {
                baseResult.setMsg("不存在该用户");
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            } else if (!adminBean.getPassword().equals(psw)) {
                baseResult.setMsg("密码错误");
                baseResult.setCode(Config.ERROR_CODE);
                return baseResult;
            } else {
                list.add(adminBean);
                baseResult.setData(list);
                baseResult.setCode(Config.SUCCESS_CODE);
                baseResult.setMsg("登录成功");

                adminBean.setLastLoginTime(new Date());
                adminService.updateAdmin(adminBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setMsg(Config.MES_SERVER_ERROR);
            baseResult.setCode(Config.ERROR_CODE);
        }
        return baseResult;
    }

    @RequestMapping(value = "/userlist")
    @ResponseBody
    public BaseListResult getAllUser(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "row") Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = adminService.getAllUser(page, row);
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

    @RequestMapping(value = "/userlist/search")
    @ResponseBody
    public BaseListResult searchJokeList(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "row") Integer row,
                                         String userId,
                                         String name,
                                         String nickname) {
        BaseListResult baseResult = new BaseListResult();
        try {
            UserBean userBean = new UserBean(userId, name, nickname);
            BaseListResult result = adminService.searchUserList(page, row, userBean);
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

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteUserById(@RequestBody Map<String, String> map) {
        BaseListResult baseResult = new BaseListResult();
        try {
            String userId = map.get("userId");
            String adminId = map.get("adminId");
            if (!Config.ADMIN_ID.equals(adminId)) {
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg("没有删除权限");
                return baseResult;
            }
            adminService.deleteUserById(userId);
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

    @RequestMapping(value = "/user/deleteList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteUserByList(@RequestParam(value = "userIds") String userIds) {
        BaseListResult baseResult = new BaseListResult();
        System.out.println("user:" + userIds);
        try {
            adminService.deleteUserByList(SimpleUtils.toList(userIds));
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

    @RequestMapping(value = "/joke/delete", method = RequestMethod.POST)
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

    @RequestMapping(value = "/joke/deleteList", method = RequestMethod.POST)
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


    @RequestMapping(value = "/planktalk/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deletePlankById(@RequestParam(value = "id") String id,
                                      @RequestParam(value = "adminId") String adminId) {
        BaseListResult baseResult = new BaseListResult();
        try {
            plankService.deletePlankById(id);
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

    @RequestMapping(value = "/talk/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteTalkById(@RequestParam(value = "id") String id,
                                     @RequestParam(value = "adminId") String adminId) {
        BaseListResult baseResult = new BaseListResult();
        try {
            plankService.deleteTalkById(id);
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

    @RequestMapping(value = "/planktalk/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addPlankTalk(@RequestParam(value = "adminId") String adminId, @RequestParam(value = "content") String content) {
        BaseResult baseResult = new BaseResult();
        try {
            PlankTalkBean plankTalkBean = plankService.addPlankTalk(content);
            List<PlankTalkBean> list = new ArrayList<>();
            list.add(plankTalkBean);
            baseResult.setData(list);
            baseResult.setCode(SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteUserById(@RequestParam(value = "adminId") String adminId,
                                     @RequestParam(value = "jsonUser") String jsonUser) {
        BaseListResult baseResult = new BaseListResult();
        try {
            UserBean userBean = SimpleUtils.json2Pojo(jsonUser, UserBean.class);
            if (!Config.ADMIN_ID.equals(adminId)) {
                baseResult.setCode(Config.ERROR_CODE);
                baseResult.setMsg("没有修改权限");
                return baseResult;
            }
            adminService.updateUserByBean(userBean);
            baseResult.setCode(Config.SUCCESS_CODE);
            baseResult.setMsg("更新成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(Config.ERROR_CODE);
            baseResult.setMsg("更新失败");
        }
        return baseResult;
    }

}
