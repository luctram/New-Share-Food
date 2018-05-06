package com.ptit.tranhoangminh.newsharefood.models;

/**
 * Created by Lãng on 15/4/2018.
 */

public class ProductDetail {
    private String id;
    private int commentcount;
    private int like;
    private String materials;
    private String recipe;
    private String video;
    private String comment;

    public ProductDetail() {
    }

    public ProductDetail(int commentcount, int like, String materials, String recipe, String video, String comment) {
        this.commentcount = commentcount;
        this.like = like;
        this.materials = materials;
        this.recipe = recipe;
        this.video = video;
        this.comment = comment;
    }

    public ProductDetail(String id, int commentcount, int like, String materials, String recipe, String video, String comment) {
        this.id = id;
        this.commentcount = commentcount;
        this.like = like;
        this.materials = materials;
        this.recipe = recipe;
        this.video = video;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addLike(int i) {
        this.like += i;
    }

    public void removeLike(int i) {
        this.like -= i;
    }
}
