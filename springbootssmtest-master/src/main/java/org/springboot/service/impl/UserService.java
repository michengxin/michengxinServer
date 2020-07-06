package org.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springboot.dto.UserDto;
import org.springboot.entity.*;
import org.springboot.dao.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
  @Resource
  UserDao userDao;

  public PageInfo<User> selectUserByWorkNum() {
    PageHelper.startPage(1,2);//这行是重点，表示从pageNum页开始，每页pageSize条数据
    List<User> list = userDao.selectUserByWorkNum();
    PageInfo<User> pageInfo = new PageInfo<User>(list);
    return pageInfo;
  }
  public String selectPhone(String id) {
    int count  = userDao.selectPhone(id);
    if(count>0){
      return "1";
    }else{
      return "0";
    }
  }

  public List<User> selectAllUser(){
    QueryWrapper wq = new QueryWrapper();
    return userDao.selectList(wq);
  }
  public List<Permission> selectView() {
    return userDao.selectView();

  }
  public int insertUser(User user){
    return userDao.insert(user);
  }

  public User checkUserId(String userId){
    QueryWrapper queryWrapper = new QueryWrapper();
    return  userDao.selectById(userId);
  }

  public UserDto selectGetUserInfo(String userId){
    //根据前端返回过来的token解析获取用户详细数据，获取该用户所属角色下的所有权限
    User user = userDao.selectGetUserInfo(userId);
    UserDto userDto = new UserDto();
    userDto.setUserCode(user.getUserCode());
    userDto.setUserType(user.getUserType());
    userDto.setName(user.getName());
    userDto.setEmail(user.getEmail());
    userDto.setPhone(user.getPhone());
    userDto.setDimissionTime(user.getDimissionTime());
    userDto.setEntryTime(user.getEntryTime());
    //公司list
    List<Enterprise> enterprises = userDao.selectEnterpriseByUserId(userId);
    userDto.setEnterprises(enterprises);
    //部门list
    List<Department> departments = userDao.selectDepartmentsByUserId(userId);
    userDto.setDepartments(departments);
    //角色list
    List<Role> roles = userDao.selectRolesByUserId(userId);
    userDto.setRoles(roles);
    //权限list(menu和button)
    List<Permission> permissionMenus = userDao.selectPermissionMenusByRoles(roles);
    //将所有父菜单的子级放在对应的sonPermissions集合中
    for(int i =0;i<permissionMenus.size();i++){
      for (int j =0;j<permissionMenus.size();j++
      ) {
         if(permissionMenus.get(i).getId().equals(permissionMenus.get(j).getParentId())){
           permissionMenus.get(i).getSonPermissions().add(permissionMenus.get(j));
         }
      }
    }
    for(int i =0;i<permissionMenus.size();i++){
      if(!permissionMenus.get(i).getName().equals("网页端")){
        permissionMenus.remove(i);
        i--;
      }
    }
    userDto.setPermissionMenus(permissionMenus);
    return userDto;
  }



}
