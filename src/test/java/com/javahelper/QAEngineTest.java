package com.javahelper;

import com.javahelper.qa.QAEngine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QAEngineTest {

    private static QAEngine engine;

    @BeforeAll
    static void setup() throws Exception {
        File knowledgeFile = new File("src/main/resources/knowledge.json");
        assertTrue(knowledgeFile.exists(), "knowledge.json file should exist");
        engine = new QAEngine(knowledgeFile.getPath());
    }

    @Test
    void testGetAnswerExistingQuestion() {
        String question = "What is Java?";
        String answer = engine.getAnswer(question);
        assertNotNull(answer);
        assertTrue(answer.toLowerCase().contains("object-oriented"));
    }

    @Test
    void testGetAnswerCaseInsensitive() {
        String question = "what IS java?";
        String answer = engine.getAnswer(question);
        assertNotNull(answer);
        assertTrue(answer.toLowerCase().contains("object-oriented"));
    }

    @Test
    void testGetAnswerUnknownQuestion() {
        String question = "What is Quantum Java?";
        String answer = engine.getAnswer(question);
        assertNotNull(answer);
        assertEquals("Sorry, I don't know the answer. Try another question!", answer);
    }

    @Test
    void testKnowledgeListNotEmpty() {
        List<QAEngine.QA> knowledge = engine.getKnowledge();
        assertNotNull(knowledge);
        assertFalse(knowledge.isEmpty());
    }
}
