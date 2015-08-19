package com.wgp.generator.customsreport;

/**
 * User: weigangpeng
 * Date: 2015-07-21
 * Time: 下午2:21
 */
public class MsgField {

    private String field;
    private String comment;
    private String type;
    private boolean isRequired;
    private String note;

    public MsgField(String field, String comment, String type, boolean required, String note) {
        this.field = field;
        this.comment = comment;
        this.type = type;
        isRequired = required;
        this.note = note;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public FieldTypeEnum getFieldType(){
        FieldTypeEnum fieldType = null;
        if(type.contains("CHAR")){
            fieldType =  FieldTypeEnum.STRING;
        }else if(type.contains("NUMBER")){
            fieldType = FieldTypeEnum.NUMBER;
        }
        return  fieldType;
    }

    public String getFieldTypeValue(){
        FieldTypeEnum fieldType = null;
        if(type.contains("CHAR") || type.contains("DATE")){
            fieldType =  FieldTypeEnum.STRING;
        }else if(type.contains("NUMBER")){
            fieldType = FieldTypeEnum.NUMBER;
        }
        return  fieldType.getName();
    }

    public boolean isString(){
        return getFieldType() == FieldTypeEnum.STRING;
    }


    public boolean isNumber(){
        return getFieldType() == FieldTypeEnum.NUMBER;
    }

    @Override
    public String toString() {
        return "MsgField{" +
                "field='" + field + '\'' +
                ", comment='" + comment + '\'' +
                ", type='" + type + '\'' +
                ", isRequired=" + isRequired +
                ", note='" + note + '\'' +
                '}';
    }
}
