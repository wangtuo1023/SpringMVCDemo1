/*
 * BaseTemplate.java
 * 版权所有，中国铁道科学研究院，2009-2010。
 * 本文件以及任何本文件的转化形式只限在中国铁道科学研究院内部使用。
 */
package com.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.cars.ict.otsweb.Constants;

/**
 * 基类
 * 
 * @author llf
 * @version 0.1
 * @date 2010-6-21 下午03:17:41
 */
public class BaseTemplate {
	protected static Logger log = Logger.getLogger(BaseTemplate.class);

	/**
	 * 添加返回两列结果集的判断处理
	 * 
	 * @author llf
	 * @param returnList
	 */
	protected void checkReturnList(List<String> returnList) {
		if (returnList != null && returnList.size() > 0) {
			String[] fields = returnList.get(0).split(Constants.SPLIT_CODE);
			if (fields.length == 2) {
				if (Constants.BUSINESS_EX_CODE.equals(fields[0])) { // 1.业务异常
					if (log.isDebugEnabled()) {
						log.debug("Check DB/TRSSRV Return result，The Return column count is 2.Get Business Exception. code: "
								+ fields[0] + " message：" + fields[1]);
					}
					// throw new BusinessOtsException(fields[0], fields[1]);
					throw new RuntimeException();
				} else { // 2.系统异常
					log.error(fields[0]
							+ ":Check DB/TRSSRV Return result，The Return column count is 2.Get Sys Exception！！！ code: "
							+ fields[0] + " message：" + fields[1]);
					// throw new SystemOtsException(fields[0], fields[1]);
					throw new RuntimeException();
				}
			}

		}
	}
}