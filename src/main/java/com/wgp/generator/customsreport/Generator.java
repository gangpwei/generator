package com.wgp.generator.customsreport;


import com.wgp.utils.CamelCaseUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 海关报文生成器
 * User: weigangpeng
 * Date: 2015-07-21
 * Time: 下午2:16
 */
public class Generator {

    public static void generator(String filePath){
        readFileByLines(filePath);

    }

    public static void readFileByLines(String fileName) {
        List<MsgField> fields = new ArrayList<MsgField>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println( tempString);
//                if(tempString.contains("NUMBER") && tempString.indexOf("(") >0){
//                    tempString = tempString.replace(tempString.)
//                }
                String[] strings = tempString.split(",");

                MsgField  field= new MsgField(strings[1], strings[2], strings[3], strings[4].equals("是"), strings.length>6? strings[5] : null);
                fields.add( field);
                System.out.println(field);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        toObj(fields);
    }
    static String TAB = "    ";
    static String WARP = "\n";

//    /**
//     * 电商海关备案编号(必填) 英文字符+数字
//     */
//    @NotBlank
//    @FieldMeta(testValue = "TOC001")
//    @Length(max = 10)
//    private String idType;

    public static void toObj(List<MsgField> fields){

        for (MsgField field : fields) {

            StringBuffer sb = new StringBuffer();
            sb.append(TAB).append("/**").append(WARP);
            sb.append(TAB).append("* ").append(field.getComment()).append(field.isRequired() ? "（必填）" : "") .append(WARP);
            sb.append(TAB).append("*/").append(WARP);
            if(field.isRequired()){
                sb.append(TAB).append("@NotBlank").append(WARP);
            }

            if(field.isNumber()){
                String type = field.getType();
//                if(type.indexOf("(") >0){
//                    String lengthStr = type.substring(type.indexOf("(")+1,  type.indexOf(")"));
//                    int length = Integer.valueOf(lengthStr);
//                    sb.append(TAB).append("@Length(max = "+length+")").append(WARP);
//                }
                sb.append(TAB).append("private double ").append(CamelCaseUtils.toCamelCase(field.getField())).append(";").append(WARP);
            }else{
                String type = field.getType();
                if(type.indexOf("(") >0){
                    String lengthStr = type.substring(type.indexOf("(")+1,  type.indexOf(")"));
                    int length = Integer.valueOf(lengthStr);
                    sb.append(TAB).append("@Length(max = "+length+")").append(WARP);
                }
                sb.append(TAB).append("private String ").append(CamelCaseUtils.toCamelCase(field.getField())).append(";").append(WARP);
            }

            System.out.println(sb);
        }

//    public void setCollectionUserCountry(String collectionUserCountry) {
//        this.collectionUserCountry = collectionUserCountry;
//    }
//
//    @XmlElement(name = "COLLECTIONUSERNAME")
//    public String getCollectionUserName() {
//        return collectionUserName;
//    }
        //getter setter
        for (MsgField field : fields) {
            String fieldStr = CamelCaseUtils.toCamelCase(field.getField());
            StringBuffer sb = new StringBuffer();
            sb.append(TAB).append("public void set").append(upserFirst(fieldStr)).append("(").append(field.getFieldTypeValue()).append(" ") .append(fieldStr).append("){")  .append(WARP);
            sb.append(TAB).append(TAB).append("this.").append(fieldStr).append(" = ").append(fieldStr).append(";")  .append(WARP);
            sb.append(TAB).append("}").append(WARP);

            sb.append(WARP);

            sb.append(TAB).append("@XmlElement(name = \""+(field.getField())+"\")").append(WARP);
            sb.append(TAB).append("public ").append(field.getFieldTypeValue()).append(" get").append(upserFirst(fieldStr)).append("(){")  .append(WARP);
            sb.append(TAB).append(TAB).append("return ").append(fieldStr).append(";") .append(WARP);
            sb.append(TAB).append("}").append(WARP);

            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
//        String fileName = "f:/临时/订单头.txt";
//        String fileName = "f:/临时/订单明细.txt";
        String fileName = "f:/临时/订单反馈.txt";
        generator(fileName);
    }

    public static String upserFirst(String str) {
        if(str!=null && str!=""){
            str  = str.substring(0,1).toUpperCase()+str.substring(1);
        }
        return str;
    }
}
