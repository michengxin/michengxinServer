package org.springboot.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.config.SupperMapper;
import org.springboot.entity.*;


import java.util.List;

@Mapper
public interface UserDao extends SupperMapper<User> {
       List<User> selectUserByWorkNum();
       int selectPhone(@Param("id") String id);
       User getUserInfo(@Param("username") String username,@Param("userType") String userType);
       User selectGetUserInfo(@Param("userId") String userId);
       List<Enterprise> selectEnterpriseByUserId(@Param("userId") String userId);
       List<Department> selectDepartmentsByUserId(@Param("userId") String userId);
       List<Role> selectRolesByUserId(@Param("userId") String userId);
       List<Permission> selectPermissionMenusByRoles(@Param("roles") List<Role> roles);
       List<Permission> selectPermissionButtonsByRoles(@Param("roles") List<Role> roles);
}
