package com.infernalgames.entities;
/**
 *
 * @author Fourat
 */
public class StreamData {

    private int id;
    private User streamer;
    private String streamKey;

    public StreamData(){}

    public StreamData(int id, User streamer, String streamKey){
        this.id= id;
        this.streamer= streamer;
        this.streamKey= streamKey;
    }

    @Override
    public String toString(){
        return this.streamer+ " "+ this.streamKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getStreamer() {
        return streamer;
    }

    public void setStreamer(User streamer) {
        this.streamer = streamer;
    }

    public String getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }
}
