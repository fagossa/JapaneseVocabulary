package com.example.fagossa.japanesevocabulary.parsing;

import org.json.JSONException;
import org.json.JSONObject;

public class Word {
    private String japanese;
    private String english;
    private String type;

    private Word(String japanese, String english, String type) {
        this.japanese = japanese;
        this.english = english;
        this.type = type;
    }

    public String getJapanese() {
        return japanese;
    }

    public String getEnglish() {
        return english;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Word{" +
                "japanese='" + japanese + '\'' +
                ", english='" + english + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    static Word buildFromJson(JSONObject jsonObject) {
        try {
            String japanese = jsonObject.getString("word");
            String english = jsonObject.getString("english");
            String type = jsonObject.getString("type");
            return new Word(japanese, english, type);
        } catch (JSONException e) {
            return null;
        }
    }
}
