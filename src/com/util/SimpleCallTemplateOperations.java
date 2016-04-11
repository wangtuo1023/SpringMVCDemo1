/*
 * SimpleCallTemplateOperations.java
 * 版权所有，中国铁道科学研究院，2009-2010。
 * 本文件以及任何本文件的转化形式只限在中国铁道科学研究院内部使用。
 */
package com.util;

import java.util.List;

/**
 * 存储过程调用封装接口
 * 
 * 
 * @author yushanyuan
 * @version 0.1
 * @date 2010-4-19 下午02:51:44
 */
public interface SimpleCallTemplateOperations {

  public List<String> execute(String storeName, final List<Object> inParamList);

}
