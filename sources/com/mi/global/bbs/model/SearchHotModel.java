package com.mi.global.bbs.model;

import java.util.List;

public class SearchHotModel extends BaseResult {
    private DataBean data;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean {
        private List<WordsBean> words;

        public List<WordsBean> getWords() {
            return this.words;
        }

        public void setWords(List<WordsBean> list) {
            this.words = list;
        }

        public static class WordsBean {
            private String url;
            private String word;

            public String getWord() {
                return this.word;
            }

            public void setWord(String str) {
                this.word = str;
            }

            public String getUrl() {
                return this.url;
            }

            public void setUrl(String str) {
                this.url = str;
            }
        }
    }
}
