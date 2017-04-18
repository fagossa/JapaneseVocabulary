package com.example.fagossa.japanesevocabulary.parsing;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class WordParser {

    public abstract List<Word> words();

    public abstract Word randomWord();

    private static OkHttpClient client = new OkHttpClient();

    public static WordParser loadFromUrl(String url) {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String contents = response.body().string();

            return new WordParserImpl(contents);
        } catch (Exception e) {
            e.printStackTrace();
            return new NullWordParser(e.getMessage());
        }
    }

    private static class WordParserImpl extends WordParser {
        private List<Word> words = new ArrayList<>();
        private Random Dice = new Random();

        private WordParserImpl(String contents) {
            try {
                JSONArray  array = new JSONArray(contents);
                for(int i = 0 ; i < array.length() ; i++){
                    Word maybeWord = Word.buildFromJson(array.getJSONObject(i));
                    if (maybeWord != null) {
                        words.add(maybeWord);
                    }
                }
            } catch (JSONException e) {
                // ignores
            }
        }

        @Override
        public List<Word> words() {
            return new ArrayList<>(words);
        }

        @Override
        public Word randomWord() {
            int n = Dice.nextInt(words.size());
            return (n >= 0 && n < words.size())
                ? words.get(n)
                : null;
        }
    }

    private static class NullWordParser extends WordParser {

        private NullWordParser(String message) {}

        @Override
        public List<Word> words() {
            return Collections.emptyList();
        }

        @Override
        public Word randomWord() {
            return null;
        }
    }

}
