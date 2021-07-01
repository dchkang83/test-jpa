package kr.co.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
public class Member {
    // 대체키 (권장 : Long + 대체키 + 키 생성전략 사용)
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 20)
    private String name;
    private int age;

//    @Column(name = "TEAM_ID")
//    private Long teamId;
    // LAZY (지연로딩)
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Temporal(TemporalType.DATE)
    private Date date; // 날짜

    @Temporal(TemporalType.TIME)
    private Date time; // 시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp; // 날짜와 시간

    @Lob
    private String lobString; // CLOB (String, char[], java.sql.CLOB)

    @Lob
    private byte[] lobType; // BLOB (byte[], java.sql.BLOB)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLobString() {
        return lobString;
    }

    public void setLobString(String lobString) {
        this.lobString = lobString;
    }

    public byte[] getLobType() {
        return lobType;
    }

    public void setLobType(byte[] lobType) {
        this.lobType = lobType;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                ", memberType=" + memberType +
                ", date=" + date +
                ", time=" + time +
                ", timestamp=" + timestamp +
                ", lobString='" + lobString + '\'' +
                ", lobType=" + Arrays.toString(lobType) +
                '}';
    }
}
