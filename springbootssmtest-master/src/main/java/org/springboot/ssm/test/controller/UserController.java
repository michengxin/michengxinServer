package org.springboot.ssm.test.controller;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springboot.ssm.test.entity.User;
import org.springboot.ssm.test.service.IUserService;
import org.springboot.ssm.test.service.impl.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@CrossOrigin
@Controller
public class UserController {

  @Autowired
  IUserService userService;
    @Autowired
    UserService2 userService2;
    //
    @RequestMapping("/randomOfEightPeople")
    @ResponseBody
    public List<User> randomOfEightPeople(){
        List<User> list =  userService2.randomOfPeople();
        return list;
    }
  //初始化登录页面
  @RequestMapping("/initLogin")
  @ResponseBody
  public String initLogin(String workNum,String telphone){
    return userService.initLogin(workNum,telphone);
  }

    //初始化登录页面
    @RequestMapping("/initLogin1")
    @ResponseBody
    public String initLogin1(){
        return "1";
    }
  //用户登录
  @RequestMapping("/login")
  @ResponseBody
  public String login(String workNum,String telphone){
    return userService.login(workNum,telphone);
  }

  //点击抽奖按钮
  @RequestMapping("/lotteryDraw")
  @ResponseBody
  public String lotteryDraw(String workNum){
    return userService.lotteryDraw(workNum);
  }
 //分配特等奖
 @RequestMapping("/distributionOfFirstPrize")
 @ResponseBody
 public String distributionOfFirstPrize(){
   return userService.distributionOfFirstPrize();
 }
  //分配一等奖
  @RequestMapping("/distributionOfTwoPrize")
  @ResponseBody
  public String distributionOfTwoPrize(){
    return userService.distributionOfTwoPrize();
  }
  //分配二等奖
  @RequestMapping("/distributionOfThreePrize")
  @ResponseBody
  public String distributionOfThreePrize(){
    return userService.distributionOfThreePrize();
  }
  //分配三等奖
  @RequestMapping("/distributionOfFourPrize")
  @ResponseBody
  public String distributionOfFourPrize(){
    return userService.distributionOfFourPrize();
  }

  //用户数据导入
  @PostMapping("importDesignExecl")
  @ResponseBody
  public String importDesignExecl(@RequestParam("file") MultipartFile file) throws Exception {
      InputStream inputStream = file.getInputStream();
      // 获取文件名
      String fileName = file.getOriginalFilename();
      // 获取文件后缀
      String prefix = fileName.substring(fileName.lastIndexOf("."));
      // 业务逻辑
      Workbook wookbook = null;
      try {
          // 2003版本的excel，用.xls结尾
          wookbook = new HSSFWorkbook(inputStream);// 得到工作簿
      } catch (Exception e) {
          try {
              // 2007版本的excel，用.xlsx结尾
              wookbook = new XSSFWorkbook(inputStream);// 得到工作簿
          } catch (IOException q) {
              // TODO Auto-generated catch block
              q.printStackTrace();
          }
      }
      // 得到一个工作表
      Sheet sheet = wookbook.getSheetAt(0);
      // 获得表头
      Row rowHead = sheet.getRow(0);
      // 获得数据的总行数
      int totalRowNum = sheet.getLastRowNum();

      List<User> projectList = new ArrayList<User>();
      String result = null;
      User project;
      // 存储表头的集合
      List<String> rowHeadList = new ArrayList<String>();
      SimpleDateFormat aDate = new SimpleDateFormat("yyyy-mm-dd");
      for (int i = 0; i < rowHead.getPhysicalNumberOfCells(); i++) {
          rowHead.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
          rowHeadList.add(rowHead.getCell(i).getStringCellValue());
      }
      for (int r = 1; r <= sheet.getLastRowNum(); r++) {
          // 获取一行数据
          Row row = sheet.getRow(r);
          project = new User();
          project.setId( UUID.randomUUID().toString().replaceAll("-",""));
          for (int i = 0; i < rowHead.getPhysicalNumberOfCells(); i++) {
              row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
              if (rowHeadList.get(i).equals("工号")) {
                  project.setWorkNum(row.getCell(i).getStringCellValue());
              } else if (rowHeadList.get(i).equals("手机号码")) {
                  project.setTelphone(row.getCell(i).getStringCellValue());
              } else if (rowHeadList.get(i).equals("姓名")) {
                  project.setName(row.getCell(i).getStringCellValue());
              }
              }
          projectList.add(project);
          }
      userService.importProjectList(projectList);
      return "true";
  }
}
