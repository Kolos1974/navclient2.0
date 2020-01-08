package data.entity;

import java.math.BigDecimal;

public class VatSummary {

    private BigDecimal afaSzazalek;
    private BigDecimal afaalapSum;
    private BigDecimal afaertekSum;
    private BigDecimal bruttoSum;

    public VatSummary(BigDecimal afaSzazalek) {
        this.afaSzazalek = afaSzazalek;
        this.afaalapSum = BigDecimal.ZERO;
        this.afaertekSum = BigDecimal.ZERO;
        this.bruttoSum = BigDecimal.ZERO;
    }

    public BigDecimal getAfaSzazalek() {
        return afaSzazalek;
    }

    public BigDecimal getAfaalapSum() {
        return afaalapSum;
    }

    public BigDecimal getAfaertekSum() {
        return afaertekSum;
    }

    public BigDecimal getBruttoSum() {
        return bruttoSum;
    }

    public void addToAfaAlap(BigDecimal b) {
        this.afaalapSum = this.afaalapSum.add(b);
    }

    public void addToAfaErtek(BigDecimal b) {
        this.afaertekSum = this.afaertekSum.add(b);
    }

    public void addToBrutto(BigDecimal b) {
        this.bruttoSum = this.bruttoSum.add(b);
    }
}
