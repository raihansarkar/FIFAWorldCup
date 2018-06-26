package smac.net.fifaworldcup;

public class Item {
    private String flag1;
    private String flag2;
    private String flag3;
    private String flag4;
    private String team1;
    private String team2;
    private String team3;
    private String team4;
    private String date;
    private String time;
    private String group;
    private String pl1;
    private String gd1;
    private String pst1;
    private String pl2;
    private String gd2;
    private String pst2;
    private String pl3;
    private String gd3;
    private String pst3;
    private String pl4;
    private String gd4;
    private String pst4;
    private String goal;

    public Item() {
    }

    public Item(String flag1, String flag2, String flag3, String flag4, String team1, String team2, String team3, String team4, String date, String time) {
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.date = date;
        this.time = time;
    }

    public Item(String flag1, String flag2, String flag3, String flag4, String team1, String team2, String team3, String team4, String group) {
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
        this.flag4 = flag4;
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.group = group;
    }

    public Item(String flag1, String flag2, String team1, String team2, String date, String time, String goal) {
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.goal = goal;
    }

    public Item(String team1, String team2, String team3, String team4, String group) {
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.group = group;
    }

    public Item(String team1, String team2, String date, String time) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
    }

    public Item(String team1, String team2, String team3, String team4, String pl1, String pl2, String pl3, String pl4, String gd1, String gd2, String gd3, String gd4, String pst1,   String pst2,   String pst3,   String pst4, String group) {
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.group = group;
        this.pl1 = pl1;
        this.gd1 = gd1;
        this.pst1 = pst1;
        this.pl2 = pl2;
        this.gd2 = gd2;
        this.pst2 = pst2;
        this.pl3 = pl3;
        this.gd3 = gd3;
        this.pst3 = pst3;
        this.pl4 = pl4;
        this.gd4 = gd4;
        this.pst4 = pst4;
    }

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    public String getFlag3() {
        return flag3;
    }

    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }

    public String getFlag4() {
        return flag4;
    }

    public void setFlag4(String flag4) {
        this.flag4 = flag4;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam3() {
        return team3;
    }

    public void setTeam3(String team3) {
        this.team3 = team3;
    }

    public String getTeam4() {
        return team4;
    }

    public void setTeam4(String team4) {
        this.team4 = team4;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPl1() {
        return pl1;
    }

    public void setPl1(String pl1) {
        this.pl1 = pl1;
    }

    public String getGd1() {
        return gd1;
    }

    public void setGd1(String gd1) {
        this.gd1 = gd1;
    }

    public String getPst1() {
        return pst1;
    }

    public void setPst1(String pst1) {
        this.pst1 = pst1;
    }

    public String getPl2() {
        return pl2;
    }

    public void setPl2(String pl2) {
        this.pl2 = pl2;
    }

    public String getGd2() {
        return gd2;
    }

    public void setGd2(String gd2) {
        this.gd2 = gd2;
    }

    public String getPst2() {
        return pst2;
    }

    public void setPst2(String pst2) {
        this.pst2 = pst2;
    }

    public String getPl3() {
        return pl3;
    }

    public void setPl3(String pl3) {
        this.pl3 = pl3;
    }

    public String getGd3() {
        return gd3;
    }

    public void setGd3(String gd3) {
        this.gd3 = gd3;
    }

    public String getPst3() {
        return pst3;
    }

    public void setPst3(String pst3) {
        this.pst3 = pst3;
    }

    public String getPl4() {
        return pl4;
    }

    public void setPl4(String pl4) {
        this.pl4 = pl4;
    }

    public String getGd4() {
        return gd4;
    }

    public void setGd4(String gd4) {
        this.gd4 = gd4;
    }

    public String getPst4() {
        return pst4;
    }

    public void setPst4(String pst4) {
        this.pst4 = pst4;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "Item{" +
                "flag1='" + flag1 + '\'' +
                ", flag2='" + flag2 + '\'' +
                ", flag3='" + flag3 + '\'' +
                ", flag4='" + flag4 + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", team3='" + team3 + '\'' +
                ", team4='" + team4 + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", group='" + group + '\'' +
                ", pl1='" + pl1 + '\'' +
                ", gd1='" + gd1 + '\'' +
                ", pst1='" + pst1 + '\'' +
                ", pl2='" + pl2 + '\'' +
                ", gd2='" + gd2 + '\'' +
                ", pst2='" + pst2 + '\'' +
                ", pl3='" + pl3 + '\'' +
                ", gd3='" + gd3 + '\'' +
                ", pst3='" + pst3 + '\'' +
                ", pl4='" + pl4 + '\'' +
                ", gd4='" + gd4 + '\'' +
                ", pst4='" + pst4 + '\'' +
                ", goal='" + goal + '\'' +
                '}';
    }
}
