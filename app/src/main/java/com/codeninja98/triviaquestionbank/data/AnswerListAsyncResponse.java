package com.codeninja98.triviaquestionbank.data;

import com.codeninja98.triviaquestionbank.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
        void processFinished(ArrayList<Question> questionArrayList);

}
