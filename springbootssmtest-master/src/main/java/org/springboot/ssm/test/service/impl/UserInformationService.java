package org.springboot.ssm.test.service.impl;

import org.springboot.ssm.test.dao.UserInformationDao;
import org.springboot.ssm.test.service.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInformationService implements IUserInformationService {

    @Autowired
    UserInformationDao userInformationDao;

    @Override
    public Object selectAll() {
        return userInformationDao.selectAll();
    }
}
