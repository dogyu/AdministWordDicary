package com.jsfkorea.app;

public interface AsyncActivity {

	void showLoadingProgressDialog();

	void showProgressDialog(CharSequence message);

	void dismissProgressDialog();

}
