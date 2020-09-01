package com.application.lyrical.controllers;

import com.application.lyrical.models.Song;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class TrackController {

    @Autowired
    Song apiresults;

    @RequestMapping("track")
    public String results(Model model,  @RequestParam String songName, @RequestParam String artistName, @RequestParam String largePicture, @RequestParam String preview) throws UnirestException, UnsupportedEncodingException {
        model.addAttribute("title", "Track Results");
        String trackValue = songName;
        String artistValue = artistName;
        String apikey = "apikey=";
        String charset = "UTF-8";
        String queryTrack = String.format("q_track=%s",
                URLEncoder.encode(trackValue, charset));
        String queryArtist = String.format("q_artist=%s",
                URLEncoder.encode(artistValue, charset));
        // Json response
        HttpResponse<JsonNode> response = Unirest.get(apiresults.getMusixUrl() + queryTrack + "&" + queryArtist + "&" + apikey + apiresults.getApiKeyValue())
                .header("Accept", "text/plain")
                .asJson();

        JSONObject responsejson = response.getBody().getObject();
        JSONObject message = responsejson.getJSONObject("message");
        JSONObject body = message.getJSONObject("body");
        JSONObject lyricsobj = body.getJSONObject("lyrics");
        String lyricsbody = lyricsobj.getString("lyrics_body");

        model.addAttribute("pic", largePicture);
        model.addAttribute("response", lyricsbody);
        model.addAttribute("title", songName);
        model.addAttribute("artist", artistName);
        model.addAttribute("preview", preview);


        return "track";
    }


}