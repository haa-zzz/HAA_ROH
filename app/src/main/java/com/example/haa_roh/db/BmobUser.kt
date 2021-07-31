package com.example.haa_roh.db
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.example.haa_roh.R
import com.example.haa_roh.base.BaseApplication.Companion.getContext
import com.example.haa_roh.bean.Bmob.Users
import com.example.haa_roh.bean.CreateUserResult
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.util.base64Encode

fun addUser(phone: String, createUser : MutableLiveData<CreateUserResult>){
    val user  = Users()
    user.photoBase64 =  getDefaultPhoto()
    user.username = getDefaultUserName(phone)
    user.phone = phone
    user.status = null
    user.save(object : SaveListener<String>(){
        override fun done(objectId: String?, e: BmobException?) {
            if(e == null){
                val personalInformation = PersonalInformation(
                    number = user.phone,
                    username = user.username,
                    state = user.status,
                    photo = user.photoBase64,
                    id = objectId
                )
                createUser.value = CreateUserResult(success = true,personalInformation = personalInformation)
            }else{
                createUser.value = CreateUserResult(error = e.toString())
            }
        }
    })
}
fun getDefaultUserName(phone: String): String {
    return getContext().getString(R.string.userId)+phone
}
fun getDefaultPhoto(): String {
    //1.找到BitmapFactory实例
    val options = BitmapFactory.Options()
    //2.设置inJustDecodeBounds参数为True
    options.inJustDecodeBounds = true
    //3.得到BitmapFactory加载进来的图片
    val bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pohdefult, options)
    return base64Encode(bitmap)
}

fun queryUser(phone: String , createUser : MutableLiveData<CreateUserResult>) {
    val bMobQuery: BmobQuery<Users> = BmobQuery<Users>()
    bMobQuery.addWhereEqualTo("phone",phone)
    bMobQuery.setLimit(1)
    bMobQuery.findObjects(object : FindListener<Users>(){
        override fun done(list : MutableList<Users>?, e: BmobException?) {
              if(e == null){
                  if(list == null || list.size == 0){
                      addUser(phone,createUser)
                  }else{
                      val personalInformation = PersonalInformation(
                          number = list[0].phone,
                          username = list[0].username,
                          state = list[0].status,
                          photo = list[0].photoBase64,
                          id = list[0].id
                      )
                      createUser.value = CreateUserResult(success = true,personalInformation = personalInformation)
                  }
              }else{
                  createUser.value = CreateUserResult(error = e.toString())
              }
        }
    })
}