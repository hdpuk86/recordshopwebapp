package controllers;

import db.DBAlbum;
import models.Album;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        staticFileLocation("/public");
        ArtistsController artistsController = new ArtistsController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Album> stock = DBAlbum.getAlbums();
            model.put("stock", stock);
            model.put("template", "templates/main.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
