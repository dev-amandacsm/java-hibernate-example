package br.com.java.hibernate.example.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail", schema = "example")
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private Long id;

    @Column(name = "youtube_channel", length = 128)
    private String youtubeChannel;

    @Column(length = 45)
    private String hobby;

    public InstructorDetail(){

    }

    public InstructorDetail(String youtubeChannel, String hobby){
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public Long getId(){
        return id;
    }

    public String getHobby(){
        return hobby;
    }

    public String getYoutubeChannel(){
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel){
        this.youtubeChannel = youtubeChannel;
    }

    public void setHobby(String hobby){
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
