package com.axa.softwareacademy.p7.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rulename")
public class RuleName {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "TINYINT")
    private Integer id;
    @Column(length = 125)
    private String name;
    @Column(length = 125)
    private String description;
    @Column(length = 125)
    private String json;
    @Column(length = 512)
    private String template;
    @Column(length = 125)
    private String sqlStr;
    @Column(length = 125)
    private String sqlPart;

    public RuleName() {
    }

    public RuleName(int id, String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this(0, name, description, json, template, sqlStr, sqlPart);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getJson() { return json; }
    public void setJson(String json) { this.json = json; }

    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }

    public String getSqlStr() { return sqlStr; }
    public void setSqlStr(String sqlStr) { this.sqlStr = sqlStr; }

    public String getSqlPart() { return sqlPart; }
    public void setSqlPart(String sqlPart) { this.sqlPart = sqlPart; }

    @Override
    public String toString() {
        return "RuleName{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", json='" + json + '\'' + ", template='" + template + '\'' + ", sqlStr='" + sqlStr + '\'' + ", sqlPart='" + sqlPart + '\'' + '}';
    }
}
