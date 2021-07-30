package com.example.haa_roh.bean.Bmob

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobFile
import java.io.File

class Users : BmobObject() {

    var username: String? = null
    var phone : String? = null
    var photo : BmobFile? = null
    var status : String? = null
    var id: String? = null
   // var photo: File? = null
}