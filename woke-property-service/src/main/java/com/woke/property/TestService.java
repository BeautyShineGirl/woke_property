package com.woke.property;


import com.woke.property.dao.TestDao;
import com.woke.property.entity.MeterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangl on 2018/6/29.
 */
@Service(value="testService")
public class TestService {
    @Autowired
    private TestDao testDao;
    public List<MeterState> test() {
        return testDao.getMeterState();
    }
    public int saveMeterState(List<MeterState> liseUser){
         int count=0;
     for (int i=0;i<liseUser.size();i++) {

          count= testDao.saveMeterState(liseUser.get(i));
     }
        return count;
    }
}
