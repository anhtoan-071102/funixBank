/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dictionary.dictionaryapp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.netty.util.AsciiString;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import org.apache.commons.lang3.StringEscapeUtils;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

/**
 *
 * @author ADMIN
 */
public class APIprocess {
    
    
    private static void playAudioFromURL(String url) throws IOException, JavaLayerException {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream())){

            AdvancedPlayer advancedPlayer = new AdvancedPlayer(inputStream);
            advancedPlayer.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    super.playbackFinished(evt);
                    advancedPlayer.close();
                }
            });
            advancedPlayer.play();
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * zalo text to speech api
     * @param text 
     * @throws java.io.IOException 
     * @throws java.util.concurrent.ExecutionException 
     * @throws java.lang.InterruptedException 
     */
    public static void TextToSpeechVI(String text) throws IOException, ExecutionException, InterruptedException, JavaLayerException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        Response response = client.prepare("POST", "https://api.zalo.ai/v1/tts/synthesize")
                .setHeader("apikey", "OPhvMwTqBnCCy8Xs42tm2ishYAq7GLha")
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .addFormParam("input", text)
                .addFormParam("encode_type", "1")
                .addFormParam("speaker_id", "3")
                .execute()
                .get();

        JsonObject jo = new Gson().fromJson(response.getResponseBody(), JsonObject.class);
        String url = jo.getAsJsonObject("data").get("url").getAsString();
        try {
            playAudioFromURL(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        client.close();
    }
    
    
    /**
     * ai power text to speech api
     * @param text
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JavaLayerException 
     */
    public static void TextToSpeechEN(String text)  throws IOException, ExecutionException, InterruptedException, JavaLayerException{
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        try {
            Response response = client.prepare("POST", "https://ai-powered-text-to-speech1.p.rapidapi.com/synthesize-speech").setHeader("x-rapidapi-key", "583c7748ebmshed5e285a0ee635dp17a02djsn858f69b61c2d")
                    .setHeader("x-rapidapi-host", "ai-powered-text-to-speech1.p.rapidapi.com")
                    .setHeader("Content-Type", "application/json")
                    .setBody("{\"sentence\":\"" + text + "\",\"language\":\"en-US\",\"voice\":\"Matthew\",\"engine\":\"neural\",\"withSpeechMarks\":false}")
                    .execute()
                    .get();
            JsonObject jo = new Gson().fromJson(response.getResponseBody(), JsonObject.class);
            String url = jo.get("fileDownloadUrl").getAsString();
            playAudioFromURL(url);
        } catch (JsonSyntaxException | IOException | InterruptedException | ExecutionException | JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * google translate API
     * @param text
     * @param targetLang
     * @return
     * @throws IOException
     * @throws Exception 
     */
    public static String ggTranslateAPI(String text, String targetLang) throws IOException, Exception {
        try (AsyncHttpClient client = new DefaultAsyncHttpClient()) {
            CompletableFuture<String> future = client.prepare("GET", "https://google-translate112.p.rapidapi.com/translate?text=" + text + "&target_lang=" + targetLang)
                    .setHeader("x-rapidapi-key", "583c7748ebmshed5e285a0ee635dp17a02djsn858f69b61c2d")
                    .setHeader("x-rapidapi-host", "google-translate112.p.rapidapi.com")
                    .execute()
                    .toCompletableFuture()
                    .thenApply(response -> {
                        if (response.getStatusCode() == 200) {
                            JsonObject jo = new Gson().fromJson(response.getResponseBody(), JsonObject.class);
                            String out = jo.get("translation").getAsString();
                            if (out != null) {
                                return StringEscapeUtils.unescapeJava(out);
                            } else {
                                return "khong dich duoc";
                            }
                        } else {
                            return "khong dich duoc";
                        }
                    });
            return future.join();
        }
    }
}
