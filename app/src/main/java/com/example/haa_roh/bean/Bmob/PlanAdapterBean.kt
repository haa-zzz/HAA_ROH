package com.example.haa_roh.bean.Bmob

import cn.bmob.v3.BmobObject

class PlanAdapterBean(
    var title: String? = null,
    var tag: String? = null,
    var isFinish : Boolean? = false,
    var phone : String? = null,
    var content: String? = null,
) : BmobObject()