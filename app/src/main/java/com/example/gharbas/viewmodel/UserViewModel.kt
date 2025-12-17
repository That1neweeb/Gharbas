package com.example.gharbas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gharbas.model.UserModel
import com.example.gharbas.repository.UserRepo

class UserViewModel(val repo : UserRepo) : ViewModel() {
    fun login(email:String,password:String,callback:(Boolean, String) -> Unit){
        repo.login(email,password,callback)
    }

    fun forgetPassword(email : String,callback: (Boolean, String) -> Unit){
        repo.forgetPassword(email,callback)
    }

    fun deleteAccount(userId: String,callback: (Boolean, String) -> Unit) {
        repo.deleteAccount(userId,callback)

    }
    fun editProfile(userId: String, model: UserModel, callback: (Boolean, String) -> Unit){
        repo.editProfile(userId,model,callback  )
    }

    fun addUserToDatabase(userId: String, model : UserModel, callback: (Boolean, String) -> Unit){
            repo.addUserToDatabase(userId,model,callback)

    }
    fun register(email: String, password: String, callback: (Boolean, String, String) -> Unit){
            repo.register(email,password,callback)
    }

    private val _users = MutableLiveData<UserModel?>()
    val users : MutableLiveData<UserModel?> get() = _users

    private val _allUsers = MutableLiveData<List<UserModel>?>()
    val allUsers: MutableLiveData<List<UserModel>?> get() = _allUsers

    fun getAllUser(callback: (Boolean, String, List<UserModel>?) -> Unit){
        repo.getAllUser {
            success, message, data ->{
                if (success){
                    _allUsers.postValue(data)
                }
        }
        }
    }

    fun getUserById(userId: String, callback: (Boolean, String, UserModel?) -> Unit){
        repo.getUserById(userId) {
                success, message, data ->{
            if (success){
                _users.postValue(data)
            }
        }
        }
    }
}