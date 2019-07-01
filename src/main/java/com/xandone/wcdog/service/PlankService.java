package com.xandone.wcdog.service;

import com.xandone.wcdog.pojo.Base.BaseListResult;
import com.xandone.wcdog.pojo.PlankTalkBean;
import com.xandone.wcdog.pojo.TalkBean;

public interface PlankService {

    TalkBean addTalk(String userId, String talkStr) throws Exception;

    PlankTalkBean addPlankTalk(String content) throws Exception;

    BaseListResult getTalkList() throws Exception;
}
