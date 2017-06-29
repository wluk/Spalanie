package localhost.spalanie;

import java.io.Serializable;
import java.util.Date;

class Refuel implements Serializable {
    private String petrolStation;
    private Double subBilling;
    private Double liters;
    private Double price;
    private Double combustion;
    private Double combustionPC;
    private Integer avg_speed;
    private Date date;

    private Integer id;
    private String comment;

    public String getPetrolStation() {
        return petrolStation;
    }

    public void setPetrolStation(String petrolStation) {
        this.petrolStation = petrolStation;
    }

    public Double getSubBilling() {
        return subBilling;
    }

    public void setSubBilling(Double subBilling) {
        this.subBilling = subBilling;
    }

    public Double getLiters() {
        return liters;
    }

    public void setLiters(Double liters) {
        this.liters = liters;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCombustion() {
        return combustion;
    }

    public void setCombustion(Double combustion) {
        this.combustion = combustion;
    }

    public Double getCombustionPC() {
        return combustionPC;
    }

    public void setCombustionPC(Double combustionPC) {
        this.combustionPC = combustionPC;
    }

    public Integer getAvg_speed() {
        return avg_speed;
    }

    public void setAvg_speed(Integer avg_speed) {
        this.avg_speed = avg_speed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
