package com.lx.exception;

/**
 * 自定义异常 主要是模拟 用于 新增或者修改 的某些参数的唯一性校验
 * @author luoxiang
 *
 */
public class AddOrUpdateUniqueException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg = "";

	public AddOrUpdateUniqueException(String msg){
		super(msg);
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
	
	
}
