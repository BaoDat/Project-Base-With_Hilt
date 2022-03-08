package com.datdang.projectbase.data.api

object ApiMethod {
    const val REGISTER = "api/account/register"
    const val CONFIRM_CODE = "api/account/confirmActiveCode"
    const val FORGOT_PASSWORD = "api/account/forgotPassword"
    const val CONFIRM_FORGOT_PASSWORD = "api/account/confirmForgotPasswordCode"
    const val RESET_PASSWORD = "api/account/resetPassword"
    const val LOGIN = "api/account/login"
    const val GET_GROUPS = "api/group/getListGroups"
    const val CREATE_GROUP = "api/group/addGroup"
    const val GET_FRIENDS = "api/account/getFriends"
    const val SEARCH_FRIEND = "api/account/searchFriend"
    const val ADD_FRIEND = "api/account/addFriend"
}
