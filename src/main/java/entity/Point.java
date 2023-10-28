package entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "point")
@Data
public class Point extends BaseEntity {

    @Id
    private Long pid; //포인트 아이디

    @Column
    @ManyToOne
    private Member member; //회원정보

    private int pbalance; // 잔액


}
