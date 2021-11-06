package com.example.haa_roh.db

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
import com.example.haa_roh.bean.Bmob.PlanAdapterBean
import com.example.haa_roh.bean.Bmob.Users
import com.example.haa_roh.bean.DataResult
import com.example.haa_roh.bean.ResultData
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.bean.room.PlanRoom
import com.example.haa_roh.db.room.addPlanToRoom
import com.example.haa_roh.db.room.changePlanToRoom
import com.example.haa_roh.db.room.deletePlanFromRoom
import com.example.haa_roh.util.getDefaultPhoto
import com.example.haa_roh.util.getDefaultUserName
import com.example.haa_roh.util.printLog

/**
 * BMob的添加用户
 *
 * 如果添加成功，把数据写进Room中，传送成功的标志
 * 如果添加失败，传输失败的标志
 *
 *
 * 遇到的问题：直接在done中把数据添加到Room中出现异常，因为 BMob的查询操作是异步线程,同样在这里面不知直接拿到数据，
 * 解决思路：通过LiVeData把数据传出，同时解决传数据和线程切换的问题
 */
fun addUser(phone: String, userData: MutableLiveData<DataResult>) {
    val user = Users()
    user.photoBase64 = getDefaultPhoto()
    user.username = getDefaultUserName(phone)
    user.phone = phone
    user.status = null
    user.save(object : SaveListener<String>() {
        override fun done(objectId: String?, e: BmobException?) {
            if (e == null) {
                val personalInformation = PersonalInformation(
                    number = phone,
                    username = user.username,
                    state = user.status,
                    photo = user.photoBase64,
                    id = objectId
                )
                userData.value =
                    DataResult(success = true, personalInformation = personalInformation)
            } else {
                userData.value = DataResult(error = e.toString())
            }
        }
    })
}

/**
 * BMob查询用户
 *
 * 首先在Room中查询，如果Room中查询不到，再调用这个方法区BMob中查询
 * 如果查询成功且有数据，直接把数据写进Room，并通过LiveData返回一个查询成功的标志
 * 如果查询成功单没数据，就调用注册方法进行注册
 * 如果查询失败，返回失败的标志
 */
fun queryUser(phone: String, userData: MutableLiveData<DataResult>) {
    val bMobQuery: BmobQuery<Users> = BmobQuery<Users>()
    bMobQuery.addWhereEqualTo("phone", phone)
    bMobQuery.setLimit(1)
    bMobQuery.findObjects(object : FindListener<Users>() {
        override fun done(list: MutableList<Users>?, e: BmobException?) {

            if (e == null) {
                if(!(list == null || list.isEmpty())){
                    val personalInformation = PersonalInformation(
                        number = phone,
                        username = list[0].username,
                        state = list[0].status,
                        photo = list[0].photoBase64,
                        id = list[0].objectId
                    )
                    userData.value =
                        DataResult(success = true, personalInformation = personalInformation)
                }else{
                    addUser(phone, userData)
                }
            } else {
                userData.value = DataResult(error = e.toString())
            }
        }
    })
}

/**
 * 向BMob中添加每日计划，表中包含 title,tag,phone(用于标识用户信息),content(内容)
 */
fun addPlanToBMob(planBean: PlanAdapterBean, result : MutableLiveData<ResultData>){
    planBean.save(object : SaveListener<String>(){
        override fun done(t: String?, e: BmobException?) {
            if (e == null) {
                val planRoom = PlanRoom(id = planBean.objectId, number = planBean.phone,title = planBean.title,tag = planBean.tag,
                    isFinish = planBean.isFinish,content = planBean.content)
                addPlanToRoom(planRoom)
                result.value = ResultData(success = true,planRoom = planRoom)
            } else {
                result.value = ResultData(error = e.toString())
            }
        }
    })
}

fun queryPlanBMob(phone : String){
    val bMobQuery: BmobQuery<PlanAdapterBean> = BmobQuery<PlanAdapterBean>()
    bMobQuery.addWhereEqualTo("phone", phone)
    bMobQuery.setLimit(50)
    bMobQuery.findObjects(object : FindListener<PlanAdapterBean>() {
        override fun done(list : MutableList<PlanAdapterBean>?, e: BmobException?) {
            if(list != null){
                //把BMob数据库中的数据添加到本地数据库
                for(planBean in list ){
                    val planRoom = PlanRoom(id = planBean.objectId,number = planBean.phone,title = planBean.title,tag = planBean.tag,
                        isFinish = planBean.isFinish,content = planBean.content)
                    addPlanToRoom(planRoom)
                }
            }
        }
    })
}
//修改数据
fun setPlanBMob(id : String,planBean: PlanAdapterBean, result : MutableLiveData<ResultData>){
    planBean.update(id,object : UpdateListener(){
        override fun done(e: BmobException?) {
            if(e == null){
                result.value = ResultData(success = true)
            }else{
                result.value = ResultData(error = e.toString())
            }
        }
    })
}
fun deletePlanBMob(id : String , result : MutableLiveData<ResultData>){
    val planBean = PlanAdapterBean()
  //  planBean.objectId = id
    printLog(id)
    planBean.delete(id , object : UpdateListener(){
        override fun done(e: BmobException?) {
            if(e == null){
                deletePlanFromRoom(id)
                result.value = ResultData(success = true)
            }else{
                result.value = ResultData(error = e.toString())
            }
        }
    })
}