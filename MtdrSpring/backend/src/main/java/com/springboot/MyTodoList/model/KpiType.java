package com.springboot.MyTodoList.model;

import jakarta.persistence.*;

@Entity
@Table(name = "KPI_TYPES")
public class KpiType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KPI_TYPE_ID")
    private int kpiTypeId;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "UNIT")
    private String unit;

    public KpiType() {}

    public KpiType(int kpiTypeId, String name, String description, String category, String unit) {
        this.kpiTypeId = kpiTypeId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.unit = unit;
    }

    public int getKpiTypeId() { return kpiTypeId; }
    public void setKpiTypeId(int kpiTypeId) { this.kpiTypeId = kpiTypeId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    @Override
    public String toString() {
        return "KpiType{kpiTypeId=" + kpiTypeId + ", name='" + name + "', category='" + category + "', unit='" + unit + "'}";
    }
}
