package com.example.haa_roh.db
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.haa_roh.R
import com.example.haa_roh.base.BaseApplication.Companion.getContext
import com.example.haa_roh.bean.Bmob.Users

fun addUser(phone: String) {
    val user  = Users()
    user.photo =  getDefaultPhoto()
    user.username = getDefaultUserName(phone)
    user.phone = phone
    user.status = null
    user.save(object : SaveListener<String>(){
        override fun done(objectId: String?, e: BmobException?) {
            if(e == null){

            }else{

            }
        }
    })
}

fun getDefaultUserName(phone: String): String {
    return getContext().getString(R.string.userId)+phone
}
fun getDefaultPhoto(): BmobFile {
//    //1.找到BitmapFactory实例
//    val options = BitmapFactory.Options()
//
//    //2.设置inJustDecodeBounds参数为True
//    options.inJustDecodeBounds = true
//
//    //3.得到BitmapFactory加载进来的图片
//    val bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pohdefult, options)
    return BmobFile.createEmptyFile()
}

fun queryUser(phone: String): Boolean {
    val bmobQuery: BmobQuery<Users> = BmobQuery<Users>()
    return false
}