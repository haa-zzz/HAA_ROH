package com.example.haa_roh.bean.Bmob

import cn.bmob.v3.BmobObject

/**
 * BMob的基类，需要集成自[BmobObject]
 */
class Users : BmobObject() {

    var id: String? = null
    var username: String? = null
    var phone : String? = null
    var photoBase64 : String? = null
    var status : String? = null
   // var photo: File? = null
}