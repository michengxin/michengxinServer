package org.springboot.config.properties;

/**
 * @ClassName CodeConstants
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/12 14:32
 * @Version 1.0
 */
public interface CodeConstants {
    Integer USER_LOGIN_ERROR = 710;//用户登录错误码
    Integer USER_INSERT_ERROR = 711;//用户保存错误码
    Integer USER_UPDATE_ERROR = 712;//用户修改错误码
    Integer USER_CODE_EXIST_ERROR = 711;//用户工号已存在
    Integer USER_NOT_EXIST_ERROR = 704;//用户不存在错误码
    Integer USER_UPDATE_PASSWORD_ERROR = 704;//密码修改错误码
    Integer ROLE_INSERT_ERROR = 751;//角色保存错误码
    Integer ROLE_UPDATE_ERROR = 752;//角色修改错误码
    Integer ROLE_NOT_EXIST_ERROR = 754;//角色不存在错误码
    Integer DICTIONARY_INSERT_ERROR = 770;//字典类别保存错误码
    Integer DICTIONARY_DELETE_ERROR = 771;//字典类别删除错误码
    Integer DICTIONARY_UPDATE_ERROR = 772;//字典类别修改错误码
    Integer PROJECT_INFO_ERROR = 800;//项目信息相关错误码
    Integer BUILDING_INFO_ERROR = 801;//楼栋信息相关错误码
    Integer UPLOAD_ERROR =773; //导入错误
    Integer EXPORT_ERROR=774;//导出错误
    Integer DEPARTMENT_UPDATE_ERROR=775;//部门重复
    Integer PARAM_ERROR=776;//参数错误
    Integer INSERT_ERROR=777;//插入错误
    Integer OTHER_ERROR=999;//其他错误
    Integer USER_IMPORT_ERROR=756;//其他错误
    Integer UPLOADING_FILE_ERROR=730;//上传文件错误
    Integer STAFF_PROMOTION_ERROR = 716;//业代晋升考核降级错误提示


    String SUCCESS_MSG = "操作成功";
    String ERROR_MSG = "操作失败";
    String EXISTING_CUSTOMERS = "该用户已存在这个客户";
    String NO_CUSTOMERS = "该用户不存在这个客户";
    String SHIFT_TO_SEA_MSG = "分享客户，无法转入公海，请重新勾选";


}