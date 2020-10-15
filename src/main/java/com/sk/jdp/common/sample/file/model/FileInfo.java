package com.sk.jdp.common.sample.file.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileInfo  {
	
	private String url;
	private String name;

	public FileInfo(String url, String name) {
		this.url = url;
		this.name = name;
	}
}
