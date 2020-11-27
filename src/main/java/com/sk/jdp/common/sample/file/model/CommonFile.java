package com.sk.jdp.common.sample.file.model;

import com.sk.jdp.common.core.model.BaseObject;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonFile extends BaseObject {

	// 통합첨부파일관리번호
    private int fileId;

    // 통합첨부파일명
    private String fileName;
    
    // 통합첨부파일경로명
    private String filePath;
    
    // 통합첨부파일사이즈바이트
    private long fileSizeByte;
    
    // 통합첨부파일확장자명
    private String fileExtsn;
    
}
