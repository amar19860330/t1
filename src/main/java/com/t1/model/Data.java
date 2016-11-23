package com.t1.model;

/**
 * Created by amar on 16/9/15.
 */
public class Data {

    public static String Status_Ok = "ok";
    public static String Status_Error = "error";
    public static String Error_Miss_Params = "Error_Miss_Params";
    public static String Error_Inner = "Error_Inner";
    public static String Error_Password_Or_Username = "Error_Password_Or_Username";
    public static String Error_Exist_Username = "Error_Exist_Username";
    public static String Error_IsValidated = "Error_IsValidated";//已经验证过了
    public static String Error_User_Not_Exist = "Error_User_Not_Exist";
    public static String Error_User_Status = "Error_User_Status"; //用户状态异常错误
    public static String Error_Incorrect_Token = "Error_Incorrect_Token";
    public static String Error_Regist_Fail = "Error_Regist_Fail"; //注册几百
    public static String Error_ = "";

    private String status;
    private String message;
    private String info;

    @Override
    public String toString() {
        if (info == null)
            return "{\"status\":\"" + status + "\",\"message\":\"" + message + "\",\"info\":\"\"}";
        else
            return "{\"status\":\"" + status + "\",\"message\":\"" + message + "\",\"info\":" + info + "}";
    }


    public static Data OkData() {
        return OkData("");
    }

    public static Data OkData(String info) {
        return new Data(Status_Ok, "", info);
    }

    public static Data ErrorData(String errorStatus, String errorMessage) {
        return new Data(errorStatus, errorMessage, "");
    }

    public static Data ErrorData(String errorStatus) {
        return new Data(errorStatus, "", "");
    }

    public Data() {
    }

    public Data(String status, String message, String info) {
        this.status = status;
        this.message = message;
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
