/**
 * Classname:com.steven.core.GlobalSetting
 * Version info:V1.0
 * Date:2011-8-5 
 * Copyright notice: minshengLife
 */
package com.steven.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.steven.core.model.GlobalConfig;
import com.steven.core.service.GlobalConfigManager;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
@Service
public class GlobalSetting {

    /**
     * 系统数据配置管理
     */
    private GlobalConfigManager globalConfigManager=null;
    
    /**
     * 是否需要初始化
     */
    private  boolean needInit=true;
    /**
     * 所有的配置信息
     */
    private   Map<String,String> allSettiing=new HashMap<String,String>();
    /**获取配置信息
     * @param key 
     * @return
     */
    public  String getParam(String key){
	if(needInit){
	    this.initParams();
	    needInit=false;
	}
	return allSettiing.get(key);
    }
    
    /**
     * 初始化所有系统参数
     */
    private void initParams(){
	List<GlobalConfig>  allConfigList=this.globalConfigManager.findAll();
	for(GlobalConfig config:allConfigList){
	    allSettiing.put(config.getParam(), config.getParamValue());
	}
    }
    
    /**重新载入参数
     * @param allConfig
     */
    public void reloadConfigParams(){
	this.allSettiing.clear();
	this.initParams();
    }
  
    /**
     * @param confManager the confManager to set
     */
    public void setGlobalConfigManager(GlobalConfigManager confManager) {
        this.globalConfigManager = confManager;
    }

}
