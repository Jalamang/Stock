package com.jbbwebsolutions.stocks.dao;

import java.io.IOException;


public class Water implements java.io.Closeable {
	
	
	public Water() {
		System.out.println("Water is on");
	}

	@Override
	public void close() throws IOException {
		System.out.println("Water closed");
	}

}
