package com.ztkj.wky.zhuantou.base;

public class Contents {
    //后台域名
    public static final String BASE = "https://api.zhuantoukj.com/birck/index.php/Home/";
    //当前版本
    public static String localVersion = " ";
    //支付宝业务
    public static final String ALI_ID = "2019101568416015";
    // 支付宝私钥
    public static final String RSA2_PRIVATE = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCiGOiR/feH0jPeESMjAN8zpovXVjG6ib0ObMsSeDbg7szv29l5r6UYSOkXq2XsjsHg8+Gvo9MWZ85lztshn2mb6H+nxGNvEzPYPz0l9p+LB4+kswh4CItAPWIddRiTzbnKkx7Sw/ko07dATWFKs9nJy5oxmWzXYsm216ad0dxfz+vr3ptb2Q/pL96G9D0lYI6gWRcTUQuCfXn31J+rC7es57q6/DLykMNQEHy+filUk5aWAX4iWOiwG0nND9B7Jv0JR/a8bhmPem3+m7TssItXf2hU0lZ55N/2U39csfKkha/JZ+k9umGIu1FkBjOMPMaFVBy8364/ShR5U0RH7+iRAgMBAAECggEBAIxjdkm6439GlpVl0kCmqKlbPRIrHLaqRIsRvm8onp8BM40po5i4fwwhWX+eeo/+s/vYPUaZZGCrVx7loPPl1Nmq66SpelThFK3dV2Bh0Jvg2I+UX2lWPQROJYPNvpOEQb0o3jE8ebbaZoPSgMjqK12ScOE0OAfzDzDEZRGaiHqRgGWiXsmNn/1BQ5N7CMRFVVyXbbjVamGizzU5CVBP2P0QgkQ9/wIOEo6cAh71y9tJaxvz46EAj94R/xyH7FJiMkszT6vg7Ugdh5pEmLbYxq5uFsAeuwuwKTRGALaQRo2zpsLE6IjF98r6wO/iJ6k0lUJKCQkYDne8mHHcFO/XBeECgYEA1BVMopNCJ1JZJHxkHaaS3AgGKg/GMMgGCg7VYQ1iQ6g87dNWwWz5TE7jm8JjMw+tUNhU/wo3etg3cbAJoCjk3p7sqp648qQOUrKlJ6O2lciyzMXGyrFuj4lvgBJW3Q2QBeLkxqHbvqmEQ0hMuOmBUNdnQfTsJwH8DKbQOZEcG68CgYEAw6nQ/Jcsdd9SRFI6Q/DnDkUiRvbmTqCSkRru3r2WywZ26vnCE+O0vhCEOr4nQM8kFnMY7ItSGXRLxbfkMiatnw8CKZbnTOi0F0rl1ST1yO2i+2Zkb+rC7gKrZgfaSRZj8gj0KhaXJ91vgWCprWM1kTcAwbnpGkYF1IVigGUoD78CgYEAszHj9KHUDYFozIlF1j++aqdk/fkiDmmToL/zzvMOCfiGsrQU4439/lnRuvO9+ZDPcjW3a17ojaVPA4IoP+kNqIRDl85QmsO/TmSfYyTSyzAu0xJw6yaMMj9/tIjKWDBzdmXFw4dTMn/svyTBnqO9nd7C4lndhwAIRXBVE67mPwUCgYA/5YMEGfJBBA7/lMLGUTODa3xIJHUNlE6I6Hy9bLiD0LahAbMpf6EUuJwV+uciuQKV+jxXcx/6hAoCpYz1+7+TQ/l2z3r1mMDHIoRv2MQTO9ePtd5y1f2WcHS8uy/D0nYNInNqEzqFoEC7xW9j2hQyOf4bRbUkCV36Cx0hxOcSTQKBgQCZQAKRyCGlk+jaatcsH6lyOp3/9jM3ewB+MfPmZE5wq2tpjKVV3+EVMbhOhInv5nNNpM37gUzEtvOINUFTXeCcOKx+1UZC3plEYwZCISQU+VOMd4qRJPPHhhhqTwGcSxbPmvc+uE4pqktkHXA86Qzh8QKCWF32KriHL9btfx3Qrw==";
    public static String strExit = "0";
    //日志小红点次数
    public static int reportReddotNum = 0;
    //打卡次数
    public static int num = 0;

    /**
     * 百度地图
     */
    public static double LONGITUDE = 0;//经度
    public static double LATITUDE = 0;//纬度
    public static String LOCATION = "0";//定位地点
    public static String NEARLOCATION = "0";//定位地点

    public static String WORKON = "0";//公司上班时间
    public static String WORKOFF = "0";//公司下班时间
    public static String ADDRESS = "0";//公司上班地点

    /**
     * 一些杂接口
     */
    //获取版本号
    public static final String UPDATEAPK = BASE + "User/androidVersion";
    //友情链接点击
    public static final String CLICKYOUQINGLIANJIE = BASE + "User/clicks";
    //验证码登录接口
    public static final String CODELOGIN = BASE + "Login/signInAPP";
    //发送验证码
    public static final String SENDCODE = BASE + "Shortmessage/send_code";
    //验证验证码
    public static final String CHECKCODE = BASE + "Shortmessage/Verification";
    //模糊查询公司
    public static final String LIKECOMPANY = BASE + "Company/likeCompany";


    /**
     * 文章接口
     */
    //文章外层
    public static final String ARTICLECLASS = BASE + "Article/articleClass";
    //Blank
    public static final String BLANK = BASE + "Article/articleList";


    /**
     * 我的
     */
    //绑定车牌号
    public static final String BINDINGCAENUM = BASE + "User/associatedVehicle";
    // 身份证照片上传
    public static final String UPDATEIDCAED = BASE + "User/addCard";
    //判断是否已经上传身份证
    public static final String ISUPDATEIDCAED = BASE + "User/selectCard";
    //添加身份证号
    public static final String ADDIDCARD = BASE + "User/addID";
    //PHP获取身份证信息
    public static final String PHPGETIDCARD = BASE + "User/idImages";
    //账单列表接口
    public static final String BILLLIST = BASE + "User/billList";
    //获取实名认证信息
    public static final String SHIMINGINFO = BASE + "User/selectID";
    //判断是否已经设置支付密码
    public static final String ISPAYPASSWORD = BASE + "User/pp";
    //验证老密码
    public static final String OLDPAYPASSWORD = BASE + "Bankcard/verificationPwd";
    //银行卡列表
    public static final String BANKCARLIST = BASE + "Bankcard/bankList";
    //修改支付密码
    public static final String RESETPAYWORD = BASE + "Bankcard/updatePwd";

    /**
     * 银行卡
     */
    //判断银行卡
    public static final String BANKCARINFO = BASE + "Bankcard/judgementBank";
    //银行预留手机号发送短信
    public static final String BANKCODE = BASE + "Bankcard/vercode";
    //绑定银行卡接口
    public static final String BANDINGBANKCARD = BASE + "Bankcard/verifi";
    //验证已绑定银行卡
    public static final String VERIFYBINDINGCODE = BASE + "Bankcard/verifiUpdate";


    /**
     * H5 URL
     */
    //樊登读书
    public static final String FDUrl = "https://card.dushu.io/generalize/welcome/welcome.html?r=skdtvt6gxavtyug9&py=1";
    //城乡市民卡
    public static final String CXSMKUrl = "https://shop43546330.youzan.com/v2/feature/Dtz0gI7ZJz";
    //车享家
    public static final String CARENJOYHOMEUrl = "https://shop43546330.youzan.com/wscshop/showcase/feature?alias=cnKYUqSiI1&banner_id=f.81948593~image_nav.3~1~KTq7RWPJ&reft=1569321637401&spm=f.81948593";
    //新租赁
    public static final String NEWLEASEUrl = "https://shop43546330.youzan.com/wscshop/showcase/feature?alias=8gPpLWcyPq&banner_id=f.81948593~image_nav.3~3~x1gO9kKK&reft=1569321760897&spm=f.81948593";
    //积分商城
    public static final String JFSHOPUrl = "https://shop43546330.youzan.com/wscump/pointstore/pointcenter?kdt_id=43354162";


    /**
     * 二維碼
     */
    //提交二维码审核信息
    public static final String SAVEEWMINFO = BASE + "User/userSaveInfo";

    /**
     * 团队
     */
    //获取团队规模表
    public static final String TEAMSCALE = BASE + "Company/size";
    //获取行业分类接口
    public static final String GRTGUIDE = BASE + "Company/industry";
    //公司通讯录
    public static final String COMPANYBOOK = BASE + "Company/mailList";
    //创建团队信息/修改团队名称
    public static final String CREATETEAM = BASE + "Company/createTeam";
    //上传营业执照
    public static final String UPDATEPICTURE = BASE + "Company/enterpriseCertificationImage";
    //判断是否已上传企业营业执照
    public static final String ISUPDATEPICTURE = BASE + "Company/businessLicense";
    //填写企业认证信息
    public static final String ENTERPRISE = BASE + "Company/enterpriseCertification";
    //上传企业logo
    public static final String UPDATEENTERPEISELOGO = BASE + "Company/teamLogo";
    //公司公告上传
    public static final String COMPANYANNOUNCEMENT = BASE + "Company/announcement";
    //获取公司公告
    public static final String GETCOMPANYANNOUNCEMENT = BASE + "Company/selectAnnouncement";

    /**
     * 企业服务
     */
    //企业服务列表
    public static final String SERVERLIST = BASE + "Article/enterpriseService";
    //企业服务商城商品
    public static final String SERVERSSHOP = BASE + "Article/enterpriseShop";


    /**
     * 日志
     */
    //查看全部日志接口
    public static final String ALLREPORT = BASE + "Company/journalList";
    //我提交的日志列表
    public static final String SUNMITREPORT = BASE + "Company/submissionList";
    //未读的日志列表
    public static final String RECEIVEREPORT = BASE + "Company/unreadList";
    //看日志详情接口
    public static final String REPORTDETAILS = BASE + "Company/lookJournal";

    //生成日报id
    public static final String DIDID = BASE + "Company/AddDid";
    //生成周报id
    public static final String WEEKID = BASE + "Company/AddWid";
    //生成月报id
    public static final String MONTHID = BASE + "Company/AddMid";
    //生成总结id
    public static final String SUMMARYID = BASE + "Company/AddTid";


    //填写日报信息
    public static final String DIDMESSAGE = BASE + "Company/addDaily";
    //填写周报信息
    public static final String WEEKMESSAGE = BASE + "Company/addWeekly";
    //填写月报信息
    public static final String MONTHMESSAGE = BASE + "Company/addMonthly";
    //填写总结信息
    public static final String SUMMARYMESSAGE = BASE + "Company/addSummary";

    //上传日报照片
    public static final String UPDATEDIDPIC = BASE + "Company/addDailyPhone";
    //上传周报照片
    public static final String UPDATEWEEKPIC = BASE + "Company/addIeeklyPhone";
    //上传月报照片
    public static final String UPDATEMONTHPIC = BASE + "Company/addMonthlyPhone";

    //上传日报文件
    public static final String UPDATEDIDFILE = BASE + "Company/addfile";
    //上传周报文件
    public static final String UPDATEWEEKFILE = BASE + "Company/addIeeklyPhone";
    //上传月报文件
    public static final String UPDATEMONTHFILE = BASE + "Company/addMonthlyPhone";
    //上传工作总结文件
    public static final String UPDATESUMMARYFILE = BASE + "Company/addSummaryFile";


    //查看会议
    public static final String LISTMEETING = BASE + "Company/minutesOfMeetingSelect";
    //添加会议
    public static final String ADDMEETING = BASE + "Company/minutesOfMeeting";
    //删除会议
    public static final String DELMEETING = BASE + "Company/minutesOfMeetingDelete";
    //修改会议
    public static final String UPDATEMEETING = BASE + "Company/minutesOfMeetingUpdate";


    //天气预报
    public static final String WEATHER = BASE + "User/weather";

    //解散团队
    public static final String BREAKTEAM = BASE + "Company/dissolutionTeam";

    /**
     * 申请
     */
    //请假申请
    public static final String ADDLEAVE = BASE + "Company/addLeave";
    //加班申请
    public static final String OVERTIME = BASE + "Company/overTime";
    //申请出差接口
    public static final String BUSINESSTRAVEL = BASE + "Company/businessTravel";
    //申请外勤接口
    public static final String PERSONNEL = BASE + "Company/fieldPersonnel";
    //申请外出接口
    public static final String GOOUT = BASE + "Company/goOut";
    //填写审批报销费用接口
    public static final String REIMBURSEMENT = BASE + "Company/reimbursement";
    //付款申请
    public static final String PAYMENT = BASE + "Company/payment";
    //用印申请接口
    public static final String SEAL = BASE + "Company/seal";
    //用车申请
    public static final String USECAR = BASE + "Company/vehicleUse";

    //查看用户自己提交的申请列表
    public static final String ALLAPPLY = BASE + "Company/approvalList";
    //申请列表-待审批的申请列表
    public static final String NOTAPPLY = BASE + "Company/approvalListNo";
    //申请列表-已审批的申请列表
    public static final String HAVEAPPLY = BASE + "Company/approvalListYes";
    //查看用户自己提交的申请列表详情
    public static final String ALLDETIALS = BASE + "Company/approvalInfo";


    //查看已审批列表的申请列表
    public static final String HAVEEXAME = BASE + "Company/yesApproval";
    //查看未审批列表的申请列表
    public static final String NOTEXAME = BASE + "Company/noApproval";
    //查看抄送我的列表的申请列表
    public static final String COPYMINE = BASE + "Company/copyMeList";
    //审批接口
    public static final String EXAMINE = BASE + "Company/saveApprova";
    //请求日志未读条数
    public static final String REPORTREDDOT = BASE + "Company/redJournal";
    /**
     * IM
     */
    //获取好友列表
    public static final String LISTFRIENDS = BASE + "IM/friendList";
    //搜索好友
    public static final String SEARCHFRIEND = BASE + "IM/selectFriend";
    //公司群列表
    public static final String LISTQUN = BASE + "IM/groupList";
    //添加好友
    public static final String AddFriend = BASE + "IM/addFriend";
    //删除好友
    public static final String DELFriend = BASE + "IM/deleteFriend";
    //创建团聊
    public static final String CREATEGROUP = BASE + "IM/addGroup";
    //修改群信息
    public static final String UPDATEGROUPINFO = BASE + "IM/updateGroupInfo";
    //获取待同意好友列表
    public static final String LISTAGREE = BASE + "IM/friendListAgree";
    //同意或拒绝好友
    public static final String AGREENEWFRIEND = BASE + "IM/friendAgree";


    /**
     * 日程
     */
    //添加日程
    public static final String ADDSCHEDULE = BASE + "Company/addSchedule";
    //查看日程
    public static final String SELECTSCHEDULE = BASE + "Company/selectSchedule";
    //修改日程
    public static final String SAVESCHEDULE = BASE + "Company/saveSchedule";
    //判断一个月有多少天
    public static final String WEEKDAY = BASE + "Company/weekDay";


    /**
     * 打卡
     */
    //三合一判断接口
    public static final String SANHEYIPANDUAN = BASE + "Company/judgingPosition";
    //获取公司打卡时间
    public static final String COMPANYPUNCHINTIME = BASE + "Company/selectPunchIn";
    //打卡
    public static final String PUNCHIN = BASE + "Company/punchIn";
    //外勤打卡
    public static final String PUNCHINOUTWORK = BASE + "Company/fieldCarding";
    //打卡人员通讯录
    public static final String PUNCHINADRESS = BASE + "Company/mailListw";
    //设置打卡人员
    public static final String NOPUNCHINPEOPLE = BASE + "Company/punchClock";
    //无需打卡人员通讯录
    public static final String NOPUNCHINADRESS = BASE + "Company/mailListwy";
    //创建或修改打卡信息
    public static final String AMENDPUNCHININFO = BASE + "Company/addPunchIn";
    //补卡申请接口
    public static final String APPLYREISSUEPUNCH = BASE + "Company/addPatchCard";
    //个人统计表
    public static final String MINESTATISTICS = BASE + "Company/statistics";
    //个人统计表详情
    public static final String MINESTATISTICSDETAILS = BASE + "Company/statisticsXq";
    //团队统计表
    public static final String TEAMSTATISTICS = BASE + "Company/teamStatistics";
    //一键恢复考勤状态（正常考勤）
    public static final String RESTOREATTENDANCENORMAL = BASE + "Company/recovery";
    //一键恢复考勤状态（外勤考勤）
    public static final String RESTOREATTENDANCEOUTWORK = BASE + "Company/recoveryFieldPersonnel";
    //考勤打卡记录
    public static final String PUNCHINLIST = BASE + "Company/punchInList";

}
