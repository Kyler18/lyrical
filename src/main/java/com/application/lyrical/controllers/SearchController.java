package com.application.lyrical.controllers;

import com.application.lyrical.models.Search;
import com.application.lyrical.models.SongData;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

@Controller
public class SearchController {

    @Autowired
    Search apiresults;


    @RequestMapping("results")
    public String results(Model model, @RequestParam String searchTerm) throws UnirestException, UnsupportedEncodingException {
        model.addAttribute("title", "Search Results");

        // Host, charset and headers vars should be the same
        String charset = "UTF-8";
        // Format query for preventing encoding problems
        String query = String.format("q=%s",
                URLEncoder.encode(searchTerm, charset));
        
        // Json response
        HttpResponse<JsonNode> response = Unirest.get(apiresults.getGeniusUrl() + "?" + query + "&limit=500")
                .header(apiresults.getHostName(), apiresults.getHostValue())
                .header(apiresults.getApiKeyName(), apiresults.getApiKeyValue())
                .asJson();

        JSONObject responsejson = response.getBody().getObject();
        JSONArray results = responsejson.getJSONArray("data");
        ArrayList<SongData> songs = new ArrayList<>();

        //loop that handles the response data and displays it
        for (int x = 0; x < results.length(); x++) {
            SongData songData = new SongData();
            JSONObject value = results.getJSONObject(x);
            String title = value.getString("title");
            songData.setTitle(title);
            String preview = value.getString("preview");
            songData.setPreview(preview);
            JSONObject artist = value.getJSONObject("artist");
            String artistname = artist.getString("name");
            songData.setArtist(artistname);
            String pic1 = artist.getString("picture_medium");
            songData.setSmallPicture(pic1);
            String pic2 = artist.getString("picture_xl");
            songData.setLargePicture(pic2);

            songs.add(songData);

        }

        model.addAttribute("hits", songs);
        model.addAttribute("searchterm", searchTerm);


        return "results";
    }


}