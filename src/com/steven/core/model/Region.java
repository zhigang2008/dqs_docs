package com.steven.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.steven.framework.base.BaseEntity;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class Region extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "地理位置数据";
	//字段名称
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_REGION_CODE = "regionCode";
	public static final String ALIAS_REGION_NAME = "regionName";
	public static final String ALIAS_ROME = "rome";
	public static final String ALIAS_ZM_CODE = "zmCode";
	public static final String ALIAS_PARENT_ID = "parentId";
	public static final String ALIAS_MEMO = "memo";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * id       db_column: ID 
        */	
	//
	private Long id;
       /**
        * regionCode       db_column: REGION_CODE 
        */	
	//@Length(max=30)
	private java.lang.String regionCode;
       /**
        * regionName       db_column: REGION_NAME 
        */	
	//@NotBlank @Length(max=30)
	private java.lang.String regionName;
       /**
        * rome       db_column: ROME 
        */	
	//@Length(max=50)
	private java.lang.String rome;
       /**
        * zmCode       db_column: ZM_CODE 
        */	
	//@Length(max=10)
	private java.lang.String zmCode;
       /**
        * parentId       db_column: PARENT_ID 
        */	
	//
	private Long parentId;
       /**
        * memo       db_column: MEMO 
        */	
	//@Length(max=50)
	private java.lang.String memo;
	//columns END

	public Region(){
	}

	public Region(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setRegionCode(java.lang.String value) {
		this.regionCode = value;
	}
	
	public java.lang.String getRegionCode() {
		return this.regionCode;
	}
	public void setRegionName(java.lang.String value) {
		this.regionName = value;
	}
	
	public java.lang.String getRegionName() {
		return this.regionName;
	}
	public void setRome(java.lang.String value) {
		this.rome = value;
	}
	
	public java.lang.String getRome() {
		return this.rome;
	}
	public void setZmCode(java.lang.String value) {
		this.zmCode = value;
	}
	
	public java.lang.String getZmCode() {
		return this.zmCode;
	}
	public void setParentId(Long value) {
		this.parentId = value;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	/**获取地区的级别
	 * @return 级别
	 */
	public Integer getLevel(){
		Integer level=1;
		if(this.getRegionCode()!=null){
			if(getRegionCode().endsWith("0000")){
				level=1;
			}else if(getRegionCode().endsWith("00")){
				level=2;
			}else{
				level=3;
			}
		}
		return level;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RegionCode",getRegionCode())
			.append("RegionName",getRegionName())
			.append("Rome",getRome())
			.append("ZmCode",getZmCode())
			.append("ParentId",getParentId())
			.append("Memo",getMemo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Region == false) return false;
		if(this == obj) return true;
		Region other = (Region)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

