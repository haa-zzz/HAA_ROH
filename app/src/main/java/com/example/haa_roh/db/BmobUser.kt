package com.example.haa_roh.db
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.example.haa_roh.bean.Bmob.Users
import com.example.haa_roh.bean.DataResult
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.util.LOGGED
import com.example.haa_roh.util.addDataToRoom
import com.example.haa_roh.util.getDefaultPhoto
import com.example.haa_roh.util.getDefaultUserName

/**
 * BMob的添加用户
 *
 * 如果添加成功，把数据写进Room中，传送成功的标志
 * 如果添加失败，传输失败的标志
 */
fun addUser(phone: String, userData : MutableLiveData<DataResult>){
    val user  = Users()
    user.photoBase64 =  getDefaultPhoto()
    user.username = getDefaultUserName(phone)
    user.phone = phone
    user.status = null
    user.save(object : SaveListener<String>(){
        override fun done(objectId: String?, e: BmobException?) {
            if(e == null){
                val personalInformation = PersonalInformation(
                    number = phone,
                    username = user.username,
                    state = user.status,
                    photo = user.photoBase64,
                    id = objectId
                )
                userData.value = DataResult(success = true,personalInformation = personalInformation)
            }else{
                LOGGED("从BMob添加数据就直接给返回错误了  ${e.errorCode}     ${e.toString()}")
                userData.value = DataResult(error = e.toString())
            }
        }
    })
}

/**
 * BMob查询用户
 *
 * 首先在Room中查询，如果Room中查询不到，再调用这个方法区BMob中查询
 * 如果查询成功，但是没有数据，说明是新用户，需要调用[addUser]去添加新用户
 * 如果查询成功且有数据，直接把数据写进Room，并通过LiveData返回一个查询成功的标志
 * 如果查询失败，通过LiveData返回一个查询失败的标志
 */
fun queryUser(phone: String, userData : MutableLiveData<DataResult>) {
    LOGGED("开始查询数据了")
    val bMobQuery: BmobQuery<Users> = BmobQuery<Users>()
    bMobQuery.addWhereEqualTo("phone",phone)
    bMobQuery.setLimit(1)
    bMobQuery.findObjects(object : FindListener<Users>(){
        override fun done(list: MutableList<Users>?, e: BmobException?) {
            if(e == null){
                if(list == null || list.size == 0){
                    LOGGED("从BMob中查询数据了，是空的")
                    addUser(phone,userData)
                }else{
                    val personalInformation = PersonalInformation(
                        number = phone,
                        username = list[0].username,
                        state = list[0].status,
                        photo = list[0].photoBase64,
                        id = list[0].objectId
                    )
                    userData.value = DataResult(success = true,personalInformation = personalInformation)
                }
            }else{
                LOGGED("从BMob查询数据就直接给返回错误了")
                userData.value = DataResult(error = e.toString())
            }
        }

    })
}
