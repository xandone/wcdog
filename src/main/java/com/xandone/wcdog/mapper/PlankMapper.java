package com.xandone.wcdog.mapper;

import com.xandone.wcdog.pojo.PlankTalkBean;
import com.xandone.wcdog.pojo.TalkBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/7/1 16:56
 * description：
 */
public interface PlankMapper {
    void addTalk(TalkBean talkBean);

    void addPlankTalk(PlankTalkBean plankTalkBean);

    List<TalkBean> getTalkList();

    List<PlankTalkBean> getPlankList();

    PlankTalkBean getLastPlank();

    void deletePlankById(String id);

    void deleteTalkById(String id);

}
