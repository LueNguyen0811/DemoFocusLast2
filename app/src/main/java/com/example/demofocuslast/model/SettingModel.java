package com.example.demofocuslast.model;

public class SettingModel {
    private String content;
    private String description;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SettingModel() {
    }

    public SettingModel(String content, String description) {
        this.content = content;
        this.description = description;
    }
}
