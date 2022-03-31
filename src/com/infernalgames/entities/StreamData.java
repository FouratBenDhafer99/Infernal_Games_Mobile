package com.infernalgames.entities;
/**
 *
 * @author Fourat
 */
public class StreamData {

    private int id;
    private Utilisateur streamer;
    private String streamKey;

    public StreamData(){}

    public StreamData(int id, Utilisateur streamer, String streamKey){
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

    public Utilisateur getStreamer() {
        return streamer;
    }

    public void setStreamer(Utilisateur streamer) {
        this.streamer = streamer;
    }

    public String getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }
}
