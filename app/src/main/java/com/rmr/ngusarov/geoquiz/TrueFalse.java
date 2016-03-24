package com.rmr.ngusarov.geoquiz;


public class TrueFalse {
    private int mQuestionId;
    private boolean mQuestionResult;

    public TrueFalse(int mQuestionId, boolean mQuestionResult) {
        this.mQuestionId = mQuestionId;
        this.mQuestionResult = mQuestionResult;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isQuestionResult() {
        return mQuestionResult;
    }

    public void setQuestionResult(boolean questionResult) {
        mQuestionResult = questionResult;
    }
}
