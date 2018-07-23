package com.woke.property.dao;


import com.woke.property.entity.MeterState;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangl on 2018/6/29.
 */
@Mapper
@Repository
public  interface TestDao {
    @Select("SELECT * FROM tb_meter_state")
    List<MeterState> getMeterState();


    @Insert("insert into tb_meter_state (METERTYPEID,NAME,CVALUE,SVALUE,STYPE,ISUSING)\n" +
            "value(#{meterTypeId},#{name},#{cValue},#{sValue},#{sType},#{isUsing})")
    int saveMeterState(MeterState meterState);
}
