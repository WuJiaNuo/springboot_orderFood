package edu.gdbtu.lzq.system.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import edu.gdbtu.lzq.system.exception.LanfException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ExcelHandler {

    /**
     * 导入简单excel数据
     * @param file ：文件流
     * @param clazz：数据对象
     * @param sheetName：要读取的sheet [不传：默认读取第一个sheet]
     * @throws Exception
     */
    public <T> List<T> importExcel(MultipartFile file, Class<T> clazz, String sheetName) throws Exception{
        this.checkFile(file);
        UploadDataListener<T> uploadDataListener = new UploadDataListener<>();
        ExcelReaderBuilder builder = EasyExcelFactory.read(file.getInputStream(), clazz, uploadDataListener);
        if (StringUtils.isEmpty(sheetName)) {
            builder.sheet().doRead();
        } else {
            builder.sheet(sheetName).doRead();
        }
        return uploadDataListener.getList();
    }
    /**
     * 指定sheet页导入通用方法
     * @param multipartFile 传入文件
     * @param objList 需要导入的sheet页实体类型集合
     * @param index sheet页个数
     * @param indexList 需要导入sheet页下标集合
     * @param <T>
     * @return <T> List<List<T>>
     * @throws Exception
     */
    public <T> List<List<T>> importExcelsByIndex(MultipartFile multipartFile, List<T> objList, int index,List<Integer> indexList) throws Exception {
        if (multipartFile == null) {
            throw new LanfException(9002,"文件为空");
        }
        List<List<T>> resultList = new LinkedList<>();
        //初始化导入sheet页实体类型下标
        int objListClass = 0;
        for (int i = 0; i < index; i++) {
            if(indexList.contains(i)){
                UploadDataListener<T> uploadDataListener = new UploadDataListener<>();
                List<T> excels;
                EasyExcelFactory.read(multipartFile.getInputStream(), objList.get(objListClass).getClass(), uploadDataListener).sheet(i).doRead();
                excels = uploadDataListener.getList();
                resultList.add(excels);
                objListClass++;
            }
        }
        return resultList;
    }
    /**
     * 读取多个sheet
     * @param file：文件流
     * @param index：需要读取的sheet个数 [默认0开始，如果传入3，则读取0 1 2]
     * @param params：每个sheet里面需要封装的对象[如果index为3，则需要传入对应的3个对象]
     * @param <T>
     * @return
     */
    public <T> List<List<T>> importExcels(MultipartFile file, int index, List<Object> params) throws Exception {
        this.checkFile(file);
        List<List<T>> resultList = new LinkedList<>();
        for (int i=0; i<index; i++) {
            List<T> list = null;
            try {
                UploadDataListener<T> uploadDataListener = new UploadDataListener<>();
                ExcelReaderBuilder builder = EasyExcelFactory.read(file.getInputStream(), params.get(i).getClass(), uploadDataListener);
                builder.sheet(i).doRead();
                list = uploadDataListener.getList();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(list != null){
                    resultList.add(list);
                }
            }

        }
        return resultList;
    }

    /**
     * 导出excel表格
     * @param response ：
     * @param dataList ：数据列表
     * @param clazz    ：数据对象
     * @param fileName ：文件名称
     * @param sheetName：sheet名称
     * @throws Exception
     */
    public <T> void exportExcel(HttpServletResponse response, List<T> dataList, Class<T> clazz, String fileName, String sheetName) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc());
        EasyExcelFactory.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(dataList);
    }

    /**
     * 导出多个sheet
     * @param response：
     * @param dataList：多个数据列表
     * @param clazzMap：对应每个列表里面的数据对应的sheet名称
     * @param fileName：文件名
     * @param <T>
     * @throws Exception
     */
    public <T> void exportExcels(HttpServletResponse response, List<List<?>> dataList, Map<Integer, String> clazzMap, String fileName) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc());
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        int len = dataList.get(0).size();
        for (int i=0; i<len; i++) {
            List<?> objects = (List<?>) dataList.get(0).get(i);
            Class<?> aClass = objects.get(0).getClass();
            WriteSheet writeSheet0 = EasyExcel.writerSheet(i, clazzMap.get(i)).head(aClass).build();
            excelWriter.write(objects, writeSheet0);
        }
        excelWriter.finish();
    }

    /**
     * 根据模板将集合对象填充表格-单个sheet
     * @param list：填充对象集合
     * @param object ：填充对象
     * @param fileName：文件名称
     * @param templateName：模板名称
     * @throws Exception
     */
    public <T> void exportTemplateExcels(HttpServletResponse response, List<T> list, Object object, String fileName, String templateName) throws Exception{
        String template = ExcelTemplateEnum.TEMPLATE_PATH.getDesc() + File.separator + templateName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(template);
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        ExcelWriter excelWriter = EasyExcelFactory.write(getOutputStream(fileName, response)).withTemplate(inputStream).build();
        WriteSheet writeSheet0 = EasyExcelFactory.writerSheet(0).build();
        excelWriter.fill(object, fillConfig, writeSheet0);
        excelWriter.fill(list, fillConfig, writeSheet0);
        excelWriter.finish();
    }

    /**
     * 根据模板将集合对象填充表格-多个sheet
     * @param list1：填充对象集合
     * @param list2：填充对象集合
     * @param object1 ：填充对象
     * @param object2 ：填充对象
     * @param fileName：文件名称
     * @param templateName：模板名称
     * @throws Exception
     */
    public <T> void exportSheetTemplateExcels(HttpServletResponse response, List<T> list1,List<T> list2, Object object1,Object object2, String fileName, String templateName) throws Exception{
        String template = ExcelTemplateEnum.TEMPLATE_PATH.getDesc() + File.separator + templateName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(template);
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        ExcelWriter excelWriter = EasyExcelFactory.write(getOutputStream(fileName, response)).withTemplate(inputStream).build();
        WriteSheet writeSheet0 = EasyExcelFactory.writerSheet(0).build();
        WriteSheet writeSheet1 = EasyExcelFactory.writerSheet(1).build();
        excelWriter.fill(object1, fillConfig, writeSheet0);
        excelWriter.fill(list1, fillConfig, writeSheet0);
        excelWriter.fill(object2, fillConfig, writeSheet1);
        excelWriter.fill(list2, fillConfig, writeSheet1);
        excelWriter.finish();
    }

    /**
     * 根据模板将单个对象填充表格
     * @param object       ：填充对象
     * @param templateName：模板名称
     * @param fileName    ：文件名称
     * @param sheetName   ：需要写入的sheet名称 [不传：填充到第一个sheet]
     * @throws Exception
     */
    public void exportTemplateExcel(HttpServletResponse response, Object object, String templateName, String fileName, String sheetName) throws Exception{
        String template = ExcelTemplateEnum.TEMPLATE_PATH.getDesc() + File.separator + templateName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(template);
        if (StringUtils.isEmpty(sheetName)) {
            EasyExcelFactory.write(getOutputStream(fileName, response)).withTemplate(inputStream).sheet().doFill(object);
        } else {
            EasyExcelFactory.write(getOutputStream(fileName, response)).withTemplate(inputStream).sheet(sheetName).doFill(object);
        }
    }

    /**
     * 根据模板将集合对象填充表格
     * @param list：填充对象集合
     * @param fileName：文件名称
     * @param templateName：模板名称
     * @param sheetName：需要写入的sheet [不传：填充到第一个sheet]
     * @throws Exception
     */
    public <T> void exportTemplateExcelList(HttpServletResponse response, List<T> list, String fileName, String templateName, String sheetName) throws Exception{
        log.info("模板名称：{}", templateName);
        String template = ExcelTemplateEnum.TEMPLATE_PATH.getDesc() + File.separator + templateName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc();
        log.info("模板路径：{}", template);
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(template);
        // 全部填充：全部加载到内存中一次填充
        if (StringUtils.isEmpty(sheetName)) {
            EasyExcelFactory.write(getOutputStream(fileName, response)).withTemplate(inputStream).sheet().doFill(list);
        } else {
            EasyExcelFactory.write(getOutputStream(fileName, response)).withTemplate(inputStream).sheet(sheetName).doFill(list);
        }
    }
    /**
     * 根据模板将集合对象填充表格
     * @param list：填充对象集合
     * @param fileName：文件名称
     * @param templateName：模板名称
     * @throws Exception
     */
    public <T> void exportTemplateExcel2(HttpServletResponse response, List<T> list, String fileName, String templateName) throws Exception{
        String template = ExcelTemplateEnum.TEMPLATE_PATH.getDesc() + File.separator + templateName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(template);
        ExcelWriter excelWriter = EasyExcelFactory.write(getOutputStream(fileName, response)).withTemplate(inputStream).build();
        WriteSheet writeSheet = EasyExcelFactory.writerSheet().build();
        excelWriter.fill(list, writeSheet);
        excelWriter.finish();
    }
    /**
     * 构建输出流
     * @param fileName：文件名称
     * @param response：
     * @return
     * @throws Exception
     */
    private OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc());
        return response.getOutputStream();
    }

    /**
     * 文件格式校验
     * @param file：
     */
    private void checkFile(MultipartFile file) {
        if (file == null) {
            throw new LanfException(9002,"文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            throw new LanfException(9002,"文件不能为空");
        }
        if (!fileName.endsWith(ExcelTemplateEnum.TEMPLATE_SUFFIX.getDesc())
                && !fileName.endsWith(ExcelTemplateEnum.TEMPLATE_SUFFIX_XLS.getDesc())) {
            throw new LanfException(9003,"请上传.xlsx或.xls文件");
        }
    }

}
