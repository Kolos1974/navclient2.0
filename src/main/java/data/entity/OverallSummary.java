package data.entity;

import java.math.BigDecimal;

public class OverallSummary {

    private BigDecimal afaalapOverallSum;
    private BigDecimal afaertekOverallSum;
    private BigDecimal bruttoOverallSum;

    public OverallSummary() {
        this.afaalapOverallSum = BigDecimal.ZERO;
        this.afaertekOverallSum = BigDecimal.ZERO;
        this.bruttoOverallSum = BigDecimal.ZERO;
    }

    public BigDecimal getAfaalapOverallSum() {
        return afaalapOverallSum;
    }

    public BigDecimal getAfaertekOverallSum() {
        return afaertekOverallSum;
    }

    public BigDecimal getBruttoOverallSum() {
        return bruttoOverallSum;
    }

    public void addToAfaAlap(BigDecimal b) {
        this.afaalapOverallSum = this.afaalapOverallSum.add(b);
    }

    public void addToAfaErtek(BigDecimal b) {
        this.afaertekOverallSum = this.afaertekOverallSum.add(b);
    }

    public void addToBrutto(BigDecimal b) {
        this.bruttoOverallSum = this.bruttoOverallSum.add(b);
    }
}
