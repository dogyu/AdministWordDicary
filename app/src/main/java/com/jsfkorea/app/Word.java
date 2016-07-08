package com.jsfkorea.app;

import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "words")
public class Word implements Serializable {

	private String no;
	private String se;
	private String wordNm;
	private String word_eng_nm;
	private String eng_abrv_nm;
	private String dfn;

	private String thema_relm;
	private String rgsde;
	private String sttus;

	public String getNo() {
		return no;
	}

	public String getSe() {
		return se;
	}

	public String getWordNm() {
		return wordNm;
	}

	public String getWord_eng_nm() {
		return word_eng_nm;
	}

	public String getEng_abrv_nm() {
		return eng_abrv_nm;
	}

	public String getDfn() {
		return dfn;
	}

	public String getThema_relm() {
		return thema_relm;
	}

	public String getRgsde() {
		return rgsde;
	}

	public String getSttus() {
		return sttus;
	}

	@Override
	public String toString() {
		return "Word [no=" + no + ", se=" + se + ", word_nm=" + wordNm + ", word_eng_nm=" + word_eng_nm
				+ ", eng_abrv_nm=" + eng_abrv_nm + ", dfn=" + dfn + ", thema_relm=" + thema_relm + ", rgsde=" + rgsde
				+ ", sttus=" + sttus + "]";
	}	
}