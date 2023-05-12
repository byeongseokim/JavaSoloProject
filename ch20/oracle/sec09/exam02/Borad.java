package ch20.oracle.sec09.exam02;

import java.sql.Blob;
import java.sql.Date;

import lombok.Data;

@Data
public class Borad {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private String bfilename;
	private Blob bfiledata;
}