package com.maomiyibian.microservice.customer.exception;

import com.maomiyibian.microservice.common.message.TradeMessages;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class CustomExceptionResolver {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public TradeMessages<String> exceptionHandler(Exception e){
		TradeMessages<String> messages=new TradeMessages<>();
		messages.setResultCode("1000");
		messages.setResultMessage("系统发生异常，异常信息为:"+e.getMessage());
		return messages;
	}

	@ResponseBody
	@ExceptionHandler(value = MultipartException.class)
	public TradeMessages<String> multipartExcptionHandler(MultipartException e){
		TradeMessages<String> messages=new TradeMessages<>();
		messages.setResultCode("10001");
		messages.setResultMessage("文件上传发生异常，异常信息为:"+e.getMessage());
		messages.setData(null);
		return messages;
	}

}
