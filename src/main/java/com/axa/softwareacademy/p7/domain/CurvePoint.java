package com.axa.softwareacademy.p7.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "TINYINT")
    private Integer id;
    @Column(columnDefinition = "TINYINT")
    private Integer curveId;
    @NotNull
    private LocalDateTime asOfDate;
    private Double term;
    private Double value;
    @NotNull
    private LocalDateTime creationDate;

    public CurvePoint() {
    }

    public CurvePoint(int id, int curveId, Double term, Double value) {
        this.id = id;
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public CurvePoint(int curveId, Double term, Double value) {
        this(0, curveId, term, value);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCurveId() { return curveId; }
    public void setCurveId(Integer curveId) { this.curveId = curveId; }

    public LocalDateTime getAsOfDate() { return asOfDate; }
    public void setAsOfDate(LocalDateTime asOfDate) { this.asOfDate = asOfDate; }

    public Double getTerm() { return term; }
    public void setTerm(Double term) { this.term = term; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    @Override
    public String toString() {
        return "CurvePoint{" + "id=" + id + ", curveId=" + curveId + ", asOfDate=" + asOfDate + ", term=" + term + ", value=" + value + ", creationDate=" + creationDate + '}';
    }
}
