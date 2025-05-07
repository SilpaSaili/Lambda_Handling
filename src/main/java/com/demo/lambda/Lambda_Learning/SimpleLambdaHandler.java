package com.demo.lambda.Lambda_Learning;

import java.util.Map;
import java.util.Map.Entry;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimpleLambdaHandler implements RequestHandler<Map<String, String>, String> {

	@Override
	public String handleRequest(Map<String, String> input, Context context) {
		LambdaLogger logger = context.getLogger();
		String inputValue = "";
		for (Entry<String, String> entrySet : input.entrySet()) {
			inputValue = entrySet.getValue();
		}
		logger.log("Input value is " + inputValue);
		String output = ((String) inputValue).toLowerCase();
		logger.log("output is " + output);
		System.out.println("System logger");
		return output;
	}

}
