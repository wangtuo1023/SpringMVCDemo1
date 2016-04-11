package com.core.exception;

import com.core.Logable;
import com.core.MessageAlertable;

/**
 * controller ���쳣
 * @author joe
 * @date  2011-10-24 ����03:32:48
 */
public class ActionException extends Exception implements MessageAlertable,Logable{
	private static final long serialVersionUID = 1L;
   
	public ActionException(String msg,Throwable e){
		super(msg, e);
	}
	public ActionException(String msg){
		super(msg);
	}
}
