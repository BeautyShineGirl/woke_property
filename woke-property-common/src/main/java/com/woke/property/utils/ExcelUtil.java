package com.woke.property.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Excel工具类
 * @author lp
 *
 */
public class ExcelUtil {
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String EMPTY = "";
	public static final String POINT = ".";
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * 获得path的后缀名
	 *
	 * @param path
	 * @return
	 */
	public static String getPostfix(String path) {
		if (path == null || EMPTY.equals(path.trim())) {
			return EMPTY;
		}
		if (path.contains(POINT)) {
			return path.substring(path.lastIndexOf(POINT) + 1, path.length());
		}
		return EMPTY;
	}

	/**
	 * 单元格格式
	 *
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings({"static-access", "deprecation"})
	public static String getHValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			String cellValue = "";
			if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
				Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
				cellValue = sdf.format(date);
			} else {
				DecimalFormat df = new DecimalFormat("#.##");
				cellValue = df.format(hssfCell.getNumericCellValue());
				String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
				if (strArr.equals("00")) {
					cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
				}
			}
			return cellValue;
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	/**
	 * 单元格格式
	 *
	 * @param xssfCell
	 * @return
	 */
	public static String getXValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String cellValue = "";
			if (XSSFDateUtil.isCellDateFormatted(xssfCell)) {
				Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());
				cellValue = sdf.format(date);
			} else {
				DecimalFormat df = new DecimalFormat("#.##");
				cellValue = df.format(xssfCell.getNumericCellValue());
				String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
				if (strArr.equals("00")) {
					cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
				}
			}
			return cellValue;
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	/**
	 * 自定义xssf日期工具类
	 *
	 * @author lp
	 */
	class XSSFDateUtil extends DateUtil {
		/*protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
			return DateUtil.absoluteDay(cal, use1904windowing);
		}*/
	}
	/**
	 * 自定义的个性化检验单元格格式
	 * 要求：
	 * 电话号码验证
	 * 邮箱验证
	 * 身份证号的验证
	 * 不为空验证
	 * @param list
	 * @return
	 */
	public String checkExcel(List<ArrayList<String>> list){
		String res=null;
		for (ArrayList<String> arr:list) {
			for (String arrString:arr) {
				//判断字段是否为空，要判断哪个字段，把哪个字段拿出来这里判断Excel表中的1,2,3,4,5列不能为空
				if("".equals(arr.get(0))||arr.get(0).equals(null)){
					System.out.println();
				}

			}
		}
		return res;
	}
	//=========================================================================================================================================


}