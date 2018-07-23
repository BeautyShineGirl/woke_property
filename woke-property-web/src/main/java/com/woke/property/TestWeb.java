package com.woke.property;


import com.woke.property.entity.MeterState;
import com.woke.property.entity.Student;
import com.woke.property.utils.ExcelRead;
import com.woke.property.utils.ExcelUtil;

import com.woke.property.utils.ExportExcel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Created by yangl on 2018/6/29.
 * Excel导入
 * Excel导出
 */
@RequestMapping(value = "test")
@RestController
public class TestWeb {
    @Autowired
    private TestService testService;

    private static final Logger logger = LoggerFactory.getLogger(TestWeb.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<MeterState> test(String  cat) {
        System.out.println(cat);
        logger.info("打印日志");
        return testService.test();
    }

    /**
     * Excel导入相关方法
     * 转到Excel上传页面
     *
     * @return
     * @author lp
     */
    @RequestMapping(value = "/read")
    public String addExcel() {
        return "baseInfo/testExcel";
    }

    /**
     * Excel导入，未进行格式验证，只验证了一行是否为空
     * 读取Excel数据到数据库
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     * @author lp
     */
    @RequestMapping(value = "/readExcel")
    public ModelAndView readExcel(@RequestParam(value = "excelFile") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView();
        //判断文件是否为空
        if (file == null) {
            mv.addObject("msg", "failed");
            mv.setViewName("excel_result");
            return mv;
        }
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if (name == null || ExcelUtil.EMPTY.equals(name) && size == 0) {
            mv.addObject("msg", "failed");
            mv.setViewName("excel_result");
            return mv;
        }
        //读取Excel数据到List中
        List<ArrayList<String>> list = new ExcelRead().readExcel(file);
        //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：
        MeterState meterState = null;
        List<MeterState> liseUser = new ArrayList<MeterState>();
        for (int i = 0; i < list.size(); i++) {
            if ("".equals(list.get(i).get(0)) || list.get(i).get(0).equals(null)) {
                continue;
            } else {
                int index = 0;
                meterState = new MeterState();
                if (list.get(i).get(index + 1).equals("meterTypeId")) {
                    meterState.setMeterTypeId(Integer.parseInt(list.get(i).get(index)));//每一行的第一个单元格
                }
                if (list.get(i).get(index + 3).equals("name")) {
                    meterState.setName(list.get(i).get(index + 2));
                }
                if (list.get(i).get(index + 5).equals("cValue")) {
                    meterState.setcValue(list.get(i).get(index + 4));
                }
                if (list.get(i).get(index + 7).equals("sValue")) {
                    meterState.setsValue(list.get(i).get(index + 6));
                }
                if (list.get(i).get(index + 9).equals("sType")) {
                    meterState.setsType(Integer.parseInt(list.get(i).get(index + 8)));
                }
                if (list.get(i).get(index + 11).equals("isUsing")) {
                    meterState.setUsing(Boolean.getBoolean(list.get(i).get(index + 10)));//每一行的第一个单元格
                }
               /*
                meterState= new MeterState();
                meterState.setMeterTypeId(Integer.parseInt(list.get(i).get(0)));//每一行的第一个单元格
                meterState.setName(list.get(i).get(1));
                meterState.setcValue(list.get(i).get(2));
                meterState.setsValue(list.get(i).get(3));
                meterState.setsType(Integer.parseInt(list.get(i).get(4)));
                meterState.setUsing( Boolean.getBoolean(list.get(i).get(5)));*/
                liseUser.add(meterState);
            }
        }

        int rows = testService.saveMeterState(liseUser);
        if (rows > 0) {
            mv.addObject("msg", "success");
        } else {
            mv.addObject("msg", "failed");
        }
        mv.setViewName("excel_result");

        return mv;
    }


    /**
     * 测试导出
     * @param args
     */
    public static void main(String[] args)
    {
        // 测试学生
        ExportExcel<Student> ex = new ExportExcel<Student>();
        String titlehead="学生信息表";
        String[] headers =
                { "学号", "姓名", "年龄", "性别", "出生日期" };
        Integer rows=4;
        List<Student> dataset = new ArrayList<Student>();
        dataset.add(new Student(10000001, "张三", 20, true, new Date()));
        dataset.add(new Student(20000002, "李四", 24, false, new Date()));
        dataset.add(new Student(30000003, "王五", 22, true, new Date()));

        try
        {
            OutputStream out = new FileOutputStream("E://a.xls");

           ex.exportExcel(titlehead,headers, dataset, out,rows);

            out.close();
           JOptionPane.showMessageDialog(null, "导出成功!");
            System.out.println("excel导出成功！");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
