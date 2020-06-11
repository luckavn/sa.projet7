package com.axa.softwareacademy.p7.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "trade")
public class Trade {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "TINYINT")
    private Integer tradeId;
    @NotNull
    @Column(length = 30)
    private String account;
    @NotNull
    @Column(length = 30)
    private String type;
    private Double buyQuantity;
    private Double sellQuantity;
    private Double buyPrice;
    private Double sellPrice;
    @Column(length = 125)
    private String benchmark;
    @NotNull
    private LocalDateTime tradeDate;
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

    public Trade() {
    }

    public Trade(int tradeId, String account, String type) {
        this.tradeId = tradeId;
        this.account = account;
        this.type = type;
    }

    public Trade(String account, String type) {
        this(0, account, type);
    }

    public Integer getTradeId() { return tradeId; }
    public void setTradeId(Integer tradeId) { this.tradeId = tradeId; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getBuyQuantity() { return buyQuantity; }
    public void setBuyQuantity(Double buyQuantity) { this.buyQuantity = buyQuantity; }

    public Double getSellQuantity() { return sellQuantity; }
    public void setSellQuantity(Double sellQuantity) { this.sellQuantity = sellQuantity; }

    public Double getBuyPrice() { return buyPrice; }
    public void setBuyPrice(Double buyPrice) { this.buyPrice = buyPrice; }

    public Double getSellPrice() { return sellPrice; }
    public void setSellPrice(Double sellPrice) { this.sellPrice = sellPrice; }

    public String getBenchmark() { return benchmark; }
    public void setBenchmark(String benchmark) { this.benchmark = benchmark; }

    public LocalDateTime getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDateTime tradeDate) { this.tradeDate = tradeDate; }

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
        return "Trade{" + "tradeId=" + tradeId + ", account='" + account + '\'' + ", type='" + type + '\'' + ", buyQuantity=" + buyQuantity + ", sellQuantity=" + sellQuantity + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + ", benchmark='" + benchmark + '\'' + ", tradeDate=" + tradeDate + ", security='" + security + '\'' + ", status='" + status + '\'' + ", trader='" + trader + '\'' + ", book='" + book + '\'' + ", creationName='" + creationName + '\'' + ", creationDate=" + creationDate + ", revisionName='" + revisionName + '\'' + ", revisionDate=" + revisionDate + ", dealName='" + dealName + '\'' + ", dealType='" + dealType + '\'' + ", sourceListId='" + sourceListId + '\'' + ", side='" + side + '\'' + '}';
    }
}
