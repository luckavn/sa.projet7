package com.axa.softwareacademy.p7.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Table(name = "bidlist")
public class BidList {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "TINYINT")
    private Integer bidListId;
    @NotNull
    @Column(length = 30)
    private String account;
    @NotNull
    @Column(length = 30)
    private String type;
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    @Column(length = 125)
    private String benchmark;
    @NotNull
    private LocalDateTime bidListDate;
    @Column(length = 125)
    private String commentary;
    @Column(length = 125)
    private String security;
    @Column(length = 10)
    private String status;
    @Column(length = 125)
    private String trader;
    @Column(length = 125)
    private String book;
    @Column(length = 125)
    private String creationName;
    @NotNull
    private LocalDateTime creationDate;
    @Column(length = 125)
    private String revisionName;
    private LocalDateTime revisionDate;
    @Column(length = 125)
    private String dealName;
    @Column(length = 125)
    private String dealType;
    @Column(length = 125)
    private String sourceListId;
    @Column(length = 125)
    private String side;

    public BidList() {
    }

    public BidList(int bidListId, String account, String type, Double bidQuantity) {
        this.bidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public BidList(String account, String type, Double bidQuantity) {
        this(0, account, type, bidQuantity);
    }

    public Integer getBidListId() { return bidListId; }
    public void setBidListId(Integer bidListId) { this.bidListId = bidListId; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getBidQuantity() { return bidQuantity; }
    public void setBidQuantity(Double bidQuantity) { this.bidQuantity = bidQuantity; }

    public Double getAskQuantity() { return askQuantity; }
    public void setAskQuantity(Double askQuantity) { this.askQuantity = askQuantity; }

    public Double getBid() { return bid; }
    public void setBid(Double bid) { this.bid = bid; }

    public Double getAsk() { return ask; }
    public void setAsk(Double ask) { this.ask = ask; }

    public String getBenchmark() { return benchmark; }
    public void setBenchmark(String benchmark) { this.benchmark = benchmark; }

    public LocalDateTime getBidListDate() { return bidListDate; }
    public void setBidListDate(LocalDateTime bidListDate) { this.bidListDate = bidListDate; }

    public String getCommentary() { return commentary; }
    public void setCommentary(String commentary) { this.commentary = commentary; }

    public String getSecurity() { return security; }
    public void setSecurity(String security) { this.security = security; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTrader() { return trader; }
    public void setTrader(String trader) { this.trader = trader; }

    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }

    public String getCreationName() { return creationName; }
    public void setCreationName(String creationName) { this.creationName = creationName; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public String getRevisionName() { return revisionName; }
    public void setRevisionName(String revisionName) { this.revisionName = revisionName; }

    public LocalDateTime getRevisionDate() { return revisionDate; }
    public void setRevisionDate(LocalDateTime revisionDate) { this.revisionDate = revisionDate; }

    public String getDealName() { return dealName; }
    public void setDealName(String dealName) { this.dealName = dealName; }

    public String getDealType() { return dealType; }
    public void setDealType(String dealType) { this.dealType = dealType; }

    public String getSourceListId() { return sourceListId; }
    public void setSourceListId(String sourceListId) { this.sourceListId = sourceListId; }

    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }

    @Override
    public String toString() {
        return "BidList{" + "bidListId=" + bidListId + ", account='" + account + '\'' + ", type='" + type + '\'' + ", bidQuantity=" + bidQuantity + ", askQuantity=" + askQuantity + ", bid=" + bid + ", ask=" + ask + ", benchmark='" + benchmark + '\'' + ", bidListDate=" + bidListDate + ", commentary='" + commentary + '\'' + ", security='" + security + '\'' + ", status='" + status + '\'' + ", trader='" + trader + '\'' + ", book='" + book + '\'' + ", creationName='" + creationName + '\'' + ", creationDate=" + creationDate + ", revisionName='" + revisionName + '\'' + ", revisionDate=" + revisionDate + ", dealName='" + dealName + '\'' + ", dealType='" + dealType + '\'' + ", sourceListId='" + sourceListId + '\'' + ", side='" + side + '\'' + '}';
    }
}
