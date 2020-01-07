package com.example.admin.Intefaces;

public interface AddQuestionDialogListener {
    void applyQuestion(String question, String difficulty);

    void applyQuestionModification(String question, String difficulty, int pos);
}
