package View;

import java.io.Serializable;

public class Scores implements Serializable
{
    String nickname;
    String score;

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

    Scores(String nickname, String score)
    {
        this.nickname = nickname;
        this.score = score;
    }
}
