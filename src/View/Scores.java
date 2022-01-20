package View;

import java.io.Serializable;

public class Scores implements Serializable
{
    String nickname;
    String score;
    String time;

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getScore()
    {
        return score;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTime()
    {
        return time;
    }


    Scores(String nickname, String score, String time)
    {
        this.nickname = nickname;
        this.score = score;
        this.time = time;
    }
}
