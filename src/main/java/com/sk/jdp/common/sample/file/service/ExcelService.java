package com.sk.jdp.common.sample.file.service;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sk.jdp.common.core.service.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ExcelService extends BaseService {

	public void excelUpload(MultipartFile multipartFile) {
		if(!multipartFile.isEmpty()) {
			try {
				Workbook workbook = null;
				
				String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
				log.debug("fileExt={}", fileExt);
				
				if("xlsx".equals(fileExt)) {
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
				} else {
					workbook = new HSSFWorkbook(multipartFile.getInputStream());
				}
				
				Sheet sheet = workbook.getSheetAt(0);
				
				int rowindex=0;
				int columnindex=0;
				
				int rows=sheet.getPhysicalNumberOfRows();
				for(rowindex=0;rowindex<rows;rowindex++) {
					Row row=sheet.getRow(rowindex);
					
					if(row != null) {
						int cells=row.getPhysicalNumberOfCells();
						
						for(columnindex=0; columnindex<=cells; columnindex++) {
							Cell cell=row.getCell(columnindex);
							String value="";
							
							if(cell==null) {
								continue;
							} else {
								log.debug("cellType={}", cell.getCellType());
								switch (cell.getCellType()) {
								case FORMULA:		//계산식
									value = cell.getCellFormula();
									break;
								case NUMERIC:		//수식(날짜, 수치)
									value = String.valueOf(cell.getNumericCellValue());
									break;
								case STRING:		//문자
									value = cell.getStringCellValue();
									break;
								case BLANK:		//boolean
									value = String.valueOf(cell.getBooleanCellValue());
									break;
								case ERROR:		//에러코드
									value = String.valueOf(cell.getErrorCellValue());
									break;
								default:
									value = cell.getStringCellValue();
									break;
								}
							}
							
							log.debug("cell value={}", value);
						}
					}
				}
			} catch(IOException ex) {
				log.error("excel poi error", ex);
			}
		}
	}
}
