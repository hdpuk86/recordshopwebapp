package controllers;

import db.DBArtist;
import models.Artist;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ArtistsController {

    public ArtistsController() {
        this.setUpEndPoints();
    }

    private void setUpEndPoints(){
        get("/artists", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Artist> artists = DBArtist.getArtists();
            model.put("artists", artists);
            model.put("template", "templates/artists/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/artists/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/artists/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/artists/:id", (req, res) -> {
            Artist artist = DBArtist.getArtistById(Integer.parseInt(req.params(":id")));
            Map<String, Object> model = new HashMap<>();
            model.put("artist", artist);
            model.put("template", "templates/artists/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/artists", (req, res) -> {
            Artist artist = new Artist(req.queryParams("name"));
            DBArtist.save(artist);
            res.redirect("/artists");
            return null;
        });

        post("/artists/:id/delete", (req, res) -> {
            DBArtist.delete(Integer.parseInt(req.params(":id")));
            res.redirect("/artists");
            return null;
        });
    }
}
