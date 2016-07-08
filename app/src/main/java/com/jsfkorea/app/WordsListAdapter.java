/*
 * Copyright 2010-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jsfkorea.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class WordsListAdapter extends BaseAdapter {

	private List<Word> words;
	private final LayoutInflater layoutInflater;

	public WordsListAdapter(Context context, List<Word> words) {
		this.words = words;
		this.layoutInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return this.words != null ? words.size() : 0;
	}

	public Word getItem(int position) {
		return this.words.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = this.layoutInflater.inflate(R.layout.words_list_item, parent, false);
		}

		Word state = getItem(position);
		if (state != null) {
			TextView t1 = (TextView) convertView.findViewById(R.id.no);
			t1.setText(state.getNo());

			TextView t2 = (TextView) convertView.findViewById(R.id.se);
			t2.setText(state.getSe());

			TextView t3 = (TextView) convertView.findViewById(R.id.word_nm);
			t3.setText(state.getWordNm());

			TextView t4 = (TextView) convertView.findViewById(R.id.word_eng_nm);
			t4.setText(state.getWord_eng_nm());

			TextView t5 = (TextView) convertView.findViewById(R.id.eng_abrv_nm);
			t5.setText(state.getEng_abrv_nm());

			TextView t6 = (TextView) convertView.findViewById(R.id.dfn);
			t6.setText(state.getDfn());

			TextView t7 = (TextView) convertView.findViewById(R.id.thema_relm);
			t7.setText(state.getThema_relm());

			TextView t8 = (TextView) convertView.findViewById(R.id.rgsde);
			t8.setText(state.getRgsde());

			TextView t9 = (TextView) convertView.findViewById(R.id.sttus);
			t9.setText(state.getSttus());
		}
		return convertView;
	}
}