package com.wgp.generator.customsreport;

/**
 * User: weigangpeng
 * Date: 2015-07-21
 * Time: 下午2:41
 */
public enum FieldTypeEnum {
    STRING("1", "String"),
    NUMBER("2", "double");

    private String id;

    private String name;

    FieldTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static FieldTypeEnum getValue(String id){
        for(FieldTypeEnum etse : FieldTypeEnum.values()){
            if(etse.getId().equals(id)){
                return etse;
            }
        }
        return null;
    }

}
