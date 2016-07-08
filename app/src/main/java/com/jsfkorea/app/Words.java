package com.jsfkorea.app;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

public class Words implements Serializable{

	private List<Word> words;

	public Words() {
	}

	public Words(List<Word> words) {
		this.words = words;
	}

	protected Words(Parcel in) {
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}
}