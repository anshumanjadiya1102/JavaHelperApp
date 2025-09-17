package com.javahelper.qa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class KnowledgeBase {

    private List<QAEngine.QA> knowledge;

    public KnowledgeBase(String filePath) throws Exception {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<QAEngine.QA>>(){}.getType();
        knowledge = gson.fromJson(new FileReader(filePath), listType);
    }

    public List<QAEngine.QA> getKnowledge() {
        return knowledge;
    }
}
