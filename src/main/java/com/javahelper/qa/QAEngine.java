package com.javahelper.qa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class QAEngine {

    private final List<QA> knowledge;

    public QAEngine(String knowledgeFile) throws Exception {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<QA>>(){}.getType();
        knowledge = gson.fromJson(new FileReader(knowledgeFile), listType);
    }

    public String getAnswer(String question) {
        question = question.toLowerCase();
        for (QA qa : knowledge) {
            if (qa.question.toLowerCase().contains(question) || question.contains(qa.question.toLowerCase())) {
                return qa.answer;
            }
        }
        return "Sorry, I don't know the answer. Try another question!";
    }

    public static class QA {
        String category;
        String question;
        String answer;
    }

    public List<QA> getKnowledge() {
        return knowledge;
    }
}

