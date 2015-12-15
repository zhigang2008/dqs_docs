package com.steven.framework.common.lang.enums;
/**
 * 用于枚举需要实现的接口
 * 
 * @author badqiu
 */
public interface EnumBase<K> {
    
    public K getCode();
    
    public String getDesc();
    
    public String name();
    
}
