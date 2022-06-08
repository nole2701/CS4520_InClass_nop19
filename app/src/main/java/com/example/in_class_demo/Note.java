package com.example.in_class_demo;

public class Note {
    private String _id;
    private String userId;
    private String text;
    private int __v;

    public Note(String _id, String userId, String text, int __v) {
        this._id = _id;
        this.userId = userId;
        this.text = text;
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id='" + _id + '\'' +
                ", userId='" + userId + '\'' +
                ", text='" + text + '\'' +
                ", __v='" + __v + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public int get__v() {
        return __v;
    }
}
