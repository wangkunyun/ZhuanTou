package com.ztkj.wky.zhuantou.Activity.chat;

import android.text.TextUtils;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {
    private SharedPreferencesHelper sharedPreferencesHelper;
    private boolean isRobot;

    @Override
    protected void setUpView() {
        super.setUpView();
        MyUserProvider.getInstance().setUser("测试","测试");        }

    @Override
    public void onSetMessageAttributes(EMMessage message) {
        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        if (isRobot) {
            // 设置消息扩展属性
            message.setAttribute("em_robot_message", isRobot);
        }

        // 通过扩展属性，将userPic和userName发送出去。
        String userhead = (String) sharedPreferencesHelper.getSharedPreference("userhead", "");
        String realname = (String) sharedPreferencesHelper.getSharedPreference("realname", "");
       if (!TextUtils.isEmpty(userhead)) {
            message.setAttribute("userPic", userhead);
        }

        if (!TextUtils.isEmpty(realname)) {
            message.setAttribute("userName", realname);
        }
//        MyUserProvider.getInstance()
//                .setUser("测试","ceshi ");
    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
