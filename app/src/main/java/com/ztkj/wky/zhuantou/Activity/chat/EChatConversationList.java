package com.ztkj.wky.zhuantou.Activity.chat;

/**
 * item会话列表
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.widget.EaseTitleBar;
import com.ztkj.wky.zhuantou.R;

public class EChatConversationList extends AppCompatActivity implements EaseConversationListFragment.finishActivityListen {
    private EaseTitleBar titleBar;
    private String TAG = "EChatConversationList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echat_conversation_list);

        EaseConversationListFragment easeConversationListFragment = new EaseConversationListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_chat_conversation, easeConversationListFragment).commit();

//        titleBar = (EaseTitleBar) getView().findViewById(R.id.title_bar);
//        titleBar.setBackgroundColor(Color.parseColor("#65C9D2"));


        easeConversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent = new Intent(EChatConversationList.this, ECChatAcitivty.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, conversation.getType());
                Log.e(TAG, "onListItemClicked: " + "====================================" + conversation.getType() + "===" + conversation.conversationId());

                startActivity(intent);
            }
        });
    }

    @Override
    public void finishActivty() {
        finish();
    }
}
