package com.xandone.wcdog.service;


import com.xandone.wcdog.pojo.JokeBean;

import java.util.List;
import java.util.Map;

public interface JokeService {


    JokeBean addJoke(String title, String joke_user_id, String content) throws Exception;


}
